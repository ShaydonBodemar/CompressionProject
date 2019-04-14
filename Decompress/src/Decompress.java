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
            HashTable table = new HashTable(439);
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
        finally{
            //handle general case
        }
        
    }

    public static HashTable DictSet(HashTable ht){
        for(int i = 0; i < 256; i++){
            ht.DictEntry(i,Integer.toString(i));

            if(checkSize(ht)){
                ht = ht.Rehash(getPrime(ht.Length()));
            }
        }
        System.out.println(ht.Length());
        return ht;
    }

    public static boolean checkSize(HashTable ht){
        if(ht.Length() >= (0.9)*ht.Size()){
            return true;
        }
        else{
            return false;
        }
    }

    public static int getPrime(int current){
        int[] primes = {439,829,1373,1823,2459,2957,3613,4349,5023,5839,6761,7759,10067,12757,15061,20089,30089};
        for(int i = 0; i < primes.length; i++){
            if(current==primes[i]){
                return primes[i+1];
            }
        }
        return -1;
    }
}