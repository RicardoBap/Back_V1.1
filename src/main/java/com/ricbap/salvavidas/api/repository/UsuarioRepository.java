package com.ricbap.salvavidas.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ricbap.salvavidas.api.model.Usuario;
import com.ricbap.salvavidas.api.repository.usuario.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {
	
	// Busca para verificar se ja existe um usuario Existente 
	public Optional<Usuario> findByEmail(String email);
	
    public Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
    
    //A propriedade PermissaoDescricao deve ser igual a que estiver passando no parametro
    public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);
	
	
}
