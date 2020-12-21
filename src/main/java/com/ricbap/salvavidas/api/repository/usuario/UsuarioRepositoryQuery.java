package com.ricbap.salvavidas.api.repository.usuario;

import java.util.Optional;

import com.ricbap.salvavidas.api.model.Usuario;

public interface UsuarioRepositoryQuery {
	
	public Optional<Usuario> porEmailEAtivo(String email);	

}
