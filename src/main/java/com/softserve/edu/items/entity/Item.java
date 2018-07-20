package com.softserve.edu.items.entity;

public class Item implements IEntity {
	
    public static enum ItemEntityQueries {
        INSERT(SqlQueries.INSERT, "INSERT INTO items (name, description, idUser) VALUES ('%s', '%s', %s);"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, name, description, idUser FROM items WHERE id = %s;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, name, description, idUser FROM items WHERE %s = '%s';"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT id, name, description, idUser FROM items;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE items SET name = '%s', description = '%s' WHERE id = %s;"),
        UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE items SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM items WHERE id = %s;"),
        DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM items WHERE %s = '%s';");
    	//
        private SqlQueries sqlQuery;
        private String query;

        private ItemEntityQueries(SqlQueries sqlQuery, String query) {
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
	private String name;
	private String description;
	private Long idUser;
	
	public Item(Long id, String name, String description, Long idUser) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.idUser = idUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
				+ " name=" + name 
				+ " description=" + description 
				+ " idUser" + idUser + ")";
	}

}
