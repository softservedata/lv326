package com.passwordsaver.dao;

import com.passwordsaver.entity.Entity;
import com.passwordsaver.entity.SqlQueries;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.passwordsaver.dao.DaoCRUDable;
import com.passwordsaver.db.ConnectionManager;;
public abstract class DaoCRUD<TEntity extends Entity> extends DaoRead<TEntity> implements DaoCRUDable<TEntity>{
	protected static final String DATABASE_INPUT_ERROR = "Database input error";
	protected DaoCRUD() {
		super();
	}
	
	protected abstract List<String> getFields(TEntity entity);
	protected abstract List<String> getUpdateFields(TEntity entity);
	
	private boolean executeQuery(String query, SqlQueries sqlQuery) {
		if(query==null) {
			throw new RuntimeException(DaoCRUD.QUERY_NOT_FOUND);
		}
		boolean result = false;
		Statement statement = null;
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			statement.execute(query);
			result = true;
		} catch (SQLException e) {
			throw new RuntimeException(DaoCRUD.DATABASE_INPUT_ERROR,e);
		}finally {
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new RuntimeException(DaoCRUD.FAILED_CLOSE_STATEMENT);
				}
			}
		}
		return result;
	}
	public boolean insert(TEntity entity) {
		String query = String.format(sqlQueries.get(SqlQueries.INSERT).toString(),
				(Object[])Arrays.copyOfRange(getFields(entity).toArray(),1,getFields(entity).size()));
		return executeQuery(query, SqlQueries.INSERT);
	}
	public boolean updateByEntity(TEntity entity) {
		String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_ID).toString(),
				getUpdateFields(entity).toArray());
		return executeQuery(query,SqlQueries.UPDATE_BY_ID);
	}
	public boolean updateByFieldName(String field, String value, String fieldCondition, String valueCondition) {
		String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_FIELD).toString(), 
										field, value, fieldCondition, valueCondition);
		return executeQuery(query, SqlQueries.UPDATE_BY_FIELD);
	} 
	public boolean deleteById(Long id) {
		String query = String.format(sqlQueries.get(SqlQueries.DELETE_BY_ID).toString(),
									id);
		try {
		return executeQuery(query, SqlQueries.DELETE_BY_ID);
		}catch(Exception exc) {
			return false;
		}
	}
	public boolean deleteByFieldName(String fieldCondition, String valueCondition) {
		String query = String.format(sqlQueries.get(SqlQueries.DELETE_BY_FIELD).toString(),
									fieldCondition, valueCondition);
		return executeQuery(query, SqlQueries.DELETE_BY_FIELD);
	}
	public boolean delete(TEntity entity) {
		return deleteById(entity.getId());
	}
}
