package com.softserve.edu.dao;

import org.hibernate.Session;

import com.softserve.edu.entity.Using;

public class UsingDAO extends CrudDAOImpl<Using> {

    public UsingDAO(Session session) {
        super(Using.class, session);
    }

}
