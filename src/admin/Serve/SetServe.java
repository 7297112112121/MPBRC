package admin.Serve;

import global.erro.RouterException;

/**
 * 封装窗口与窗口信息设置方法，目的减少重复代码
 * */
public class SetServe implements AdminRouterMessage{
    /**
     * 注册登陆窗口
     * */

    /**
     * 主窗口
     * */
    /// 创建主窗口
    public static void createAdminMainJFrame() {
        AdminRouterServe.getRouter().newJFrame(AdminMainJFrame);
    }
    /// 关闭主窗口
    public static void closeAdminMainJFrame() {
        AdminRouterServe.getRouter().removeJFrame(AdminMainJFrame);
    }
}
