package com.reciclanavirai.web.repositories;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class AbstractDao<T, ID extends Serializable> {
	
	@SuppressWarnings("unchecked")
	private final Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
			.getActualTypeArguments()[0];

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void save(T entity) {
		entityManager.persist(entity);
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(ID id) {
		entityManager.remove(entityManager.getReference(entityClass, id));
	}

	public T findById(ID id) {
		return entityManager.find(entityClass, id);
	}
	
	public List<T> findAll(){
		return entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
	}
	
	protected List<T> createQuery(String jpql, Object... params){
		TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
		for(int i = 0; i<params.length; i++) {
			query.setParameter(i+1, params[1]);
		}
		return query.getResultList();
		//return query.getSingleResult();
	}

}
