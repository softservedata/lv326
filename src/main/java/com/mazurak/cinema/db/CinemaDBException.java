package com.mazurak.cinema.db;

import java.sql.SQLException;

public class CinemaDBException extends SQLException {

	private static final long serialVersionUID = 1L;

	public CinemaDBException(String reason) {
		super(reason);
	}

	public CinemaDBException(String reason, Throwable exception) {
		super(reason, exception);
	}
}
