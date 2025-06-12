package Serve.auth;

import DAO.UserFieldEnum;
import DAO.query.ContextQuery;
import DAO.query.SimplyQueryWhere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

import static Config.PasswordConfig.PWD_B_TEXT;

public class CommonLogin {
    private static Logger logger = LogManager.getLogger(CommonLogin.class);
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
        query.query(
                UserFieldEnum.FORM,
                UserFieldEnum.NAME, name.getText().trim(),
                UserFieldEnum.PASSWORD, password.getText().trim()
        );

        return true;
    }
    //展示提示词
    private boolean add(JTextField name , JLabel nameRimd , JPasswordField password, JLabel passwordRimd) {
        String nam = name.getText().trim();
        String passwor = password.getText().trim();
        Is is = new Is();
        if(!is.isName(nam)){
            nameRimd.setText("用户名不能为空");
            return false;
        }
        if (!is.isPassword(passwor)) {
            passwordRimd.setText(PWD_B_TEXT);
            return false;
        }
        return true;
    }
    //清空提示词
    private void clear( JLabel nameRimd ,  JLabel passwordRimd) {
        nameRimd.setText("");
        passwordRimd.setText("");
    }
}
