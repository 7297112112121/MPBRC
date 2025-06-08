package admin.Serve;

import admin.DAO.QueryUserMessageDAO;
import admin.DAO.AdminUpUserMessageDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import user.User;

import java.util.Collection;
import java.util.List;

/**
 * 管理员管理用户的所有信息服务
 * */
public class UserMessageManager {
    private static final Logger logger = LogManager.getLogger(UserMessageManager.class);

    /**
     * 查询UserForm的最新的数据
     * @return Collection<User> 对象集合
     * **/
    public static Collection<User> queryAll() {
        return QueryUserMessageDAO.queryAll();
    }

    /**
     * 重新加载所有用户信息
     * @return void
     * */
    public static void upDataUserAllMessage() {
        AdminUpUserMessageDAO.loadUserALL();
    }

    /**
     * 封装：更新全部用户数据并返回所有用户数据
     * **/
    public static Collection<User> upDataUserAllMessageAndQueryAll() {
        AdminUpUserMessageDAO.loadUserALL();
        return QueryUserMessageDAO.queryAll();
    }

    /**
     * 更新所有用户数据
     * */
    public static void upAllUserMessage(List<User> users) {
        for (User user : users) {
            if (user.getNameID() < 0) {
                logger.warn("没有用户ID，无法更新,请检查是否有传入id");
            }
            if (user.getName() != null) {
                AdminUpUserMessageDAO.upUserName(user.getName(), user.getNameID());
            }
            if (user.getSex() != null) {
                AdminUpUserMessageDAO.upUserSex(user.getSex(), user.getNameID());
            }
            if (user.getPhone() != null) {
                AdminUpUserMessageDAO.upUserPhone(user.getPhone(), user.getNameID());
            }
            if (user.getPassword() != null) {
                AdminUpUserMessageDAO.upUserPassword(user.getPassword(), user.getNameID());
            }
            if (user.getDevice_id() != null) {
                AdminUpUserMessageDAO.upUserDeviceId(user.getDevice_id(), user.getNameID());
            }
            if (user.getBalance() >= 0.0) {
                AdminUpUserMessageDAO.upUserBalance(user.getBalance(), user.getNameID());
            }
            if (user.getAdminID() >= 0) {
                AdminUpUserMessageDAO.upUserAdminID(user.getAdminID(), user.getNameID());
            }
        }
    }

}
