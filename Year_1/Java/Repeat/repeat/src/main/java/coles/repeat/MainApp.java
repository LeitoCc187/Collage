package coles.repeat;

import java.util.Scanner;
import java.io.*;
import java.util.Random;

// MainApp
// Entry point for the Virtual Pet Adoption System.
// Handles user interaction through menus, manages adoption centers,
// and links the user with pets and file saving/loading.

public class MainApp
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        AdoptionCenter center = null;

        // Force the user to either create or load a center before proceeding
        while (center == null)
        {
            System.out.println("\n=== Adoption Center Setup ===");
            System.out.println("1. Create a new adoption center");
            System.out.println("2. Load center from file");
            System.out.print("Enter your choice: ");

            int setupChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (setupChoice == 1)
            {
                // Manual creation of a new adoption center
                System.out.print("Enter center name: ");
                String name = scanner.nextLine();
                System.out.print("Enter location: ");
                String location = scanner.nextLine();
                System.out.print("Enter adoption fee: ");
                double fee = scanner.nextDouble();
                scanner.nextLine();

                center = new AdoptionCenter(name, location, fee);
                System.out.println("Adoption center created successfully!");

            }
            else if (setupChoice == 2)
            {
                // Attempt to load pets from file into a new center
                File file = new File("pets.txt");
                if (file.exists())
                {
                    center = new AdoptionCenter("Loaded Center", "Unknown", 25.0);

                    try (Scanner fileScanner = new Scanner(file))
                    {
                        while (fileScanner.hasNextLine())
                        {
                            String[] data = fileScanner.nextLine().split(",");
                            if (data.length == 7)
                            {
                                // Parse pet data from file
                                String name = data[0];
                                String species = data[1];
                                int age = Integer.parseInt(data[2]);
                                int hunger = Integer.parseInt(data[3]);
                                String mood = data[4];
                                double weight = Double.parseDouble(data[5]);
                                boolean isAdopted = Boolean.parseBoolean(data[6]);

                                Pet pet = new Pet(name, species, age, hunger, mood, weight);
                                pet.setAdopted(isAdopted);
                                center.addPet(pet);
                            }
                        }
                    }
                    catch (IOException e)
                    {
                        System.out.println("Error loading saved data: " + e.getMessage());
                    }
                    System.out.println("Adoption center and pets loaded!");
                }
                else
                {
                    System.out.println("No saved data found. Please create a new center.");
                }
            }
            else
            {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Main multi-level menu system
        boolean running = true;
        while (running)
        {
            System.out.println("\n=== Virtual Pet Adoption Center ===");
            System.out.println("1. Pet Management");
            System.out.println("2. Adoptions & Revenue");
            System.out.println("3. Random & Save");
            System.out.println("4. Exit without saving");
            System.out.print("Enter your choice: ");

            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            if (mainChoice == 1)
            {
                petManagementMenu(scanner, center);
            }
            else if (mainChoice == 2)
            {
                adoptionRevenueMenu(scanner, center);
            }
            else if (mainChoice == 3)
            {
                randomAndSaveMenu(scanner, center);
            }
            else if (mainChoice == 4)
            {
                running = false;
                System.out.println("Exiting without saving... Goodbye!");
            }
            else
            {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    // Pet Management Menu
    
    // Handles all pet-related functionality (add, view, manage, sort).
    
    private static void petManagementMenu(Scanner scanner, AdoptionCenter center)
    {
        boolean managing = true;
        while (managing)
        {
            System.out.println("\n--- Pet Management ---");
            System.out.println("1. Add a new pet");
            System.out.println("2. View all pets");
            System.out.println("3. View available pets");
            System.out.println("4. Manage a pet");
            System.out.println("5. Sort pets");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1)
            {
                addPet(scanner, center);
            }
            else if (choice == 2)
            {
                center.displayAllPets();
            }
            else if (choice == 3)
            {
                center.displayAvailablePets();
            }
            else if (choice == 4)
            {
                managePet(scanner, center);
            }
            else if (choice == 5)
            {
                sortMenu(scanner, center);
            }
            else if (choice == 6)
            {
                managing = false;
            }
            else
            {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Adoption & Revenue Menu

    // Displays adopted pets and calculates total adoption revenue.
    
    private static void adoptionRevenueMenu(Scanner scanner, AdoptionCenter center)
    {
        boolean viewing = true;
        while (viewing)
        {
            System.out.println("\n--- Adoptions & Revenue ---");
            System.out.println("1. View adopted pets");
            System.out.println("2. Show adoption revenue");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1)
            {
                center.displayAdoptedPets();
            }
            else if (choice == 2)
            {
                System.out.printf("Total revenue from adoptions: £%.2f\n", center.calculateTotalRevenue());
            }
            else if (choice == 3)
            {
                viewing = false;
            }
            else
            {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Random & Save Menu
    
    // Menu for generating random pets and saving program data to file.
    
    private static void randomAndSaveMenu(Scanner scanner, AdoptionCenter center)
    {
        boolean working = true;
        while (working)
        {
            System.out.println("\n--- Random & Save ---");
            System.out.println("1. Generate random pets");
            System.out.println("2. Save and Exit");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1)
            {
                generateRandomPets(scanner, center);
            } else if (choice == 2)
            {
                saveData(center);
                System.out.println("Data saved. Goodbye!");
                System.exit(0);
            }
            else if (choice == 3)
            {
                working = false;
            }
            else
            {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Utility Methods

    // Adds a new pet with user input.
    
    private static void addPet(Scanner scanner, AdoptionCenter center)
    {
        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();
        System.out.print("Enter species: ");
        String species = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.print("Enter hunger level (0-100): ");
        int hunger = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter mood: ");
        String mood = scanner.nextLine();
        System.out.print("Enter weight (kg): ");
        double weight = scanner.nextDouble();
        scanner.nextLine();

        Pet pet = new Pet(name, species, age, hunger, mood, weight);
        center.addPet(pet);
        System.out.println("Pet added successfully!");
    }

    // Manage a specific pet: feed, play, adopt, or view info.

    private static void managePet(Scanner scanner, AdoptionCenter center)
    {
        System.out.print("Enter pet name to manage: ");
        String name = scanner.nextLine();
        Pet pet = center.findPet(name);

        if (pet == null)
        {
            System.out.println("Pet not found.");
            return;
        }

        boolean managing = true;
        while (managing)
        {
            System.out.println("\n--- Managing Pet: " + pet.getName() + " ---");
            System.out.println("1. Feed Pet");
            System.out.println("2. Play with Pet");
            System.out.println("3. Adopt Pet");
            System.out.println("4. View Pet Info");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1)
            {
                pet.feed();
                System.out.println(pet.getName() + " has been fed.");
            }
            else if (choice == 2)
            {
                pet.play();
                System.out.println(pet.getName() + " played and is now " + pet.getMood());
            }
            else if (choice == 3)
            {
                if (center.adoptPet(pet.getName()))
                {
                    System.out.println(pet.getName() + " has been adopted!");
                }
                else
                {
                    System.out.println("Could not adopt pet. Maybe already adopted?");
                }
            }
            else if (choice == 4)
            {
                System.out.println(pet);
            }
            else if (choice == 5)
            {
                managing = false;
            }
            else
            {
                System.out.println("Invalid option.");
            }
        }
    }

    // Sorting menu (by age or weight).
    
    private static void sortMenu(Scanner scanner, AdoptionCenter center)
    {
        System.out.println("\nSort pets by:");
        System.out.println("1. Age");
        System.out.println("2. Weight");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1)
        {
            center.sortByAge();
            System.out.println("Pets sorted by age.");
        } else if (choice == 2)
        {
            center.sortByWeight();
            System.out.println("Pets sorted by weight.");
        }
        else
        {
            System.out.println("Invalid choice.");
        }
    }

    
    // Generates a number of random pets with randomized attributes.
    
    private static void generateRandomPets(Scanner scanner, AdoptionCenter center)
    {
        System.out.print("Enter the number of random pets to generate: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        String[] names = {"Buddy", "Milo", "Luna", "Charlie", "Max", "Bella", "Coco", "Daisy", "Oscar", "Misty"};
        String[] species = {"Dog", "Cat", "Rabbit", "Hamster", "Parrot"};
        String[] moods = {"Happy", "Sleepy", "Excited", "Calm", "Grumpy"};
        Random random = new Random();

        for (int i = 0; i < count; i++)
        {
            String name = names[random.nextInt(names.length)] + random.nextInt(1000);
            String petSpecies = species[random.nextInt(species.length)];
            int age = random.nextInt(15) + 1;
            int hunger = random.nextInt(101);
            String mood = moods[random.nextInt(moods.length)];
            double weight = 0.5 + (5.0 * random.nextDouble());

            Pet pet = new Pet(name, petSpecies, age, hunger, mood, weight);
            center.addPet(pet);
        }

        System.out.println(count + " random pets generated successfully!");
    }

    // Saves pet data to file.
    
    private static void saveData(AdoptionCenter center)
    {
        File file = new File("pets.txt");
        try
        {
            FileWriter writer = new FileWriter(file);
            for (Pet pet : center.getPets())
            {
                writer.write(pet.getName() + "," + pet.getSpecies() + "," + pet.getAge() + "," + pet.getHungerLevel()
                        + "," + pet.getMood() + "," + pet.getWeight() + "," + pet.isAdopted() + "\n");
            }
            writer.close();
        }
        catch (IOException e)
        {
            System.out.println("Unable to save data: " + e.getMessage());
        }
    }
}
