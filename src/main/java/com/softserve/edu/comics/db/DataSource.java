package com.softserve.edu.comics.db;

import lombok.Getter;
import lombok.Setter;

import java.sql.Driver;

@Getter @Setter
public final class DataSource {

	private Driver jdbcDriver;
	private String connectionUrl;
	private String username;
	private String password;

	public DataSource(Driver jdbcDriver, String connectionUrl, String username, String password) {
		this.jdbcDriver = jdbcDriver;
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;
	}

	@Override
	public boolean equals(Object dataSource) {
		boolean result = false;
		if (dataSource instanceof DataSource) {
			result = getJdbcDriver().getClass().getName()
					.equals(((DataSource) dataSource).getJdbcDriver().getClass().getName())
					&& getConnectionUrl().equals(((DataSource) dataSource).getConnectionUrl())
					&& getUsername().equals(((DataSource) dataSource).getUsername())
					&& getPassword().equals(((DataSource) dataSource).getPassword());
		}
		return result;
	}

}
