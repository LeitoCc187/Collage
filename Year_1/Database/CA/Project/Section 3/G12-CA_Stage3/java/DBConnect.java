import java.sql.Connection; // Import Connection class for database connections
import java.sql.DriverManager; // Import DriverManager to manage database drivers
import java.sql.SQLException; // Import SQLException to handle SQL-related errors

// Class responsible for connecting to the SQLite database
public class DBConnect
{
    // Static method to create and return a Connection object
    public static Connection connect()
    {
        // Define the connection URL pointing to the SQLite database file
        String url = "jdbc:sqlite:db/database.sqlite";

        try
        {
            // Attempt to establish a connection to the database
            return DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            // If a connection error occurs, print an error message
            System.out.println("Connection failed: " + e.getMessage());
            return null; // Return null if connection fails
        }
    }
}
