package admin.Serve;

import admin.Admin;
import data.AdminForm;
import util.view_tool.RenderingPanel;


public class GetServe {
    /// 获得登录注册界面渲染面板
    public static RenderingPanel getAdminJFrameRenderingPanel() {
        return AdminRouterServe.getRouter().getAdminJFrame().getRendering();
    }
    ///
    public static Admin getLoginAdmin() {
        Admin admin = AdminForm.getAdmin(Admin.getLoginID());
        if (admin == null) {
            return null;
        }
        return admin;
    }
}
