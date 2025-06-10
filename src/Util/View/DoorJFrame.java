package Util.View;

public class DoorJFrame extends MyJFrame {
    private int doorID;
    public DoorJFrame(int doorID) {
        this.doorID = doorID;
    }

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    @Override
    public MyRenderingPanel getRendering() {
        return null;
    }
}
