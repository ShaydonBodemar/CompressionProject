import java.lang.*;

public class HashTable {
    LinkedList[] table;
    private int numItems;
    private int numLists;
    private int longest;

    public HashTable(LinkedList[] input){
        table = input;
    }
    
    public HashTable(int size){
        table = new LinkedList[size];
        longest = 1;
    }
    
    public void DictEntry(Integer entry){
         if(table[(entry)%(table.length)]==null){
             table[(entry)%(table.length)] = new LinkedList();
             table[(entry)%(table.length)].ListInsert(entry);
             numLists++;
         }
         else{
             table[(entry)%(table.length)].ListInsert(entry);
             if(table[(entry)%(table.length)].ListLength() > longest){
                 longest++;
             }
         }
         numItems++;
    }
    
    public int Length(){
        return table.length;
    }
    
    public int Size(){
        return numItems;
    }

    public int NumLists(){
        return numLists;
    }

    public int LongestList(){
        return longest;
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
        longest = 1;
        numLists = 0;
        LinkedList[] temp = new LinkedList[size];
        for(int i = 0; i < table.length; i++){
            int listLength = table[i].ListLength();
            if(listLength > 0){
                for(int j = 1; j <= listLength; j++){
                    Integer entryTemp = (int)table[i].RetrieveCodeAt(j);
                    if(temp[(entryTemp)%(temp.length)]==null){
                        temp[(entryTemp)%(temp.length)] = new LinkedList();
                        temp[(entryTemp)%(temp.length)].ListInsert(entryTemp);
                        numLists++;
                    }
                    else{
                        temp[(entryTemp)%(temp.length)].ListInsert(entryTemp);
                        if(temp[(entryTemp)%(temp.length)].ListLength() > longest){
                            longest++;
                        }
                    }
                }
            }
        }
        HashTable newTable = new HashTable(temp);
        return newTable;
    }
}