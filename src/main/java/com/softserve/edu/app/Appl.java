package com.softserve.edu.app;

import java.sql.Date;

import com.softserve.edu.dto.BookDTO;
import com.softserve.edu.dto.ReaderDTO;
import com.softserve.edu.dto.UsingDTO;
import com.softserve.edu.entity.Book;
import com.softserve.edu.entity.Reader;
import com.softserve.edu.entity.Using;
import com.softserve.edu.service.BookService;
import com.softserve.edu.service.HibernateManager;
import com.softserve.edu.service.OrderService;
import com.softserve.edu.service.ReaderService;

public class Appl {

    public static void main(String[] args) {
        HibernateManager hibernateManager = new HibernateManager();
        //
        BookService bookService = new BookService(hibernateManager);
        ReaderService readerService = new ReaderService(hibernateManager);
        OrderService orderService = new OrderService(hibernateManager);
        //
        BookDTO bookDto = new BookDTO("One112-3");
        BookDTO bookDto2 = new BookDTO("One212-3");
        ReaderDTO readerDTO = new ReaderDTO("Ivan22-3");
        //
        Book book1 = bookService.addBook(bookDto);
        Book book2 = bookService.addBook(bookDto2);
        Reader reader = readerService.addReader(readerDTO);
        //
        UsingDTO usingDTO1 = new UsingDTO(book1, reader,
        		new Date(System.currentTimeMillis()+4*60*60*24*1000) );
        UsingDTO usingDTO2 = new UsingDTO(book2, reader,
        		new Date(System.currentTimeMillis()+4*60*60*24*1000) );
        //
        Using using1 = orderService.addUsing(usingDTO1);
        Using using2 = orderService.addUsing(usingDTO2);
        //
        //bookDto.setTitle("Two4");
        //bookService.addBook(bookDto);
        //bookService.updateBook(bookDto, new BookDTO("Tree"));
        //
        System.out.println("book1.getId() = " + book1.getId());
        bookService.readBook(book1.getId());
        //bookService.readBook(new Long(5));
        //
        hibernateManager.closeSession();
        hibernateManager = null;
        System.out.println("done");
    }
    
}
