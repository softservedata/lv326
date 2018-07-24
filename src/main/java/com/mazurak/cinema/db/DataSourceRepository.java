package com.mazurak.cinema.db;

import java.sql.Driver;
import java.sql.SQLException;

public final class DataSourceRepository {
	
	private DataSourceRepository() {
	}

	public static DataSource getDataSource() {
		Driver driver = null;
		try {
			driver = new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			System.out.println(ConnectionValidatorMessage.FAILED_REGISTRATE_DRIVER);
			e.printStackTrace();
		}
		return new DataSource(driver, 
				DBConstant.PROTOCOL,
				DBConstant.PRODUCTNAME,
				DBConstant.CONNECTIONDETAILS,
				DBConstant.USERNAME,
				DBConstant.PASSWORD);
	}
}
