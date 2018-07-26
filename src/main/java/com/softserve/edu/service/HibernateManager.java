package com.softserve.edu.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateManager {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public HibernateManager() {
        initSessionFactory();
    }

    private void initSessionFactory() {
        // Hibernate 5.
        // TODO Exceptions
        sessionFactory = new MetadataSources(new StandardServiceRegistryBuilder()
        			.configure().build())
                .getMetadataBuilder().build()
                .getSessionFactoryBuilder().build();
    }

    private void initSession() {
        session = sessionFactory.openSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getSession() {
        if (session == null) {
            initSession();
        }
        return session;
    }

    public void closeSession() {
        if (session != null) {
            session.close();
            session = null;
        }
    }

    public void transactionBegin() {
        transaction = getSession().beginTransaction();
    }

    public void transactionEnd() {
        if (transaction == null) {
            new RuntimeException("transaction not found");
        }
        transaction.commit();
        transaction = null;
    }

}
