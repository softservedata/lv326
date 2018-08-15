package com.softserve.edu.project.entity;

public class UserHasTeams implements IEntity{
    public static enum UserHasTeamsSqlQueries {
            INSERT(SqlQueries.INSERT, "INSERT INTO user_has_teams ( idUser, idTeam) VALUES (%s, %s);"),
            GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, idUser, idTeam FROM user_has_teams WHERE id = %s;"),
            GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, idUser, idTeam FROM user_has_teams WHERE %s = '%s';"),
            GET_ALL(SqlQueries.GET_ALL, "SELECT id, idUser, idTeam FROM user_has_teams;"),
            UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE user_has_teams SET idUser = %s, idTeam = %s WHERE id = %s;"),
            UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE user_has_teams SET %s = '%s' WHERE %s = '%s';"),
            DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM user_has_teams WHERE id = %s;"),
        GET_PAGINATION(SqlQueries.GET_PAGINATION,"SELECT id, idUser, idTeam FROM user_has_teams WHERE %s = '%s'  LIMIT %s, %s"),
        DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM user_has_teams WHERE %s = '%s';");
            //
            private SqlQueries sqlQuery;
            private String query;

            private UserHasTeamsSqlQueries(SqlQueries sqlQuery, String query) {
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
    private Long idUser;
    private Long idTeam;

    public UserHasTeams() {
    }

    public UserHasTeams(Long id, Long idUser, Long idTeam) {
        this.id = id;
        this.idUser = idUser;
        this.idTeam = idTeam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Long idTeam) {
        this.idTeam = idTeam;
    }
}
