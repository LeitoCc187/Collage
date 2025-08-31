package coles.repeat;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

// AdoptionSystemUser

// Manages multiple adoption centers at once.
// Allows creating centers, switching between them,
// and saving/loading all centers with their pets from file.
 
public class AdoptionSystemUser
{
    private ArrayList<AdoptionCenter> centers; // All centers managed by the user
    private AdoptionCenter currentCenter;      // Currently active center

    // Constructor: starts with no centers
    public AdoptionSystemUser()
    {
        this.centers = new ArrayList<>();
        this.currentCenter = null;
    }

    // Center Management
    
    // Create a new adoption center and add it to the list.
    // The first created center automatically becomes the active one.
    
    public void createCenter(String name, String location, double adoptionFee)
    {
        AdoptionCenter center = new AdoptionCenter(name, location, adoptionFee);
        centers.add(center);
        if (currentCenter == null)
        {
            currentCenter = center; // Make the first one the default
        }
        System.out.println("Center created: " + name);
    }
    
    // Switch to another existing adoption center by name.
    // @return true if successful, false if not found
    
    public boolean switchCenter(String name)
    {
        for (AdoptionCenter c : centers)
        {
            if (c.getCenterName().equalsIgnoreCase(name))
            {
                currentCenter = c;
                return true;
            }
        }
        return false;
    }

    /**
     * @return the currently active adoption center
     */
    public AdoptionCenter getCurrentCenter()
    {
        return currentCenter;
    }

    // Display all available adoption centers.
    
    public void displayCenters()
    {
        if (centers.isEmpty())
        {
            System.out.println("No centers available.");
        }
        else
        {
            System.out.println("\nAvailable Adoption Centers:");
            for (AdoptionCenter c : centers)
            {
                System.out.println("- " + c.getCenterName() + " (" + c.getLocation() + ")");
            }
        }
    }

    // File Handling

    // Load centers and pets from a save file.
    // Format:
    //   CENTER|name|location|fee
    //   PET|name|species|age|hunger|mood|weight|adopted
    
    public void loadFromFile(String filePath)
    {
        File file = new File(filePath);
        if (!file.exists())
        {
            System.out.println("No save file found. Starting fresh.");
            return;
        }

        try (Scanner fileScanner = new Scanner(file))
        {
            AdoptionCenter activeCenter = null;

            while (fileScanner.hasNextLine())
            {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts[0].equals("CENTER"))
                {
                    // Create a new adoption center
                    String name = parts[1];
                    String location = parts[2];
                    double fee = Double.parseDouble(parts[3]);

                    activeCenter = new AdoptionCenter(name, location, fee);
                    centers.add(activeCenter);

                    // First loaded center becomes active if none is selected
                    if (currentCenter == null)
                    {
                        currentCenter = activeCenter;
                    }

                }
                else if (parts[0].equals("PET") && activeCenter != null)
                {
                    // Add a pet to the most recently created/loaded center
                    String name = parts[1];
                    String species = parts[2];
                    int age = Integer.parseInt(parts[3]);
                    int hunger = Integer.parseInt(parts[4]);
                    String mood = parts[5];
                    double weight = Double.parseDouble(parts[6]);
                    boolean adopted = Boolean.parseBoolean(parts[7]);

                    Pet pet = new Pet(name, species, age, hunger, mood, weight);
                    pet.setAdopted(adopted);
                    activeCenter.addPet(pet);
                }
            }
            System.out.println("Data loaded from " + filePath);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: File not found.");
        }
    }

    // Save all centers and their pets to a file.
    
    public void saveToFile(String filePath)
    {
        try
        {
            FileWriter writer = new FileWriter(filePath);

            for (AdoptionCenter center : centers)
            {
                // Save center info
                writer.write("CENTER|" + center.getCenterName() + "|" + center.getLocation() + "|" + center.getAdoptionFee() + "\n");

                // Save each pet
                for (Pet pet : center.getPets())
                {
                    writer.write("PET|" + pet.getName() + "|" + pet.getSpecies() + "|" + pet.getAge() + "|" +
                            pet.getHungerLevel() + "|" + pet.getMood() + "|" + pet.getWeight() + "|" + pet.isAdopted() + "\n");
                }
            }

            writer.close();
            System.out.println("Data saved to " + filePath);
        }
        catch (IOException e)
        {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
