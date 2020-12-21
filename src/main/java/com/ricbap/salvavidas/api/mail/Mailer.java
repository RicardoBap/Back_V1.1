package com.ricbap.salvavidas.api.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ricbap.salvavidas.api.dto.LancamentoMensal;
import com.ricbap.salvavidas.api.model.Contato;
import com.ricbap.salvavidas.api.model.Evento;
import com.ricbap.salvavidas.api.model.Lancamento;
import com.ricbap.salvavidas.api.model.Usuario;

@Component
public class Mailer {
	
	private static final Logger logger = LoggerFactory.getLogger(Mailer.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	
	/*
	 @EventListener 
	  public void teste(ApplicationReadyEvent event) {
		  String template = "mail/convite-eventos";		  
		  List<Evento> lista = eventoRepository.findAll();		
		  
		  Map<String, Object> variaveis = new HashMap<>();
		  variaveis.put("eventos",  lista);
		  
		  this.enviarEmail("ricbapdevs@gmail.com", Arrays.asList("ric_bap@hotmail.com", "heloisa.ciulla@gmail.com"), "Convite", template, variaveis);
		  System.out.println("Terminado o envio de email"); 
	  }  */
	
	
	
	public void convidarContatosParaEvento(List<Evento> eventos, List<Contato> destinatarios) {		
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("eventos", eventos);
		
		List<String> emails = destinatarios.stream()
				.map(c -> c.getEmail())
				.collect(Collectors.toList());
		
		this.enviarEmail(
				"ricbapdevs@gmail.com",	emails, "Convite", "mail/convite-eventos", variaveis);
	} 	
	
	
	public void avisarSobreEventos(List<Evento> eventos, List<Usuario> destinatarios) {
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("eventos", eventos);
		
		List<String> emails = destinatarios.stream()
				.map(u -> u.getEmail())
				.collect(Collectors.toList());
		
		this.enviarEmail(
				"ricbapdevs@gmail.com",	emails, "Próximos eventos", "mail/aviso-eventos", variaveis);
	}	
	
	
	public void avisarSobreLancamentosVencidos(List<Lancamento> vencidos, List<Usuario> destinatarios) {
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("lancamentos", vencidos);
		
		List<String> emails = destinatarios.stream()
				.map(u -> u.getEmail())
				.collect(Collectors.toList());
		
		this.enviarEmail(
				"ricbapdevs@gmail.com",	emails, "Lancamentos vencidos", "mail/aviso-lancamentos-vencidos", variaveis);
	}
	
	
	public void enviarRelatorioMensal(List<LancamentoMensal> vencidos, List<Usuario> destinatarios) {
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("lancamentos", vencidos);
		
		List<String> emails = destinatarios.stream()
				.map(u -> u.getEmail())
				.collect(Collectors.toList());
		
		this.enviarEmail(
				"ricbapdevs@gmail.com",	emails, "Relatório Mensal", "mail/mensal-lancamentos-relatorio", variaveis);
	} 
	
	
	public void enviarEmail(String remetente, List<String> destinatarios,
			String assunto, String template, Map<String, Object> variaveis ) {
		
		Context context = new Context(new Locale("pt", "BR"));
		context.setVariable("logo", "logo");
		context.setVariable("logoazul", "logoazul");
		
		variaveis.entrySet().forEach(
				e -> context.setVariable(e.getKey(), e.getValue()));
		
		String mensagem = thymeleaf.process(template, context);
		this.enviarEmail(remetente, destinatarios, assunto, mensagem);
	}	
	
	
	public void enviarEmail(String remetente, List<String> destinatarios, String assunto, String mensagem) {		
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // <----
			
			helper.setFrom(remetente);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(assunto);
			helper.setText(mensagem, true);
			
			helper.addInline("logo", new ClassPathResource("static/images/aa-logo-white.png"));	
			helper.addInline("logoazul", new ClassPathResource("static/images/aa-logo.png"));	
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Erro enviando email", e);  // <------
			//throw new RuntimeException("Problemas com o envio de e-mail!", e);
		}		
	} 

}

//@Autowired
	//private LancamentoRepository repo;	
	
	/*
	  @EventListener 
	  public void teste(ApplicationReadyEvent event) {
		  String template = "mail/aviso-lancamentos-vencidos";		  
		  List<Lancamento> lista = repo.findAll();		
		  
		  Map<String, Object> variaveis = new HashMap<>();
		  variaveis.put("lancamentos",  lista);
		  
		  this.enviarEmail("ricbapdevs@gmail.com", Arrays.asList("ric_bap@hotmail.com"), "Testando", template, variaveis);
		  System.out.println("Terminado o envio de email"); 
	  } 
	 
	
	public void enviarEmail(
			String remetente, List<String> destinatarios, String assunto, String template, Map<String, Object> variaveis) {
		Context context = new Context(new Locale("pt", "BR"));
		
		variaveis.entrySet().forEach(e -> context.setVariable(e.getKey(), e.getValue()));
		
		String mensagem = thymeleaf.process(template, context);
		
		this.enviarEmail(remetente, destinatarios, assunto, mensagem);
	}
	
	public void enviarEmail(String remetente, List<String> destinatarios, String assunto, String mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(remetente);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(assunto);
			helper.setText(mensagem, true);
			
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!", e);
		}
	} */
	
	 

