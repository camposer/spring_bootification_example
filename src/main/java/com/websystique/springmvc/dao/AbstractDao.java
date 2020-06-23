package com.websystique.springmvc.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) entityManager.find(persistentClass, key);
	}

	public void persist(T entity) {
		entityManager.persist(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

}
