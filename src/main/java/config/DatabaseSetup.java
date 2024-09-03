package config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    private static final String CREATE_ORGANIZATION_TABLE = """
        CREATE TABLE IF NOT EXISTS organization (
            id INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR(255) NOT NULL,
            country VARCHAR(50) NOT NULL,
            phone VARCHAR(20),
            email VARCHAR(255)
        )
    """;

    private static final String CREATE_PERSON_TABLE = """
        CREATE TABLE IF NOT EXISTS person (
            id INT PRIMARY KEY AUTO_INCREMENT,
            name VARCHAR(255) NOT NULL,
            country VARCHAR(50) NOT NULL,
            date_of_birth DATE NOT NULL,
            age INT NOT NULL,
            phone VARCHAR(20),
            email VARCHAR(255)
        )
    """;

    private static final String CREATE_PROJECT_TABLE = """
        CREATE TABLE IF NOT EXISTS project (
            id INT PRIMARY KEY AUTO_INCREMENT,
            title VARCHAR(255) NOT NULL,
            description TEXT,
            euid VARCHAR(50) UNIQUE NOT NULL,
            status VARCHAR(20) NOT NULL,
            start_date DATE NOT NULL,
            end_date DATE NOT NULL,
            organizer_id INT,
            FOREIGN KEY (organizer_id) REFERENCES organization(id) ON DELETE SET NULL
        )
    """;

    private static final String CREATE_APPLICATION_TABLE = """
        CREATE TABLE IF NOT EXISTS application (
            id INT PRIMARY KEY AUTO_INCREMENT,
            person_id INT NOT NULL,
            project_id INT NOT NULL,
            status VARCHAR(20) NOT NULL,
            submission_date DATETIME NOT NULL,
            details TEXT,
            FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE,
            FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
        )
    """;

    public static void setupDatabase() {
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            Statement statement = connection.createStatement();

            statement.execute(CREATE_ORGANIZATION_TABLE);
            statement.execute(CREATE_PERSON_TABLE);
            statement.execute(CREATE_PROJECT_TABLE);
            statement.execute(CREATE_APPLICATION_TABLE);

            System.out.println("Database tables created successfully.");

        } catch (SQLException e) {
            System.err.println("Error setting up database tables:");
            e.printStackTrace();
        } finally {
            DatabaseConfiguration.closeDatabaseConnection();
        }
    }

    public static void main(String[] args) {
        setupDatabase();
    }
}