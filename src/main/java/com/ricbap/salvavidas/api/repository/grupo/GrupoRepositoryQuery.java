package com.ricbap.salvavidas.api.repository.grupo;

import java.util.List;

import com.ricbap.salvavidas.api.repository.filter.GrupoFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoGrupo;

public interface GrupoRepositoryQuery {
	
	public List<ResumoGrupo> resumir(GrupoFilter filter);

	public List<ResumoGrupo> filtrar(GrupoFilter filter);

}
