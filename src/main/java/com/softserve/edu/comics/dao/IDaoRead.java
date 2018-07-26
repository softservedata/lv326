package com.softserve.edu.comics.dao;

import java.util.List;

public interface IDaoRead<TEntity> {

	TEntity getById(Long id);

	List<TEntity> getByFieldName(String fieldName, String text);

	List<TEntity> getAll();

}
