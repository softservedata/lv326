package com.softserve.edu.comics.dao;

import com.softserve.edu.comics.constants.Message;
import com.softserve.edu.comics.db.ConnectionManager;
import com.softserve.edu.comics.entity.SqlQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class ADaoRead<TEntity> implements IDaoRead<TEntity> {

	protected final Map<Enum<?>, Enum<?>> sqlQueries;


	protected ADaoRead() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
		init();
	}

	protected abstract TEntity createInstance(String[] args);
	protected abstract void init();

	// ------ READ ------
	private List<TEntity> getQueryResult(String query, SqlQueries sqlQueries) {
		List<TEntity> allEntities = new ArrayList<TEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		String[] queryResult;
		if (query == null) {
			throw new RuntimeException(String.format(Message.QUERY_NOT_FOUND, sqlQueries.name()));
		}
		try {
			statement = ConnectionManager.getConnectionInstance().getConnection().createStatement();
			resultSet = statement.executeQuery(query);
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				for (int i = 0; i < queryResult.length; i++) {
					queryResult[i] = resultSet.getString(i + 1);
				}
				allEntities.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			throw new RuntimeException(Message.DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (allEntities.isEmpty()) {
			throw new RuntimeException(String.format(Message.EMPTY_RESULTSET, query));
		}
		return allEntities;
	}

	// ------ GET ------
	public TEntity getById(Long id) {
		return getQueryResult(String.format(sqlQueries.get(SqlQueries.GET_BY_ID).toString(), id),
				SqlQueries.GET_BY_ID).get(0);
	}

	public List<TEntity> getByFieldName(String fieldName, String text) {
		return getQueryResult(String.format(sqlQueries.get(SqlQueries.GET_BY_FIELD).toString(), fieldName, text),
				SqlQueries.GET_BY_FIELD);
	}

	public List<TEntity> getAll() {
		return getQueryResult(sqlQueries.get(SqlQueries.GET_ALL).toString(), SqlQueries.GET_ALL);
	}

}
