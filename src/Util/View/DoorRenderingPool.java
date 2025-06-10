package Util.View;

public class DoorRenderingPool extends MyRenderingPanel{
    private int doorID;
    public DoorRenderingPool(int doorID) {
        this.doorID = doorID;
    }
    public void update(DoorJPanel showPanel) {
        super.update(showPanel);
        //设置id
        showPanel.setDoorID(doorID);
    }
}
