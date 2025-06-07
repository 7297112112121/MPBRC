package data;

import admin.Admin;

public class AdminLogin {
    //默认管理员未登陆
    private static Admin admin = null;

    /**
     * 获取与修改
     * 用户对象
     * */

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin admin) {
        AdminLogin.admin = admin;
    }
}
