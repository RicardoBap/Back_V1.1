package com.ricbap.salvavidas.api.resource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ricbap.salvavidas.api.dto.AnexoDTO;
import com.ricbap.salvavidas.api.event.RecursoCriadoEvent;
import com.ricbap.salvavidas.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.ricbap.salvavidas.api.model.Grupo;
import com.ricbap.salvavidas.api.repository.GrupoRepository;
import com.ricbap.salvavidas.api.repository.filter.GrupoFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoGrupo;
import com.ricbap.salvavidas.api.service.GrupoService;
import com.ricbap.salvavidas.api.service.exception.NomeGrupoJaCadastradoException;
import com.ricbap.salvavidas.api.storage.S3;

@RestController
@RequestMapping("/grupos")
public class GrupoResource {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private S3 s3;

	
	@PostMapping("/anexo")
	public AnexoDTO upLoad(@RequestParam MultipartFile anexo) throws IOException {		  
		//OutputStream out = new FileOutputStream("C:\\Users\\LENOVO\\teste/foto--" + anexo.getOriginalFilename());
		//out.write(anexo.getBytes());
		//out.close();
		String nome = s3.salvarTemporariamente(anexo);
		return new AnexoDTO(nome, s3.configurarUrl(nome));
	} 	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_GRUPO') and #oauth2.hasScope('read')")
	public Page<Grupo> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return grupoRepository.findByNomeContaining(nome, pageable);
	} 
	
	@GetMapping(value = "/nav-grupos", params = "resumo")
	public List<ResumoGrupo> pesquisaResumo(GrupoFilter grupoFilter, String nome) {
		return grupoRepository.resumir(grupoFilter);
	}
	
	/*
	 * @PostMapping //@
	 * PreAuthorize("hasAuthority('ROLE_CADASTRAR_GRUPO') and #oauth2.hasScope('write')"
	 * ) public ResponseEntity<Grupo> criar(@Valid @RequestBody Grupo grupo,
	 * HttpServletResponse response) { Grupo grupoSalvo =
	 * grupoRepository.save(grupo); publisher.publishEvent(new
	 * RecursoCriadoEvent(this, response, grupoSalvo.getCodigo())); return
	 * ResponseEntity.status(HttpStatus.CREATED).body(grupoSalvo); }
	 */	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_GRUPO') and #oauth2.hasScope('write')")
	public ResponseEntity<Grupo> criar(@Valid @RequestBody Grupo grupo, HttpServletResponse response) {		
		Grupo grupoSalvo = grupoService.salvar(grupo);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, grupoSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(grupoSalvo);
	}
	
	@GetMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_GRUPO) and #oauth2.hasScope('read')")
	public ResponseEntity<Grupo> buscarPeloCodigo(@PathVariable Long codigo) {
		Grupo grupo = grupoRepository.findOne(codigo);
		return grupo != null ? ResponseEntity.ok(grupo) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_GRUPO') and #oauth2.hasScope('write')")
	public ResponseEntity<Grupo> atualizar(@PathVariable Long codigo, @Valid @RequestBody Grupo grupo) {
		Grupo grupoSalvo = grupoService.atualizar(codigo, grupo);
		return ResponseEntity.ok(grupoSalvo);
	}
	
	@DeleteMapping("/{codigo}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_GRUPO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		grupoRepository.delete(codigo);
	}
	
	@ExceptionHandler({ NomeGrupoJaCadastradoException.class })
	public ResponseEntity<Object> handleNomeGrupoJaCadastradoException(NomeGrupoJaCadastradoException ex) {
		String mensagemUsuario = messageSource.getMessage("grupo.ja-cadastrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}

/*
 *  {
        "codigo": 1,
        "nome": "Salva vidas",
        "endereco": {
            "logradouro": "Rua Guaraci",
            "numero": "38",
            "complemento": null,
            "bairro": "Vila Angela Marta",
            "cep": "13031-460",
            "cidade": "Campinas",
            "estado": "São Paulo"
        }
        "reunioes": {
        	[
       
        	]
        }
    }
    */
