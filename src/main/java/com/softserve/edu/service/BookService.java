package com.softserve.edu.service;

import com.softserve.edu.dao.BookDAO;
import com.softserve.edu.dto.BookDTO;
import com.softserve.edu.entity.Book;
import com.softserve.edu.entity.Using;

public class BookService {
    private HibernateManager hibernateManager;
    private BookDAO bookDao;

    public BookService(HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
        this.bookDao = new BookDAO(hibernateManager.getSession());
    }

    public Book addBook(BookDTO bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setUsing(bookDto.getUsings());
        hibernateManager.transactionBegin();
        book = bookDao.add(book);
        hibernateManager.transactionEnd();
        return book;
    }

    public Book readBook(Long id) {
    	hibernateManager.transactionBegin();
    	Book book = bookDao.getById(id);
    	System.out.println("Id: " + book.getId());
    	System.out.println("Title: " + book.getTitle());
    	System.out.println("Usings size: " + book.getUsing().size());
    	for (Using using : book.getUsing()) {
        	System.out.println("ReaderName: " + using.getReader().getName());
    	}
        hibernateManager.transactionEnd();
    	return book;
    }

    public void updateBook(BookDTO oldBookDto, BookDTO newBookDto) {
        for (Book book : bookDao.getAll()) {
            if (book.getTitle().equals(oldBookDto.getTitle())) {
                //Book newBook = new Book();
                //newBook.setId(book.getId());
                //newBook.setTitle(newBookDto.getTitle());
                hibernateManager.transactionBegin();
                // TODO move to DAO
                book.setTitle(newBookDto.getTitle());
                //bookDao.update(newBook);
                hibernateManager.transactionEnd();
            }
        }
    }

}
