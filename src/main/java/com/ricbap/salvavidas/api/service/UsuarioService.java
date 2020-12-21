package com.ricbap.salvavidas.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ricbap.salvavidas.api.model.Usuario;
import com.ricbap.salvavidas.api.repository.UsuarioRepository;
import com.ricbap.salvavidas.api.service.exception.EmailUsuarioJaCadastradoException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;		

	public Usuario criar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());		
		if(usuarioExistente.isPresent()) {
			throw new EmailUsuarioJaCadastradoException();
		}	
		
		usuario = salvarSenha(usuario);		
		usuario.setSenha(criptografaSenha(usuario.getSenha()));
		
		usuario = usuarioRepository.save(usuario);
		return usuario;
	}	

	public Usuario atualizar(Long codigo, Usuario usuario) {
	    Usuario usuarioSalvo = buscarUsuarioPeloCodigo(codigo);
	    usuario = salvarSenha(usuario);
	    usuario.setSenha(criptografaSenha(usuario.getSenha()));
	    
		BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");		
		return usuarioRepository.save(usuario);		
	}
	
	private Usuario salvarSenha(Usuario usuario) {
		String senhaUsuario = usuario.getSenha(); // pega a senha e atribui para senhaUsuario
		usuario.setSenhaUsuario(senhaUsuario); // atribui a senha so usuario para senhaUsuario
		return usuario;
	}

	private String criptografaSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}	
	
	public Usuario buscarUsuarioPeloCodigo(Long codigo) {
		Usuario usuarioSalvo = usuarioRepository.findOne(codigo);
		if (usuarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		//usuarioSalvo.setSenha(null);//oculta senha no template na hora de carregar usuario
		return usuarioSalvo;
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Usuario usuarioSalvo = buscarUsuarioPeloCodigo(codigo);
		usuarioSalvo.setAtivo(ativo);
		usuarioRepository.save(usuarioSalvo);
	}
	
	
}
