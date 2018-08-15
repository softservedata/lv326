package com.softserve.edu.project.dao;


import com.softserve.edu.project.entity.UserHasTeams;

public final class UserHasTeamsDao extends ADaoCRUD<UserHasTeams>{
    public UserHasTeamsDao() {
        super();
        init();
    }


    protected void init() {
        for (UserHasTeams.UserHasTeamsSqlQueries usrHasteamsEntityQueries : UserHasTeams.UserHasTeamsSqlQueries.values()) {
            sqlQueries.put(usrHasteamsEntityQueries.getSqlQuery(), usrHasteamsEntityQueries);
        }
    }

    protected UserHasTeams createInstance(String[] args) {
        return new UserHasTeams(
                Long.parseLong(args[0] == null ? "0" : args[0]),
                Long.parseLong(args[1] == null ? "0" : args[1]),
                Long.parseLong(args[2] == null ? "0" : args[2]));
    }

    protected String[] getUpdateFields(UserHasTeams entity) {
        String[] result = new String[2];
        String[] allFields = getFields(entity);
        result[0] = allFields[1]; // idUser
        result[1] = allFields[2]; // idTeam
        result[2] = allFields[0]; // id
        return result;
    }

    protected String[] getFields(UserHasTeams entity) {
        //String[] fields = new String[Role.class.getDeclaredFields().length];
        String[] fields = new String[3];
        fields[0] = entity.getId().toString();
        fields[1] = entity.getIdUser().toString();
        fields[2] = entity.getIdTeam().toString();
        return fields;
    }
}
