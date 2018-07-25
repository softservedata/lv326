package com.softserve.edu.items.entity;

public class Order implements IEntity {

	public static enum OrderEntityQueries {
        INSERT(SqlQueries.INSERT, "INSERT INTO orders (shop, address, production, scope, status, idUser) VALUES ('%s', '%s', '%s', %s, '%s', %s);"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, shop, address, production, scope, status, idUser FROM orders WHERE id = %s;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, shop, address, production, scope, status, idUser FROM orders WHERE %s = '%s';"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT id, shop, address, production, scope, status, idUser FROM orders;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE orders SET shop = '%s',address = '%s', production = '%s', scope= %s, status = '%s' WHERE id = %s;"),
        UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE orders SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM orders WHERE id = %s;"),
        DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM orders WHERE %s = '%s';");
    	//
        private SqlQueries sqlQuery;
        private String query;

        private OrderEntityQueries(SqlQueries sqlQuery, String query) {
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
	private String shop;
	private String address;
	private String production;
	private int scope;
	private String status;
	private Long idUser;
	
	public Order(Long id, String shop, String address, String production, int scope,String status, Long idUser) {
		super();
		this.id = id;
		this.shop = shop;
		this.address = address;
		this.production = production;
		this.scope = scope;
		this.status=status;
		this.idUser = idUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public String toString() {
		return "(" + "id=" + id 
				+ " shop=" + shop 
				+ " address=" + address 
				+ " production=" + production 
				+ " scope=" + scope 
				+ " status=" + status 
				+ " idUser=" + idUser + ")";
	}
	
}
