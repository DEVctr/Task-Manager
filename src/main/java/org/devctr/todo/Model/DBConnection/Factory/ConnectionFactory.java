package org.devctr.todo.Model.DBConnection.Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final String url;
    private Connection connection;

    public ConnectionFactory() {
        this.url = "jdbc:postgresql://localhost:5432/todo";
    }

    public Connection getConnection() {
        String username = "postgres";
        String password = "databaseii";

        if (connection == null) {
            try {

                this.connection = DriverManager.getConnection(url, username, password);

                return connection;
            } catch (SQLException e) {
                System.out.println("Connection error: " + e.getMessage());
            }
        } else {
            return connection;
        }
        return null;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Close connection error: " + e.getMessage());
        }
    }
}
