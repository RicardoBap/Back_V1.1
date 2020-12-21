package com.ricbap.salvavidas.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.api.model.Cidade;
import com.ricbap.salvavidas.api.model.Grupo;
import com.ricbap.salvavidas.api.repository.grupo.GrupoRepositoryQuery;

public interface GrupoRepository extends JpaRepository<Grupo, Long>, GrupoRepositoryQuery {
	
	public Page<Grupo> findByNomeContaining(String nome, Pageable pageable);
	
	public Optional<Grupo> findByNomeAndCidade(String nome, Cidade cidade);
	

}
