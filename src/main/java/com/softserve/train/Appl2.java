package com.softserve.train;

import org.hibernate.Session;

import com.softserve.edu.HibernateUtil;

public class Appl2 {
    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book();
        book1.setTitle("book1");
        book2.setTitle("book2");
        //
        Reader reader1 = new Reader();
        Reader reader2 = new Reader();
        Reader reader3 = new Reader();
        reader1.setName("reader1");
        reader2.setName("reader2");
        reader3.setName("reader3");
        //
        book1.getReaders().add(reader1);
        book1.getReaders().add(reader2);
        book2.getReaders().add(reader1);
        book2.getReaders().add(reader3);
        //
        Session session = null;
        // try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(reader1);
        session.save(reader2);
        session.save(reader3);
        session.save(book1);
        session.save(book2);
        session.getTransaction().commit();
        session.close();
        // } finally {
        // if (session != null && session.isOpen()) {
        // session.close();
        // }
        // }
        HibernateUtil.getSessionFactory().close();
        System.out.println("\nThe END.\n");
    }

}
