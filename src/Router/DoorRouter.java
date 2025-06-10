package Router;

import View.admin.AdminLoginJFrame;
import Util.View.MyJFrame;
import View.user.UserLoginJFrame;

import java.util.HashMap;

/// 当管理员，用户没有登陆时候启动，退出登陆时候，清除路由表中该用户主窗口信息
public class DoorRouter {

    public static HashMap<Integer, MyJFrame> loginJFrameMap = new HashMap<>();
    private static int userRouterID = 1;

    /// 用户登陆
    public static void UserloginJFrame() {
       UserLoginJFrame userLoginJFrame = new UserLoginJFrame(userRouterID);
       loginJFrameMap.put(userRouterID, userLoginJFrame);
       userRouterID++;
    }
    /// 管理员登陆
    public static void AdminloginJFrame() {
        AdminLoginJFrame adminLoginJFrame = new AdminLoginJFrame(userRouterID);
        loginJFrameMap.put(userRouterID, adminLoginJFrame);
        userRouterID++;
        }
    /// 若用户或管理员登陆，清楚此窗口记录
    public static void closeLoginJFrame(int id) {
        loginJFrameMap.get(id).dispose();
        loginJFrameMap.remove(id);
    }


}



