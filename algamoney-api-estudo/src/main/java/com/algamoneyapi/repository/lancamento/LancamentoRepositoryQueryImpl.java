package com.algamoneyapi.repository.lancamento;

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

import com.algamoneyapi.model.Lancamento;
import com.algamoneyapi.repository.filter.LancamentoFilter;

public class LancamentoRepositoryQueryImpl implements LancamentoRepositoryQuery {


	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		// criar as restrições
		
		Predicate [] predicates = criarRestricoes(filter, builder ,root);
		criteria.where(predicates);
		
		TypedQuery<Lancamento> typeQuery = manager.createQuery(criteria);
		return typeQuery.getResultList();
	}

	private Predicate[] criarRestricoes(LancamentoFilter filter,
			CriteriaBuilder builder, Root<Lancamento> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(filter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get("descricao"))
					, "%" + filter.getDescricao().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(filter.getDataVencimentoDe())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoDe()));
		}
		
		if(!StringUtils.isEmpty(filter.getDataVencimentoAte())) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoAte()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
