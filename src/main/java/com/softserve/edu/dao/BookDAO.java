package com.softserve.edu.dao;

import org.hibernate.Session;

import com.softserve.edu.entity.Book;

public class BookDAO extends CrudDAOImpl<Book> {

    public BookDAO(Session session) {
        super(Book.class, session);
    }

}
