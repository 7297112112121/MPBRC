package admin.Serve;

import admin.Admin;
import data.AdminForm;
import global.view_tool.MyJFrame;
import global.view_tool.RenderingPanel;

/**
 * 窗口信息管理
 * */
public class AdminJFrameManger {
    /**
     * 注册登录窗口
     * */
    /// 获得登录注册界面
    public static MyJFrame getLoginRegisterFrame() {
        return AdminRouterServe.getRouter().getAdminJFrame();
    }

    /// 获得登录注册界面渲染面板
    public static RenderingPanel getAdminJFrameRenderingPanel() {
        return AdminRouterServe.getRouter().getAdminJFrame().getRendering();
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
