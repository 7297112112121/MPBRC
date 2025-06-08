package user.Service;

import admin.DAO.AdminSQL;
import data.UserForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.User;

/**
 * 登陆业务
 * */
public class Loginer implements  AdminSQL {
    private static final Logger logger = LogManager.getLogger(Loginer.class);
    private static final Marker USER = MarkerManager.getMarker("USER");

    public static boolean login(Object identity)  {
        //转为用户对象
        User ViewUser = (User) identity;
        int nameid = UserForm.getUserID(ViewUser.getName(), ViewUser.getPassword());
        User.setLogNameID(nameid);

        //判断是否为空
        if (nameid <= 0) {
            logger.info(USER,"用户不存在");
            return false;
        }

        logger.info(USER,"nameID：" + User.getUser().getNameID());
        logger.info(USER,User.getUser().getName()+ "用户已登录");

        return true;
    }
}
