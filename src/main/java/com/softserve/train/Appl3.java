package com.softserve.train;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.hibernate.Session;

import com.softserve.edu.HibernateUtil;

public class Appl3 {
    public static void main(String[] args) {
        Book book1 = null;
        Session session = null;
        // try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        book1 = session.get(Book.class, 1L);
        //
//        System.out.println("book1: Title = " + book1.getTitle());
//        for (Reader reader : book1.getReaders()) {
//        	System.out.println("Reader, Name = " + reader.getName());
//        }
        //
        // For @ManyToMany(fetch = FetchType.EAGER)
        session.getTransaction().commit();
        session.close();
        // } finally {
        // if (session != null && session.isOpen()) {
        // session.close();
        // }
        // }
        //
        System.out.println("book1: Title = " + book1.getTitle());
        for (Reader reader : book1.getReaders()) {
            System.out.println("Reader, Name = " + reader.getName());
        }
        //
        // For @ManyToMany(fetch = FetchType.LAZY)
        // COMMIT AFTER reader.getName()
        //session.getTransaction().commit();
        //session.close();
        //
        HibernateUtil.getSessionFactory().close();
        System.out.println("\nThe END.\n");
    }
}
