package Util.View;

public class MobilePhoneJFrame extends MyJFrame{
    private int deviceID;
    public MobilePhoneJFrame(int deviceID) {
        this.deviceID = deviceID;
    }
    @Override
    public MyRenderingPanel getRendering() {
        return null;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }
}
