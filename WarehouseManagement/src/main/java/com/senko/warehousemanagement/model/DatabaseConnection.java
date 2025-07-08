
package com.senko.warehousemanagement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:doanis210";
    private static final String USER = "system";
    private static final String PASSWORD = "123";

    // Phương thức lấy kết nối
    public static Connection getConnection() throws SQLException {
        try{
        return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch(SQLException e){
             System.err.println("Database connection failed: " + e.getMessage());
                throw e;
        }
    }
    
}
