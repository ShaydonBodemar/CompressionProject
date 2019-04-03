import java.util.*;
import java.lang.*;

public class HashTable {
    private LinkedList<Integer>[] table;
    private int numItems;
    
    public HashTable(){
        table = null;
    }
    
    public void DictEntry(Integer entry){
         table[(entry)%(table.length)].append(entry);
    }

    public void InitHashTable(int size){
        table = new LinkedList[size];
    }
    
    public boolean CheckPresent(Integer entry){
        for(int i = 0; i < table[(entry)%(table.length)].getLength(); i++){
            if(entry==(table[(entry)%(table.length)].peek())){
                return true;
            }
        }
        return false;
    }

    public void Rehash(int size){
        LinkedList<Integer>[] temp = new LinkedList[size];
        int index = 0;
        for(int i = 0; i < table.length; i++){
            int listLength = table[i].getLength();
            if(listLength > 0){
                for(int j = 0; j < listLength; j++){
                    Integer entryTemp = (int)table[i].peek();
                    temp[(entryTemp)%(temp.length)].append(entryTemp);
                }
            }
        }
    }
}