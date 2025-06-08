package admin.Serve;

import admin.Admin;
import data.AdminForm;
import global.view_tool.MyJFrame;
import global.view_tool.RenderingPanel;


public class GetServe {
    /**
     * 注册登录
     * */
    /// 获得登录注册界面
    public static MyJFrame getLoginRegisterFrame() {
        return AdminRouterServe.getRouter().getAdminJFrame();
    }

    /// 获得登录注册界面渲染面板
    public static RenderingPanel getAdminJFrameRenderingPanel() {
        return AdminRouterServe.getRouter().getAdminJFrame().getRendering();
    }

    /// 获得已登录管理员信息记录ID
    public static Admin getLoginAdmin() {
        Admin admin = AdminForm.getAdmin(Admin.getLoginID());
        if (admin == null) {
            return null;
        }
        return admin;
    }

    /**
     * 主窗口
     * */
    /// 获得主窗口
    public static MyJFrame getAdminMainJFrame() {
        return AdminRouterServe.getRouter().getAdminMainJFrame();
    }

    /// 获得主窗口渲染面板
    public static RenderingPanel getAdminMainJFrameRenderingPanel() {
        return AdminRouterServe.getRouter().getAdminMainJFrame().getRendering();
    }
}
