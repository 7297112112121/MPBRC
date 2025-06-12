package Config;

import static Config.CaptchaConfig.SixNum;
import static Config.PasswordConfig.PWD_B;
import static Config.WorkIDConfig.WORK_ID;

public class Global {
    //信息储存区域（默认密码区域）
    private static String captcha = SixNum;
    private static String password = PWD_B;
    private static String workID = WORK_ID;

    public static String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public static String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
