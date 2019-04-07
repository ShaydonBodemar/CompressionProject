//The Compress method uses this class and methods to implement a hash table as an array of LinkedLists
public class LinkedList
{
    private Node head;
    private int numItems;

    public LinkedList()
    {
        head = null;
        numItems = 0;
    }

    public Node getHead() {
        return head;
    }

    public boolean ListIsEmpty()
    {
        return numItems == 0;
    }

    public int ListLength()
    {
        return numItems;
    }

    private Node Locate(int position)
    {
        Node curr = head;
        while (position > 1)
        {
            curr = curr.getNext();
            position--;
        }
        return curr;
    }

    public boolean ContainsCode(int code)
    {
        for (int i = 1; i <= numItems; i++)
        {
            if (RetrieveCodeAt(i) == code)
                return true;
        }
        return false;
    }

    public int RetrieveCodeAt(int position) throws IndexOutOfBoundsException
    {
        if (position >= 1 && position <= numItems)
        {
            Node curr = Locate(position);
            return curr.getCode();
        }
        else
        {
            throw new IndexOutOfBoundsException("Error.  Attempted to retrieve at an invalid index in RetrieveCodeAt()");
        }
    }

    public void ListInsert(int code)
    {
        Node newNode = new Node(code);
        if(head == null)
        {
            head = newNode;
            numItems++;
        }
        else
        {
            Node curr = head;
            while(curr.getNext()!=null)
            {
                curr = curr.getNext();
            }
            curr.setNext(newNode);
            numItems++;
        }
    }
}