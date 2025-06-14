package Serve.auth;

import DAO.fromemu.UserFieldEnum;
import DAO.query.ContextQuery;
import DAO.query.SimplyQueryWhere;
import MyObject.User;
import MyObject.UserCreationFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCommonLogin {
    private static Logger logger = LogManager.getLogger(UserCommonLogin.class);
    private ResultSet resultSet ;
    private User user;
    public UserCommonLogin() {
    }

    /**
     * 传入：
     * 用户名
     * 密码
     * */
    public boolean login(JTextField name , JLabel nameRimd , JPasswordField password, JLabel passwordRimd) {
        clear( nameRimd ,   passwordRimd);
        if (!add( name ,  nameRimd ,  password,  passwordRimd)) {
            return false;
        }
        //操作数据库
        ContextQuery query = new ContextQuery();
        query.setQuery(new SimplyQueryWhere());
        ResultSet rst = query.query(
                UserFieldEnum.FORM.getValue(),
                UserFieldEnum.NAME.getValue(), name.getText().trim(),
                UserFieldEnum.PASSWORD.getValue(), password.getText().trim()
        );
        try {
            if (!rst.next()) {
                passwordRimd.setText("用户名或密码错误");
                return false;
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
            this.user = user;

            //获取用户id
            return true;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }
    //展示提示词
    private boolean add(JTextField name , JLabel nameRimd , JPasswordField password, JLabel passwordRimd) {
        String nam = name.getText().trim();
        String passwor = password.getText().trim();
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

    public User getUser() {
        return user;
    }

    //清空提示词
    private void clear( JLabel nameRimd ,  JLabel passwordRimd) {
        nameRimd.setText("");
        passwordRimd.setText("");
    }

}
