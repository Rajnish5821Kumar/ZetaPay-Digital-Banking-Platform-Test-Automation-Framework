package com.zetapay.automation.db;

import com.zetapay.automation.utils.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
    
    private static Connection connection;

    public static void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        ConfigReader.get("db.url"),
                        ConfigReader.get("db.user"),
                        ConfigReader.get("db.password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query) {
        connect();
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void initDb() {
        connect();
        try {
            Statement stmt = connection.createStatement();
            // Create table
            stmt.execute("CREATE TABLE IF NOT EXISTS transactions (" +
                    "id INT PRIMARY KEY, " +
                    "status VARCHAR(50), " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
            
            // Insert dummy data
            stmt.execute("MERGE INTO transactions KEY(id) VALUES (1, 'SUCCESS', CURRENT_TIMESTAMP)");
            stmt.execute("MERGE INTO transactions KEY(id) VALUES (2, 'PENDING', CURRENT_TIMESTAMP)");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
