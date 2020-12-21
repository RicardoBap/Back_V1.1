package com.ricbap.salvavidas.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import com.ricbap.salvavidas.api.event.RecursoCriadoEvent;
import com.ricbap.salvavidas.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.ricbap.salvavidas.api.model.Cidade;
import com.ricbap.salvavidas.api.repository.CidadeRepository;
import com.ricbap.salvavidas.api.repository.filter.CidadeFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoCidade;
import com.ricbap.salvavidas.api.service.CidadeService;
import com.ricbap.salvavidas.api.service.exception.NomeCidadeJaCadastradaException;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@Cacheable(value = "cidades", key = "#estado")
	@GetMapping
	//@PreAuthorize("isAuthenticated()")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CIDADE') and #oauth2.hasScope('write')")
	public List<Cidade> pesquisar(@RequestParam Long estado) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) { 	}
		return cidadeRepository.findByEstadoCodigo(estado);
	}	
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CIDADE') and #oauth2.hasScope('write')")
	public Page<ResumoCidade> resumir(CidadeFilter cidadeFilter, Pageable pageable) {
		return cidadeRepository.resumir(cidadeFilter, pageable);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CIDADE') and #oauth2.hasScope('write')")
	public ResponseEntity<Cidade> buscarPeloCodigo(@PathVariable Long codigo) {
		Cidade cidade= cidadeRepository.findOne(codigo);
		return cidade != null ? ResponseEntity.ok(cidade) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@CacheEvict(value = "cidades", key = "#cidade.estado.codigo", condition = "#cidade.temEstado()")  // allEntries = true
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CIDADE') and #oauth2.hasScope('write')")
	public ResponseEntity<Cidade> cadastrar(@Valid @RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade cidadeSalva = cidadeService.salvar(cidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidadeSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CIDADE') and #oauth2.hasScope('write')")
	public ResponseEntity<Cidade> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cidade cidade) {
		Cidade cidadeSalva = cidadeService.atualizar(codigo, cidade);
		return ResponseEntity.ok(cidadeSalva);
	}
	
	@DeleteMapping("/{codigo}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CIDADE') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		cidadeRepository.delete(codigo);
	}
	
	
	
	@ExceptionHandler({ NomeCidadeJaCadastradaException.class })
	public ResponseEntity<Object> handleNomeCidadeJaCadastradaException(NomeCidadeJaCadastradaException ex) {
		String mensagemUsuario = messageSource.getMessage("cidade.ja-cadastrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}


/*
/*
@GetMapping("filtro")
public Page<Cidade> buscarTodas(CidadeFilter cidadeFilter, Pageable pageable) {
	return cidadeRepository.filtrar(cidadeFilter, pageable);
} 

@PutMapping("/{codigo}")
//@PreAuthorize("hasAuthority('ROLE_CADASTRAR_GRUPO') and #oauth2.hasScope('write')")
public ResponseEntity<Cidade> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cidade cidade) {
	Cidade cidadeSalva = cidadeService.atualizar(codigo, cidade);
	return ResponseEntity.ok(cidadeSalva);
}

 */



