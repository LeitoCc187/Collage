import java.sql.*; // Import all classes from java.sql (including ResultSet and ResultSetMetaData)

/**
 * DBOutputFormatter class
 * Responsible for formatting and printing the results of SQL queries.
 */
public class DBOutputFormatter
{
    /**
     * Prints the contents of a ResultSet in a formatted table style.
     * 
     * rs The ResultSet to be printed
     * throws SQLException if a database access error occurs
     */
    public static void printResults(ResultSet rs) throws SQLException 
    {
        // Get metadata about the ResultSet (such as number of columns and column names)
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount(); // Find out how many columns there are

        // Print column headers
        for (int i = 1 ; i <= cols ; i++)
        {
            System.out.printf("%-30s", meta.getColumnName(i)); // Print each column name with fixed width
        }

        // Print a separator line
        System.out.println("\n" + "-".repeat(30 * cols));

        // Loop through each row in the ResultSet
        while (rs.next())
        {
            // For each column, print the value for the current row
            for (int i = 1; i <= cols; i++)
            {
                System.out.printf("%-30s", rs.getString(i)); // Print each value with fixed width
            }
            System.out.println(); // Move to the next line after each row
        }
    }
}
