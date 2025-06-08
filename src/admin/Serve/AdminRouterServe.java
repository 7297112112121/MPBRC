package admin.Serve;

import admin.View.AdminMainJFrame;
import global.erro.RouterException;
import user.Service.UserRouter;
import global.view_tool.MyJFrame;
import admin.View.AdminJFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import global.erro.NullException;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminRouterServe implements AdminRouterMessage {
    private static final Logger logger = LogManager.getLogger(UserRouter.class);
    private static final Marker ADMIN = MarkerManager.getMarker("ADMIN");
    //单例封装——不直接调用本类方法,也利于继承与当作对象使用
    private static AdminRouterServe router = new AdminRouterServe();
    private  MyJFrame adminMainJFrame ;
    private  MyJFrame adminJFrame     ;

    /**
     * 配置说明：
     * 配置区域：实例变量、newJFrame（）、removeJFrame（）
     * checkJFrame() 检查是否已开启窗口  （确保全局单一同类窗口）
     * 创建该窗口实例
     * 该窗口默认关闭时引用设置为null    （确保全局单一同类窗口）
     *
     * removeJFrame（）
     * 窗口关闭
     * 窗口引用设置为null              （确保全局单一同类窗口）
     */

    public void newJFrame(int id) {
        try {
            switch (id) {
                //主窗口路由默认作用域：全局
                case AdminMainJFrame:
                    checkJFrame(adminMainJFrame);
                    adminMainJFrame = new AdminMainJFrame();
                    adminMainJFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            adminMainJFrame = null;
                        }
                    });
                    logger.info(ADMIN, "管理员打开了管理界面");
                    break;

                //以下路由配置默认作用域：用户未登录时期
                case AdminJFrame:
                    checkJFrame(adminJFrame);
                    adminJFrame = new AdminJFrame();
                    adminJFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            adminJFrame = null;
                        }
                    });
                    logger.info(ADMIN, "管理员打开了登录窗口");
                    break;

                default:
                    throw new RuntimeException("=========没有该窗口，请检查是否配置路由表=========");
            }
        } catch (NullException ee) {
            logger.debug("试图打开已存在窗口，已阻止 : 窗口id：" + id);
        } catch (RuntimeException e) {
            logger.warn(e.getMessage(),e);
        }
    }

    /**
     * 关闭窗口
     * */
    public void removeJFrame(int id) {
        try {
            switch (id) {
                case AdminMainJFrame:
                    adminMainJFrame.dispose();
                    adminMainJFrame = null;
                    break;
                case AdminJFrame:
                    adminJFrame.dispose();
                    adminJFrame = null;
                    break;

                default:
                    throw new RuntimeException("=========没有该窗口，请检查是否配置路由表=========");

            }
        }catch (NullException e) {
            logger.warn("{}",e.getMessage());
        }
    }

    /**
     * 检查窗口引用，若存在自动显示最前面
     * */
    public void checkJFrame(MyJFrame obj) {
        if (!(obj == null)) {
            // 将窗口最小化
            obj.setExtendedState(JFrame.ICONIFIED);
            // 将窗口恢复到正常大小
            obj.setExtendedState(JFrame.NORMAL);
            throw new NullException("该窗口已存在");
        }
    }

    /**
     * 获得窗口
     * */
    public static AdminRouterServe getRouter() {
        return router;
    }

    public MyJFrame getAdminMainJFrame() {
        return adminMainJFrame;
    }

    public MyJFrame getAdminJFrame() {
        return adminJFrame;
    }
}
