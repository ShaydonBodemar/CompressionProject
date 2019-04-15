import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

public class Decompress{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean repeat = true;
        
        while(repeat){
            System.out.println("\nWelcome!\n\nEnter the name of the file you would like to decompress: ");
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
        String output = "";
        try{
            DataInputStream in = new DataInputStream(new FileInputStream(filename));
            int q = in.readInt();
            int qlength = ht.GetText(ht.Contains(q)).length();
            output += ht.GetText(ht.Contains(q));
            int p = in.readInt();
            String temp;
            int nextCode;
            int index;
            
            while(true){
                nextCode = in.readInt();
                index = ht.Contains(p);
                System.out.println(index);
                if(index != -1){
                    temp = ht.GetText(index);
                    output += temp;
                    ht.DictEntry(q + (int)Math.pow(37,qlength)*temp.charAt(0), ht.GetText(ht.Contains(q)) + temp.substring(0,1));
                }
                else{
                    System.out.println("Entry found");
                    temp = ht.GetText(ht.Contains(q));
                    temp += temp.substring(0,1);
                    output += temp;
                    System.out.println(output + "\n");
                    ht.DictEntry(p, temp);
                }

                q = p;
                p = nextCode;
            }
        }
        catch(FileNotFoundException e){
            //handle exception e
            System.out.println("File not found");
        }
        catch(EOFException e){
            System.out.println(output);
        }
        catch(IOException e){
            //donothing
        }
        finally{
            //handle general case
        }
        
    }

    public static HashTable DictSet(HashTable ht){
        for(int i = 0; i < 256; i++){
            ht.DictEntry(i,Character.toString((char)i));

            //rehashing may not be necessary using a LinkedList() approach
            /*if(checkSize(ht)){
                ht = ht.Rehash(getPrime(ht.Length()));
            }*/
        }
        System.out.println(ht.Length());
        return ht;
    }

    //only useful if rehashing is necessary
    /*public static boolean checkSize(HashTable ht){
        if(ht.Length() >= (0.9)*ht.Size()){
            return true;
        }
        else{
            return false;
        }
    }*/

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