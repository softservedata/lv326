package com.softserve.edu.comics.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User implements IEntity {

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

	// ------ QUERIES ------
	public static enum UserQueries {
		INSERT(SqlQueries.INSERT, "INSERT INTO users (login, password, name, id_role) VALUES ('%s', '%s', '%s', %s);"),
		GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, login, password, name, id_role FROM users WHERE id = %s;"),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, login, password, name, id_role FROM users WHERE %s = '%s';"),
		GET_ALL(SqlQueries.GET_ALL, "SELECT id, login, password, name, id_role FROM users;"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE users SET password = '%s', name = '%s' WHERE id = %s;"),
		UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE users SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM users WHERE id = %s;"),
		DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM users WHERE %s = '%s';");

		private SqlQueries sqlQuery;
		private String query;

		private UserQueries(SqlQueries sqlQuery, String query) {
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

}
