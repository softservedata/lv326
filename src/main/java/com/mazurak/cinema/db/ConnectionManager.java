package com.mazurak.cinema.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {

	private static volatile ConnectionManager instance = null;
	private DataSource dataSource;
	private Map<Long, Connection> connections;

	private ConnectionManager() {
		this.connections = new HashMap<Long, Connection>();
	}

	public static ConnectionManager getInstance() {
		return getInstance(null);
	}

	public static ConnectionManager getInstance(DataSource dataSource) {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null)
					instance = new ConnectionManager();
			}
		}
		instance.checkStatus(dataSource);
		return instance;
	}

	private void checkStatus(DataSource dataSource) {
		/*-		dataSource		this.dataSource		    Action
		 * 			null			null				create default
		 * 			null			not null			nothing
		 * 			not null		null				save dataSource
		 * 			not null		not null			if equals then nothing 
		 */
		if (dataSource == null) {
			if (getDataSource() == null) {
				setDataSource(DataSourceRepository.getDataSource());
			}
		} else if ((getDataSource() == null) || (!getDataSource().equals(dataSource))) {
			setDataSource(dataSource);
		}
	}

	private DataSource getDataSource() {
		return dataSource;
	}

	// Setter new Driver
	private void setDataSource(DataSource dataSource) {
		synchronized (ConnectionManager.class) {
			this.dataSource = dataSource;
			registerDriver();
			closeAllConnection();

		}
	}
	private void registerDriver() {
		try {
			DriverManager.registerDriver(getDataSource().getDriver());
		} catch (SQLException e) {
			System.out.println(ConnectionValidatorMessage.FAILED_REGISTRATE_DRIVER);
			e.printStackTrace();
		}
	}

	private void addConnection(Connection connection) {
		getAllConnections().put(Thread.currentThread().getId(), connection);
	}

	private Map<Long, Connection> getAllConnections() {
		return this.connections;
	}

	public Connection getConnection() {
		Connection connection = getAllConnections().get(Thread.currentThread().getId());
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(
						getDataSource().getProtocol() + getDataSource().getProductName()
								+ getDataSource().getConnectionDetails(),
						getDataSource().getUserName(), getDataSource().getPassword());
			} catch (SQLException e) {
				System.out.println(ConnectionValidatorMessage.FAILED_CREATE_CONNECTION);
				e.printStackTrace();
			}
			addConnection(connection);
		}

		return connection;
	}

	public void closeAllConnection() {
		if (instance != null) {
			for (Long key : instance.getAllConnections().keySet()) {
				if (instance != null) {
					try {
						instance.getAllConnections().get(key).close();
					} catch (SQLException e) {
						System.out.println(ConnectionValidatorMessage.FAILED_CLOSE_CONNECTION);
						e.printStackTrace();
					}
					instance.getAllConnections().put(key, null);
				}
			}
		}
	}
}
