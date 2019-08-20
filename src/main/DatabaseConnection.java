package main;

import java.sql.*;

public class DatabaseConnection {
    
    private String URL = "";
    private String USER = "";
    private String PASS = "";
    
    
    public DatabaseConnection(String URL,String USER,String PASS){
        this.URL = URL;
        this.USER = USER;
        this.PASS = PASS;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(this.URL,this.USER, this.PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}