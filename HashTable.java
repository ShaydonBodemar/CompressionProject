import java.util.*;
import java.lang.*;
import java.io.*;

public class HashTable {
    LinkedList<Integer>[] table;
    private int numItems;
    
    public HashTable(int size){
        table = new LinkedList[size];
    }
    
    public void DictEntry(Integer entry){
         if(table[(entry)%(table.length)]==null){
             table[(entry)%(table.length)] = new LinkedList<Integer>();
             table[(entry)%(table.length)].add(entry);
         }
         else{
             table[(entry)%(table.length)].add(entry);
         }
    }

    public void InitHashTable(int size){
        table = new LinkedList[size];
    }
    
    public boolean CheckPresent(Integer entry){
        if(table[(entry)%(table.length)]==null){
            return false;
        }
        else{
            for(int i = 0; i < table[(entry)%(table.length)].size(); i++){
                Integer code = table[(entry)%(table.length)].get(i);
                if(entry==code){
                    return true;
                }
            }
            return false;
        }
    }

    public void Rehash(int size){
        LinkedList<Integer>[] temp = new LinkedList[size];
        int index = 0;
        for(int i = 0; i < table.length; i++){
            int listLength = table[i].size();
            if(listLength > 0){
                for(int j = 0; j < listLength; j++){
                    Integer entryTemp = (int)table[i].peek();
                    temp[(entryTemp)%(temp.length)].add(entryTemp);
                }
            }
        }
    }
}