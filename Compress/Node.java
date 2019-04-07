public class Node
{
    // private String stringEntry;
    //private char charEntry;
    private int code;
    private Node next;

    public Node()
    {
        code = 0;
        next = null;
        //charEntry = 0;
        //stringEntry = "";
    }

    public Node(int newEntry)
    {
        code=newEntry;
        next = null;
    }

    public Node (int newEntry, Node nextNode)
    {
        code = newEntry;
        next = nextNode;
    }
    
    public int getCode()
    {
        return code;
    }

    public Node getNext()
    {
        return next;
    }

    public void setNext(Node nextNode)
    {
        next = nextNode;
    }
}