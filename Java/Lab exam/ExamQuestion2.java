
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author D00281856
 */
public class ExamQuestion2 {
    public static void main(String[] args)
    {
        final int storage = 3;
        int count = 0;
        
        String[] movies = new String[storage];
    
        Scanner input = new Scanner(System.in);
        
        for(int i = 0 ; i < storage ; i++)
        {
            System.out.println("Please enter movie title " + (i+1));
            movies[i] = input.next();
        }
        
        System.out.println("The movie titles you enterd are: \n[" + movies[1].toString() + ", " + movies[2].toString() + ", " + movies[3].toString() + "]\n");
        
        System.out.println("Total lenght of all titles is " + (movies[1].length() + movies[2].length() + movies[3].length()) + " characters.");
        
        for (int i = 0 ; i < movies[i].length() ; i++) 
        {            
            if (Character.toLowerCase(movies.charAt(i)).equals.a) 
            {
                count++;
            }
        }
        
        System.out.println("The number of titles with at least one 'a' is " + count);
        
        System.out.println("The total number of words in the titles is " + count);
    }
}
