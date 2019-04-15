import java.lang.*;

/**
 * Shaydon Bodemar
 * 4/4/2019
 */
public class HashTable
{
    LinkedList table;
    private int numItems;

    public HashTable(LinkedList input){
        table = input;
    }

    public HashTable()
    {
        table = new LinkedList();
    }

    public void DictEntry(int code, String entry){
        table.ListInsert(code,entry);
        numItems++;
    }

    public int Contains(int code){
        return table.ContainsCode(code);
    }

    public String GetText(int index){
        return table.RetrieveTextAt(index);
    }

    //may not end up being necessary in this data structure
    //keeping it available just in case
    /*public HashTable Rehash(int size){
        Entry[] temp = new Entry[size];
        for(int i = 0; i < numItems; i++){
            temp[i] = table[i];
        }
        HashTable newTable = new HashTable(temp);
        return newTable;
    }*/

    public int Length(){
        return table.ListLength();
    }
}