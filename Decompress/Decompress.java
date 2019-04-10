import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

public class Decompress{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean repeat = true;
        
        while(repeat){
            System.out.println("\nWelcome!\n\nEnter the name of the file you would like to compress: ");
            String filename = input.nextLine();
            HashTable table = new HashTable();
            table = DictSet(table);
            reader(filename, table);
            
            //ask user if he/she would like to read a new file in
            System.out.println("\n\nWould you like to repeat (y/n)?");
            char yn = input.next().charAt(0);
            input.nextLine();
            if(yn=='n') {
                repeat = false;
                System.out.println("\n\nGoodbye\n\n");
            }
            else{
                continue;
            }
        }
    }
    
    public static void reader(String filename, HashTable ht){
        try{
            DataInputStream in = new DataInputStream(new FileInputStream(filename));
            int entry = 0;
            int q = 0;
            int p = 0;
            
            while(true){
                
            }
        }
        catch(FileNotFoundException e){
            //handle exception e
        }
        catch(EOFException e){
            //handles the event of the binary file having been read all the way through
        }
        catch(IOException e){
            //handle exception e
        }
        finally{
            //handle general case
        }
        
    }
}