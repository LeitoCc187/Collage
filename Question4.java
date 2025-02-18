
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;

/**
 *
 * @author D00281856
 */
public class Question4 {

    public static void main(String[] args) 
    {
        // Creating vars
        Scanner keyborad = new Scanner(System.in);
        double average;
        int booknum = 0, totalbooks = 0,months;
        
        //input amount of months you wanna check
        System.out.println("Please input the number of months you wish to check: ");
        months = keyborad.nextInt();
        
        // Loop
        for(int i = 1 ; i <= months ; i++)
        {
            // User input
            System.out.println("Please input the number of books you borrowed in Month " + i + ":");
            booknum = keyborad.nextInt();
            
            totalbooks = totalbooks + booknum;
        }

        // Average
        average = (double)totalbooks/months;
        
        //ui display
        System.out.println("Total Number of Books Borrowed in the last " + months + " months: " + totalbooks);
        System.out.printf("Average Number of Books Borrowed in the past " + months + "months: %.01f", average);
    }
}