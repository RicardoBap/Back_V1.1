package com.ricbap.salvavidas.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.ricbap.salvavidas.api.model.Cidade;
import com.ricbap.salvavidas.api.repository.CidadeRepository;
import com.ricbap.salvavidas.api.service.exception.NomeCidadeJaCadastradaException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidadeRepository.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());		
		if(cidadeExistente.isPresent()) {
			throw new NomeCidadeJaCadastradaException();
		}		
		return cidadeRepository.save(cidade);		
	}
	
	public Cidade atualizar(Long codigo, Cidade cidade) {
		Cidade cidadeSalva = buscarCidadePeloCodigo(codigo);
		BeanUtils.copyProperties(cidade, cidadeSalva, "codigo");
		return cidadeRepository.save(cidadeSalva);		
	}
	
	public Cidade buscarCidadePeloCodigo(Long codigo) {
		Cidade cidadeSalva = cidadeRepository.findOne(codigo);
		if (cidadeSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cidadeSalva;
	}


}
