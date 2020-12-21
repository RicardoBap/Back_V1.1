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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ricbap.salvavidas.api.event.RecursoCriadoEvent;
import com.ricbap.salvavidas.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.ricbap.salvavidas.api.model.Evento;
import com.ricbap.salvavidas.api.repository.EventoRepository;
import com.ricbap.salvavidas.api.repository.filter.EventoFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoEvento;
import com.ricbap.salvavidas.api.service.EventoService;
import com.ricbap.salvavidas.api.service.exception.DataHoraEventoNaoPodeSerNulaException;
import com.ricbap.salvavidas.api.service.exception.LocalEventoNaoPodeSerNulaException;

@RestController
@RequestMapping("/eventos")
public class EventoResource {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private EventoService eventoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	/*
	 * @GetMapping public List<Evento> listar() { return eventoRepository.findAll();
	 * }
	 */	
	
	@GetMapping(params = "resumo")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EVENTO) and #oauth2.hasScope('read')")
	public Page<ResumoEvento> pesquisaResumo(EventoFilter filter, Pageable pageable) {
		return eventoRepository.pesquisar(filter, pageable);
	}
	
	@GetMapping(value = "/nav-eventos", params = "resumo")
	public List<ResumoEvento> listaNaHome(EventoFilter filter, Boolean destaque) {
		return eventoRepository.resumir(filter);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EVENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Evento> cadastrar(@Valid @RequestBody Evento evento, HttpServletResponse response) {		
		Evento eventoSalvo = eventoService.salvar(evento);		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, eventoSalvo.getCodigo()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(eventoSalvo);
	}
	
	@GetMapping("/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EVENTO) and #oauth2.hasScope('read')")
	public ResponseEntity<Evento> buscarPeloCodigo(@PathVariable Long codigo) {
		Evento evento = eventoRepository.findOne(codigo);
		return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EVENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Evento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Evento evento) {
		Evento eventoSalvo = eventoService.atualizar(codigo, evento);
		return ResponseEntity.ok(eventoSalvo);
	}
	
	@DeleteMapping("/{codigo}")	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_EVENTO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		eventoRepository.delete(codigo);
	}
	
	
	@ExceptionHandler({ DataHoraEventoNaoPodeSerNulaException.class })
	public ResponseEntity<Object> handleDataHoraEventoNaoPodeSerNulaException(DataHoraEventoNaoPodeSerNulaException ex) {
		String mensagemUsuario = messageSource.getMessage("evento.data-hora-nao-nulo", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ LocalEventoNaoPodeSerNulaException.class })
	public ResponseEntity<Object> handleLocalEventoNaoPodeSerNulaException(LocalEventoNaoPodeSerNulaException ex) {
		String mensagemUsuario = messageSource.getMessage("evento.local-evento-nao-nulo", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

}
