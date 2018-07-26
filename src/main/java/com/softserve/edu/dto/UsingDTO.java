package com.softserve.edu.dto;

import java.sql.Date;

import com.softserve.edu.entity.Book;
import com.softserve.edu.entity.Reader;

public class UsingDTO {
	private Book book;
	private Reader reader;
	private Date dateReturn;

	public UsingDTO(Book book, Reader reader, Date dateReturn) {
		this.book = book;
		this.reader = reader;
		this.dateReturn = dateReturn;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}

}
