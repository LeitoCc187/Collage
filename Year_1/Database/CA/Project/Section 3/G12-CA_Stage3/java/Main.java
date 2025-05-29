import java.util.Scanner; // Import Scanner class for user input

public class Main
{
    public static void main(String[] args)
    {
        // Create a Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Define an array of SQL query strings
        String[] queries =
        {
            // Query 1: List bugs and the developers assigned to them
            "SELECT b.bug_id, b.title, d.name FROM bug b " +
            "JOIN bug_assignment ba ON b.bug_id = ba.bug_id " +
            "JOIN developer d ON ba.developer_id = d.developer_id;",

            // Query 2: Count of bugs per game
            "SELECT g.title, COUNT(b.bug_id) FROM game g " +
            "JOIN bug b ON g.game_id = b.game_id GROUP BY g.title;",

            // Query 3: Count of bugs grouped by severity
            "SELECT severity, COUNT(*) FROM bug GROUP BY severity;",

            // Query 4: Developers assigned to the most recently reported bug
            "SELECT d.name, b.title FROM bug_assignment ba " +
            "JOIN developer d ON ba.developer_id = d.developer_id " +
            "JOIN bug b ON ba.bug_id = b.bug_id " +
            "WHERE b.reported_date = (SELECT MAX(reported_date) FROM bug);",

            // Query 5: List bugs with readable severity levels (using CASE)
            "SELECT title, CASE severity " +
            "WHEN 'Low' THEN 'Minor' " +
            "WHEN 'Medium' THEN 'Moderate' " +
            "WHEN 'High' THEN 'Critical' END AS severity_level FROM bug;",

            // Query 6: Find bugs with 'load' in their title
            "SELECT * FROM bug WHERE title LIKE '%load%';"
        };

        // Define an array of user-friendly descriptions matching each query
        String[] descriptions =
        {
            "1. List bugs and the developers assigned to them",
            "2. Count of bugs per game",
            "3. Count of bugs grouped by severity",
            "4. Developers assigned to the most recently reported bug",
            "5. List bugs with readable severity levels",
            "6. Find bugs with 'load' in their title"
        };

        // Infinite loop to keep showing the menu until the user chooses to exit
        while (true)
        {
            System.out.println("\n=== Bug Tracker Query Menu ===");

            // Print the list of available queries
            for (int i = 0; i < descriptions.length; i++)
            {
                System.out.println(descriptions[i]);
            }

            // Print the exit option
            System.out.println("0. Exit");

            // Prompt user to enter a choice
            System.out.print("Enter the number of the query to run: ");

            // Read user input
            String userInput = input.nextLine();
            int choice;

            try
            {
                // Try to parse the user input into an integer
                choice = Integer.parseInt(userInput);
            }
            catch (NumberFormatException e)
            {
                // If parsing fails, display an error and prompt again
                System.out.println("Please enter a valid number.");
                continue; // Skip to the next loop iteration
            }

            // Handle the user's choice
            if (choice == 0)
            {
                // Exit the application
                System.out.println("Goodbye!");
                break;
            }
            else if (choice > 0 && choice <= queries.length)
            {
                // Run the selected query by passing it to DBCommand class
                DBCommand.runQuery(queries[choice - 1]);
            }
            else
            {
                // If the user entered an invalid number
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Close the Scanner to avoid resource leaks
        input.close();
    }
}