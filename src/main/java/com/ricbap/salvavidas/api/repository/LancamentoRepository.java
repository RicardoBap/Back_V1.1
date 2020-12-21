package com.ricbap.salvavidas.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.api.model.Lancamento;
import com.ricbap.salvavidas.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
	
	                                  //Menor ou igual e dataPagamento ser nula
	public List<Lancamento> findByDataVencimentoLessThanEqualAndDataPagamentoIsNull(LocalDate data);
	
	
	public List<Lancamento> findByDataVencimentoBefore(LocalDate data);

}
