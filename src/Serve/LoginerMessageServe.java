package Serve;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import Object.User;

/**
 * 在线用户服务
 * */
public class LoginerMessageServe {
    private static final Logger logger = LogManager.getLogger(LoginerMessageServe.class);
    private static final Marker USER = MarkerManager.getMarker("USER");

    /**
     * 用户登陆
     * */
    public static boolean login(Object identity)  {
        //转为用户对象
        User ViewUser = (User) identity;
        int nameid = UserForm.getUserID(ViewUser.getName(), ViewUser.getPassword());
        OnlineUser useronline = new OnlineUser();
        useronline.setUserOnline(nameid);
        User user = useronline.getUserOnline();

        //判断是否为空
        if (nameid <= 0) {
            logger.info(USER,"用户不存在");
            return false;
        }

        logger.info(USER,"nameID：" + user.getNameID());
        logger.info(USER,user.getName()+ "用户已登录");

        return true;
    }

    /**
     * 更新用户数据
     * */
    public static boolean upUserName(String name) {
        OnlineUser useronline = new OnlineUser();
        User user = useronline.getUserOnline();
        boolean fals = UpUserMessageDAO.upDataName(name, user.getNameID());
        if (fals) user.setName(name);
        return fals;
    }
    public static boolean upUserPassword(String password) {
        OnlineUser useronline = new OnlineUser();
        User user = useronline.getUserOnline();
        boolean fals = UpUserMessageDAO.upDataPassword(password , user.getNameID());
        if (fals) user.setPassword(password);
        return fals;
    }
    public static boolean upUserPhone(String phone) {
        OnlineUser useronline = new OnlineUser();
        User user = useronline.getUserOnline();
        boolean fals = UpUserMessageDAO.upDataPhone(phone, user.getNameID());
        if (fals) user.setPhone(phone);
        return fals;
    }
}
