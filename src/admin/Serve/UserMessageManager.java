package admin.Serve;

import admin.DAO.QueryUserMessageDAO;
import admin.DAO.UpDataUserMessageDAO;
import user.User;

import java.util.Collection;
import java.util.HashMap;

/**
 * 管理关于用户的所有信息服务
 * */
public class UserMessageManager {

    /**
     * 查询UserForm的最新的数据
     * **/
    public static Collection<User> queryAll() {
        return QueryUserMessageDAO.queryAll();
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
    public static Collection<User> upDataUserAllMessageAndQueryAll() {
        UpDataUserMessageDAO.loadUserALL();
        return QueryUserMessageDAO.queryAll();
    }
}
