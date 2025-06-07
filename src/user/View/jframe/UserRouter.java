package user.View.jframe;

import user.User;
import util.view_tool.MyJFrame;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import util.erro.NullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.view_tool.RenderingPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 窗口路由表
 * 原理：传入窗口id，创建指定id下的窗口，并为其组件发送该窗口引用，返回窗口对象
 * */
public class UserRouter implements UserRouteMessage {
    private static final Logger logger = LogManager.getLogger(UserRouter.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    //单例封装
    private static UserRouter router = new UserRouter();
    private  MyJFrame userMainJFrame  ;
    private  MyJFrame userJFrame      ;

    /**
     * 配置说明：
     * 配置区域：实例变量、newJFrame（）、removeJFrame（）
     * newJFrame（）：
     * 检查是否已开启窗口            （确保全局单一同类窗口）
     * 创建该窗口实例
     * 该窗口默认关闭时引用设置为null    （确保全局单一同类窗口）
     * 该窗口告知其组件引用           （确保组件互动能使用）
     * <p>
     * removeJFrame（）
     * 窗口关闭
     * 窗口引用设置为null              （确保全局单一同类窗口）
     */

    /**
     * 新建窗口
     * */
    public void newJFrame(int id) {
        int idt = checkUserLogin(id);
        try {
            switch (idt) {
                //主窗口路由默认作用域：全局
                case USERMAINFRAME:
                    checkJFrame(userMainJFrame);
                    userMainJFrame = new UserMainJFrame();
                    userMainJFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            userMainJFrame = null;
                        }
                    });
                    logger.info(USER, "用户打开了首页");
                    break;

                case UserJFrame:            //用户登陆界面
                    checkJFrame(userJFrame);
                    userJFrame = new LoginJFrame();
                    userJFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            userJFrame = null;
                        }
                    });
                    logger.info(USER, "用户打开了登陆窗口");
                    break;

                default:
                    throw new RuntimeException("=========没有该窗口，请检查是否配置路由表=========");
            }
        } catch (NullException ee) {
            logger.debug("用户试图打开已存在窗口，已阻止 : 窗口id：" + id);
        } catch (RuntimeException e) {
            logger.warn(e.getMessage(),e);
        }
    }

    /**
     * 关闭指定窗口
     * */
    public void removeJFrame(int id) {
        try {
            switch (id) {
                case USERMAINFRAME:
                    userMainJFrame.dispose();
                    userMainJFrame = null;
                    break;
                case UserJFrame:
                    userJFrame.dispose();
                    userJFrame = null;
                    break;
                default:
                    throw new RuntimeException("=========没有该窗口，请检查是否配置路由表=========");

            }
        }catch (NullException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 获取路由引用
     * */
    public static UserRouter getRouter() {
        return router;
    }

    public MyJFrame getUserMainJFrame() {
        return userMainJFrame;
    }

    /**
     * 获得窗口引用
     * */

    public MyJFrame getUserJFrame() {
        return userJFrame;
    }

    /**
     * 获得用户窗口渲染面板
     * */
    public static RenderingPanel getTabPanel() {
        return router.getUserMainJFrame().getRendering();
    }

    /**
     * 检查窗口引用
     * */
    private void checkJFrame(MyJFrame obj) {
        if (!(obj == null)) {
            // 将窗口最小化
            obj.setExtendedState(JFrame.ICONIFIED);
            // 将窗口恢复到正常大小
            obj.setExtendedState(JFrame.NORMAL);
            throw new NullException("该窗口已存在");
        }
    }

    /**
     * 检查用户是否登陆，没有登录自动跳转到登陆
     * */
    private int checkUserLogin(int id) {
        if (UserJFrame == id) {
            return id;
        }
        //用户没有登录，自动打开登录窗口
        if (User.getLogNameID() < 0) {
            id = UserJFrame;
        }
        return id;
    }
}