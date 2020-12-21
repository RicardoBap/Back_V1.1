package com.ricbap.salvavidas.api.repository.evento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.ricbap.salvavidas.api.model.Cidade_;
import com.ricbap.salvavidas.api.model.Estado_;
import com.ricbap.salvavidas.api.model.Evento;
import com.ricbap.salvavidas.api.model.Evento_;
import com.ricbap.salvavidas.api.model.Grupo_;
import com.ricbap.salvavidas.api.repository.filter.EventoFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoEvento;

public class EventoRepositoryImpl implements EventoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ResumoEvento> resumir(EventoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoEvento> criteria = builder.createQuery(ResumoEvento.class);
		Root<Evento> root = criteria.from(Evento.class);
		
		criteria.select(builder.construct(ResumoEvento.class
				, root.get(Evento_.codigo)
				, root.get(Evento_.dataHoraEvento)
				, root.get(Evento_.grupo).get(Grupo_.nome)
				, root.get(Evento_.grupo).get(Grupo_.logradouro)
				, root.get(Evento_.grupo).get(Grupo_.numero)
				, root.get(Evento_.grupo).get(Grupo_.complemento)
				, root.get(Evento_.grupo).get(Grupo_.bairro)			
				, root.get(Evento_.grupo).get(Grupo_.cidade).get(Cidade_.nome)
				, root.get(Evento_.grupo).get(Grupo_.cidade).get(Cidade_.estado).get(Estado_.sigla)
				, root.get(Evento_.tipoEvento)
				, root.get(Evento_.tema)
				, root.get(Evento_.palestrante)
				, root.get(Evento_.destaque) ));
		
		//criar as restrições
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
				
		TypedQuery<ResumoEvento> query = manager.createQuery(criteria);
		//adicionarRestricoesDePaginacao(query, pageable);		
				
		return query.getResultList();
	}
	
	
	private Predicate[] criarRestricoes(EventoFilter filter, CriteriaBuilder builder, Root<Evento> root) {
		List<Predicate> predicates = new ArrayList<>();				
		
		if (filter.getDestaque() != null) {
			  predicates.add(
					  builder.equal(root.get(Evento_.destaque), filter.getDestaque()) );
		}		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
// ------------------------------------------------------ PESQUISA COM AUTENTICAÇÃO SEM PARAMETROS E RESUMIDO
	@Override
	public Page<ResumoEvento> pesquisar(EventoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoEvento> criteria = builder.createQuery(ResumoEvento.class);
		Root<Evento> root = criteria.from(Evento.class);
		
		criteria.select(builder.construct(ResumoEvento.class
				, root.get(Evento_.codigo)
				, root.get(Evento_.dataHoraEvento)
				, root.get(Evento_.grupo).get(Grupo_.nome)
				, root.get(Evento_.grupo).get(Grupo_.logradouro)
				, root.get(Evento_.grupo).get(Grupo_.numero)
				, root.get(Evento_.grupo).get(Grupo_.complemento)
				, root.get(Evento_.grupo).get(Grupo_.bairro)			
				, root.get(Evento_.grupo).get(Grupo_.cidade).get(Cidade_.nome)
				, root.get(Evento_.grupo).get(Grupo_.cidade).get(Cidade_.estado).get(Estado_.sigla)
				, root.get(Evento_.tipoEvento)
				, root.get(Evento_.tema)
				, root.get(Evento_.palestrante)
				, root.get(Evento_.destaque) ));
		
		//criar as restrições
		//Predicate[] predicates = criarRestricoes(filter, builder, root);
		//criteria.where(predicates);
				
		TypedQuery<ResumoEvento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);		
				
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(EventoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Evento> root = criteria.from(Evento.class);
		
		//Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		//criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}	
	

}
