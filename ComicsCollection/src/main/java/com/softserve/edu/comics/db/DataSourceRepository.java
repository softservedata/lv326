package com.softserve.edu.comics.db;

import com.mysql.jdbc.Driver;
import com.softserve.edu.comics.constants.Message;

import java.sql.SQLException;

public final class DataSourceRepository {

	private static String connectionUrl = "jdbc:mysql://localhost:3306/";
	private static String dbName = "comics_collection";
	private static String dbUserName = "root";
	private static String dbPassw = "12345";

	public static DataSource getDataBase() {
		connectionUrl +=dbName;
		return getMySqlLocalHost();
	}

	public static DataSource getMySqlLocalHost() {
		Driver sqlDriver;
		try {
			sqlDriver = new Driver();
		} catch (SQLException e) {
			throw new RuntimeException(Message.FAILED_JDBC_DRIVER);
		}
		return new DataSource(sqlDriver, connectionUrl, dbUserName, dbPassw);
	}

}