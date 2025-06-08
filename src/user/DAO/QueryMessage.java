package user.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import global.db.DBQuary;

import java.sql.ResultSet;
import java.sql.SQLException;


public class QueryMessage {
    private static Logger logger = LogManager.getLogger(QueryMessage.class);
    /**
     * 根据用户名查找用户信息
     * @return 用户名 or null
     * */
    public static String nameQuery(String name) {
        try {
            String sql = "SELECT * FROM user WHERE name = ?";
            ResultSet rs = DBQuary.query(sql,name);
            rs.next();
            return rs.getString("name");
        } catch (SQLException e) {
            logger.warn("没有该条用户信息");
            return null;
        }
    }
}
