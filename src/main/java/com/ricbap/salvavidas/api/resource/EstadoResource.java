package com.ricbap.salvavidas.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricbap.salvavidas.api.model.Estado;
import com.ricbap.salvavidas.api.repository.EstadoRepository;

@RestController
@RequestMapping("estados")
public class EstadoResource {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	//@PreAuthorize("isAuthenticated()")
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

}
