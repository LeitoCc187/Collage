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
public class gd1BTestP2 {
    public static void main(String[] args)
    {
        //Decleration
        final double standingCharge = 2.5;
        final double tutorial = 1.5;
        final double discountAmount = 0.15;
        Scanner input = new Scanner(System.in);
        int compleatedTutorials;
        double discount;
        double total;
        double monthly;
        
        //Ask for user input
        System.out.println("Please enter the number of completed Tutorials: ");
        compleatedTutorials = input.nextInt();
        
        //deciding on if they get the discound
        if (compleatedTutorials >= 15)
        {
            //Calculate total cost of bill and display it
            monthly = standingCharge +(compleatedTutorials*tutorial);
            discount = monthly /discountAmount;
            total = monthly - discount;
            System.out.printf("Monthly Charge:\tf.2%",total);
            System.out.printf("Discount:\tf.2%",discount);
            System.out.printf("Total Due:\tf.2%",total);
        }
        else
        {
            //Calculate total cost of bill and display it
            monthly = standingCharge +(compleatedTutorials*tutorial);
            total = monthly;
            System.out.printf("Monthly Charge:\tf.2%",monthly);
            System.out.println("Discount:\t0.00");
            System.out.printf("Total Due:\tf.2%",total);
        }
    }
}
