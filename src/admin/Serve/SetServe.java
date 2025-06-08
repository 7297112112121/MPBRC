package admin.Serve;

import global.erro.RouterException;

public class SetServe implements AdminRouterMessage{
    /**
     * 注册登陆窗口
     * */

    /**
     * 主窗口
     * */
    /// 创建窗口
    public static void createAdminMainJFrame() {
        AdminRouterServe.getRouter().newJFrame(AdminMainJFrame);
    }
}
