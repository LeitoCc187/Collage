
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;

/**
 *
 * @author D00281856
 */
public class Question3 {

    public static void main(String[] args) 
    {
        // Creating vars
        Scanner keyborad = new Scanner(System.in);
        
        String firstname = null, lastname = null;
        double average;
        int booknum = 0, booknum1 = 0, booknum2 = 0, booknum3 = 0, totalbooks = 0;
        final int MONTHS = 3;
        
        System.out.println("Please enter you first name: ");
        firstname = keyborad.next();

        System.out.println("Please enter you first name: ");
        lastname = keyborad.next();
        
        // Loop
        for(int i = 1 ; i <= MONTHS ; i++)
        {
            // User input   
            System.out.println("How many books did you borrow in Month " + i + "?");
            booknum = keyborad.nextInt();
            
            if (booknum >=0)
            {
                if(i == 1)
                {
                    booknum1 = booknum;
                }
                else if (i == 2)
                {
                    booknum2 = booknum;
                }
                else if (i == 3)
                {
                    booknum3 = booknum;
                }
            }
            else
            {
                System.out.println("Invalid Number of books. Please enter a valid Number: ");
                booknum = keyborad.nextInt();
                
                if (booknum >=0)
                {
                    if(i == 1)
                    {
                        booknum1 = booknum;
                    }
                    else if (i == 2)
                    {
                        booknum2 = booknum;
                    }
                    else if (i == 3)
                    {
                        booknum3 = booknum;
                    }
                }
                else
                {
                    System.out.println("Invalid Number of books. Please enter a valid Number: ");
                    booknum = keyborad.nextInt();
                    if (booknum >=0)
                    {
                        if(i == 1)
                        {
                            booknum1 = booknum;
                        }
                        else if (i == 2)
                        {
                            booknum2 = booknum;
                        }
                        else if (i == 3)
                        {
                            booknum3 = booknum;
                        }
                    }
                    else
                    {
                        System.out.println("Invalid Number of books. Please enter a valid Number: ");
                    }
                }
            }
            totalbooks = totalbooks + booknum;
        }

        // Average
        average = (double)totalbooks/MONTHS;
        
        //ui display
        System.out.println("\n\nName: " + firstname + " " + lastname);
        System.out.println("Books borrowed in Month 1: " + booknum1);
        System.out.println("Books borrowed in Month 2: " + booknum2);
        System.out.println("Books borrowed in Month 3: " + booknum3);
        System.out.println("Total number of books in 3 Months: " + totalbooks);
        System.out.printf("Average number of books (3 Months): %.01f\n", average);
        
        // Logic for vouchers
        if(totalbooks > 30)
        {
           // voucher 50
            System.out.println("Voucher Value: $50");
        }
        else if(totalbooks >=7 && average > 8.0)
        {
            // voucher 30
            System.out.println("Voucher Value: $30");
        }
        else if(totalbooks >=5 && average > 6.0)
        {
            // voucher 10
            System.out.println("Voucher Value: $10");
        }
        else
        {
            // did not get a voucher
            System.out.println("Voucher Value: $0");
        }
    }
}

