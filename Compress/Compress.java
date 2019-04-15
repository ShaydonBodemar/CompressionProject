import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

public class Compress{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean repeat = true;
        
        while(repeat){
            System.out.println("\nWelcome!\n\nEnter the name of the file you would like to compress: ");
            String filename = input.nextLine();
            HashTable table = new HashTable(89);
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
        File file = new File(filename);
        BufferedReader freader = null;
        int text = 0;
        int temp = 0;
        int code = 0;
        int i = 0;
        
        try{
            freader = new BufferedReader(new FileReader(file));
            ArrayList<Integer> compressed = new ArrayList<Integer>();
            while((text = freader.read()) != -1){
                if(i==0){
                    temp = text;
                    code = temp;
                    i++;
                }
                else if(i>=1){
                    temp += (text)*(Math.pow(37,i));
                    if(ht.CheckPresent(temp)){
                        code = temp;
                        i++;
                    }
                    else{
                        compressed.add(code);
                        ht.DictEntry(temp);
                        i = 1;
                        temp = text;
                        code = text;
                    }
                }
                
                
                if(checkSize(ht)){
                    ht = ht.Rehash(getPrime(ht.Length()));
                }
            }
            compressed.add(temp);
            
            
            fileWriter(filename, compressed);
            //test print 2
            System.out.println(compressed);
        }
        catch(FileNotFoundException e){
            //handle exception e
        }
        catch(IOException e){
            //handle exception e
        }
        finally{
            //handle general case
        }
    }
    
    public static boolean checkSize(HashTable ht){
        int numItems = ht.Size();
        if((numItems/(ht.Length())) >= 0.9){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static HashTable DictSet(HashTable ht){
        for(int i = 0; i <= 255; i++){
            ht.DictEntry(i);
            
            if(checkSize(ht)){
                ht = ht.Rehash(getPrime(ht.Length()));
            }
        }
        System.out.println(ht.Length());
        return ht;
    }
    
    public static int getPrime(int current){
        int[] primes = {89,439,829,1373,1823,2459,2957,3613,4349,5023,5839,6761,7759,10067,12757,15061,20089,30089};
        for(int i = 0; i < primes.length; i++){
            if(current==primes[i]){
                return primes[i+1];
            }
        }
        return -1;
    }

    public static void fileWriter(String filename, ArrayList<Integer> data){
        try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(filename+".zzz"));
            for(int i: data){
                out.writeInt(i);
            }
            out.close();
        }
        catch(IOException e){
            
        }
    }
    
    public static void logFileWriter(){
        
    }
}