package user.Service;

import data.UserForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import user.User;
import global.view_tool.MyJFrame;
import global.view_tool.MyJPanel;


public class Get {
    private static final Logger logger = LogManager.getLogger(Get.class);

   /**
     *  * 获取在线用户引用封装
     *  * */
    public static User getUser(){
        return UserForm.getUser(User.getLogNameID());
    }

    /**
     * 获得MainUserFrame窗口
     * */
    public static MyJFrame getMainUserFrame() {
        MyJFrame jf = UserRouter.getRouter().getUserMainJFrame();
        if (jf == null) {
            logger.warn("UserMainJFrame（用户主窗口）没有被创建");
            return null;
        }
        return jf;
    }

    /**
     * 获得MainUserFrame（用户主窗口）的Rendering（渲染面板）
     * */
    public static MyJPanel getMainUserFrame_Rendering() {
        MyJPanel jf = UserRouter.getRouter().getUserMainJFrame().getRendering();
        if (jf == null) {
            logger.warn("UserMainJFrame（用户主窗口）没有被创建");
            return null;
        }
        return jf;
    }
}
