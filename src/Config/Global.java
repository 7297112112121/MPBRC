package Config;

import Serve.auth.password.ContextPassword;
import Serve.auth.password.Password_PWD_B;
import Serve.auth.workID.ContextWorkID;
import Serve.auth.workID.WorkID_WORK_ID;


public class Global {
    /***
     * 设置验证码、密码、手机号码正则表达式
     * */
    //初始化Global设置

    //密码
    private static ContextPassword contextPassword = new ContextPassword();
    private static String passwordRegular ;//密码正则表达式
    private static String passwordRemind;
    //工号
    private static ContextWorkID contextWorkID = new ContextWorkID();
    private static String workIDRegular ;//工号正则表达式
    private static String workIDRemindText;//工号提示信息
    //单例
    private static Global gl = new Global();
    public Global() {
        //设置密码正则表达式
        contextPassword.setConfig(new Password_PWD_B());
        passwordRegular = contextPassword.getPasswordRegular();
        passwordRemind = contextPassword.setPasswordRemindText();

        //设置工号正则表达式
        contextWorkID.setWorkIDConfig(new WorkID_WORK_ID());
        workIDRegular = contextWorkID.getWorkID();
        workIDRemindText = contextWorkID.setWorkIDText();
    }

// 获取密码正则表达式
    public static String getPasswordRegular() {
        return passwordRegular;
    }

// 获取密码提醒
    public static String getPasswordRemind() {
        return passwordRemind;
    }

// 获取上下文工作ID
    public static ContextWorkID getContextWorkID() {
        return contextWorkID;
    }

// 获取workIDRegular的提醒文本
    public static String getWorkIDRegular() {
        return workIDRegular;
    }

// 获取工作ID提醒文本
    public static String getWorkIDRemindText() {
        return workIDRemindText;
    }
}
