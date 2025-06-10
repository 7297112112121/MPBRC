package Util.View;

public abstract class UserJFrame extends MyJFrame{
    private int userID;
    private int deviceID;
    public UserJFrame() {
        this.userID = userID;
        this.deviceID = deviceID;
    }

    @Override
    public abstract MyRenderingPanel getRendering();

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }
}
