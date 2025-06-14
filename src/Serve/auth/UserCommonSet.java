package Serve.auth;

import DAO.set.SimplySet;
import View.MyFrame;
import View.user.UserFrame;

import javax.swing.*;
import java.awt.*;

public class UserCommonSet {
    private static Verify verify = new Verify();
    public UserCommonSet() {
    }
    //用户名正确则进行用户名修改
    public boolean setName(JTextField name , JLabel Remind, int nameid) {
        String nameText = name.getText().trim();
        String id = String.valueOf(nameid);
        boolean fa =verify.isName(nameText);
        if (!fa){
            Remind.setText(verify.setNameRimd());
            return false;
        }
        else new SimplySet().set(
                "user",
                "name", nameText,
                ";",
                "nameid", id
                );
        return true;
    }

    //密码正确则进行密码修改
    public boolean setPassword(
            JTextField Orpassword, JLabel OrPasswordRemind,
            JTextField password , JLabel Remind,
            JPasswordField confirmPassword, JLabel confirmLabel,
            UserFrame frame) {

        //对比用户原密码是否正确
        String userOrPassword = frame.getUser().getPassword();
        String userInputOrPassword = Orpassword.getText().trim();
        if (!userOrPassword.equals(userInputOrPassword)) {
            OrPasswordRemind.setText("原密码错误");
            OrPasswordRemind.setForeground(Color.RED);
            return false;
        }
        String id = String.valueOf(frame.getUser().getNameID());
        String passwordText = password.getText().trim();
        String confirmPasswordText = confirmPassword.getText().trim();
        //判断新密码是否符合强度
        boolean fa = verify.isPassword(passwordText);
        if (!fa){
            Remind.setText(verify.setPasswordRimd());
            return false;
        }
        //判断确认密码是否相同
        else if (!passwordText.equals(confirmPasswordText)){
            confirmLabel.setText("两次密码不一致");
            confirmPassword.setForeground(Color.RED);
            return false;
        }
        else new SimplySet().set(
                "user",
                "password", passwordText,
                ";",
                "nameid", id
        );
        return true;
    }

    //电话号码正确则修改电话号码
    public boolean setPhone(String phone, JLabel phoneRimd, JTextField cap, int nameid) {
        String capcha = verify.getCapcha(phone);
        if (capcha.equals(cap.getText().trim())) {
            new SimplySet().set(
                    "user",
                    "phone", phone,
                    ";",
                    "nameid", String.valueOf(nameid)
                    );
        }else {
            phoneRimd.setText("验证码错误");
        }
        return true;
    }


}
