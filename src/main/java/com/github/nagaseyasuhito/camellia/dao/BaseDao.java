package com.github.nagaseyasuhito.camellia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

public abstract class BaseDao<T> {
	protected interface Query<T, R> {
		CriteriaQuery<R> execute(CriteriaBuilder builder, CriteriaQuery<R> query, Root<T> root);
	}

	protected interface EntityQuery<T> extends Query<T, T> {
	}

	@PersistenceContext
	private EntityManager entityManager;

	protected abstract Class<T> getEntityClass();

	protected T getSingleResult(EntityQuery<T> query) {
		return this.createQuery(query, this.getEntityClass()).getSingleResult();
	}

	protected List<T> getResultList(EntityQuery<T> query) {
		return this.createQuery(query, this.getEntityClass()).getResultList();
	}

	protected List<T> getResultList(EntityQuery<T> query, int firstResult, int maxResults) {
		return this.createQuery(query, this.getEntityClass()).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

	protected <R> R getSingleResult(Query<T, R> query, Class<R> resultClass) {
		return this.createQuery(query, resultClass).getSingleResult();
	}

	private <R> TypedQuery<R> createQuery(Query<T, R> query, Class<R> resultClass) {
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<R> criteriaQuery = criteriaBuilder.createQuery(resultClass);
		Root<T> root = criteriaQuery.from(this.getEntityClass());

		return this.entityManager.createQuery(query.execute(criteriaBuilder, criteriaQuery, root));
	}

	public T merge(@Valid T entity) {
		return this.entityManager.merge(entity);
	}

	public void persist(@Valid T entity) {
		this.entityManager.persist(entity);
	}

	public void remove(@Valid T entity) {
		this.entityManager.remove(entity);
	}
}
