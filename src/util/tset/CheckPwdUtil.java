package util.tset;

public class CheckPwdUtil {

    /**
     * 密码必须包含大写、小写、数字和特殊字符，且长度是8位以上
     */
    private static final String PWD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()=_+;':,.?]).{8,}$";

    /**
     * 密码复杂度校验
     * @param password 密码
     * @return 校验密码强度是否合格 true/false
     */
    public static boolean isStrongPassword(String password) {
        return password.matches(PWD_REGEX);
    }
}

