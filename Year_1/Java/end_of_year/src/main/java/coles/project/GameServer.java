package coles.project;

public class GameServer implements Comparable<GameServer>
{
    // Instance variables to define the properties of a game server
    private String serverName;  // The name of the server
    private String version;  // The version of the game that the server is running
    private String status;  // The current status of the server (e.g., "Running", "Stopped")
    private int ram;  // The amount of RAM allocated to the server, in MB
    private int cpuCores;  // The number of CPU cores allocated to the server
    private int maxPlayers;  // The maximum number of players that the server can handle

    // Constructor to initialize all the properties of the GameServer
    public GameServer(String serverName, int ram, int cpuCores, int maxPlayers, String version)
    {
        this.serverName = serverName;
        this.ram = ram;
        this.cpuCores = cpuCores;
        this.maxPlayers = maxPlayers;
        this.version = version;
        this.status = "Stopped";  // Default status is "Stopped" when the server is created
    }
    
    // Empty constructor initializes the GameServer with default values (useful for testing)
    public GameServer()
    {
        this.serverName = "";
        this.ram = 0;
        this.cpuCores = 0;
        this.maxPlayers = 0;
        this.version = "";
        this.status = "";
    }
    
    // Constructor for initializing only the server name (useful for testing)
    public GameServer(String serverName)
    {
        this.serverName = serverName;
        this.ram = 0;
        this.cpuCores = 0;
        this.maxPlayers = 0;
        this.version = "";
        this.status = "";
    }
    
    // Accessor (Getter) methods
    public String getServerName()  // Returns the server's name
    { 
        return serverName;
    }

    public int getRam()  // Returns the amount of RAM in the server, in MB
    {
        return ram;
    }

    public int getCpuCores()  // Returns the number of CPU cores assigned to the server
    {
        return cpuCores;
    }

    public int getMaxPlayers()  // Returns the maximum number of players the server can handle
    {
        return maxPlayers;
    }

    public String getVersion()  // Returns the version of the game running on the server
    {
        return version;
    }

    public String getStatus()  // Returns the current status of the server
    {
        return status;
    }

    // Mutator (Setter) methods
    public void setServerName(String serverName)  // Sets the server's name
    {
        this.serverName = serverName;
    }

    public void setRam(int ram)  // Sets the amount of RAM for the server
    {
        this.ram = ram;
    }

    public void setCpuCores(int cpuCores)  // Sets the number of CPU cores for the server
    {
        this.cpuCores = cpuCores;
    }

    public void setMaxPlayers(int maxPlayers)  // Sets the maximum number of players for the server
    {
        this.maxPlayers = maxPlayers;
    }

    public void setVersion(String version)  // Sets the version of the game running on the server
    {
        this.version = version;
    }
    
    public void setStatus(String status)  // Sets the status of the server (e.g., "Running", "Stopped")
    {
        this.status = status;
    }

    // Methods to control server behavior
    public void start()  // Starts the server if it's not already running
    {
        if (status.equals("Running"))
        {
            System.out.println(serverName + " is already running.");  // If already running, notify the user
        }
        else
        {
            status = "Running";  // Set status to "Running"
            System.out.println(serverName + " has been started.");  // Notify the user
        }
    }

    public void stop()  // Stops the server if it's not already stopped
    {
        if (status.equals("Stopped"))
        {
            System.out.println(serverName + " is already stopped.");  // If already stopped, notify the user
        }
        else
        {
            status = "Stopped";  // Set status to "Stopped"
            System.out.println(serverName + " has been stopped.");  // Notify the user
        }
    }

    public void restart()  // Restarts the server
    {
        System.out.println("Restarting " + serverName + "...");
        status = "Restarting";  // Temporarily set status to "Restarting"
        // Simulate delay if needed, then change the status to "Running"
        status = "Running";
        System.out.println(serverName + " has restarted.");  // Notify the user
    }

    // Overriding equals() method to compare two servers by their names
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) 
        {
            return true;  // If they are the same object, return true
        }

        if (obj == null || getClass() != obj.getClass()) 
        {
            return false;  // If the object is null or not the same class, return false
        }

        GameServer other = (GameServer) obj;  // Cast the object to GameServer

        return this.serverName.equalsIgnoreCase("\n\n" + other.serverName);  // Compare the server names, ignoring case
    }

    // Implementing compareTo method to allow comparison between servers (sorting by name)
    @Override
    public int compareTo(GameServer other)
    {
        // Compare servers based on the server name case-insensitively
        return this.serverName.compareToIgnoreCase(other.serverName);
    }

    // toString method to provide a string representation of the GameServer object
    @Override
    public String toString()
    {
        return "\n\nServer Name: " + serverName + 
               "\nVersion: " + version + 
               "\nRAM: " + ram + " MB" + 
               "\nCPU Cores: " + cpuCores + 
               "\nMax Players: " + maxPlayers + 
               "\nStatus: " + status + "\n";  // Format the string to display server details
    }
}