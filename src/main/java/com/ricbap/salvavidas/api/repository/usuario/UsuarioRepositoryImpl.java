package com.ricbap.salvavidas.api.repository.usuario;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ricbap.salvavidas.api.model.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {		
		return manager
				.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
				.setParameter("email", email)
				.getResultList()
				.stream()
				.findFirst();
	}

	/*
	@Override
	public List<String> permissoes(Usuario usuario) {		
		return manager
				.createQuery("select distinct p.descricao from Usuario u inner join u.cargos g inner join g.permissoes p where u = :usuario", String.class)
				.setParameter("usuario", usuario)
				.getResultList();
	} */

}
