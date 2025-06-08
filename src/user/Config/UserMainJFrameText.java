package user.Config;


/**
 * 储存常被使用的字符串，实现一改全改功能
 * */
public interface UserMainJFrameText {
    /**
     * 主窗口用户信息面板（最顶部）
     * */
    //用户尚未登陆时，主窗口用户信息面板（最顶部）显示文字
    String USER_TEXT_Not_logged = "先生/女士,请登录";
    //用户已经登陆时，主窗口用户信息面板（最顶部）显示文字
    String USER_TEXT_Logged = "欢迎您！";
    //主窗口用户信息面板（最顶部），右边显示文字
    String USER_TEXT_Right = "租凭充电宝在这里祝您生活愉快！！";
    //用户信息-name
}
