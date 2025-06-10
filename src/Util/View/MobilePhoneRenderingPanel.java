package Util.View;

public class MobilePhoneRenderingPanel extends MyRenderingPanel{
    private int deviceID;
    public MobilePhoneRenderingPanel(int deviceID) {
        this.deviceID = deviceID;
    }

    public void update(DoorJPanel showPanel) {
        super.update(showPanel);
        //设置id
        showPanel.setDoorID(deviceID);
    }
}
