package com.ricbap.salvavidas.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ricbap.salvavidas.api.mail.Mailer;
import com.ricbap.salvavidas.api.model.Contato;
import com.ricbap.salvavidas.api.model.Evento;
import com.ricbap.salvavidas.api.model.Usuario;
import com.ricbap.salvavidas.api.repository.ContatoRepository;
import com.ricbap.salvavidas.api.repository.EventoRepository;
import com.ricbap.salvavidas.api.repository.UsuarioRepository;
import com.ricbap.salvavidas.api.service.exception.DataHoraEventoNaoPodeSerNulaException;
import com.ricbap.salvavidas.api.service.exception.LocalEventoNaoPodeSerNulaException;

@Service
public class EventoService {
	
	private static final String DESTINATARIOS = "ROLE_PESQUISAR_LANCAMENTO";
	
	private static final Logger logger = LoggerFactory.getLogger(EventoService.class);
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Mailer mailer;	
	
	@Scheduled(cron = "0 0 19 10,20 * ?")
	//@Scheduled(fixedDelay = 1000 * 60 * 30)
	public void convidarContatosParaEvento() {		
			
		if(logger.isDebugEnabled()) {
			logger.debug("Preparando envio de emails de convite de evento");
		}
			
		List<Evento> eventos = eventoRepository.
				findByDataHoraEventoAfterAndDataReferenciaIsNotNullAndDestaqueTrue(LocalDateTime.now() );	
		
		if(eventos.isEmpty()) {
			logger.info("Sem eventos para convidar");
			return;
		}
			
		logger.info("Existem {} eventos", eventos.size());		
			
		List<Contato> destinatarios = contatoRepository.findAll();
		
		if(destinatarios.isEmpty()) {
			logger.warn("Existem eventos, mas o sistema não encontrou destinatarios");
			return;
		}		
			
		mailer.convidarContatosParaEvento(eventos, destinatarios);	// <--------
			
		logger.info("envio de e-mail de convite para evento concluido"); 
	}
	
//-------------------------------------------------------------------------------------------	
	
	@Scheduled(cron = "0 0 19 * * MON")
	//@Scheduled(fixedDelay = 1000 * 60 * 30)
	public void avisarSobreEventos() {		
		
		if(logger.isDebugEnabled()) {
			logger.debug("Preparando envio de emails de aviso de eventos");
		}
		
		List<Evento> eventos = eventoRepository.findByDataHoraEventoAfter(LocalDateTime.now());		
		
		if(eventos.isEmpty()) {
			logger.info("Sem eventos para avisar");
			return;
		}
		
		logger.info("Existem {} eventos", eventos.size());		
		
		List<Usuario> destinatarios = usuarioRepository
				.findByPermissoesDescricao(DESTINATARIOS);		
		
		if(destinatarios.isEmpty()) {
			logger.warn("Existem eventos, mas o sistema não encontrou destinatarios");
			return;
		}		
		
		mailer.avisarSobreEventos(eventos, destinatarios);	// <--------
		
		logger.info("envio de e-mail de aviso de eventos concluido"); 
	}
	
	
//#########################################################################################################
	public Evento salvar(Evento evento) {
		if(evento.getDataEvento() != null && evento.getHoraEvento() != null) {			
			evento.setDataHoraEvento(LocalDateTime.of(evento.getDataEvento(), evento.getHoraEvento()));
		} else {
			throw new DataHoraEventoNaoPodeSerNulaException();
		}
		if(evento.getGrupo().getCodigo() == null) {
			throw new LocalEventoNaoPodeSerNulaException();
		}
		return eventoRepository.save(evento);
	}
	
	public Evento atualizar(Long codigo, Evento evento) {
		if(evento.getDataEvento() != null) {			
			evento.setDataHoraEvento(LocalDateTime.of(evento.getDataEvento(), evento.getHoraEvento()));
		}
		Evento eventoSalvo = buscarPeloCodigo(codigo);		
		BeanUtils.copyProperties(evento, eventoSalvo, "codigo");
		return eventoRepository.save(eventoSalvo);		
	}	
	
	private Evento buscarPeloCodigo(Long codigo) {
		Evento evento = eventoRepository.findOne(codigo);
		if (evento == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return evento;
	}

}
