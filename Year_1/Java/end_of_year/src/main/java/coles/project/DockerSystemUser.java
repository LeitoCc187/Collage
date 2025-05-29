package coles.project;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class DockerSystemUser
{
    private ArrayList<DockerSystem> dockerSystems;  // List to hold all Docker systems
    private DockerSystem currentDockerSystem;  // The currently logged-in Docker system

    // Constructor to initialize the Docker systems list and set the current system to null
    public DockerSystemUser()
    {
        this.dockerSystems = new ArrayList<>();
        this.currentDockerSystem = null;
    }

    // Create a new Docker system with the provided name, capacity, and location
    public void createDockerSystem(String name, int capacity, String location)
    {
        // Check if a Docker system with the same name already exists
        boolean exists = false;
        for (int i = 0; i < dockerSystems.size(); i++)
        {
            if (dockerSystems.get(i).getDockerName().equalsIgnoreCase(name))
            {
                exists = true;  // System with this name already exists
            }
        }

        // If the system doesn't exist, create and add it to the list
        if (!exists)
        {
            DockerSystem newDockerSystem = new DockerSystem(name, capacity, location);
            dockerSystems.add(newDockerSystem);
            System.out.println("Docker system " + name + " created successfully.");
        }
        else
        {
            System.out.println("Docker system with that name already exists.");
        }
    }

    // Log into an existing Docker system by name
    public boolean login(String dockerName)
    {
        for (DockerSystem ds : dockerSystems)
        {
            if (ds.getDockerName().equals(dockerName))
            {
                currentDockerSystem = ds;  // Set the current system to the found one
                return true;  // Login successful
            }
        }
        return false;  // No matching system found
    }

    // Get the currently logged-in Docker system
    public DockerSystem getCurrentDockerSystem()
    {
        return currentDockerSystem;
    }

    // Check if a Docker system is logged in
    public boolean isLoggedIn()
    {
        return currentDockerSystem != null;  // Return true if a system is logged in
    }

    // Display all available Docker systems
    public void displayDockerSystems()
    {
        if (dockerSystems.size() == 0)
        {
            System.out.println("No Docker systems available.");
        }
        else
        {
            System.out.println("\n\nAvailable Docker Systems:\n");
            // Iterate through the list and display each system
            for (int i = 0; i < dockerSystems.size(); i++)
            {
                DockerSystem ds = dockerSystems.get(i);
                System.out.println("- " + ds.getDockerName() + " (Location: " + ds.getLocation() + ")");
            }
        }
    }

    // Get a Docker system by name
    public DockerSystem getDockerSystemByName(String name)
    {
        for (DockerSystem ds : dockerSystems)
        {
            if (ds.getDockerName().equals(name))
            {
                return ds;  // Return the system if found
            }
        }
        return null;  // Return null if no system is found with that name
    }

    // Load data from a file to initialize the Docker systems
    public void loadFromFile(String filePath)
    {
        File file = new File(filePath);
        if (!file.exists())
        {
            System.out.println("File not found: " + filePath);
            return;  // If the file doesn't exist, return early
        }

        try (Scanner fileScanner = new Scanner(file))  // Use scanner to read file
        {
            while (fileScanner.hasNextLine())  // Read each line from the file
            {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");  // Split the line by '|'

                // Check if the line represents a Docker system or server
                if (parts[0].equals("DOCKER"))
                {
                    String name = parts[1];
                    int capacity = Integer.parseInt(parts[2]);
                    String location = parts[3];

                    // Add Docker system if it doesn't already exist
                    if (getDockerSystemByName(name) == null)
                    {
                        dockerSystems.add(new DockerSystem(name, capacity, location));
                    }
                }
                else if (parts[0].equals("SERVER"))
                {
                    String dockerName = parts[1];
                    DockerSystem target = getDockerSystemByName(dockerName);

                    // If the Docker system exists, create and add the server
                    if (target != null)
                    {
                        String serverName = parts[2];
                        int ram = Integer.parseInt(parts[3]);
                        int cpu = Integer.parseInt(parts[4]);
                        int maxPlayers = Integer.parseInt(parts[5]);
                        String version = parts[6];

                        GameServer server = new GameServer(serverName, ram, cpu, maxPlayers, version);
                        target.addServer(server);  // Add the server to the system
                    }
                }
            }

            System.out.println("Data loaded from file.");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: File not found - " + filePath);
        }
    }

    // Save the Docker systems data to a file
    public void saveToFile(String filePath)
    {
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                file.createNewFile();  // Create the file if it doesn't exist
                System.out.println("File created: " + filePath);
            }

            try (FileWriter writer = new FileWriter(file))  // Use FileWriter to write data to the file
            {
                for (DockerSystem ds : dockerSystems)
                {
                    // Write each Docker system to the file
                    writer.write("DOCKER|" + ds.getDockerName() + "|" + ds.getMaxCapacity() + "|" + ds.getLocation() + "\n");
                    for (GameServer server : ds.getServers())
                    {
                        // Write each server under its Docker system
                        writer.write("SERVER|" + ds.getDockerName() + "|" + server.getServerName() + "|" + server.getRam() + "|"
                            + server.getCpuCores() + "|" + server.getMaxPlayers() + "|" + server.getVersion() + "\n");
                    }
                }
            }

            System.out.println("Data saved to file.");
        }
        catch (IOException e)
        {
            System.out.println("Error writing to file: " + filePath);
        }
    }

    // Wipe the storage (clear the data and file content)
    public void wipeStorage(String filePath)
    {
        try
        {
            File file = new File(filePath);
            if (!file.exists())
            {
                file.createNewFile();  // Create the file if it doesn't exist
                System.out.println("File created: " + filePath);
            }

            try (FileWriter writer = new FileWriter(file))
            {
                writer.write("");  // Clear the content of the file
            }

            dockerSystems.clear();  // Clear the list of Docker systems in memory
            System.out.println("Storage wiped successfully.");
        }
        catch (IOException e)
        {
            System.out.println("Error wiping storage: " + filePath);
        }
    }
}