/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author D00281856
 */

import java.util.Random;

public class ExamQuestion1 {
    public static void main(String[] args)
    {
        int pot1Balls=0,pot2Balls=0,round=0;
        String pot1, pot2;
        boolean match = false, red = true, blue = false;
        
        // Creating random number generator for the game
        Random rand = new Random();
        
        while(!match)
        {
            round++;
            pot1Balls = rand.nextInt(8) + 1;
            pot2Balls = rand.nextInt(6) + 1;
            
            if(pot1Balls <= 4)
            {
                pot1 = "Pot 1: Red " + pot1Balls;
                red = true;
                blue = false;
            }
            else
            {
                pot1Balls = pot1Balls/2;
                pot1 = "Pot 1: Blue " + pot1Balls;
                red = false;
                blue = true;
            }
            
            pot2 = "Pot 2: Blue " + pot2Balls;
           
            if(pot1Balls == pot2Balls && blue == true)
            {
                System.out.println("Round " + round + ":\n" + pot1 + "\n" + pot2 + "\nMatch!");
                match = true;
            }
            else
            {
                System.out.println("Round " + round + ":\n" + pot1 + "\n" + pot2 + "\nNo Match");
            }
        }
        System.out.println("A match occurred after " + round + " rounds.");
    }      
}
