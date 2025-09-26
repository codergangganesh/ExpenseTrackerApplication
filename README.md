# Expense Tracker

A simple desktop application for tracking personal expenses built with Java and SQLite.

## Features

- Add new expenses with description, amount, category, and date
- View all expenses in a tabular format
- Delete existing expenses
- Basic data validation
- Persistent storage using SQLite database

## Prerequisites

- Java 8 or higher
- Maven 3.6.0 or higher
- SQLite JDBC Driver (included in pom.xml)

## Getting Started

1. **Clone the repository**
   ```bash
   git clone [((https://github.com/codergangganesh/ExpenseTrackerApplication.git))]
   cd ExpenseTrackerApplication
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.expensetracker.Main"
   ```

## Project Structure

```
src/main/java/com/expensetracker/
├── Main.java                 # Entry point of the application
├── dao/
│   └── ExpenseDAO.java       # Data Access Object for Expense operations
├── gui/
│   └── ExpenseTrackerGUI.java # Main GUI class
├── model/
│   └── Expense.java          # Expense model class
└── util/
    └── DatabaseConnection.java # Database connection utility
```

## Database Schema

The application uses SQLite with the following schema:

```sql
CREATE TABLE IF NOT EXISTS expenses (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    description TEXT NOT NULL,
    amount REAL NOT NULL,
    category TEXT NOT NULL,
    date TEXT NOT NULL
);
```

## Dependencies

- SQLite JDBC - For SQLite database connectivity
- JUnit 5 - For unit testing (test scope)

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Screenshots

*Screenshots of the application will be added here.*

## Future Enhancements

- Add expense categories management
- Implement data filtering and searching
- Add charts for expense visualization
- Export/Import functionality
- User authentication and multi-user support
#
