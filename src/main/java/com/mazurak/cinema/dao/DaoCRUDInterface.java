package com.mazurak.cinema.dao;

public interface DaoCRUDInterface<TEntity> extends DaoReadInterface<TEntity> {

		//CREATE
		boolean insert(TEntity entity);

		//UPDATE
		boolean updateByEntity(TEntity entity);
		boolean updateByFieldName(String fieldName, String text, String fieldCondition, String textCondition);

		//DELETE
		boolean deleteById(Long id);
		boolean deleteByFieldName(String fieldName, String text);
		boolean delete(TEntity entity);
}
