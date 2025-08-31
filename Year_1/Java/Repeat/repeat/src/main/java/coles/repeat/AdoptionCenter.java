package coles.repeat;

import java.util.ArrayList;
import java.util.Collections;

// AdoptionCenter
// Represents a single adoption center where pets can be stored,
// adopted, and managed. Each center has its own details (name, location,
// adoption fee) and maintains a list of pets.

public class AdoptionCenter
{

    // Attributes
    private String centerName;         // Name of the adoption center
    private String location;           // Physical location of the center
    private double adoptionFee;        // Standard fee charged for each adoption
    private ArrayList<Pet> pets;       // List of pets in this center

    // Constructor 

    // Creates a new adoption center with the given details.
    
    // @param centerName  Name of the adoption center
    // @param location    Location of the center
    // @param adoptionFee Standard fee for adopting a pet
    
    public AdoptionCenter(String centerName, String location, double adoptionFee)
    {
        this.centerName = centerName;
        this.location = location;
        this.adoptionFee = adoptionFee;
        this.pets = new ArrayList<>();
    }

    // Getters 
    public String getCenterName() { return centerName; }
    public String getLocation() { return location; }
    public double getAdoptionFee() { return adoptionFee; }
    public ArrayList<Pet> getPets() { return pets; }

    // Pet Management Methods

    // Add a new pet to this adoption center.
    
    // @param pet The pet to be added
    
    public void addPet(Pet pet)
    {
        pets.add(pet);
    }

    // Find a pet in this center by its name.
    
    // @param name The name of the pet
    // @return The matching Pet object, or null if not found
    
    public Pet findPet(String name)
    {
        for (Pet p : pets)
        {
            if (p.getName().equalsIgnoreCase(name))
            {
                return p;
            }
        }
        return null;
    }

    // Mark a pet as adopted if found and not already adopted.
    
    // @param name The name of the pet to adopt
    // @return true if adoption succeeded, false otherwise

    public boolean adoptPet(String name)
    {
        Pet pet = findPet(name);
        if (pet != null && !pet.isAdopted())
        {
            pet.setAdopted(true);
            return true;
        }
        return false;
    }

    // Display Methods

    // Display all pets in this center (adopted and available).

    public void displayAllPets()
    {
        if (pets.isEmpty())
        {
            System.out.println("No pets in the adoption center.");
        }
        else
        {
            for (Pet p : pets)
            {
                System.out.println(p);
            }
        }
    }

    //Display only pets that are not yet adopted.

    public void displayAvailablePets()
    {
        for (Pet p : pets)
        {
            if (!p.isAdopted())
            {
                System.out.println(p);
            }
        }
    }

    // Display only pets that have been adopted.

    public void displayAdoptedPets()
    {
        for (Pet p : pets) {
            if (p.isAdopted())
            {
                System.out.println(p);
            }
        }
    }

    // Revenue Calculation

    // Calculates the total revenue earned from adoptions.
    // Each adopted pet adds the standard adoption fee.
    
    // @return The total revenue as a double

    public double calculateTotalRevenue()
    {
        double revenue = 0;
        for (Pet p : pets)
        {
            if (p.isAdopted())
            {
                revenue += adoptionFee;
            }
        }
        return revenue;
    }

    // Sorting Methods

    // Sort pets by age (youngest to oldest).
    public void sortByAge()
    {
        Collections.sort(pets, (a, b) -> Integer.compare(a.getAge(), b.getAge()));
    }

    // Sort pets by weight (lightest to heaviest).
    public void sortByWeight()
    {
        Collections.sort(pets, (a, b) -> Double.compare(a.getWeight(), b.getWeight()));
    }

    // Extra Info

    // Display general information about this adoption center.
    public void displayCenterInfo()
    {
        System.out.println("Welcome to " + centerName + " in " + location + "!");
        System.out.println("Standard Adoption Fee: £" + adoptionFee);
    }
}
