package com.softserve.edu.items.entity;

public class User implements IEntity {
	
	public static enum UserEntityQueries {
		INSERT(SqlQueries.INSERT, "INSERT INTO users (login, password, name, idRole) VALUES ('%s', '%s', '%s', %s);"),
		GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, login, password, name, idRole FROM users WHERE id = %s;"),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, login, password, name, idRole FROM users WHERE %s = '%s';"),
		GET_ALL(SqlQueries.GET_ALL, "SELECT id, login, password, name, idRole FROM users;"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE users SET login = '%s', password = '%s', name = '%s', idRole = %s  WHERE id = %s;"),
		UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE users SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM users WHERE id = %s;"),
		DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM users WHERE %s = '%s';");
		//
		private SqlQueries sqlQuery;
		private String query;

		private UserEntityQueries(SqlQueries sqlQuery, String query) {
			this.sqlQuery = sqlQuery;
			this.query = query;
		}

		public SqlQueries getSqlQuery() {
			return sqlQuery;
		}

		@Override
		public String toString() {
			return query;
		}
	}

	private Long id;
	private String login;
	private String password;
	private String name;
	private Long idRole;

	public User(Long id, String login, String password, String name, Long idRole) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.idRole = idRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	@Override
	public String toString() {
		return "(" + "id=" + id 
				+ " login=" + login 
				+ " password=" + password 
				+ " name=" + name 
				+ " idRole=" + idRole + ")";
	}
}
