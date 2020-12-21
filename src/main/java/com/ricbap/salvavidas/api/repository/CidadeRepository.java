package com.ricbap.salvavidas.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.api.model.Cidade;
import com.ricbap.salvavidas.api.model.Estado;
import com.ricbap.salvavidas.api.repository.cidade.CidadeRepositoryQuery;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryQuery {
	
	public List<Cidade> findByEstadoCodigo(Long estadoCodigo);
	
	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);

}
