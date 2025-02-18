package cc.project.programmingclass;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Cole Cumiskey
 */
public class HangMan 
{
    public static void main(String[] args) throws IOException
    {

        // Flag to quit the game and other functions
        boolean quit = false;
        boolean back = false;

        // High Scores for all games
        int hscorehm = 0; // Hang Man

        // Score for all games
        int score = 0;

        // Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Creating random number generator for the game
        Random rand = new Random();

        /* This was used to find the working directiory to help me with file loactions and pathing files
        System.out.println("Current working directory: " + new File(".").getAbsolutePath());
        */

        // Create file link
        File inputFile = new File("WorldCities2024_v3.txt");

        // Read file
        Scanner fileInput = new Scanner(inputFile);
        String aLine;
        int lines = 0;

        // amount of values in file?
        while (fileInput.hasNext()) 
        {
            aLine = fileInput.nextLine();
            lines++;
        }
        // Close the scanner
        fileInput.close();

        // Creating Arays for hang man
        String[] city = new String[lines];
        String[] countries = new String[lines];
        int[] pop = new int[lines];

        System.out.println(lines);

        // Reinitialize the scanner
        fileInput = new Scanner(inputFile);

        for (int i = 0 ; i < lines ; i++) // Logic for setting up the arays for the Hang Man ame
        {
            if (fileInput.hasNext()) {
                city[i] = fileInput.next(); // Read city name
            } else {
                System.out.println("Error: Missing city name on line " + (i + 1));
                break;
            }

            if (fileInput.hasNext()) {
                countries[i] = fileInput.next(); // Read country name
            } else {
                System.out.println("Error: Missing country name on line " + (i + 1));
                break;
            }

            if (fileInput.hasNextInt()) {
                pop[i] = fileInput.nextInt(); // Read population
            } else {
                System.out.println("Error: Invalid or missing population on line " + (i + 1));
                break;
            }
            /* This was used to test if the arrays where correctly working
            System.out.printf("%s\t%s\t%s\n",city[i],countries[i],pop[i]);
            */

        }

        // Close the scanner
        fileInput.close();

        // Game menu loop
        while (!quit)
        {

            // Create a list of unique countries using an array (no ArrayList)
            String[] countryList = new String[lines];
            int countryCount = 0;
            boolean isNewCountry;

            for (int i = 0; i < lines; i++) 
            {
                isNewCountry = true;
                for (int j = 0; j < countryCount; j++) 
                {
                    if (countries[i].equals(countryList[j])) 
                    {
                        isNewCountry = false; // Country already added, skip it
                        break;
                    }
                }
                if (isNewCountry) 
                {
                    countryList[countryCount] = countries[i];
                    countryCount++;
                }
            }

            while (!back) 
            {
                // Display the available countries for the player to choose from
                System.out.println("Select a country for the Hangman game:");

                for (int i = 0; i < countryCount; i++) 
                {
                    System.out.println((i + 1) + ". " + countryList[i]);
                }

                // Handle invalid input for country selection
                int countryChoice = 0;
                boolean validChoice = false;

                while (!validChoice) 
                {
                    System.out.print("Enter your choice (1-" + countryCount + "): ");
                    countryChoice = scanner.nextInt();

                    // Check if the choice is valid
                    if (countryChoice >= 1 && countryChoice <= countryCount) {
                        validChoice = true; // Exit the loop if the choice is valid
                    } 
                    else 
                    {
                        System.out.println("Invalid choice! Please choose a number between 1 and " + countryCount + ".");
                    }
                }

                String selectedCountry = countryList[countryChoice - 1]; // Get the selected country

                // Display the selected country
                System.out.println("\nYou selected: " + selectedCountry);

                // Filter the cities by the selected country
                String[] citiesInCountry = new String[lines];
                int[] populationsInCountry = new int[lines];
                int cityCount = 0;
                
                for (int i = 0; i < lines; i++) 
                {
                    if (countries[i].equals(selectedCountry)) 
                    {
                        citiesInCountry[cityCount] = city[i];
                        populationsInCountry[cityCount] = pop[i];
                        cityCount++;
                    }
                }

                // Randomly select a city from the filtered list
                int cityIndex = rand.nextInt(cityCount); // Random index for the city
                String cityName = citiesInCountry[cityIndex];
                int population = populationsInCountry[cityIndex];

                // Start the Hangman game with the selected city
                char[] guessedWord = new char[cityName.length()];

                for (int i = 0; i < cityName.length(); i++) 
                {
                    guessedWord[i] = '_'; // Initialize the word as underscores
                }
                int attemptsLeft = 6; // Maximum attempts allowed
                boolean hintUsed = false; // Track if hint has been used

                System.out.println("Welcome to Hangman! The city name has " + cityName.length() + " letters.");
                System.out.println("Country: " + selectedCountry); // Display the country during gameplay

                // Main loop for the Hangman game
                while (attemptsLeft > 0) 
                {
                    // Display the current guessed word and remaining attempts
                    System.out.println("\nCurrent word: " + String.valueOf(guessedWord));
                    System.out.println("Attempts left: " + attemptsLeft);
                    System.out.print("Enter a letter to guess: ");
                    char guess = scanner.next().toLowerCase().charAt(0); // Get the guessed letter

                    // Check if the guessed letter is in the city name
                    boolean correctGuess = false;

                    for (int i = 0; i < cityName.length(); i++) 
                    {
                        if (Character.toLowerCase(cityName.charAt(i)) == guess) 
                        {
                            guessedWord[i] = cityName.charAt(i); // Update the guessed word
                            correctGuess = true;
                        }
                    }

                    // Inform the player about the result of their guess
                    if (correctGuess) 
                    {
                        System.out.println("Good guess!");
                    } 
                    else 
                    {
                        attemptsLeft--; // Decrease attempts if the guess is wrong
                        System.out.println("Wrong guess!");
                    }

                    // Check if the word is fully guessed
                    if (String.valueOf(guessedWord).equalsIgnoreCase(cityName)) 
                    {
                        System.out.println("Congratulations! You guessed the city: " + cityName);
                        score++; // Increment the score
                        hscorehm = Math.max(hscorehm, score); // Update high score if necessary
                        break;
                    }

                    // Option to get a hint
                    if (!hintUsed && attemptsLeft > 3) 
                    {
                        System.out.println("Would you like a hint? (" + attemptsLeft + " guesses left) [Y/N]");
                        String hintChoice = scanner.next().toUpperCase();

                        if (hintChoice.equals("Y")) 
                        {
                            // Provide a hint: show the population of the city (without revealing the city name)
                            System.out.println("Hint: The population of this city is " + population + " people.");
                            attemptsLeft -= 3;  // Reduce attempts by 3 for using a hint
                            hintUsed = true; // Mark hint as used
                        }
                    }
                }

                // If out of attempts, the game ends and the city is revealed
                if (attemptsLeft == 0) 
                {
                    System.out.println("You ran out of attempts! The city was: " + cityName);
                }

                // Ask the player if they want to play again
                System.out.println("Do you want to play again? (Y/N)");
                String playAgain = scanner.next().toUpperCase();

                if (playAgain.equals("N")) 
                {
                    quit = true; // Exit the Hangman game loop
                }
            }
        }
        scanner.close();
    }
}
