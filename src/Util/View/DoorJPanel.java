package Util.View;

public abstract class DoorJPanel extends MyJPanel {
    private int doorID;
    public DoorJPanel(int doorID) {
        super();
        this.doorID = doorID;
    }

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }
}
