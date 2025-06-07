package user.DAO;

import admin.DAO.AdminSQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.User;
import util.db.DBQuary;
import util.db.DBUpData;
import util.db.DataBase;
import util.erro.UserObjectException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 注册业务
 * */
public class RegisterDAO implements  AdminSQL {
    private static final Logger logger = LogManager.getLogger(RegisterDAO.class);
    private static final Marker USER = MarkerManager.getMarker("USER");

    /**
     * 执行用户注册
     * 若要添加或删除注册表请在这里操作
     * */
    public static boolean register(Object obj)  {
        Connection connection = null;
        //默认无需注册
        try {
            connection = DataBase.getConnection();
            // 关闭自动提交，开启事务
            connection.setAutoCommit(false);

            //检查用户是否已注册
            User userUI = (User) obj;
            String sql = "SELECT * FROM user WHERE name = ? AND password = ?";
            if (DBQuary.query(sql, userUI.getName(), userUI.getPassword()).next()) {
                throw new UserObjectException("用户已存在");
            }

            //user表注册
            String sql2 = "INSERT INTO user(name,sex, password, phone, adminid, device_id, account) VALUES(?, ?, ?, ?, ?, ?, ?)";
            int nameid = DBUpData.updateGetGeneratedKey(
                    sql2,
                    userUI.getName(),
                    "无",
                    userUI.getPassword(),
                    userUI.getPhone(),
                    0,
                    0,
                    0.00
            );


//            //wechat_accounts表注册
//            String wechat = "INSERT INTO wechat_accounts(nameid, wechat_id, balance) VALUES(?, ?, ?)";
//            int wechatForm = DBUpData.update(wechat, nameid, nameid, 0.0);

//            //alipay_accounts表注册
//            String alipay = "INSERT INTO alipay_accounts(nameid, alipay_id, balance) VALUES(?, ?, ?)";
//            int alipayForm = DBUpData.update(alipay, nameid, nameid, 0.0);

//            //提交事务
//            if (nameid > 0 && wechatForm > 0 && alipayForm > 0) {
//                //提交事务
//                connection.commit();
//                logger.info("用户注册成功,昵称为："+ userUI.getName());
//                return true;
            //提交事务
            if ( nameid > 0) {
                //提交事务
                connection.commit();
                logger.info("用户注册成功,昵称为："+ userUI.getName());
                return true;

            }else {
                //回滚
                connection.rollback();
                throw new SQLException();
            }

        }catch (UserObjectException e) {
            logger.info(USER,e.getMessage());
            return false;
        } catch (SQLException e) {
            logger.error("注册事务失败，请检查sql语句是否正确",e);
            return false;
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("设置事务自动提交失败",e);
            }
        }
    }
}
