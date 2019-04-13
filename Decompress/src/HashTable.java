import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Shaydon Bodemar
 * 4/4/2019
 */
public class HashTable
{
    Entry table[];
    private int numItems;

    public HashTable(int size)
    {
        table = new Entry[size];
    }

    public void sampleMethod(int y)
    {
        
    }

    public int Length(){
        return table.length;
    }

    public int Size() {
        return numItems;
    }
}