package Util.View;

public class AdminRenderingPanel extends MyRenderingPanel{
    private int adminID;
    private int deviceID;
    public AdminRenderingPanel(int adminID, int deviceID) {
        this.adminID = adminID;
        this.deviceID = deviceID;
    }
    public void update(AdminPanel showPanel) {
        super.update(showPanel);
        //设置id
        showPanel.setAdminID(adminID);
        showPanel.setDeviceID(deviceID);
    }
}
