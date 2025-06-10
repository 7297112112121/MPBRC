package Serve;

import Util.Reflection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Object.User;

import java.util.Collection;
import java.util.List;

/**
 * 管理员管理用户的所有信息服务
 * */
public class UserMessageManager {
    private static final Logger logger = LogManager.getLogger(UserMessageManager.class);

    /**
     * 查询UserForm的最新的数据
     * @return Collection<Object.User> 对象集合
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
            Reflection ref = new Reflection();
            ref.traverseFields(user);
            List<Object> value = ref.getValue();
            List<String> attribute = ref.getAttribute();
            //获取User类成员变量数量
            int length = value.size();
            //遍历赋值，修改该成员变量对应的数据库的值
            for(int i = 0 ; i < length; i++) {
                AdminUpUserMessageDAO.upUserMessage(attribute.get(i), value.get(i).toString(), user.getNameID());
            }

//            AdminUpUserMessageDAO.upUserName(user.getName(), user.getNameID());
//            AdminUpUserMessageDAO.upUserSex(user.getSex(), user.getNameID());
//            AdminUpUserMessageDAO.upUserPhone(user.getPhone(), user.getNameID());
//            AdminUpUserMessageDAO.upUserPassword(user.getPassword(), user.getNameID());
//            AdminUpUserMessageDAO.upUserDeviceId(user.getDevice_id(), user.getNameID());
//            AdminUpUserMessageDAO.upUserBalance(user.getBalance(), user.getNameID());
//            AdminUpUserMessageDAO.upUserAdminID(user.getAdminID(), user.getNameID());

        }
    }

}
