package Serve.auth;

import DAO.UserMessageDAO;
import MyObject.User;
import Util.db.query.ContextQuery;
import Util.db.query.SimplyQueryWhere;
import Util.factory.UserCreationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminCommonLogin {
    private static Logger logger = LogManager.getLogger(AdminCommonLogin.class);
    private ResultSet resultSet ;
    public AdminCommonLogin() {
    }

    /**
     *
     * 非登陆界面在线用户对象(一般用于注册后直接登陆)
     * */
    public boolean login(String name, String password) {
        boolean fa =checkSameAdmin(name, password);
        if (!fa) {
            logger.error("逻辑错误，这里本应该能生成对象，请检查输入的是否为用户的名字或密码");
            return false;
        }else {
            return true;
        }
    }


    //清空提示词
    private void clear( JLabel nameRimd ,  JLabel passwordRimd) {
        nameRimd.setText("");
        passwordRimd.setText("");
    }

    //查询是否有相同的管理员
    private boolean checkSameAdmin(String name, String password) {
        try {
            //操作数据库
            ContextQuery query = new ContextQuery();
            query.setQuery(new SimplyQueryWhere());
            ResultSet rst = query.query(
                    "admin",
                    "name", name,
                    "password", password
            );
            if (rst.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            logger.error("数据库操作异常，请检查数据库连接是否正常");
            return false;
        }
    }
}
