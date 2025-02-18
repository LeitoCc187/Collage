/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cc.project.programmingclass;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Cole Cumiskey
 */
public class RockPaperScissors 
{

     public static void main(String[] args)
    {
        // Score for all games
        int score = 0;

        // High Scores for all games
        int hscorerps = 0; // Rock, Paper, Sissorse

        // Bot Choice
        int botchoice = 0;

        // Flag to quit the game 
        boolean quit = false;
        // Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Creating random number generator for the game
        Random rand = new Random();
        
        // Game menu loop
        while (!quit)
        {
            System.out.println("========= Pick An Option =========\n");
            System.out.println("1 - Rock");
            System.out.println("2 - Paper");
            System.out.println("3 - Scissors");
            System.out.println("4 - Quit\n");
            System.out.println("Your High Score: " + hscorerps + "!");
            System.out.println("Current Score: " + score + "\n");
            System.out.println("=================================\n");
            System.out.print("Please select an option! - Selection: ");
            int choice = scanner.nextInt();
            System.out.println("");

            botchoice = rand.nextInt(3) + 1;

            if (choice > 4) // Invalid Choice
            {
                System.out.println("=================================\n");
                System.out.println("Invalid choice made! - Please try again!\n");
            }
            else if (choice == 4) // exit game
            {
                if(score > hscorerps) // Update high score
                {
                    hscorerps = score;
                }

                score = 0;

                quit = true;
            }
            else if ((choice + 1) % 3 == botchoice) // User lost scenario
            {
                System.out.println("=================================\n");
                System.out.println("You lost! - Unfortunately - Want to play again?");
                System.out.print("I selected: ");
                // Start logic for what to display on messages
                if(botchoice == 1)
                {
                    System.out.println("Rock!");
                }
                else if (botchoice == 2)
                {
                    System.out.println("Paper!");
                }
                else
                {
                    System.out.println("Scissors!");
                }
                System.out.print("You selected: ");
                if(choice == 1)
                {
                    System.out.println("Rock!");
                }
                else if (choice == 2)
                {
                    System.out.println("Paper!");
                }
                else
                {
                    System.out.println("Scissors!");
                }
                // End of logic for what to display on messages
                System.out.println("Your High Score: " + hscorerps + "!");
                System.out.println("Your Current Score: " + score + "\n");
                System.out.println("1 - Yes");
                System.out.println("2 - No\n");
                System.out.println("=================================\n");
                System.out.print("Please select an option! - Selection: ");
                choice = scanner.nextInt();
                System.out.println("");
                score = 0;

                if(score > hscorerps) // Update high score
                {
                    hscorerps = score;
                }
                if (choice == 1)
                {
                    //this will just loop
                }
                if (choice == 2)  // Exit game scenario
                {
                    quit = true;
                }
                else // Invalid Choice
                {
                    System.out.println("=================================\n");
                    System.out.println("Invalid choice made! - Please try again!\n");
                }
            }
            else if (botchoice == choice) // Draw scenario
            {

                System.out.println("=================================\n");
                System.out.println("You Draw! - Close one - Want to play again?");
                System.out.print("We selected: ");

                // Start logic for what to display on messages
                if(botchoice == 1)
                {
                    System.out.println("Rock!");
                }
                else if (botchoice == 2)
                {
                    System.out.println("Paper!");
                }
                else
                {
                    System.out.println("Scissors!");
                }
                // End of logic for what to display on messages
                System.out.println("Your High Score: " + hscorerps + "!");
                System.out.println("Your Current Score: " + score + "\n");
                System.out.println("1 - Yes");
                System.out.println("2 - No\n");
                System.out.println("=================================\n");
                System.out.print("Please select an option! - Selection: ");
                choice = scanner.nextInt(); // creates a scnnner to detect the user selection
                System.out.println("");

                if(score > hscorerps) // Update high score
                {
                    hscorerps = score;
                }

                if (choice == 1) // retry
                {
                    //this will just loop
                }
                else if (choice == 2)
                {
                    score = 0;
                    quit = true;
                }
                else // Invalid Choice
                {
                    System.out.println("=================================\n");
                    System.out.println("Invalid choice made! - Please try again!\n");
                }
            }
            else // User won scenario
            {
                score = score + 1;

                if(score > hscorerps) // Update high score
                {
                    hscorerps = score;
                }

                System.out.println("=================================\n");
                System.out.println("You Win! - Well Played - Let's play again!");
                System.out.print("I selected: ");

                if(botchoice == 1) // Start of logic for what to display on messages
                {
                    System.out.println("Rock!");
                }
                else if (botchoice == 2)
                {
                    System.out.println("Paper!");
                }
                else
                {
                    System.out.println("Scissors!");
                }
                System.out.print("You selected: ");
                if(choice == 1)
                {
                    System.out.println("Rock!");
                }
                else if (choice == 2)
                {
                    System.out.println("Paper!");
                }
                else
                {
                    System.out.println("Scissors!");
                } // End of logic for what to display on messages
            }
        }
        scanner.close();
    }
}
