
/**
 * Matthew Rozendaal
 * Module 9_2
 * Creates a database if not exists,
 * Creates a table if not exists
 * Inserts 3 rows of data into the table
 * Executes 4 queries:
 * 1. Current user
 * 2. Current database
 * 3. Tables in the current database
 * 4. All data in address33
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MySQLQueries {

    // Base URL without a database name so we can connect before the DB exists
    private static final String BASE_URL = "jdbc:mysql://127.0.0.1:3306/";

    // Name of the database to create/use
    private static final String DB_NAME = "databasedb";

    // Credentials for the MySQL user
    private static final String USER = "student1";
    private static final String PASSWORD = "pass"; // While I could have used an environment variable,
    // I wanted to keep this simple.

    public static void main(String[] args) {

        // Explicitly load the MySQL JDBC driver class.
        // Required for some environments where it isn't auto-detected.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        // Step 1: Connect to MySQL without specifying a database.
        // This lets us run CREATE DATABASE safely even if databasedb doesn't exist yet.
        try (Connection init = DriverManager.getConnection(BASE_URL, USER, PASSWORD); Statement ist = init.createStatement()) {

            // Create the database only if it doesn't already exist
            ist.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("=== Database Setup ===");
            System.out.println("Database '" + DB_NAME + "' ready.\n");

        } catch (Exception e) {
            System.err.println("Failed to create database: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Step 2: Reconnect now targeting the databasedb database directly.
        // try-with-resources ensures the connection and statement are closed automatically.
        try (Connection conn = DriverManager.getConnection(BASE_URL + DB_NAME, USER, PASSWORD); Statement stmt = conn.createStatement()) {

            // --- Table Setup ---
            // Drop address33 if it already exists so we start fresh each run
            System.out.println("=== Setting Up Table ===");
            stmt.executeUpdate("DROP TABLE IF EXISTS address33");

            // Create the address33 table with an int primary key and six varchar columns
            stmt.executeUpdate(
                    "CREATE TABLE address33 ("
                    + "ID int PRIMARY KEY, "
                    + "LASTNAME varchar(40), "
                    + "FIRSTNAME varchar(40), "
                    + "STREET varchar(40), "
                    + "CITY varchar(40), "
                    + "STATE varchar(40), "
                    + "ZIP varchar(40))"
            );

            // Insert three sample rows into address33
            stmt.executeUpdate("INSERT INTO address33 VALUES(24,'Lou','Woods','1919 Bluewing Circle','Bellevue','NE','68123')");
            stmt.executeUpdate("INSERT INTO address33 VALUES(25,'Lou','Woods','1919 Bluewing Circle','Bellevue','NE','68123')");
            stmt.executeUpdate("INSERT INTO address33 VALUES(26,'Lou','Woods','1919 Bluewing Circle','Bellevue','NE','68123')");
            System.out.println("Table address33 created and populated.\n");

            // --- Query 1: Current User ---
            // SELECT CURRENT_USER() returns the authenticated MySQL user (user@host)
            System.out.println("=== Current User ===");
            try (ResultSet rs = stmt.executeQuery("SELECT CURRENT_USER()")) {
                if (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }

            // --- Query 2: Current Database ---
            // SELECT DATABASE() returns the name of the currently selected database
            System.out.println("\n=== Current Database ===");
            try (ResultSet rs = stmt.executeQuery("SELECT DATABASE()")) {
                if (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }

            // --- Query 3: Tables in the Database ---
            // SHOW TABLES lists all tables in the current database
            System.out.println("\n=== Database Tables ===");
            try (ResultSet rs = stmt.executeQuery("SHOW TABLES")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }

            // --- Query 4: All Rows in address33 ---
            // SELECT * retrieves every column and row from address33
            System.out.println("\n=== All Data in address33 ===");
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM address33")) {

                // ResultSetMetaData lets us read column names and count dynamically
                ResultSetMetaData meta = rs.getMetaData();
                int colCount = meta.getColumnCount();

                // Print column header row, each column left-padded to 20 characters
                StringBuilder header = new StringBuilder();
                for (int i = 1; i <= colCount; i++) {
                    header.append(String.format("%-20s", meta.getColumnName(i)));
                }
                System.out.println(header);

                // Print a separator line scaled to the number of columns
                System.out.println("-".repeat(colCount * 20));

                // Iterate through each row and print all column values
                while (rs.next()) {
                    StringBuilder row = new StringBuilder();
                    for (int i = 1; i <= colCount; i++) {
                        row.append(String.format("%-20s", rs.getString(i)));
                    }
                    System.out.println(row);
                }
            }

        } catch (Exception e) {
            // Catch any SQL or connection errors and print details
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
