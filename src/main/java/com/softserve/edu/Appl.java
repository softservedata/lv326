package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

public class Appl {
	public static void main(String[] args) {
		Book book = new Book();
		book.setTitle("book345");
		//
		Reader reader = new Reader();
		reader.setName("reader345");
		//
		ReaderBooks using = new ReaderBooks();
		using.setIdBook(book);
		using.setIdReader(reader);
		//
		List<Book> elements = new ArrayList<Book>();
		//
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(book);
			session.save(reader);
			session.save(using);
			elements = session.createCriteria(Book.class).list();
			session.getTransaction().commit();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		for (Book b : elements) {
			System.out.println("book: id=" + b.getIdBook() + " Title=" + b.getTitle());
		}
		System.out.println("\nThe END.\n");
	}

}
