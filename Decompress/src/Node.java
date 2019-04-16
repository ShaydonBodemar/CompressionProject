public class Node
{
    private int code;
    private String text;
    private Node next;

    public Node()
    {
        code = 0;
        text = null;
        next = null;
    }

    public Node(int newCode, String newText)
    {
        code = newCode;
        text = newText;
        next = null;
    }

    public Node(int newCode, String newText, Node nextNode)
    {
        code = newCode;
        text = newText;
        next = nextNode;
    }

    public int getCode()
    {
        return code;
    }

    public String getText(){
        return text;
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