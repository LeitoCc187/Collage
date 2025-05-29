/*
 * Ask the user to input a runner’s name and neatly display all information regarding this runner,
 * including their average running time and speed. If the name is not available, 
 * display an error message to the user. 
 */
package arrays.test.araystest;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author D00281856
 */
public class Question2 
{
    public static void main(String[] args) throws IOException
    {
        // red File object with a file on disk
        File inputFile = new File("Races.txt");

        int numberRunners = 5;
        //link Scanner object with the File
        Scanner input = new Scanner(inputFile);
        String[] firstName = new String[numberRunners];
        String[] lastName = new String[numberRunners];
        double[] time1 = new double[numberRunners];
        double[] time2 = new double[numberRunners];
        double[] time3 = new double[numberRunners];
        double[] average = new double[numberRunners];
        double[] averageSpeed = new double[numberRunners];
        System.out.print("First Name\tLast Name\tRace1\tRace2\tRace3\tAverage Time\tAverage Speed\n");
        
        for(int i = 0; i < numberRunners; i++)
        {
            firstName[i] = input.next();
            lastName[i] = input.next();
            time1[i] = input.nextDouble();
            time2[i] = input.nextDouble();
            time3[i] = input.nextDouble();
            average[i] = (time1[i]+time2[i]+time3[i])/3;
            averageSpeed[i] = 5/average[i];
            
            System.out.printf("%s\t\t%-10s\t%.1f\t%.1f\t%.1f\t%.2f\t\t%.4f\n",firstName[i],lastName[i],time1[i],time2[i],time3[i],average[i],averageSpeed[i]);
        }
    }
}
