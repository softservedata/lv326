package com.softserve.edu.comics.dao;

import com.softserve.edu.comics.constants.Message;
import com.softserve.edu.comics.db.ConnectionManager;
import com.softserve.edu.comics.entity.IEntity;
import com.softserve.edu.comics.entity.SqlQueries;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

abstract class ADaoCRUD<TEntity extends IEntity> extends ADaoRead<TEntity> implements IDaoCRUD<TEntity> {



	protected abstract String[] getFields(TEntity entity);
	protected abstract String[] getUpdateFields(TEntity entity);

	private boolean executeQuery(String query, SqlQueries sqlQueries) {
		boolean result = false;
		PreparedStatement statement = null;
		if (query == null) {
			throw new RuntimeException(String.format(Message.QUERY_NOT_FOUND, sqlQueries.name()));
		}
		try {
			statement = ConnectionManager.getConnectionInstance().getConnection().prepareStatement(query);
			result = statement.execute(query);
		} catch (SQLException e) {
			throw new RuntimeException(Message.DATABASE_INPUT_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// ------ CREATE ------
	public boolean insert(TEntity entity) {
		String query = String.format(sqlQueries.get(SqlQueries.INSERT).toString(),
					(Object[]) Arrays.copyOfRange(getFields(entity), 1, getFields(entity).length));
		return executeQuery(query, SqlQueries.INSERT);
	}

	// ------ UPDATE ------
	public boolean updateByEntity(TEntity entity) {
		String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_ID).toString(),
				(Object[]) getUpdateFields(entity));
		return executeQuery(query, SqlQueries.UPDATE_BY_FIELD);
	}

	public boolean updateByFieldName(String fieldName, String text, String fieldCondition, String textCondition) {
		String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_FIELD).toString(),
					fieldName, text, fieldCondition, textCondition);
		return executeQuery(query, SqlQueries.UPDATE_BY_FIELD);
	}

	// ------ DELETE ------
	public boolean deleteById(Long id) {
		String query = String.format(sqlQueries.get(SqlQueries.DELETE_BY_ID).toString(), id);
		return executeQuery(query, SqlQueries.DELETE_BY_ID);
	}

	public boolean deleteByFieldName(String fieldCondition, String textCondition) {
		String query = String.format(sqlQueries.get(SqlQueries.DELETE_BY_FIELD).toString(), fieldCondition, textCondition);
		return executeQuery(query, SqlQueries.DELETE_BY_FIELD);
	}

	public boolean delete(TEntity entity) {
		return deleteById(entity.getId());
	}

}