package com.ricbap.salvavidas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.api.model.Lancamento;
import com.ricbap.salvavidas.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
