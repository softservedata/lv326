package com.softserve.edu.project.db;




import com.softserve.edu.project.consts.DBConst;

import java.sql.*;

public final class DataSourceRepository {
    public final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";
    public final static String FAILED_CREATE_DB ="Data base was not created ";
    private DataSourceRepository() {
    }

    public static DataSource getDefault() {
        return getMySqlLocalHost();
    }

    public static DataSource getMySqlLocalHost() {
        Driver sqlDriver = null;
        try {
            sqlDriver = new com.mysql.jdbc.Driver();
        } catch (SQLException e) {
           System.out.print(FAILED_JDBC_DRIVER +e);
        }
        if(checkDBExists("myteams")==false){
              createDBTables();
        }
        return new DataSource(sqlDriver,
                "jdbc:mysql://localhost:3306/myteams?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");
    }
    public static boolean checkDBExists(String dbName){

        try{
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","root");
            ResultSet resultSet = conn.getMetaData().getCatalogs();
            //iterate each catalog in the ResultSet
            while (resultSet.next()) {
                // Get the database name, which is at position 1
                String databaseName = resultSet.getString(1);
                if(dbName.equalsIgnoreCase(databaseName)){
                    return true;
                }
            }
            resultSet.close();

        }
        catch(SQLException e){
            System.out.print(FAILED_JDBC_DRIVER +e);
        }

        return false;
    }
    public static void createDBTables(){
        try {
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","root");
           Statement st=conn.createStatement();
            st.executeUpdate(DBConst.CREATE_DB);
            st.executeUpdate(DBConst.CREATE_TABLE_USERS);
            st.executeUpdate(DBConst.CREATE_TABLE_ROLES);
            st.executeUpdate(DBConst.CREATE_TABLE_TEAMS);
            st.executeUpdate(DBConst.CREATE_TABLE_USER_HAS_TEAMS);
            st.close();


        } catch (SQLException e) {
            System.out.println(FAILED_CREATE_DB+e);
        }
    }

}
