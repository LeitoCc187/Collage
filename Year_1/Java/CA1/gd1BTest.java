/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.colecumiskey;

import java.util.Scanner;
/**
 *
 * @author D00281856
 */
public class gd1BTest {
    public static void main(String[] args)
    {
        //Decleration
        final double standingCharge = 2.5;
        final double tutorial = 1.5;
        Scanner input = new Scanner(System.in);
        int compleatedTutorials;
        double total;
        
        //Ask for user input
        System.out.println("Please enter the number of completed Tutorials: ");
        compleatedTutorials = input.nextInt();
        
        //Calculate total cost of bill and display it
        total = standingCharge+(compleatedTutorials*tutorial);
        System.out.println("Your next monthly bill is: " + total);
    }
}
