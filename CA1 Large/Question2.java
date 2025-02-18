
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;

/**
 *
 * @author D00281856
 */
public class Question2 {

    public static void main(String[] args) 
    {
        // Creating vars
        Scanner keyborad = new Scanner(System.in);
        double average;
        int booknum = 0, totalbooks = 0;
        final int MONTHS = 3;
        
        // question
        System.out.println("Please enter the number of books you borrowed in the past 3 months");
        // Loop
        for(int i = 1 ; i <= MONTHS ; i++)
        {
            // User input
            System.out.println("How many books did you borrow in Month " + i + "?");
            booknum = keyborad.nextInt();
            
            totalbooks = totalbooks + booknum;
        }
        
        // Average
        average = (double)totalbooks/MONTHS;
        System.out.println("Average Number of books in the last 3 Months: " + average);
        System.out.println("Total Number of Books Borrowed in the last 3 Months: " + totalbooks);
        
        // Logic for vouchers
        if(totalbooks > 30)
        {
           // voucher 50
            System.out.println("Congratulations! You will recive a $50 voucher");
        }
        else if(totalbooks >=7 && average > 8.0)
        {
            // voucher 30
            System.out.println("Congratulations! You will recive a $30 voucher");
        }
        else if(totalbooks >=5 && average > 6.0)
        {
            // voucher 10
            System.out.println("Congratulations! You will recive a $10 voucher");
        }
        else
        {
            // did not get a voucher
            System.out.println("You did not qualify for a voucher");
        }
    }
}
