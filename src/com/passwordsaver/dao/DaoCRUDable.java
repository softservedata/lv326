package com.passwordsaver.dao;

public interface DaoCRUDable<TEntity> extends DaoReadable<TEntity> {
	
	boolean insert(TEntity entity);
	
	boolean updateByEntity(TEntity entity);
	
	boolean updateByFieldName(String field, String newValue,String fieldCondition, String valueCondition);
	
	boolean deleteById(Long id);
	
	boolean deleteByFieldName(String fieldCondition, String valueCondition);
	
	boolean delete(TEntity entity);
}
