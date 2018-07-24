package com.mazurak.cinema.dao;

import java.util.List;

public interface DaoReadInterface<TEntity> {

	//Read -> Select 
		TEntity getById(Long id);
		
		List<TEntity> getAll();

		List<TEntity> getByFieldName(String fielName, String value);
}
