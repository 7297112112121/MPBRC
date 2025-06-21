package Serve.auth;

import DAO.UserMessageDAO;
import Util.UserFieldEnum;
import Util.db.query.ContextQuery;
import Util.db.query.SimplyQueryWhere;
import MyObject.User;
import Util.factory.UserCreationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCommonLogin {
    private static Logger logger = LogManager.getLogger(UserCommonLogin.class);
    private ResultSet resultSet ;
    public UserCommonLogin() {
    }

    /**
     * 登录界面生成在线用户对象
     * */
    public User loginAndCreateUserOnline(JTextField name , JLabel nameRimd , JPasswordField password, JLabel passwordRimd) {
        clear( nameRimd ,   passwordRimd);
        if (!verify( name ,  nameRimd ,  password,  passwordRimd)) {
            return null;
        }
        User user1 = createUser(name.getText().trim(), password.getText().trim());
        if (user1 == null) {
            passwordRimd.setText("用户名或密码错误");
        }else {
            return user1;
        }
        return null;
    }

    /**
     *
     * 非登陆界面在线用户对象(一般用于注册后直接登陆)
     * */
    public User loginAndCreateUserOnline(String name, String password) {
        User user1 = createUser(name, password);
        if (user1 == null) {
            logger.error("逻辑错误，这里本应该能生成对象，请检查输入的是否为用户的名字或密码");
            return null;
        }else {
            return user1;
        }
    }

    //更新用户数据
    public User upDataUserMessage(int nameid) {
        return UserMessageDAO.updataUserMessage(nameid);
    }
    //展示提示词
    private boolean verify(JTextField name , JLabel nameRimd , JPasswordField password, JLabel passwordRimd) {
        String nam = name.getText().trim();
        Verify is = new Verify();
        if(!is.isName(nam)){
            nameRimd.setText("用户名不能为空");
            return false;
        }
        if (!password.getText().isEmpty()) {
            passwordRimd.setText("密码不能为空");
        }
        return true;
    }

    //清空提示词
    private void clear( JLabel nameRimd ,  JLabel passwordRimd) {
        nameRimd.setText("");
        passwordRimd.setText("");
    }

    private User createUser(String name, String password) {
        //操作数据库
        ContextQuery query = new ContextQuery();
        query.setQuery(new SimplyQueryWhere());
        ResultSet rst = query.query(
                "user",
                "name", name,
                "password", password
        );
        try {
            if (!rst.next()) {
                return null;
            }
            resultSet = rst;
            User user = UserCreationFactory.createFullUser(
                    rst.getInt("nameid"),
                    rst.getString("name"),
                    rst.getString("sex"),
                    rst.getString("password"),
                    rst.getString("phone"),
                    rst.getDouble("account")
            );
            return user;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

}
