package CurrencyConverterApp.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDbConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/currency_converter";
    private static final String USER = "appuser";
    private static final String PASSWORD = "dev@123";

    private static MariaDbConnection instance;
    private Connection connection;

    // Private constructor for Singleton pattern
    private MariaDbConnection() {
    }

    public static MariaDbConnection getInstance() {
        if (instance == null) {
            instance = new MariaDbConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load MariaDB JDBC driver
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established successfully.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MariaDB JDBC Driver not found. Add it to your pom.xml", e);
            } catch (SQLException e) {
                System.err.println("Failed to connect to database: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    public boolean testConnection() {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
