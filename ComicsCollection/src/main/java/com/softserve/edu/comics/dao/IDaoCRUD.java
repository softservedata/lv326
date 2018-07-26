package com.softserve.edu.comics.dao;

public interface IDaoCRUD<TEntity> extends IDaoRead<TEntity> {

	// CREATE
	boolean insert(TEntity entity);

	// UPDATE
	boolean updateByEntity(TEntity entity);

	// DELETE
	boolean deleteById(Long id);

}
