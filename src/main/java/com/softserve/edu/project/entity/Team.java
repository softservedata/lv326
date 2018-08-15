package com.softserve.edu.project.entity;

public class Team implements IEntity{

    public static enum TeamEntityQueries {

        INSERT(SqlQueries.INSERT, "INSERT INTO teams (name, country, city) VALUES ('%s', '%s', '%s' );"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, name, country, city FROM teams WHERE id = %s;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, name, country, city FROM teams WHERE %s = '%s';"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT id, name, country, city FROM teams;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE teams SET name = '%s', country = '%s', city = '%s' WHERE id = %s;"),
        UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE teams SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM teams WHERE id = %s;"),
        GET_PAGINATION(SqlQueries.GET_PAGINATION,"SELECT id, name, country, city FROM teams LIMIT %s, %s"),
        DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM teams WHERE %s = '%s';");
        //
        private SqlQueries sqlQuery;
        private String query;

        private TeamEntityQueries(SqlQueries sqlQuery, String query) {
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
    private String country;
    private String city;

    public Team() {
    }

    public Team(Long id, String name, String country, String city) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
