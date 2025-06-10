package Util;

import Util.View.MyJPanel;
import Util.View.MyRenderingPanel;

public class DoorMyRenderingPanel extends MyRenderingPanel {
    private int doorID;
    public DoorMyRenderingPanel(int doorID) {
        super();
        this.doorID = doorID;
    }
    public void update(MyJPanel showPanel){
        super.update(showPanel);
    }
}
