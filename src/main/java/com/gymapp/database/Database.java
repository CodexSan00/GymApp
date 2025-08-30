package com.gymapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conn;

    public static Connection getConnection() throws SQLException{
        if(conn == null || conn.isClosed()){
            //Read user & password from env variables
            // Do "set DB_USER=YourUser" in CMD, same for URL or PASS (without:")
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");

            if( url == null || user == null || password == null){
                throw new RuntimeException("Please define env variable: DB_URL, DB_USER & DB_PASS");
            }
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }
    public static void closeConnection() throws SQLException{
        if(conn != null && !conn.isClosed()){
            conn.close();
        }
    }
}
