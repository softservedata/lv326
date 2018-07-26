package com.softserve.edu.comics.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Role implements IEntity {

    private Long id;
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // ------ QUERIES ------
    public static enum RoleEntityQueries {
        INSERT(SqlQueries.INSERT, "INSERT INTO roles (name) VALUES ('%s');"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, name FROM roles WHERE id = %s;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, name FROM roles WHERE %s = '%s';"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT id, name FROM roles;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE roles SET name = '%s' WHERE id = %s;"),
        UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE roles SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM roles WHERE id = %s;"),
        DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM roles WHERE %s = '%s';");

        private SqlQueries sqlQuery;
        private String query;

        private RoleEntityQueries(SqlQueries sqlQuery, String query) {
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
