package com.passwordsaver.db;
import java.sql.Driver;
import java.sql.SQLException;
public class DataSourceRepository {
	private static final String FAILED_JDBC_DRIVER = "Failed to create JDBC driver";
	
	private DataSourceRepository() {
		
	}
	public static DataSource getDefault() {
		return getMySqlLocalHost();
	}
	public static DataSource getMySqlLocalHost() {
		Driver sqlDriver;
		try {
			sqlDriver = new com.mysql.jdbc.Driver();
		}catch(SQLException e) {
			throw new RuntimeException(FAILED_JDBC_DRIVER);
		}
		return new DataSource(sqlDriver
				,"jdbc:mysql://localhost:3306/psdb"
				,"root"
				,"root");
	}
	
}
