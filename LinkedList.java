
/**
 * Provides functions for LinkedList creation and use.
 *
 * Shaydon Bodemar
 * 4-1-2019
 */
public class LinkedList
{
    private Node first;
    private Node current;
    private Node last;
    private int length = 0;

    public LinkedList()
    {
        first = null;
        current = null;
    }
    
    public boolean ListIsEmpty(){
        return(first==null);
    }

    public Object peek()
    {
        if(current!=null){
            Object temp = current.getItem();
            current = current.getNext();
            return temp;
        }
        else{
            return null;
        }
    }
    
    public void append(Object newItem){
        if(ListIsEmpty()){
            first = new Node(newItem);
            current = first;
            last = first;
            length++;
        }
        else{
            Node temp = new Node(newItem);
            last.setNext(temp);
            last = last.getNext();
            length++;
        }
    }
    
    public int getLength(){
        return length;
    }
}

