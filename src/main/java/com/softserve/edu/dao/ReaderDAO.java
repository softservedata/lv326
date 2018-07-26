package com.softserve.edu.dao;

import org.hibernate.Session;

import com.softserve.edu.entity.Reader;

public class ReaderDAO extends CrudDAOImpl<Reader> {

    public ReaderDAO(Session session) {
        super(Reader.class, session);
    }

}
