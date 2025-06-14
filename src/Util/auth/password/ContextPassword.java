package Util.auth.password;

public class ContextPassword {
    private PasswordConfig passwordConfig;

    public ContextPassword() {

    }
    //设置配置
    public void setConfig(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }
    //使用方法
    //获得密码的正则表达式
    public String getPasswordRegular() {
        return passwordConfig.getPassword();
    }

    public String setPasswordRemindText() {
        return passwordConfig.setPasswordRemindText();
    }
}
