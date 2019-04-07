import java.util.*;
import java.lang.*;
import java.io.*;

public class HashTable {
    LinkedList[] table;
    private int numItems;
    
    public HashTable(LinkedList[] input){
        table = input;
    }
    
    public HashTable(int size){
        table = new LinkedList[size];
    }
    
    public void DictEntry(Integer entry){
         if(table[(entry)%(table.length)]==null){
             table[(entry)%(table.length)] = new LinkedList();
             table[(entry)%(table.length)].ListInsert(entry);
         }
         else{
             table[(entry)%(table.length)].ListInsert(entry);
         }
         numItems++;
    }

    public void InitHashTable(int size){
        table = new LinkedList[size];
    }
    
    public int Length(){
        return table.length;
    }
    
    public int Size(){
        return numItems;
    }
    
    public boolean CheckPresent(Integer entry){
        LinkedList test = table[(entry)%(table.length)];
        if(test==null){
            return false;
        }
        else{
            if(test.ContainsCode(entry)){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public HashTable Rehash(int size){
        LinkedList[] temp = new LinkedList[size];
        int index = 0;
        for(int i = 0; i < table.length; i++){
            int listLength = table[i].ListLength();
            if(listLength > 0){
                for(int j = 1; j <= listLength; j++){
                    Integer entryTemp = (int)table[i].RetrieveCodeAt(j);
                    if(temp[(entryTemp)%(temp.length)]==null){
                        temp[(entryTemp)%(temp.length)] = new LinkedList();
                        temp[(entryTemp)%(temp.length)].ListInsert(entryTemp);
                    }
                    else{
                        temp[(entryTemp)%(temp.length)].ListInsert(entryTemp);
                    }
                }
            }
        }
        HashTable newTable = new HashTable(temp);
        return newTable;
    }
}