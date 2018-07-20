package com.passwordsaver.db;
import java.sql.Driver;

public final class DataSource {
	
	private Driver jdbcDriver;
	private String connectionURL;
	private String username;
	private String password;
	
	public DataSource(Driver jdbcDriver, String connectionURL, String username, String password) {
		this.jdbcDriver = jdbcDriver;
		this.connectionURL = connectionURL;
		this.username = username;
		this.password = password;
	}
	
	public void setJdbcDriver(Driver jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}
	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Driver getJdbcDriver() {
		return jdbcDriver;
	}
	public String getConnectionURL() {
		return connectionURL;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword() {
		return password;
	}
	
	@Override
	public boolean equals(Object dataSource) {
		boolean result = false;
		if(dataSource instanceof DataSource) {
			result = getJdbcDriver().getClass().getName().equals(((DataSource)dataSource).getJdbcDriver().getClass().getName())
					&&getConnectionURL().equals(((DataSource)dataSource).getConnectionURL())
					&&getUsername().equals(((DataSource)dataSource).getPassword())
					&&getPassword().equals(((DataSource)dataSource).getUsername());
		}
		return result;
	}
}
