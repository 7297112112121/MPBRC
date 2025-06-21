package DAO;

import MyObject.User;
import Util.db.DBQuary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMessageDAO {
    private static Logger logger = LogManager.getLogger(UserMessageDAO.class);
    //查询是否有相同名字的信息存在（用户）
    public static boolean sameName(User user) {
        try {
            String sql = "SELECT * FROM user WHERE name = ?";
            String name = user.getName();
            ResultSet resultSet = DBQuary.query(sql, name);
            if (resultSet.next()) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            logger.error("查询用户名称是否存在时出错：" + e);
            return false;
        }
    }

    //更新用户数据
    public static User updataUserMessage(int nameID) {
        String sql = "SELECT * FROM user WHERE nameid = ?";
        ResultSet resultSet = DBQuary.query(sql, nameID);
        try {
            if (resultSet.next()) {
                User user = new User();
                user.setNameID(resultSet.getInt("nameid"));
                user.setName(resultSet.getString("name"));
                user.setSex(resultSet.getString("sex"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setAccount(Double.parseDouble(resultSet.getString("account")));
                return user;
            }else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("查询用户信息时出错：" + e);
            return null;
        }
    }
}
