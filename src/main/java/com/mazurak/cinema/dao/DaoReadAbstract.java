package com.mazurak.cinema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.mazurak.cinema.db.ConnectionManager;
import com.mazurak.cinema.entity.enums.SqlQueries;

public abstract class DaoReadAbstract<TEntity> implements DaoReadInterface<TEntity> {

	protected final static String QUERY_NOT_FOUND = "Query not found %s";
	protected final static String DATABASE_READING_ERROR = "Database Reading Error";
	protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";

	protected final Map<Enum<?>, Enum<?>> sqlQueries;

	public DaoReadAbstract() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
	}

	protected abstract TEntity createInstance(String[] args);
	protected abstract void init();

	// READ
	private List<TEntity> getQueryResult(String query, SqlQueries sqlQueries) {
		List<TEntity> allEntities = new ArrayList<TEntity>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String[] queryResult;
		if (query == null) {
			System.out.println((String.format(QUERY_NOT_FOUND, sqlQueries.name())));
		}
		try {
			preparedStatement = ConnectionManager.getInstance().getConnection().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				for (int i = 0; i < queryResult.length; i++) {
					queryResult[i] = resultSet.getString(i + 1);
				}
				allEntities.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			checkDataBaseExisting(preparedStatement);
			System.out.println(DATABASE_READING_ERROR);
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (allEntities.isEmpty()) {
			throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
		}
		return allEntities;
	}

	private void checkDataBaseExisting(Statement statement) {
		String query = "Select * from user";
		try {
			statement.execute(query);

		} catch (SQLException e) {
			try {
				createTables(statement);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void createTables(Statement statement) throws SQLException {

		String createDB = "CREATE DATABASE test";
		String queruCreateUser = "Create table users(" + "id int primary key auto_increment,"
				+ "name varchar(50)," + "login varchar(30)," + "password varchar(45)," + "roleId int";
		String queruCreateMovie = "Create table movies(" + "id int primary key auto_increment,"
				+ "filmName varchar(50)," + "description varchar(30),"
				+ "ageLimit ENUM('0+', '9+', '12+','16+','18+')," + "year int," + "userId int";
		String queruCreateRoles =
				"Create table roles(" + "id int primary key auto_increment," + "name varchar(45)";
		String queruCreateReferencesUserRole =
				"ALTER TABLE users ADD " + "FOREIGN KEY (roleId) REFERENCES roles(id);";
		String queruCreateReferencesMovieUser =
				"ALTER TABLE movies ADD " + "FOREIGN KEY (userId) REFERENCES users(id) on delete cascade;";

		String queryCreateRolesUser = "INSERT INTO roles (name) VALUES ('User');";
		String queryCreateRolesAdmin = "INSERT INTO roles (name) VALUES ('Admin');";
		String queryCreateAdmin = "INSERT INTO users (login, password, name, id_role) "
				+ "VALUES ('admin', 'admin', 'Taras', 2);";

		statement.execute(createDB);
		statement.execute(queruCreateUser);
		statement.execute(queruCreateMovie);
		statement.execute(queruCreateRoles);
		statement.execute(queruCreateReferencesUserRole);
		statement.execute(queruCreateReferencesMovieUser);
		statement.execute(queryCreateRolesUser);
		statement.execute(queryCreateRolesAdmin);
		statement.execute(queryCreateAdmin);
	}

	public TEntity getById(Long id) {
		try {
			return getQueryResult(String.format(sqlQueries.get(SqlQueries.GET_BY_ID).toString(), id),
					SqlQueries.GET_BY_ID).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public List<TEntity> getByFieldName(String fieldName, String text) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_BY_FIELD).toString(), fieldName, text),
				SqlQueries.GET_BY_FIELD);
	}

	public List<TEntity> getAll() {
		return getQueryResult(sqlQueries.get(SqlQueries.GET_ALL).toString(), SqlQueries.GET_ALL);
	}

	public List<TEntity> getByOpertorLike(String name) {
		String query = "Select id,filmName,description,ageLimit,year,"
				+ "idUser from movies where filmName like '%" + name + "%'";
		return getQueryResult(query, SqlQueries.GET_BY_LIKE_OPERATOP);
	}
}

