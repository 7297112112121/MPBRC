package Util.View;

import javax.swing.*;

public abstract class MyJPanel extends JPanel  {
    /**
     * 设置三大窗口id，由渲染面板设置
     * */
    private int userjframeID;
    private int adminjframeID;
    private int staticjframeID;

    public MyJPanel(int staticjframeID) {
        this.userjframeID = userjframeID;
        this.adminjframeID = adminjframeID;
    }

    public MyJPanel(int userjframeID, int staticjframeID) {
        this.userjframeID = userjframeID;
        this.staticjframeID = staticjframeID;
    }

    public MyJPanel(int userjframeID, int adminjframeID, int staticjframeID) {
        this.userjframeID = userjframeID;
        this.adminjframeID = adminjframeID;
        this.staticjframeID = staticjframeID;
    }

    public MyJPanel() {
    }

    public int getUserjframeID() {
        return userjframeID;
    }

    public void setUserjframeID(int userjframeID) {
        this.userjframeID = userjframeID;
    }

    public int getAdminjframeID() {
        return adminjframeID;
    }

    public void setAdminjframeID(int adminjframeID) {
        this.adminjframeID = adminjframeID;
    }

    public int getStaticjframeID() {
        return staticjframeID;
    }

    public void setStaticjframeID(int staticjframeID) {
        this.staticjframeID = staticjframeID;
    }
}
