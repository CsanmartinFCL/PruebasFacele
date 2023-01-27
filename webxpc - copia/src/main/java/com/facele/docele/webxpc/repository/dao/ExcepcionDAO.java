package com.facele.docele.webxpc.repository.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.facele.docele.webxpc.domain.Excepcion;
import com.facele.docele.webxpc.repository.IExcepcionDAO;
import com.facele.docele.webxpc.util.SearchCriteria;

@Repository
public class ExcepcionDAO implements IExcepcionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Excepcion> consultar(List<SearchCriteria> params, Pageable pageable) {

		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Excepcion> query = builder.createQuery(Excepcion.class);
		final Root<Excepcion> root = query.from(Excepcion.class);

		Predicate predicate = builder.conjunction();

		for (SearchCriteria param : params) {

			switch (param.getOperation()) {
			case greaterThanOrEqualTo:
				predicate = builder.and(predicate,
						builder.greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
				break;
			case lessThanOrEqualTo:
				predicate = builder.and(predicate,
						builder.lessThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
				break;
			case like:
				if (root.get(param.getKey()).getJavaType() == String.class) {
					predicate = builder.and(predicate,
							builder.like(root.get(param.getKey()), "%" + param.getValue() + "%"));
				} else {
					predicate = builder.and(predicate, builder.equal(root.get(param.getKey()), param.getValue()));
				}
				break;
			case between:
				predicate = builder.and(predicate, builder.between(root.get(param.getKey()),
						(LocalDate) param.getValue(), (LocalDate) param.getValue2()));
				break;
			case equal:
				predicate = builder.and(predicate, builder.equal(root.get(param.getKey()), param.getValue()));
				break;
			case in:
				break;
			}
		}
		query.where(predicate);

		return entityManager.createQuery(query.select(root)).setFirstResult(pageable.getPageNumber())
				.setMaxResults(pageable.getPageSize()).getResultList();
	}

}
