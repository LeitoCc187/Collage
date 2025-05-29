import java.sql.*; // Import all classes from java.sql package

public class DBCommand
{
    // Method to run a SQL query and print the results
    public static void runQuery(String sql)
    {
        // Try-with-resources block to automatically close Connection, Statement, and ResultSet
        try (
                Connection conn = DBConnect.connect();    // Establish a connection to the SQLite database
                Statement stmt = conn.createStatement();  // Create a SQL statement object
                ResultSet rs = stmt.executeQuery(sql)      // Execute the SQL query and get the result set
            )
        {
            // Format and print the results of the query
            DBOutputFormatter.printResults(rs);
        }
        catch (SQLException e)
        {
            // If any SQL error occurs, catch it and display a friendly error message
            System.out.println("Query failed: " + e.getMessage());
        }
    }
}
