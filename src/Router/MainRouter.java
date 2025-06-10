package Router;

import View.admin.AdminMainJFrame;
import Util.View.MobilePhoneJFrame;
import Util.View.MyJFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import View.user.UserMainJFrame;

import java.util.HashMap;

public class MainRouter {
    private static final Logger logger = LogManager.getLogger(MainRouter.class);
    public static HashMap<Integer, MyJFrame> userJFrameMap = new HashMap<>();
    public static HashMap<Integer, MyJFrame> adminJFrameMap = new HashMap<>();
    public static HashMap<Integer, MyJFrame> phoneJFrameMap = new HashMap<>();

    //用户主窗口
    public static void createUserMainJFrame(int nameID , int deviceID) {
        UserMainJFrame userLoginJFrame = new UserMainJFrame();
        userLoginJFrame.setDeviceID(deviceID);
        userLoginJFrame.setUserID(nameID);
        userJFrameMap.put(nameID, userLoginJFrame);
    }

    //管理员主窗口
    public static void createAdminMainJFrame(int adminID , int deviceID) {
        AdminMainJFrame adminLoginJFrame = new AdminMainJFrame();
        adminLoginJFrame.setDevicID(deviceID);
        adminLoginJFrame.setAdminID(adminID);
        adminJFrameMap.put(adminID, adminLoginJFrame);
    }

    //关闭用户主窗口
    public static void closeUserJFrame(int nameID) {
        userJFrameMap.get(nameID).dispose();
        userJFrameMap.remove(nameID);
    }
    //关闭管理员主窗口
    public static void closeAdminJFrame(int adminID) {
        adminJFrameMap.get(adminID).dispose();
        adminJFrameMap.remove(adminID);
    }

    //创建模拟手机窗口
    public static void createMobilePhoneJFrame(int deviceID) {
        MobilePhoneJFrame mobilePhoneJFrame = new MobilePhoneJFrame(deviceID);
        phoneJFrameMap.put(deviceID, mobilePhoneJFrame);
    }

    //关闭模拟手机窗口
    public static void closeMobilePhoneJFrame(int deviceID) {
        phoneJFrameMap.get(deviceID).dispose();
        phoneJFrameMap.remove(deviceID);
    }
}
