package com.ricbap.salvavidas.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricbap.salvavidas.api.model.Permissao;
import com.ricbap.salvavidas.api.repository.PermissaoRepository;

@RestController
@RequestMapping("/permissao")
public class PermissaoResource {
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	@GetMapping
	public List<Permissao> getPermissao() {
		return permissaoRepository.findAll();
	}

}
