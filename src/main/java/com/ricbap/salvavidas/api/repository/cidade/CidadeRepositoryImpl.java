package com.ricbap.salvavidas.api.repository.cidade;

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
import org.springframework.util.StringUtils;

import com.ricbap.salvavidas.api.model.Cidade;
import com.ricbap.salvavidas.api.model.Cidade_;
import com.ricbap.salvavidas.api.model.Estado_;
import com.ricbap.salvavidas.api.repository.filter.CidadeFilter;
import com.ricbap.salvavidas.api.repository.projection.ResumoCidade;

public class CidadeRepositoryImpl implements CidadeRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		//criar as restrições
		Predicate[] predicates = criarRestricoes(cidadeFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Cidade> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);		
		
		return new PageImpl<>(query.getResultList(), pageable, total(cidadeFilter));
	}	

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
	
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);		
		
	}
	
	private Long total(CidadeFilter cidadeFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		Predicate[] predicates = criarRestricoes(cidadeFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarRestricoes(CidadeFilter cidadeFilter, CriteriaBuilder builder,
			Root<Cidade> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(cidadeFilter.getEstado() )) {
			predicates.add(
				builder.equal(root.get(Cidade_.estado), cidadeFilter.getEstado() ));
		}		
		
		if (!StringUtils.isEmpty(cidadeFilter.getNome())) {
			predicates.add(builder.like(
				builder.lower(root.get(Cidade_.nome)), "%" + cidadeFilter.getNome().toLowerCase() + "%"));
		}		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public Page<ResumoCidade> resumir(CidadeFilter cidadeFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoCidade> criteria = builder.createQuery(ResumoCidade.class);
		Root<Cidade> root = criteria.from(Cidade.class);
		
		criteria.select(builder.construct(ResumoCidade.class
				, root.get(Cidade_.codigo)
				, root.get(Cidade_.nome)
				, root.get(Cidade_.estado).get(Estado_.nome)));
		
		//criar as restrições
		Predicate[] predicates = criarRestricoes(cidadeFilter, builder, root);
		criteria.where(predicates);
				
		TypedQuery<ResumoCidade> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);		
				
		return new PageImpl<>(query.getResultList(), pageable, total(cidadeFilter));
	}

} 



/*
 * public Page<Cidade> filtrar(CidadeFilter filter, Pageable pageable) {
 * Criteria criteria =
 * manager.unwrap(Session.class).createCriteria(Cidade.class);
 * 
 * paginacaoUtil.preparar(criteria, pageable); adicionarFiltro(filter,
 * criteria); criteria.createAlias("estado", "e");
 * 
 * return new PageImpl<>(criteria.list(), pageable, total(filter)); }
 * 
 * private Long total(CidadeFilter filter) { Criteria criteria =
 * manager.unwrap(Session.class).createCriteria(Cidade.class);
 * adicionarFiltro(filter, criteria);
 * criteria.setProjection(Projections.rowCount()); return (Long)
 * criteria.uniqueResult(); }
 * 
 * private void adicionarFiltro(CidadeFilter filter, Criteria criteria) { if
 * (filter != null) { if (filter.getEstado() != null) {
 * criteria.add(Restrictions.eq("estado", filter.getEstado())); }
 * 
 * if (!StringUtils.isEmpty(filter.getNome())) {
 * criteria.add(Restrictions.ilike("nome", filter.getNome(),
 * MatchMode.ANYWHERE)); } } }
 */