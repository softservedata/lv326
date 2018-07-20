package com.passwordsaver.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.passwordsaver.db.ConnectionManager;
import com.passwordsaver.entity.SqlQueries;
public abstract class DaoRead<TEntity> implements DaoReadable<TEntity> {

	protected final static String QUERY_NOT_FOUND = "Query '%s' not found";
	protected final static String EMPTY_RESULTSET = "Empty resultset by Query '%s'";
	protected final static String DATABASE_READING_ERROR = "Database Reading Error";
	protected final static String FAILED_CLOSE_RESULTSET = "Failed to close resultset";
	protected final static String FAILED_CLOSE_STATEMENT = "Failed to close statement";
	
	protected final Map<Enum<?>,Enum<?>> sqlQueries;
	
	protected DaoRead() {
		sqlQueries = new HashMap<Enum<?>,Enum<?>>();
		init();
	}
	
	protected abstract TEntity createInstance(List<String> args);
	
	protected abstract void init();
	
	private List<TEntity> getQueryResult(String query, SqlQueries sqlQueries){
		List<TEntity> all = new ArrayList<TEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		List<String> queryResult = null;
		if(query==null) {
			throw new RuntimeException(String.format(DaoRead.QUERY_NOT_FOUND, sqlQueries.name()));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			resultSet = statement.executeQuery(query);
			queryResult = new ArrayList<String>();
			while(resultSet.next()) {
				queryResult.clear();
				for(int i = 1; i<=resultSet.getMetaData().getColumnCount(); i++) {
					queryResult.add(resultSet.getString(i));
				}
				all.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			throw new RuntimeException(DaoRead.DATABASE_READING_ERROR,e);
		}finally {
			if(resultSet!=null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new RuntimeException(DaoRead.FAILED_CLOSE_RESULTSET,e);
				}
			}
			if(statement!=null){
				try {
					statement.close();
				} catch (SQLException e) {
					throw new RuntimeException(DaoRead.FAILED_CLOSE_STATEMENT,e);
				}
			}
		}
		if(all.isEmpty()) {
			throw new RuntimeException(String.format(DaoRead.EMPTY_RESULTSET, query));
		}
		return all;
	}

	@Override
	public TEntity getById(Long id) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_BY_ID).toString(), id),
				SqlQueries.GET_BY_ID)
				.get(0);
	}

	@Override
	public List<TEntity> getByFieldName(String field, String value) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_BY_FIELD).toString(), field, value),
				SqlQueries.GET_BY_FIELD);
	}

	@Override
	public List<TEntity> getAll() {
		return getQueryResult(
				sqlQueries.get(SqlQueries.GET_ALL).toString(),
				SqlQueries.GET_ALL);
	}

}
