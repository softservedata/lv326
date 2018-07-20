package com.passwordsaver.entity;

public class Key implements Entity{
	public static enum KeyEntityQueries{
		INSERT(SqlQueries.INSERT, "INSERT INTO psdb.keys (service, serv_password, idUser) VALUES ('%s', '%s', %s);"),
		GET_BY_ID(SqlQueries.GET_BY_ID,"SELECT id, service, serv_password, idUser FROM psdb.keys WHERE id = %s;"),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, service, serv_password, idUser FROM psdb.keys WHERE %s = '%s';"),
		GET_ALL(SqlQueries.GET_ALL, "SELECT id, service, serv_password, idUser FROM psdb.keys;"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID,"UPDATE psdb.keys SET service = '%s', serv_password = '%s', idUser = '%s' WHERE id = %s;"),
		UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE psdb.keys SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM psdb.keys WHERE id = %s;"),
		DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM psdb.keys WHERE %s = '%s';");
		
		private SqlQueries sqlQuery;
		private String query;
		private KeyEntityQueries(SqlQueries sqlQuery, String query)
		{
			this.sqlQuery = sqlQuery;
			this.query = query;
		}
		public SqlQueries getSqlQuery()
		{
			return sqlQuery;
		}
		public String toString() {
			return query;
		}
	}
	private Long id;
	private String service;
	private String serv_password;
	private Long idUser;
	
	public Key(Long id, String service, String serv_password, Long idUser) {
		this.id = id;
		this.service = service;
		this.serv_password = serv_password;
		this.idUser = idUser;
	} 
	@Override
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
	public void setServPassvord(String serv_password) {
		this.serv_password = serv_password;
	}
	public String getServPassword() {
		return serv_password;
	}
	public Long getIdUser() {
		return this.idUser;
	}
	
	public String toString(){
		return "("
				+" id: "+id
				+" service: "+service
				+" serv_password: "+serv_password
				+" idUser: "+idUser
				+" )";
	}
}
