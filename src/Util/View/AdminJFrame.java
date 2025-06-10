package Util.View;

public abstract class AdminJFrame extends MyJFrame{
    private int adminID;
    private int devicID;
    public AdminJFrame() {
        this.adminID = adminID;
        this.devicID = devicID;
    }

    public abstract MyRenderingPanel getRendering() ;

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public int getDevicID() {
        return devicID;
    }

    public void setDevicID(int devicID) {
        this.devicID = devicID;
    }
}
