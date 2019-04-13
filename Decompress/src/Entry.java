//creates the Object for Entries

public class Entry{

    private int code;
    private String entry;

    public Entry() {
        code = 0;
        entry = null;
    }

    public void setCode(int c) {
        code = c;
    }

    public void setEntry(String e) {
        entry = e;
    }

    public void set(int c, String e){
        code = c;
        entry = e;
    }
}