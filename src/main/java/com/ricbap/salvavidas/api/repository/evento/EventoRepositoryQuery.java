package com.ricbap.salvavidas.api.repository.evento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ricbap.salvavidas.api.repository.filter.EventoFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoEvento;

public interface EventoRepositoryQuery {
	
	// Faz pesquisa de eventos para listar todos resumido
	public Page<ResumoEvento> pesquisar(EventoFilter filter, Pageable pageable);
	
	// Busca eventos com destaque true para apresentar na Home
	public List<ResumoEvento> resumir(EventoFilter filter);
	
	//public Evento buscarPeloCodigo(Long codigo);


}
