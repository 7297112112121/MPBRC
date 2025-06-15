package Util.insert;

public class ContextInsert {
    private Insert insert;

    public void setInsert(Insert insert) {
        this.insert = insert;
    }

    public int insert(String fromName ,String... parms) {
        return insert.insert(fromName, parms);
    }
}
