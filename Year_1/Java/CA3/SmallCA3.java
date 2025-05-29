/*
    Write a program that reads from file “Races.txt”. This file contains a set of runners’
    names (First and Last Names) and their running times for three separate 5km races (in minutes).

(i)	Please write an application to display each runner, their running time for each race and the 
        average time for each runner. 

(ii)	Modify the table by displaying the average speed in km/minute for each runner (distance/time). 
        The speed should be displayed with 4 decimal places.

(iii)	Identify and display the runner with the highest speed and the runner with the lowest speed. 
 */

package colecumiskey.smallca3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class SmallCA3 {

    public static void main(String[] args) throws FileNotFoundException 
    {
        // Link File to a file on disk
        File racesFile = new File("Races.txt");
        //Link Scanner to the file
        
        Scanner input = new Scanner(racesFile);
        String name = null;
        int raceTime = 0;
        int raceDistance = 0;
        int raceSpeed = 0;
        double average;
        
        System.out.print("First Name \tLast Name");
        while (input.hasNext()) // more values
        {
            name = input.nextLine();
            System.out.print(name);
            raceTime = input.nextInt(); // reads next int
            System.out.print(" " + raceTime);
            raceDistance = input.nextInt(); // reads next int
            System.out.print(" " + raceDistance);
            raceSpeed = input.nextInt(); // reads next int
            System.out.print(" " + raceSpeed + "\n");
            average = (raceDistance / raceTime);
        }
    }
}
