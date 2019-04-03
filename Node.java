import java.util.*;

public class Node //creates and makes calls to methods within ListRefBased in a manner that is simple for the user to understand
    {
    private Object item;
    private Node next;

    //creates a new node with an Object and a reference to the next Node
    public Node(Object newItem) 
    {
        item = newItem;
        next = null;
    }

    //creates a new node in a linked list which already has some nodes
    public Node(Object newItem, Node newNode) 
    {
        item = newItem;
        next = newNode;
    }

    //reference the object within the Node
    public Object getItem() 
    {
        return item;
    }

    //finds the Node that follows the current one
    public Node getNext() 
    {
        return next;
    }

    //changes the Object contained by a Node into a new Object
    public void setItem(Object newItem) 
    {
        item = newItem;
    }

    //sets the reference for the Node that is to follow the current one
    public void setNext(Node newNode) 
    {
        next = newNode;
    }
}