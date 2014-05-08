package com.talentica.resam.base.dao;

import java.io.Serializable;
import java.util.List;

import com.talentica.resam.base.entity.BaseDomain;

public interface BaseDAO<T extends BaseDomain, ID extends Serializable> {

	Class<T> getEntityClass();

	T findById(final ID id);

	List<T> findAll();

	T save(final T entity);

	void delete(final T entity);
}