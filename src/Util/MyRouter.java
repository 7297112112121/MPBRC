package Util;

public class MyRouter {
    private int userjframeID;
    private int adminjframeID;
    private int staticjframeID;

    //模拟手机
    public MyRouter(int staticjframeID) {
        this.staticjframeID = staticjframeID;
    }

    //用户
    public MyRouter(int userjframeID, int staticjframeID) {
        this.userjframeID = userjframeID;
        this.staticjframeID = staticjframeID;
    }

    //管理员
    public MyRouter(int userjframeID, int adminjframeID, int staticjframeID) {
        this.userjframeID = userjframeID;
        this.adminjframeID = adminjframeID;
        this.staticjframeID = staticjframeID;
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
