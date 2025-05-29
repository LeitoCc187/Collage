package coles.project;

import java.util.Scanner;

public class MainApp
{
    public static void main(String[] args)
    {
        // Initialize the scanner for user input and create a DockerSystemUser instance
        Scanner scanner = new Scanner(System.in);
        DockerSystemUser dockerSystemUser = new DockerSystemUser();
    
        // Ask the user if they want to load sample data from file
        System.out.print("\n\n\n\n\nIf you load sample data the Storage file wont be read from last time the progarm was run \nDo you want to load sample Docker systems from file? (yes/no): ");
        String loadChoice = scanner.nextLine().trim().toLowerCase();
        
        // Load data from the appropriate file based on the user's input
        if (loadChoice.equals("yes"))
        {
            dockerSystemUser.loadFromFile("data/data.txt");
        }
        else
        {
            // Load default storage if the user doesn't want sample data
            dockerSystemUser.loadFromFile("data/storage.txt");
        }

        boolean running = true;

        // Prompt user to log into a Docker system before accessing any menu
        while (!dockerSystemUser.isLoggedIn())
        {
            System.out.println("\n\n\nWelcome to Docker Game Server Manager!");
            System.out.println("1. Log into an existing Docker system");
            System.out.println("2. Create a new Docker system");
            System.out.println("3. Display all Docker systems");
            System.out.println("4. Wipe Storage");
            System.out.print("Enter your choice: ");
            int loginChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (loginChoice == 1)
            {
                // Log into an existing Docker system
                System.out.print("Enter Docker system name to log into: ");
                String dockerName = scanner.nextLine();
                if (dockerSystemUser.login(dockerName))
                {
                    System.out.println("Logged into Docker system: " + dockerName);
                }
                else
                {
                    System.out.println("Docker system not found. Please try again.");
                }
            }
            else if (loginChoice == 2)
            {
                // Create a new Docker system
                System.out.print("Enter new Docker system name: ");
                String dockerName = scanner.nextLine();
                System.out.print("Enter max capacity for the Docker system: ");
                int capacity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter location for the Docker system: ");
                String location = scanner.nextLine();
                dockerSystemUser.createDockerSystem(dockerName, capacity, location);
            }
            else if (loginChoice == 3)
            {
                // Display all Docker systems
                dockerSystemUser.displayDockerSystems();
            }
            else if (loginChoice == 4)
            {
                // Wipe storage by clearing data and resetting the Docker systems
                dockerSystemUser.wipeStorage("data/storage.txt");
            }
            else
            {
                // Invalid choice handling
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Once logged in, display the main menu for managing the Docker system
        DockerSystem dockerSystem = dockerSystemUser.getCurrentDockerSystem();

        while (running)
        {
            System.out.println("\n=== Docker Game Server Manager ===");
            System.out.println("1. Add a new server");
            System.out.println("2. Display all servers");
            System.out.println("3. Manage a server");
            System.out.println("4. Remove a server");
            System.out.println("5. Count total servers");
            System.out.println("6. Check if two servers are equal");
            System.out.println("7. Compare two servers");
            System.out.println("8. Log out and exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1)
            {
                addServer(scanner, dockerSystem);  // Add a new server
            }
            else if (choice == 2)
            {
                dockerSystem.displayServers();  // Display all servers in the Docker system
            }
            else if (choice == 3)
            {
                manageServerMenu(scanner, dockerSystem);  // Manage a server
            }
            else if (choice == 4)
            {
                removeServer(scanner, dockerSystem);  // Remove a server
            }
            else if (choice == 5)
            {
                // Count and display the total number of servers in the Docker system
                System.out.println("Total servers: " + dockerSystem.countServers());
            }
            else if (choice == 6)
            {
                checkServerEquality(scanner, dockerSystem);  // Check if two servers are equal
            }
            else if (choice == 7)
            {
                compareServers(scanner, dockerSystem);  // Compare two servers
            }
            else if (choice == 8)
            {
                // Log out and save the current Docker system data before exiting
                running = false;
                dockerSystemUser.saveToFile("data/storage.txt");
                System.out.println("\nLogging out and exiting... Goodbye!");
            }
            else
            {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();  // Close the scanner resource
    }

    // Method to add a new server to the Docker system
    private static void addServer(Scanner scanner, DockerSystem dockerSystem)
    {
        System.out.print("Enter server name: ");
        String name = scanner.nextLine();
        System.out.print("Enter RAM (MB): ");
        int ram = scanner.nextInt();
        System.out.print("Enter CPU cores: ");
        int cpuCores = scanner.nextInt();
        System.out.print("Enter max players: ");
        int maxPlayers = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter game version: ");
        String version = scanner.nextLine();

        // Create a new server instance and add it to the Docker system
        GameServer newServer = new GameServer(name, ram, cpuCores, maxPlayers, version);
        dockerSystem.addServer(newServer);
        System.out.println("Server added successfully!");
    }

    // Method to remove a server from the Docker system
    private static void removeServer(Scanner scanner, DockerSystem dockerSystem)
    {
        System.out.print("Enter server name to remove: ");
        String name = scanner.nextLine();
        dockerSystem.removeServer(name);  // Attempt to remove the server
        System.out.println("Server removed (if it existed).");
    }

    // Method to manage a specific server's settings
    private static void manageServerMenu(Scanner scanner, DockerSystem dockerSystem)
    {
        System.out.print("Enter server name to manage: ");
        String name = scanner.nextLine();
        GameServer server = dockerSystem.findServerByName(name);

        if (server == null)
        {
            System.out.println("Server not found.");
            return;
        }

        // Loop for server management options
        boolean managing = true;
        while (managing)
        {
            System.out.println("\n--- Managing Server: " + server.getServerName() + " ---");
            System.out.println("1. Start Server");
            System.out.println("2. Stop Server");
            System.out.println("3. Restart Server");
            System.out.println("4. View Server Info");
            System.out.println("5. Update Server Settings");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1)
            {
                server.start();  // Start the server
            }
            else if (choice == 2)
            {
                server.stop();  // Stop the server
            }
            else if (choice == 3)
            {
                server.restart();  // Restart the server
            }
            else if (choice == 4)
            {
                System.out.println(server);  // Print server information
            }
            else if (choice == 5)
            {
                updateServer(scanner, server);  // Update server settings
            }
            else if (choice == 6)
            {
                managing = false;  // Exit server management
            }
            else
            {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method to update server settings
    private static void updateServer(Scanner scanner, GameServer server)
    {
        System.out.println("\nWhich setting do you want to update?");
        System.out.println("1. RAM");
        System.out.println("2. CPU Cores");
        System.out.println("3. Max Players");
        System.out.println("4. Version");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Update the selected server setting
        if (choice == 1)
        {
            System.out.print("Enter new RAM (MB): ");
            int ram = scanner.nextInt();
            scanner.nextLine();
            server.setRam(ram);
        }
        else if (choice == 2)
        {
            System.out.print("Enter new CPU Cores: ");
            int cpu = scanner.nextInt();
            scanner.nextLine();
            server.setCpuCores(cpu);
        }
        else if (choice == 3)
        {
            System.out.print("Enter new Max Players: ");
            int max = scanner.nextInt();
            scanner.nextLine();
            server.setMaxPlayers(max);
        }
        else if (choice == 4)
        {
            System.out.print("Enter new Version: ");
            String version = scanner.nextLine();
            server.setVersion(version);
        }
        else
        {
            System.out.println("Invalid option.");
        }

        System.out.println("Server updated.");
    }

    // Method to compare two servers based on RAM
     private static void compareServers(Scanner scanner, DockerSystem dockerSystem)
    {
        System.out.print("Enter first server name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter second server name: ");
        String name2 = scanner.nextLine();

        GameServer server1 = dockerSystem.findServerByName(name1);
        GameServer server2 = dockerSystem.findServerByName(name2);

        if (server1 == null || server2 == null)
        {
            System.out.println("One or both servers not found.");
            return;
        }

        int result = server1.compareTo(server2);
        if (result == 0)
        {
            System.out.println("Both servers are equal in RAM.");
        }
        else if (result > 0)
        {
            System.out.println(server1.getServerName() + " has more RAM than " + server2.getServerName());
        }
        else
        {
            System.out.println(server2.getServerName() + " has more RAM than " + server1.getServerName());
        }
    }

    // Method to check if two servers are equal in terms of their settings
    private static void checkServerEquality(Scanner scanner, DockerSystem dockerSystem)
    {
        System.out.print("Enter first server name: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter second server name: ");
        String name2 = scanner.nextLine();

        GameServer server1 = dockerSystem.findServerByName(name1);
        GameServer server2 = dockerSystem.findServerByName(name2);

        if (server1 == null || server2 == null)
        {
            System.out.println("One or both servers not found.");
            return;
        }

        if (server1.equals(server2))
        {
            System.out.println("Servers are equal.");
        }
        else
        {
            System.out.println("Servers are not equal.");
        }
    }
}