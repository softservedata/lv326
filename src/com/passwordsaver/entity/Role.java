package com.passwordsaver.entity;

public class Role implements Entity{
	public static enum RoleEntityQueries
	{
		INSERT(SqlQueries.INSERT,"INSERT INTO roles (title) VALUES ('%s');"),
		GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, title FROM roles WHERE id = %s;"),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, title FROM roles WHERE %s = '%s';"),
		GET_ALL(SqlQueries.GET_ALL,"SELECT id, title FROM roles;"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID,"UPDATE roles SET title = %s WHERE id = %s;"),
		UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD,"UPDATE roles SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID,"DELETE FROM roles WHERE id = %s;"),
		DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD,"DELETE FROM roles WHERE %s = '%s';");
		
		private SqlQueries sqlQueries;
		private String query;
		private RoleEntityQueries(SqlQueries sqlQueries, String query)
		{
			this.sqlQueries = sqlQueries;
			this.query = query;
		}
		public SqlQueries getSqlQuery() {
			return sqlQueries;
		}
		@Override
		public String toString() {
			return query;
		}
	}
	
	Long id;
	String title;
	public Role(Long id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Long getId() {
		return id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
	public String toString(){
		return "("
				+" id: "+id
				+" title: "+title
				+" )";
	}
}
