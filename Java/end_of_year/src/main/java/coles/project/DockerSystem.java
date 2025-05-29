package coles.project;

import java.util.ArrayList;
import java.util.Collections;

public class DockerSystem
{
    // Variables
    private ArrayList<GameServer> servers;  // List to hold the servers in the Docker system
    private String dockerName;  // Name of the Docker system
    private int maxCapacity;  // Maximum capacity for the Docker system
    private String location;  // Location of the Docker system

    // Default Constructor
    // Initializes the Docker system with default values
    public DockerSystem()
    {
        this.servers = new ArrayList<>();  // Initialize the list of servers as an empty list
        this.dockerName = "DefaultDocker";  // Default name
        this.maxCapacity = 10;  // Default capacity
        this.location = "Unknown";  // Default location
    }

    // Overloaded Constructor
    // Initializes the Docker system with specific name, capacity, and location
    public DockerSystem(String dockerName, int maxCapacity, String location)
    {
        this.servers = new ArrayList<>(maxCapacity);  // Initialize list with capacity
        this.dockerName = dockerName;
        this.maxCapacity = maxCapacity;
        this.location = location;
    }

    // Accessors (Getters)
    public ArrayList<GameServer> getServers()  // Returns the list of servers in this Docker system
    {
        return servers;
    }

    public String getDockerName()  // Returns the name of the Docker system
    {
        return dockerName;
    }

    public int getMaxCapacity()  // Returns the maximum capacity of the Docker system
    {
        return maxCapacity;
    }

    public String getLocation()  // Returns the location of the Docker system
    {
        return location;
    }

    public int getCurrentServerCount()  // Returns the current number of servers in the Docker system
    {
        return servers.size();
    }

    public boolean isFull()  // Returns true if the Docker system has reached its max capacity, false otherwise
    {
        return servers.size() >= maxCapacity;
    }

    // Mutators (Setters)
    public void setDockerName(String dockerName)  // Sets the name of the Docker system
    {
        this.dockerName = dockerName;
    }

    public void setMaxCapacity(int maxCapacity)  // Sets the maximum capacity of the Docker system
    {
        this.maxCapacity = maxCapacity;
    }

    public void setServers(ArrayList<GameServer> servers)  // Sets the list of servers in the Docker system
    {
        this.servers = servers;
    }

    public void setLocation(String location)  // Sets the location of the Docker system
    {
        this.location = location;
    }

    // Add server (with capacity check)
    public void addServer(GameServer server)  // Adds a new server to the Docker system
    {
        if (!isFull())  // Check if the system is full
        {
            servers.add(server);  // Add the server to the list if there is space
        }
        else
        {
            System.out.println("Cannot add server: DockerSystem is at full capacity (" + maxCapacity + ").");
        }
    }

    // Remove server by name
    public void removeServer(String serverName)  // Removes a server from the Docker system by its name
    {
        servers.removeIf(server -> server.getServerName().equalsIgnoreCase(serverName));  // Find and remove the server
    }

    // Find server by name
    public GameServer findServerByName(String serverName)  // Searches for a server by its name and returns it if found
    {
        for (GameServer server : servers)  // Iterate through the list of servers
        {
            if (server.getServerName().equalsIgnoreCase(serverName))  // If a match is found
            {
                return server;  // Return the server
            }
        }
        return null;  // Return null if no server is found with that name
    }

    // Display all servers
    public void displayServers()  // Displays all the servers in the Docker system
    {
        if (servers.isEmpty())  // If there are no servers
        {
            System.out.println("No servers found.");
        }
        else
        {
            for (GameServer server : servers)  // Iterate through the list of servers
            {
                System.out.println(server);  // Print each server
            }
        }
    }

    // Sort servers by their names
    public void sortServersByName()  // Sorts the list of servers alphabetically by name
    {
        Collections.sort(servers);  // Sort using the compareTo method defined in the GameServer class
    }

    // Count servers
    public int countServers()  // Returns the total number of servers in the Docker system
    {
        return servers.size();
    }
}