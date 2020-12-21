package com.ricbap.salvavidas.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ricbap.salvavidas.api.model.Grupo;
import com.ricbap.salvavidas.api.repository.GrupoRepository;
import com.ricbap.salvavidas.api.service.exception.NomeGrupoJaCadastradoException;
import com.ricbap.salvavidas.api.storage.S3;

@Service
public class GrupoService {
	
	@Autowired
	private S3 s3;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public Grupo atualizar(Long codigo, Grupo grupo) {
		Grupo grupoSalvo = buscarGrupoPeloCodigo(codigo);
		
		grupoSalvo.getContatos().clear();
		grupoSalvo.getContatos().addAll(grupo.getContatos());		
		grupoSalvo.getContatos().forEach(c -> c.setGrupo(grupoSalvo));
		
		if (StringUtils.isEmpty(grupo.getAnexo()) && StringUtils.hasText(grupoSalvo.getAnexo())) {
			s3.removerAnexo(grupoSalvo.getAnexo());
		} else if (StringUtils.hasText(grupo.getAnexo()) && !grupo.getAnexo().equals(grupoSalvo.getAnexo())) {
			s3.substituirAnexo(grupoSalvo.getAnexo(), grupo.getAnexo());
		}
		
		BeanUtils.copyProperties(grupo, grupoSalvo, "codigo", "contatos");
		return grupoRepository.save(grupoSalvo);		
	}
	
	public Grupo buscarGrupoPeloCodigo(Long codigo) {
		Grupo grupoSalvo = grupoRepository.findOne(codigo);
		if (grupoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return grupoSalvo;
	}

	/*
	public Grupo salvar(Grupo grupo) {
		grupo.getContatos().forEach(c -> c.setGrupo(grupo));
		return grupoRepository.save(grupo);
	} */
	
	
	
	public Grupo salvar(Grupo grupo) {
		Optional<Grupo> grupoExistente = grupoRepository.findByNomeAndCidade(grupo.getNome(), grupo.getCidade());
		if(grupoExistente.isPresent()) {
			throw new NomeGrupoJaCadastradoException();
		}	
		grupo.getContatos().forEach(c -> c.setGrupo(grupo));
		
		if(grupo.getAnexo() != null) {
			s3.salvarAnexo(grupo.getAnexo());
		}
		/*
		if(StringUtils.hasText(grupo.getAnexo())) {
			s3.salvarAnexo(grupo.getAnexo());
		}*/		
		
		return grupoRepository.save(grupo);
	}

}
