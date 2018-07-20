package com.passwordsaver.dao;
import java.util.List;
public interface DaoReadable<TEntity> {
	
	TEntity getById(Long id);
	List<TEntity> getByFieldName(String field, String value);
	List<TEntity> getAll();
	//List<TEntity> getFilterRange(int number, int partSize, Map<String, String> filters);
}
