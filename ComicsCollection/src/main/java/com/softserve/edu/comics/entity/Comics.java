package com.softserve.edu.comics.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Comics implements IEntity {

    private Long id;
    private String title;
    private String author;
    private String publishingOffice;
    private String description;
    private Long idUser;

    public Comics(Long id, String title, String author, String publishingOffice, String description, Long idUser) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishingOffice = publishingOffice;
        this.description = description;
        this.idUser = idUser;
    }

    // ------ QUERIES ------
    public static enum ComicsQueries {
        INSERT(SqlQueries.INSERT, "INSERT INTO comics (title, author, publishing_office, description, id_user) VALUES ('%s', '%s', '%s', '%s', %s);"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, title, author, publishing_office, description, id_user FROM comics WHERE id = %s;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, title, author, publishing_office, description, id_user FROM comics WHERE %s = '%s';"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT id, title, author, publishing_office, description, id_user FROM comics;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE comics SET title = '%s', author = '%s', publishing_office = '%s', description = '%s' WHERE id = %s;"),
        UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE comics SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM comics WHERE id = %s;"),
        DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM comics WHERE %s = '%s';");

        private SqlQueries sqlQuery;
        private String query;

        private ComicsQueries(SqlQueries sqlQuery, String query) {
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
