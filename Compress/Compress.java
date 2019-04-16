import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

public class Compress{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean repeat = true;

        if(args.length > 0){
            String filename = args[0];
            HashTable table = new HashTable(439);
            table = DictSet(table);
            reader(filename, table);
        }
        
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
        File file = new File(filename);
        BufferedReader freader = null;
        long initTime;
        long elapTime;
        int rehashed = 0;
        int text = 0;
        int temp = 0;
        int code = 0;
        int i = 0;
        
        try{
            initTime = System.nanoTime();
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
                    rehashed++;
                }
            }
            compressed.add(temp);
            elapTime = System.nanoTime() - initTime;

            
            fileWriter(filename, compressed);
            logFileWriter(filename,ht,(double)elapTime/1000000000,rehashed);
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
    
    public static void logFileWriter(String filename, HashTable ht, double time, int rehashed){
        File file1 = new File(filename);
        File file2 = new File(filename+".zzz");
        double initLenKB = (double)file1.length()/1024;
        double finalLenKB = (double)file2.length()/1024;

        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(filename+".zzz.log"));
            out.write("Compression of "+filename+"\n");
            out.write("Compressed from "+round(initLenKB,3)+"KB to "+round(finalLenKB,3)+"KB\n");
            out.write("Compression took "+round(time,6)+" seconds\n");
            out.write("Hash table is "+round(((double)ht.NumLists()/ht.Length())*100,3)+"% full\n");
            out.write("The average linked list is "+round((double)ht.Size()/(double)ht.NumLists(),3)+" elements long\n");
            out.write("The longest list contains "+ht.LongestList()+" elements\n");
            out.write("The dictionary contains "+ht.Size()+" total entries\n");
            out.write("The dictionary was rehashed "+rehashed+" times");
            out.close();
        }
        catch(IOException e){
            //handle this bad boy
        }
    }

    private static double round(double value, int places){
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}