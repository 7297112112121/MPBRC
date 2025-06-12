package Serve.auth;

import javax.swing.*;

public class CommonRegister {
    private Is is = new Is();
    public boolean register(
            JTextField name , JLabel nameRimd ,
            JPasswordField password, JLabel passwordRimd,
            JPasswordField comfirmPassword, JLabel comfirPasswordRimd,
            JTextField phone, JLabel phoneRimd,
            JTextField captcha, JLabel captchaRimd) {

        String nam = name.getText().trim();
        String passwor = password.getText().trim();
        String comfirPasswor = comfirmPassword.getText().trim();
        String phon = phone.getText().trim();
        String ca = captcha.getText().trim();

        if (!is.isName(nam)) {
            return false;
        }
        if (!is.isPassword(passwor)) {
            return false;
        }
        if (!is.isConfirmPassword(passwor, comfirPasswor)) {
            return false;
        }
        if (!is.isPhone(phon)) {
            return false;
        }


        return true;
    }
}
