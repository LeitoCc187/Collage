
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;

/**
 *
 * @author D00281856
 */
public class Question1 {

    public static void main(String[] args) 
    {
        // Creating vars
        Scanner keyborad = new Scanner(System.in);
        int booknum;
        
        // User input
        System.out.println("Please enter the number of books you borrowed in the past month:");
        booknum = keyborad.nextInt();
        
        // Logic for Bagde achieved
        if(booknum > 10)
        {
           // badge level 1
            System.out.println("Congratulations! You achieved the highest level badge: Level 1");
        }
        else if(booknum <=10 || booknum > 7)
        {
            // badge level 2
            System.out.println("Congratulations! You achieved the following badge: Level 2");
        }
        else if(booknum <=7 || booknum > 5)
        {
            // badge level 3
            System.out.println("Congratulations! You achieved the following badge: Level 3");
        }
        else
        {
            //badge Novice
            System.out.println("You achieved the following badge: Novice");
        }
    }
}
