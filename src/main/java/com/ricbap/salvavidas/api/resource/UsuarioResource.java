package com.ricbap.salvavidas.api.resource;

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

import com.ricbap.salvavidas.api.event.RecursoCriadoEvent;
import com.ricbap.salvavidas.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.ricbap.salvavidas.api.model.Usuario;
import com.ricbap.salvavidas.api.repository.UsuarioRepository;
import com.ricbap.salvavidas.api.service.UsuarioService;
import com.ricbap.salvavidas.api.service.exception.EmailUsuarioJaCadastradoException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public Page<Usuario> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
		return usuarioRepository.findByNomeContaining(nome, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {		
		Usuario usuarioSalvo = usuarioService.criar(usuario);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}	

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario usuario = usuarioService.buscarUsuarioPeloCodigo(codigo);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	} 
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.atualizar(codigo, usuario);
		usuarioSalvo.setSenha(null); //oculta senha no template após atualizar
		return ResponseEntity.ok(usuarioSalvo);
	}
	
	@DeleteMapping("/{codigo}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		usuarioRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		usuarioService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	
	
	@ExceptionHandler({ EmailUsuarioJaCadastradoException.class })
	public ResponseEntity<Object> handleEmailUsuarioJaCadastradoException(EmailUsuarioJaCadastradoException ex) {
		String mensagemUsuario = messageSource.getMessage("usuario.ja-cadastrado", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
