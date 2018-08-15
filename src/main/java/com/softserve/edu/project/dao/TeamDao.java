package com.softserve.edu.project.dao;


import com.softserve.edu.project.entity.Team;

public final class TeamDao extends ADaoCRUD<Team> {
    public TeamDao() {
        super();
        init();
    }


    protected void init() {
        for (Team.TeamEntityQueries teamEntityQueries : Team.TeamEntityQueries.values()) {
            sqlQueries.put(teamEntityQueries.getSqlQuery(), teamEntityQueries);
        }
    }


    protected Team createInstance(String[] args) {
        return new Team(
                Long.parseLong(args[0] == null ? "0" : args[0]),
                args[1] == null ? new String() : args[1],
                args[2] == null ? new String() : args[2],
                args[3] == null ? new String() : args[3]);
    }

    protected String[] getUpdateFields(Team entity) {
        String[] result = new String[4];
        String[] allFields = getFields(entity);
        result[0] = allFields[1]; // name
        result[1] = allFields[2]; // country
        result[2] = allFields[3]; // city
        result[3] = allFields[0]; // id
        return result;
    }

    protected String[] getFields(Team entity) {
        String[] fields = new String[6];
        fields[0] = entity.getId().toString();
        fields[1] = entity.getName();
        fields[2] = entity.getCountry();
        fields[3] = entity.getCity();
        return fields;
    }
}
