package Util.View;

public class UserRenderingPanel extends MyRenderingPanel{
    private int userID;
    private int deviceID;
    public UserRenderingPanel(int userID, int deviceID) {
        this.userID = userID;
        this.deviceID = deviceID;
    }
    public void update(UserPanel showPanel) {
        super.update(showPanel);
        //设置id
        showPanel.setUserID(userID);
        showPanel.setDeviceID(deviceID);
    }
}
