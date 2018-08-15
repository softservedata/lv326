package com.softserve.edu.project.entity;

public class User implements IEntity{

    public static enum UserEntityQueries {
        INSERT(SqlQueries.INSERT, "INSERT INTO users (username, password, name,surname, idRole) VALUES ('%s', '%s', '%s', '%s', %s);"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, username, password, name,surname, idRole FROM users WHERE id = %s;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, username, password, name,surname, idRole FROM users WHERE %s = '%s';"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT id, username, password, name,surname, idRole FROM users;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE users SET password = '%s', name = '%s', surname = '%s', idRole = %s WHERE id = %s;"),
        UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE users SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM users WHERE id = %s;"),
        GET_PAGINATION(SqlQueries.GET_PAGINATION,"SELECT id, username, password, name,surname, idRole FROM users LIMIT %s, %s"),
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
    private String username;
    private String password;
    private String name;
    private String surname;
    private Long idRole;

    public User() {
    }

    public User(Long id, String username, String password, String name, String surname, Long idRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.idRole = idRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }
}
