package com.softserve.edu.items.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {
	private final static String FAILED_REGISTRATE_DRIVER = "Failed to Registrate JDBC Driver";
	private final static String FAILED_CREATE_CONNECTION = "Failed to Create Connection";
	private final static String FAILED_CLOSE_CONNECTION = "Failed to Close Connection";
	private final static String FAILED_CONNECTION = "Connection Failed";

	//
	private static volatile ConnectionManager instance = null;
	//
	private DataSource dataSource;
	private final Map<Long, Connection> connections;

	private ConnectionManager() {
		this.connections = new HashMap<Long, Connection>();
	}

	public static ConnectionManager getInstance() {
		return getInstance(null);
	}

	public static ConnectionManager getInstance(DataSource dataSource) {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null) {
					instance = new ConnectionManager();
				}
			}
		}
		instance.checkStatus(dataSource);
		return instance;
	}

	private void checkStatus(DataSource dataSource) {//- na poshatkyzabezpet4ye zberigannayatablv tomy g vugliadi
		/*-		dataSource		this.dataSource		    Action
		 * 			null			null				create default
		 * 			null			not null			nothing
		 * 			not null		null				save dataSource
		 * 			not null		not null			if equals then nothing 
		 */
		if (dataSource == null) {
			if (getDataSource() == null) {
				setDataSource(DataSourceRepository.getDefault());
			}
		} else if ((getDataSource() == null) || (!getDataSource().equals(dataSource))) { //2 ymova ozna4 ne=
			setDataSource(dataSource);
		}
	}

	private void setDataSource(DataSource dataSource) {
		synchronized (ConnectionManager.class) {
			this.dataSource = dataSource;
			registerDriver();
			closeAllConnections();
		}
	}

	private DataSource getDataSource() {
		return dataSource;
	}

	private void registerDriver() {
		try {
			DriverManager.registerDriver(getDataSource().getJdbcDriver());
		} catch (SQLException e) {
			throw new RuntimeException(FAILED_REGISTRATE_DRIVER, e);
		}
	}

	private Map<Long, Connection> getAllConnections() {
		return this.connections;
	}

	private void addConnection(Connection connection) {
		getAllConnections().put(Thread.currentThread().getId(), connection);
	}

	public Connection getConnection() {
		Connection connection = getAllConnections().get(Thread.currentThread().getId());
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(getDataSource().getConnectionUrl(),
						getDataSource().getUsername(), getDataSource().getPassword());
			} catch (SQLException e) {
				throw new RuntimeException(FAILED_CREATE_CONNECTION, e);
			}
			addConnection(connection);
		}
		return connection;
	}

	public void beginTransaction() {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(FAILED_CONNECTION, e);
		}
	}

	public void commitTransaction() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(FAILED_CONNECTION, e);
		}
	}

	public void rollbackTransaction() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(FAILED_CONNECTION, e);
		}
	}

	public static void closeAllConnections() {
		if (instance != null) {
			for (Long key : instance.getAllConnections().keySet()) {
				if (instance.getAllConnections().get(key) != null) {
					try {
						instance.getAllConnections().get(key).close();
					} catch (SQLException e) {
						throw new RuntimeException(FAILED_CLOSE_CONNECTION, e);
					}
					instance.getAllConnections().put(key, null);
				}
			}
		}
	}

}
