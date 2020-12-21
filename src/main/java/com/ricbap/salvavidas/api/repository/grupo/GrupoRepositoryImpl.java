package com.ricbap.salvavidas.api.repository.grupo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.ricbap.salvavidas.api.model.Cidade_;
import com.ricbap.salvavidas.api.model.Estado_;
import com.ricbap.salvavidas.api.model.Grupo;
import com.ricbap.salvavidas.api.model.Grupo_;
import com.ricbap.salvavidas.api.repository.filter.GrupoFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoGrupo;

public class GrupoRepositoryImpl implements GrupoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ResumoGrupo> filtrar(GrupoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoGrupo> criteria = builder.createQuery(ResumoGrupo.class);
		Root<Grupo> root = criteria.from(Grupo.class);
		
		//criar restricoes
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		
		TypedQuery<ResumoGrupo> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(GrupoFilter filter, CriteriaBuilder builder, Root<Grupo> root) {
		List<Predicate> predicates = new ArrayList<>();		
		
		if(!StringUtils.isEmpty(filter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Grupo_.nome)), "%" + filter.getNome().toUpperCase() + "%"));
		}		
		
		if (!StringUtils.isEmpty(filter.getCidade() )) {
			  predicates.add(
					  builder.equal(root.get(Grupo_.cidade).get(Cidade_.nome), filter.getCidade()) );
		}		 	
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}


	@Override
	public List<ResumoGrupo> resumir(GrupoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoGrupo> criteria = builder.createQuery(ResumoGrupo.class);
		Root<Grupo> root = criteria.from(Grupo.class);
		
		criteria.select(builder.construct(ResumoGrupo.class
				, root.get(Grupo_.codigo)
				, root.get(Grupo_.nome)
				, root.get(Grupo_.logradouro)
				, root.get(Grupo_.numero)
				, root.get(Grupo_.complemento)
				, root.get(Grupo_.bairro)
				, root.get(Grupo_.cep)
				, root.get(Grupo_.diaReuniao)
				, root.get(Grupo_.cidade).get(Cidade_.nome)
				, root.get(Grupo_.cidade).get(Cidade_.estado).get(Estado_.sigla)
				, root.get(Grupo_.anexoUrl)));
		
		//criar as restrições
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
				
		TypedQuery<ResumoGrupo> query = manager.createQuery(criteria);
		//adicionarRestricoesDePaginacao(query, pageable);		
				
		return query.getResultList();
	}

}
