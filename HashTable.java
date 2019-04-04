import java.util.*;
import java.lang.*;
import java.io.*;

public class HashTable {
    LinkedList<Integer>[] table;
    private int numItems;
    
    public HashTable(LinkedList<Integer>[] input){
        table = input;
    }
    
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
        LinkedList<Integer> test = table[(entry)%(table.length)];
        if(test==null){
            return false;
        }
        else{
            if(test.contains(entry)){
                return true;
            }
            else{
                return false;
            }
        }
    }

    public HashTable Rehash(int size){
        LinkedList<Integer>[] temp = new LinkedList[size];
        int index = 0;
        for(int i = 0; i < table.length; i++){
            int listLength = table[i].size();
            if(listLength > 0){
                for(int j = 0; j < listLength; j++){
                    Integer entryTemp = (int)table[i].peek();
                    if(temp[(entryTemp)%(temp.length)]==null){
                        temp[(entryTemp)%(temp.length)] = new LinkedList<Integer>();
                        temp[(entryTemp)%(temp.length)].add(entryTemp);
                    }
                    else{
                        temp[(entryTemp)%(temp.length)].add(entryTemp);
                    }
                }
            }
        }
        HashTable newTable = new HashTable(temp);
        return newTable;
    }
}