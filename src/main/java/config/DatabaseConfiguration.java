package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3307/erasmus_project";
    private static final String USER = "root";
    private static final String PASSWORD = "erasmus";
    private static Connection connection;

    private DatabaseConfiguration() {
    }

    public static Connection getDatabaseConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to database.", e);
        }
        return connection;
    }

    public static void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}