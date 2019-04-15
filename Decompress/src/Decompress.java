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
            LinkedList ll = new LinkedList();
            ll = DictSet(ll);
            try {
                decompress(filename, ll);
            }
            catch(IOException e){
                //ok cool now what
            }
            
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

    public static void decompress(String filename, LinkedList ll) throws IOException {
        String output = "";
        int p, q;

        DataInputStream in = new DataInputStream(new FileInputStream(filename));
        File f = new File(filename);

        p = in.readInt();
        output += ll.RetrieveTextAt(p+1);
        q = p;

        for(int i = 0 ; i < ((f.length()-4) / 4) ; i++){
            p = in.readInt();
            output += process(q,p,ll);
            q = p;
        }
        in.close();

        System.out.println(output);
    }

    public static String process(int q, int p, LinkedList ll){
        String temp = "";
        int index;

        if((index = ll.ContainsCode(p)) != -1){
            temp = ll.RetrieveTextAt(index);
            ll.ListInsert(q + (int)Math.pow(37,ll.RetrieveTextAt(ll.ContainsCode(q)).length())*temp.charAt(0), ll.RetrieveTextAt(ll.ContainsCode(q)) + temp.charAt(0));
        }
        else{
            temp = ll.RetrieveTextAt(ll.ContainsCode(q));
            temp += temp.substring(0, 1);
            ll.ListInsert(p, temp);
        }
        return temp;
    }

    
    /*public static void reader(String filename, LinkedList ll){
        String output = "";
        try{
            DataInputStream in = new DataInputStream(new FileInputStream(filename));
            int q = in.readInt();
            output += ll.RetrieveTextAt(ll.ContainsCode(q));
            int p = in.readInt();
            String temp;
            int nextCode;
            int index;
            
            while(true) {
                nextCode = in.readInt();
                index = ll.ContainsCode(p);
                System.out.println(index);
                if (index != -1) {
                    temp = ll.RetrieveTextAt(index);
                    output += temp;
                    ll.ListInsert(nextCode, ll.RetrieveTextAt(ll.ContainsCode(q)) + temp.charAt(0));
                } else {
                    System.out.println("Entry found");
                    temp = ll.RetrieveTextAt(ll.ContainsCode(q));
                    temp += temp.substring(0, 1);
                    output += temp;
                    ll.ListInsert(p, temp);
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
        
    }*/

    public static LinkedList DictSet(LinkedList ll){
        for(int i = 0; i < 256; i++){
            ll.ListInsert(i,Character.toString((char)i));
        }
        System.out.println(ll.ListLength());
        return ll;
    }
}