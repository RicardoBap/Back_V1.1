package com.ricbap.salvavidas.api.repository.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ricbap.salvavidas.api.model.Cidade;
import com.ricbap.salvavidas.api.repository.filter.CidadeFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoCidade;

public interface CidadeRepositoryQuery {
	
	public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable);
	
	public Page<ResumoCidade> resumir(CidadeFilter filter, Pageable pageable);

}
