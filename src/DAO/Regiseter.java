package DAO;

import MyObject.User;
import Util.db.DBQuary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Regiseter {
    private static Logger logger = LogManager.getLogger(Regiseter.class);
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
}
