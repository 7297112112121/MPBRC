package admin.Serve;

import admin.DAO.QueryUserMessageDAO;
import admin.DAO.UpDataUserMessageDAO;

/**
 * 管理关于用户的所有信息服务
 * */
public class UserMessageManager {

    /**
     * 查询UserForm的最新的数据
     * **/
    public static void queryAll() {
        QueryUserMessageDAO.queryAll();
    }

    /**
     * 更新所有用户信息
     * */
    public static void upDataUserAllMessage() {
        UpDataUserMessageDAO.loadUserALL();
    }

    /**
     * 封装：更新全部用户数据并返回所有用户数据
     * **/
    public static void upDataUserAllMessageAndQueryAll() {
        QueryUserMessageDAO.queryAll();
        UpDataUserMessageDAO.loadUserALL();
    }
}
