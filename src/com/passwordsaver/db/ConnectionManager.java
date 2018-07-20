package com.passwordsaver.db;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnectionManager {
	private final static String FAILED_REGISTRATE_DRIVER = "Failed to registrate JDBC Driver";
	private final static String FAILED_CREATE_CONNECTION = "Failed to create connection";
	private final static String FAILED_CLOSE_CONNECTION = "Failed to close connection";
	private final static String FAILED_CONNECTION = "Connection faild";
	
	private static volatile ConnectionManager instance = null;
	
	private DataSource dataSource;
	private final Map<Long, Connection> connections;
	
	private ConnectionManager() {
		this.connections = new HashMap<Long, Connection>();
	}
	public static ConnectionManager getInstance() {
		return getInstance(null);
	}
	public static ConnectionManager getInstance(DataSource dataSource) {
		if(instance == null) {
			synchronized(ConnectionManager.class) {
				if(instance==null) {
					instance = new ConnectionManager(); 
				}
			}
		}
		instance.checkStatus(dataSource);
		return instance;
	}
	private void checkStatus(DataSource dataSource) {
		if(dataSource==null) {
			if(getDataSource()==null) {
				setDataSource(DataSourceRepository.getDefault());
			}
		}else if(getDataSource()==null || !getDataSource().equals(dataSource)) {
			setDataSource(dataSource);
		}
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	private void setDataSource(DataSource dataSource) {
		synchronized(ConnectionManager.class) {
			this.dataSource = dataSource;
			registerDriver();
			closeAllConnections();
		}
	}
	private void registerDriver() {
		try {
			DriverManager.registerDriver(this.dataSource.getJdbcDriver());
		} catch (SQLException e) {
			throw new RuntimeException(ConnectionManager.FAILED_REGISTRATE_DRIVER, e);
		}
	}
	private Map<Long, Connection> getAllConnections(){
		return this.connections;
	}
	private static void closeAllConnections() {
		if(instance!=null) {
			for(Long id: instance.getAllConnections().keySet()) {
				if(instance.getAllConnections().get(id) != null) {
					try {
						instance.getAllConnections().get(id).close();
					} catch (SQLException e) {
						throw new RuntimeException(FAILED_CLOSE_CONNECTION,e);
					}
					instance.getAllConnections().put(id, null);
				}
			}
		}
	}
	private void addConnection(Connection connection) {
		connections.put(Thread.currentThread().getId(), connection);
	}
	public Connection getConnection(){
		Connection connection = getAllConnections().get(Thread.currentThread().getId());
		if(connection==null) {
			try {
				connection = DriverManager.getConnection(getDataSource().getConnectionURL()
														,getDataSource().getUsername() 
														,getDataSource().getPassword());
			} catch (SQLException e) {
				throw new RuntimeException(ConnectionManager.FAILED_CREATE_CONNECTION, e);
			}
			addConnection(connection);
		}
		return connection;
	}
	public void beginTransaction() {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(ConnectionManager.FAILED_CONNECTION,e);
		}
	}
	public void commitTransaction() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			throw new RuntimeException(ConnectionManager.FAILED_CONNECTION, e);
		}
	}
	public void rollbackTransaction() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			throw new RuntimeException(ConnectionManager.FAILED_CONNECTION, e);
		}
	}
}
