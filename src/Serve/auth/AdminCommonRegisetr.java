package Serve.auth;


import DAO.UserMessageDAO;
import MyObject.User;
import Util.db.insert.ContextInsert;
import Util.db.insert.SimplyInsertAllForm;
import View.FatherFrame;

import javax.swing.*;

public class AdminCommonRegisetr  {
    private Verify verify = new Verify();

    public boolean register(
            JTextField name, JLabel nameRimd,
            JPasswordField password, JLabel passwordRimd,
            JPasswordField comfirmPassword, JLabel comfirPasswordRimd,
            JTextField phone, JLabel phoneRimd,
            JTextField workI, JLabel workIDRimd
    ) {

        //清理提示标签
        clear(nameRimd, passwordRimd, comfirPasswordRimd, phoneRimd);

        //输入的名字
        String nam = name.getText().trim();
        //输入的密码
        String passwor = password.getText().trim();
        //输入的重复密码
        String comfirPasswor = comfirmPassword.getText().trim();
        //输入的手机号
        String phon = phone.getText().trim();
        //输入的工号
        String workID = workI.getText().trim();
        boolean fals = true;    //验证开关
        int count = 0;          //记录验证不通过次数
        // 验证用户名
        if (!verify.isName(nam)) {
            // 如果用户名验证失败，设置错误提示信息
            nameRimd.setText(verify.setNameRimd());
            // 返回false，表示验证失败
            count++;
        }

        // 验证密码
        if (!verify.isPassword(passwor)) {
            // 如果密码验证失败，设置错误提示信息
            passwordRimd.setText(verify.setPasswordRimd());
            // 返回false，表示验证失败
            count++;
        }

        // 验证确认密码
        if (!verify.isConfirmPassword(passwor, comfirPasswor)) {
            // 如果确认密码验证失败，设置错误提示信息
            comfirPasswordRimd.setText(verify.setConfirmPasswordRimd());
            // 返回false，表示验证失败
            count++;
        }

        // 验证手机号
        if (!verify.isPhone(phon)) {
            // 如果手机号验证失败，设置错误提示信息
            phoneRimd.setText(verify.setPhoneRimd());
            // 返回false，表示验证失败
            count++;
        }

        // 验证工号
        if (!verify.isWorkNumber(workID)) {
            workIDRimd.setText(verify.setWorkNumberRimd());
            count++;
        }

        //如果验证不通过，则返回false
        if (count > 0) {
            return false;
        }
        //相同身份查询
        if (UserMessageDAO.sameName(new User(nam))) {
            nameRimd.setText("用户名已存在");
            return false;
        }
        //注册信息到数据库中
        ContextInsert contextInsert =new ContextInsert();
        contextInsert.setInsert(new SimplyInsertAllForm());
        contextInsert.insert(
                //要插入的表
                "admin", "name", "password", "phone", "workid",
                //要写入的字段
                ";", nam, passwor, phon, workID
        );

        //符合验证码返回true
        return true;
    }
    //密码提示信息
    public String setPasswordRemind() {
        return verify.setPasswordRimd();
    }

    //清理标签
    private void clear(JLabel nameRimd, JLabel passwordRimd, JLabel comfirPasswordRimd, JLabel phoneRimd) {
        nameRimd.setText("");
        passwordRimd.setText("");
        comfirPasswordRimd.setText("");
        phoneRimd.setText("");
    }
}
