package com.ricbap.salvavidas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
