package com.expensetracker;

import com.expensetracker.gui.ExpenseTrackerGUI;
import com.expensetracker.util.DatabaseConnection;

import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Initialize database connection
        try {
            DatabaseConnection.getConnection();
            System.out.println("Database connected successfully");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            System.exit(1);
        }
        
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Error setting look and feel: " + e.getMessage());
        }
        
        // Launch the application
        SwingUtilities.invokeLater(() -> {
            try {
                // Create and display the main window
                ExpenseTrackerGUI expenseTracker = new ExpenseTrackerGUI();
                expenseTracker.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error starting application: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                    null,
                    "Failed to start Expense Tracker: " + e.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
