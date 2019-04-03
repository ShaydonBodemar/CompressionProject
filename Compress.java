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
            HashTable table = new HashTable();
            table.InitHashTable(439);
            table = DictSet(table);
            reader(filename, table);
        }
    }
    
    public static void reader(String filename, HashTable ht){
        File file = new File(filename);
        BufferedReader freader = null;
        
        try{
            freader = new BufferedReader(new FileReader(file));
            List<Integer> compressed = new ArrayList<Integer>();
            int text = 0;
            int temp = 0;
            int code = 0;
            int i = 0;
            while((text = freader.read()) != -1){
                if(i==0){
                    temp = text;
                    code = temp;
                    i++;
                }
                else if(i>1){
                    temp += (text)*(Math.pow(37,i));
                    if(ht.CheckPresent(text)){
                        code = temp;
                        i++;
                    }
                    else{
                        compressed.add(code);
                        i = 1;
                        temp = text;
                        code = text;
                    }
                }
            }
            
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
    
    public static HashTable DictSet(HashTable ht){
        for(int i = 0; i <= 255; i++){
            ht.DictEntry(i);
        }
        return ht;
    }

}