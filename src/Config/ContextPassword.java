package Config;

public class ContextPassword {
    private PasswordConfig passwordConfig;

    //设置配置
    public void setConfig(PasswordConfig passwordConfig) {
        this.passwordConfig = passwordConfig;
    }
    //使用方法
    public void getPassword() {
        passwordConfig.getPassword();
    }
}
