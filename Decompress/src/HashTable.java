import java.lang.*;

/**
 * Shaydon Bodemar
 * 4/4/2019
 */
public class HashTable
{
    Entry table[];
    private int numItems;

    public HashTable(Entry[] input){
        table = input;
    }

    public HashTable(int size)
    {
        table = new Entry[size];
    }

    public void DictEntry(int code, String entry){
        table[numItems].set(code,entry);
        numItems++;
    }

    public HashTable Rehash(int size){
        Entry[] temp = new Entry[size];
        for(int i = 0; i < numItems; i++){
            temp[i] = table[i];
        }
        HashTable newTable = new HashTable(temp);
        return newTable;
    }

    public int Length(){
        return table.length;
    }

    public int Size(){
        return numItems;
    }
}