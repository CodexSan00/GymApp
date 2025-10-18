package com.gymapp.database;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection conn;
    private static final Dotenv dotenv = Dotenv.load();
    public static Connection getConnection() throws SQLException{
        if(conn == null || conn.isClosed()){
            //Read user & password from env variables
            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASS");

            if( url == null || user == null || password == null){
                throw new RuntimeException("Missing DB_URL, DB_USER, or DB_PASS in .env");
            }
            try {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to database successfully...");
            } catch (SQLException e) {
                System.err.println("Database connection failed: " + e.getMessage());
                throw e;
            }
        }
        return conn;
    }
    public static void closeConnection() throws SQLException{
        if(conn != null && !conn.isClosed()){
            conn.close();
        }
    }
}
