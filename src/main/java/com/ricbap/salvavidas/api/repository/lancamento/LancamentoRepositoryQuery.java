package com.ricbap.salvavidas.api.repository.lancamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ricbap.salvavidas.api.dto.LancamentoEstatisticaCategoria;
import com.ricbap.salvavidas.api.dto.LancamentoEstatisticaDia;
import com.ricbap.salvavidas.api.dto.LancamentoEstatisticaPessoa;
import com.ricbap.salvavidas.api.dto.LancamentoMensal;
import com.ricbap.salvavidas.api.dto.LancamentoTesouraria;
import com.ricbap.salvavidas.api.model.Lancamento;
import com.ricbap.salvavidas.api.repository.filter.LancamentoFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	
	// E-mail
	public List<LancamentoMensal> lancamentoMensal(LocalDate mesReferencia);

	
	// Relatórios
	public List<LancamentoTesouraria> porTesoura(LocalDate inicio, LocalDate fim);	
	public List<LancamentoEstatisticaPessoa> porPessoa(LocalDate inicio, LocalDate fim);
	
	// Dashboard
	public List<LancamentoEstatisticaCategoria> porCategoria(LocalDate mesReferencia);	
	public List<LancamentoEstatisticaDia> porDia(LocalDate mesReferencia);
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
