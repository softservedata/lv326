package com.softserve.edu.comics.dao;

import com.softserve.edu.comics.entity.Comics;
import com.softserve.edu.comics.entity.Comics.ComicsQueries;

public final class ComicsDao extends ADaoCRUD<Comics> {

    public ComicsDao() {
        super();
        init();
    }

    @Override
    protected void init() {
        for (ComicsQueries comicsQueries : ComicsQueries.values()) {
            sqlQueries.put(comicsQueries.getSqlQuery(), comicsQueries);
        }
    }

    protected Comics createInstance(String[] args){
        return new Comics(
                Long.parseLong(args[0] == null ? "0" : args[0]),
                args[1] == null ? new String() : args[1],
                args[2] == null ? new String() : args[2],
                args[3] == null ? new String () : args[3],
                args[4] == null ? new String () : args[4],
                Long.parseLong(args[5] == null ? "0" : args[5]));
    }

    protected String[] getUpdateFields(Comics entity){
        String[] result = new String[5];
        result[0] = entity.getTitle();
        result[1] = entity.getAuthor();
        result[2] = entity.getPublishingOffice();
        result[3] = entity.getDescription();
        result[4] =  entity.getId().toString();
        return result;
    }

    protected String[] getFields(Comics entity){
        String[] fields = new String[6];
        fields[0] = entity.getId().toString();
        fields[1] = entity.getTitle();
        fields[2] = entity.getAuthor();
        fields[3] = entity.getPublishingOffice();
        fields[4] = entity.getDescription();
        fields[5] = entity.getIdUser().toString();
        return fields;
    }

}
