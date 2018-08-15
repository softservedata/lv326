package com.softserve.edu.project.dao;


import com.softserve.edu.project.db.ConnectionManager;
import com.softserve.edu.project.entity.SqlQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class ADaoRead<TEntity> implements IDaoRead<TEntity> {
	protected final static String QUERY_NOT_FOUND = "Query not found %s";
	protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
	protected final static String DATABASE_READING_ERROR = "Database Reading Error";
	//
	protected final Map<Enum<?>, Enum<?>> sqlQueries;

	protected ADaoRead() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
		init();
	}

	protected abstract TEntity createInstance(String[] args);

	protected abstract void init();

	// Read
	private List<TEntity> getQueryResult(String query, SqlQueries sqlQueries) {
		List<TEntity> all = new ArrayList<TEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		String[] queryResult;
		if (query == null) {
			System.out.println(QUERY_NOT_FOUND+ sqlQueries.name());
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			resultSet = statement.executeQuery(query);
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				for (int i = 0; i < queryResult.length; i++) {
					queryResult[i] = resultSet.getString(i + 1);
				}
				all.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			System.out.println(DATABASE_READING_ERROR + e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
		if (all.isEmpty()) {
			System.out.println(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}

	public TEntity getById(Long id) {
		return getQueryResult(String.format(
				sqlQueries.get(SqlQueries.GET_BY_ID).toString(), id),
				SqlQueries.GET_BY_ID).get(0);
	}

	public List<TEntity> getByFieldName(String fieldName, String text) {
		return getQueryResult(String.format(
				sqlQueries.get(SqlQueries.GET_BY_FIELD).toString(), fieldName, text),
				SqlQueries.GET_BY_FIELD);
	}

	public List<TEntity> getAll() {
		return getQueryResult(
				sqlQueries.get(SqlQueries.GET_ALL).toString(),
				SqlQueries.GET_ALL);
	}
	public List<TEntity> getPagination(int currentPage, int recordsPerPage) {
		return getQueryResult(String.format(
				sqlQueries.get(SqlQueries.GET_PAGINATION).toString(), currentPage, recordsPerPage),
				SqlQueries.GET_PAGINATION);
	}
	public List<TEntity> getPagination(String fieldName, String text,int currentPage, int recordsPerPage) {
		return getQueryResult(String.format(
				sqlQueries.get(SqlQueries.GET_PAGINATION).toString(),fieldName,text, currentPage, recordsPerPage),
				SqlQueries.GET_PAGINATION);
	}

}
