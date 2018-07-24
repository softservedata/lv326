package com.mazurak.cinema.entity;

import com.mazurak.cinema.entity.enums.SqlQueries;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Role extends BaseEntityCommonFieldsAbstract {
public static enum RoleEntityQueries {
		
		INSERT(SqlQueries.INSERT, "INSERT INTO roles (name) values ('%s');"),
		GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, name FROM roles WHERE id = %s; "),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD,"SELECT id, name from roles WHERE name = '%s'; "),
		GET_ALL(SqlQueries.GET_ALL , "SELECT id, name from roles;"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE roles SET name = '%s' WHERE id = %s;"),
		UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD,  "UPDATE roles SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM roles where id = %s;"),
		DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD , "DELETE FROM roles where '%s' = '%s';");

		private SqlQueries sqlQueries;
		private String query;

		private RoleEntityQueries(SqlQueries sqlQueries, String query) {
			this.sqlQueries = sqlQueries;
			this.query = query;
		}

		public SqlQueries getSqlQueries() {
			return sqlQueries;
		}

		@Override
		public String toString() {
			return query;
		}
	}

	//id
	private String name;

	public Role(Long id,String name) {
		super(id);
		this.name = name;
	}
}
