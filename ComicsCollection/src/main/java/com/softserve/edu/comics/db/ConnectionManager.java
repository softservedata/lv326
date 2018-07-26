package com.softserve.edu.comics.db;

import com.softserve.edu.comics.constants.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {

    private DataSource dataSource;
    private final Map<Long, Connection> connections;
    private static volatile ConnectionManager connectionInstance = null;

    private ConnectionManager() {
        this.connections = new HashMap<Long, Connection>();
    }

    public static ConnectionManager getConnectionInstance() {
        return getConnectionInstance(null);
    }

    public static ConnectionManager getConnectionInstance(DataSource dataSource) {
        if (connectionInstance == null) {
            synchronized (ConnectionManager.class) {
                if (connectionInstance == null) {
                    connectionInstance = new ConnectionManager();
                }
            }
        }
        connectionInstance.checkStatus(dataSource);
        return connectionInstance;
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
                setDataSource(DataSourceRepository.getDataBase());
            }
        } else if ((getDataSource() == null) || (!getDataSource().equals(dataSource))) {
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
            throw new RuntimeException(Message.FAILED_REGISTRATE_DRIVER, e);
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
                throw new RuntimeException(Message.FAILED_CREATE_CONNECTION, e);
            }
            addConnection(connection);
        }
        return connection;
    }

    // ------ TRANSACTION ------
    public void beginTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(Message.FAILED_CONNECTION, e);
        }
    }

    public void commitTransaction() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            throw new RuntimeException(Message.FAILED_CONNECTION, e);
        }
    }

    public void rollbackTransaction() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new RuntimeException(Message.FAILED_CONNECTION, e);
        }
    }

    //CLOSE ALL
    public static void closeAllConnections() {
        if (connectionInstance != null) {
            for (Long key : connectionInstance.getAllConnections().keySet()) {
                if (connectionInstance.getAllConnections().get(key) != null) {
                    try {
                        connectionInstance.getAllConnections().get(key).close();
                    } catch (SQLException e) {
                        throw new RuntimeException(Message.FAILED_CLOSE_CONNECTION, e);
                    }
                    connectionInstance.getAllConnections().put(key, null);
                }
            }
        }
    }

}
