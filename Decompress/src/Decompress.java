import java.io.*;
import java.lang.*;
import java.util.*;

public class Decompress{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        LinkedList ll;
        boolean repeat = true;

        if(args.length > 0){
            String filename = args[0];
            ll = new LinkedList();
            ll = DictSet(ll);

            try {
                long startTime = System.nanoTime();
                decompress(filename, ll);
                long elapTime = System.nanoTime() - startTime;
                writeLogFile(filename,elapTime);
            }
            catch(IOException e){
                //ok cool now what
            }
        }
        
        while(repeat){
            System.out.println("\nWelcome!\n\nEnter the name of the file you would like to decompress: ");
            String filename = input.nextLine();
            ll = new LinkedList();
            ll = DictSet(ll);
            try {
                long startTime = System.nanoTime();
                decompress(filename, ll);
                long elapTime = System.nanoTime() - startTime;
                writeLogFile(filename,elapTime);
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
        }
    }

    private static void decompress(String filename, LinkedList ll) throws IOException {
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
            System.out.println(q + "\t" + p);
            q = p;
        }
        System.out.println(q + "\t" + p);
        in.close();

        writeTextFile(filename,output);

        System.out.println(output);
    }

    private static String process(int q, int p, LinkedList ll){
        String temp;
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

    private static LinkedList DictSet(LinkedList ll){
        for(int i = 0; i < 256; i++){
            ll.ListInsert(i,Character.toString((char)i));
        }
        System.out.println(ll.ListLength());
        return ll;
    }

    public static void writeTextFile(String filename, String output){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(filename.substring(0,filename.length() - 4)));
            out.write(output);
            out.close();
        }
        catch(IOException e){
            //exception handling
        }
    }

    public static void writeLogFile(String filename, long elapTime){
        try{
            PrintWriter out = new PrintWriter(filename.substring(0,filename.length() - 4)+".log", "UTF-8");
            double seconds = (double)elapTime/1000000000;
            out.print("Decompression for file "+filename+".\nDecompression took "+seconds+".\nThe table was doubled 0 times.");
            out.close();
        }
        catch(IOException e){
            //exception handling
        }
    }
}