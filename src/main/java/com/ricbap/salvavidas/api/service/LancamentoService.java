package com.ricbap.salvavidas.api.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ricbap.salvavidas.api.dto.LancamentoEstatisticaPessoa;
import com.ricbap.salvavidas.api.dto.LancamentoMensal;
import com.ricbap.salvavidas.api.dto.LancamentoTesouraria;
import com.ricbap.salvavidas.api.mail.Mailer;
import com.ricbap.salvavidas.api.model.Lancamento;
import com.ricbap.salvavidas.api.model.Pessoa;
import com.ricbap.salvavidas.api.model.Usuario;
import com.ricbap.salvavidas.api.repository.LancamentoRepository;
import com.ricbap.salvavidas.api.repository.PessoaRepository;
import com.ricbap.salvavidas.api.repository.UsuarioRepository;
import com.ricbap.salvavidas.api.service.exception.PessoaInexistenteOuInativaException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class LancamentoService {
	
	private static final String DESTINATARIOS = "ROLE_PESQUISAR_LANCAMENTO";
	
	private static final Logger logger = LoggerFactory.getLogger(LancamentoService.class);
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Mailer mailer;
	
//-------------------------------------------------------------------- ENVIO DE E-MAILS VENCIDOS	
	@Scheduled(cron = "0 0 19 * * *")
	//@Scheduled(fixedDelay = 1000 * 60 * 30)
	public void avisarSobreLancamentosVencidos() {		
		
		if(logger.isDebugEnabled()) {
			logger.debug("Preparando envio de emails de aviso de lancamentos vencidos");
		}
		
		List<Lancamento> vencidos = lancamentoRepository
				.findByDataVencimentoLessThanEqualAndDataPagamentoIsNull(LocalDate.now());		
		
		if(vencidos.isEmpty()) {
			logger.info("Sem lancamentos vencidos para aviso");
			return;
		}
		
		logger.info("Existem {} lancamentos vencidos", vencidos.size());		
		
		List<Usuario> destinatarios = usuarioRepository
				.findByPermissoesDescricao(DESTINATARIOS);
		
		if(destinatarios.isEmpty()) {
			logger.warn("Existem lancamentos vencidos, mas o sistema não encontrou destinatarios");
			return;
		}		
		
		mailer.avisarSobreLancamentosVencidos(vencidos, destinatarios);	
		
		logger.info("envio de e-mail de aviso de lancamentos vencidos concluido"); 
	}	
// ########################################################################## E-MAIL RELATORIO MENSAL
	
		@Scheduled(cron = "0 0 19 28-31 * ?")
		//@Scheduled(fixedDelay = 1000 * 60 * 30)
		public void enviarRelatorioMensal() {		
			
			if(logger.isDebugEnabled()) {
				logger.debug("Preparando envio de relatório mensal de lancamentos.");
			}					
			
			List<LancamentoMensal> relatorioMensal = lancamentoRepository
					.lancamentoMensal(LocalDate.now());
			
			if(relatorioMensal.isEmpty()) {
				logger.info("Sem lancamentos para este relatório.");
			return;
		    }
			
			logger.info("Existem {} lancamentos neste mês.", relatorioMensal.size());
			
			
			List<Usuario> destinatarios = usuarioRepository
					.findByPermissoesDescricao(DESTINATARIOS);
			
			if(destinatarios.isEmpty()) {
				logger.warn("Existem lancamentos no relatório, mas o sistema não encontrou destinatarios");
				return;
			}		
			
			mailer.enviarRelatorioMensal(relatorioMensal, destinatarios);
			
			logger.info("envio de e-mail do relatório concluido"); 
		} 
		
//----------------------------[ TESTE ]---------------------------------------
	public byte[] relatorioTesouraria(LocalDate inicio, LocalDate fim) throws Exception {
		List<LancamentoTesouraria> dados = lancamentoRepository.porTesoura(inicio, fim);
				
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/tesouraria.jasper");
		
		JasperPrint jasperPrint = JasperFillManager
				.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}	
	
//----------------------------[ OK ] ----------------------------------------	
	
	public byte[] relatorioPorPessoa(LocalDate inicio, LocalDate fim) throws Exception {
		List<LancamentoEstatisticaPessoa> dados = lancamentoRepository.porPessoa(inicio, fim);
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT LOCALE", new Locale("pt", "BR"));
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/lancamentos-por-pessoa.jasper");
		
		JasperPrint jasperPrint = JasperFillManager
				.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(dados));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return lancamentoRepository.save(lancamento);
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(lancamento);
		}
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	private void validarPessoa(Lancamento lancamento) {
		Pessoa pessoa = null;
		if (lancamento.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		}
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}
	
	private Lancamento buscarLancamentoExistente(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);
		if (lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}

}

