package com.expensetracker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:expensetracker.db";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS expenses (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "title TEXT NOT NULL, " +
            "description TEXT, " +
            "amount REAL NOT NULL, " +
            "category TEXT NOT NULL, " +
            "expense_date TIMESTAMP NOT NULL, " +
            "created_at TIMESTAMP NOT NULL, " +
            "updated_at TIMESTAMP NOT NULL" +
            ")";

    static {
        try (Connection conn = getConnection()) {
            conn.createStatement().execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            System.err.println("Error creating database tables: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite JDBC driver not found", e);
        }
    }
}
