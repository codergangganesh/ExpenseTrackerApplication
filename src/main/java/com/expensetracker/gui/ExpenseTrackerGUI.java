package com.expensetracker.gui;

import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.model.Expense;
import com.expensetracker.util.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExpenseTrackerGUI extends JFrame {
    private final ExpenseDAO expenseDAO;
    private DefaultTableModel tableModel;
    private JTable expenseTable;
    private JTextField titleField, amountField, categoryField;
    private JTextArea descriptionArea;
    private JComboBox<String> categoryComboBox;

    public ExpenseTrackerGUI() {
        // Initialize DAO
        expenseDAO = new ExpenseDAO();
        
        // Set up the main frame
        setTitle("Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create form panel
        JPanel formPanel = createFormPanel();
        mainPanel.add(formPanel, BorderLayout.NORTH);
        
        // Create table panel
        JPanel tablePanel = createTablePanel();
        mainPanel.add(new JScrollPane(tablePanel), BorderLayout.CENTER);
        
        // Add main panel to frame
        add(mainPanel);
        
        // Load existing expenses
        loadExpenses();
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Add New Expense"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Title:"), gbc);
        
        gbc.gridx = 1;
        titleField = new JTextField(20);
        formPanel.add(titleField, gbc);
        
        // Amount
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Amount:"), gbc);
        
        gbc.gridx = 1;
        amountField = new JTextField(10);
        formPanel.add(amountField, gbc);
        
        // Category
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Category:"), gbc);
        
        gbc.gridx = 1;
        String[] categories = {"Food", "Transportation", "Housing", "Utilities", "Entertainment", "Shopping", "Other"};
        categoryComboBox = new JComboBox<>(categories);
        formPanel.add(categoryComboBox, gbc);
        
        // Description
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Description:"), gbc);
        
        gbc.gridx = 1;
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        formPanel.add(new JScrollPane(descriptionArea), gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton addButton = new JButton("Add Expense");
        addButton.addActionListener(e -> addExpense());
        buttonPanel.add(addButton);
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearForm());
        buttonPanel.add(clearButton);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(buttonPanel, gbc);
        
        return formPanel;
    }
    
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        
        // Create table model
        String[] columnNames = {"ID", "Title", "Amount", "Category", "Date", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        // Create table
        expenseTable = new JTable(tableModel);
        expenseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        expenseTable.getTableHeader().setReorderingAllowed(false);
        
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(expenseTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        // Add delete button
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.addActionListener(e -> deleteSelectedExpense());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(deleteButton);
        
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return tablePanel;
    }
    
    private void loadExpenses() {
        // Clear existing rows
        tableModel.setRowCount(0);
        
        // Get all expenses from database
        List<Expense> expenses = expenseDAO.getAllExpenses();
        
        // Add expenses to table
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (Expense expense : expenses) {
            Object[] row = {
                expense.getId(),
                expense.getTitle(),
                String.format("$%.2f", expense.getAmount()),
                expense.getCategory(),
                expense.getExpenseDate().format(formatter),
                expense.getDescription()
            };
            tableModel.addRow(row);
        }
    }
    
    private void addExpense() {
        try {
            // Get input values
            String title = titleField.getText().trim();
            String description = descriptionArea.getText().trim();
            double amount = Double.parseDouble(amountField.getText().trim());
            String category = (String) categoryComboBox.getSelectedItem();
            
            // Validate input
            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create new expense
            Expense expense = new Expense(title, description, amount, category);
            expenseDAO.addExpense(expense);
            
            // Refresh table
            loadExpenses();
            
            // Clear form
            clearForm();
            
            // Show success message
            JOptionPane.showMessageDialog(this, "Expense added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding expense: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteSelectedExpense() {
        int selectedRow = expenseTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an expense to delete", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Are you sure you want to delete this expense?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            
            if (expenseDAO.deleteExpense(id)) {
                loadExpenses();
                JOptionPane.showMessageDialog(this, "Expense deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error deleting expense", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void clearForm() {
        titleField.setText("");
        amountField.setText("");
        descriptionArea.setText("");
        categoryComboBox.setSelectedIndex(0);
        titleField.requestFocus();
    }
}
