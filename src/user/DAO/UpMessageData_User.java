package user.DAO;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.User;
import util.db.DBUpData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * 数据更新业务
 * */
public class UpMessageData_User {
    private static final Logger logger = LogManager.getLogger(UpMessageData_User.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    /**
     * 方案一：一次性全部更新方法
     * 1、获得需要修改用户对象类型
     * 2、根据用户id查找该用户，覆盖原本数据库信息
     * 3、用户对象调用set方法更新信息
     * 3.1、更新用户登陆状态表与用户管理表
     * 4、返回Boolean值，若true则修改成功，否则false失败
     *
     * 传入参数：完整用户对象
     * */
    public static boolean upData(User user) {
        boolean fals = false;           //默认范围错误
        User loginUser = User.getUser();
        String sql = "UPDATE user SET name = ?, password = ?, phone = ? WHERE nameid = ?";
        try {
            fals = DBUpData.update(sql,"user") > 0;    //更新数据库用户信息
            logger.info("用户信息修改成功,新昵称为：" + user.getName());
        } catch (SQLException e) {
            logger.error("用户信息修改失败",e);
        }
        return fals;
    }

    /**
     * 方案二：局部信息更新方法
     * */
    //传入最新的名字
    public static boolean upDataName(String name) {
        boolean fals = false;
        String sql = "UPDATE user SET name = ? WHERE nameid = ?";
        try {
            fals = DBUpData.update(sql, name, User.getLogNameID()) > 0;
            User.getUser().setName(name);
            logger.info(USER,"用户昵称修改完成，新昵称："+ name);
        } catch (SQLException e) {
            logger.error("用户昵称修改失败",e);
        }
        return fals;
    }
    //传入最新的密码
    public static boolean upDataPassword(String password) {
        boolean fals = false;
        String sql = "UPDATE user SET password = ? WHERE nameid = ?";
        try {
            fals = DBUpData.update(sql, password, User.getLogNameID()) > 0;
            User.getUser().setName(password);
            logger.info(USER,"用户密码已修改");
        } catch (SQLException e) {
           logger.error("用户密码修改失败",e);
        }
        return fals;
    }
    //传入最新的电话
    public static boolean upDataPhone(String phone) {
        boolean fals = false;
        String sql = "UPDATE user SET phone = ? WHERE nameid = ?";
        try {
            fals = DBUpData.update(sql, phone, User.getLogNameID()) > 0;
            User.getUser().setName(phone);
            logger.info(USER,"用户电话修改完成");
        } catch (SQLException e) {
            logger.error("用户电话修改失败",e);
        }
        return fals;
    }

}
