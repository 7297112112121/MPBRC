package Util.auth.password;

public interface PasswordConfig {
    //获得密码的正则表达式
    String getPassword();

    //获得密码的提示信息
    String setPasswordRemindText();
    /**
     * 用户密码格式--强
     * 密码必须包含大写、小写、数字和特殊字符，且长度是8-32位
     * */
    String PWD_S = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()=_+;':,.?]).{8,32}$";
    String PWD_S_TEXT = "密码必须包含字母大写、小写、数字和特殊字符，且长度是8-32位";

    /**
     * 用户密码格式--中
     * 密码必须包含大写、小写和数字，且长度是8-32位
     * */
    String PWD_A = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,32}$";
    String PWD_A_TEXT = "密码必须包含字母大写、小写和数字，且长度是8-32位";




    /**
     * 用户密码格式--超弱
     * 密码必须包含数字，且长度是8-32位
     * */
    String PWD_C = "^(?=.*\\d).{8,32}$";
    String PWD_C_TEXT = "密码必须包含数字，且长度是8-32位";




    /**
     * 工号格式
     * */
    String WORK_ID_NUM = "^[0-9]{10}$";
    String WORK_ID_TEXT = "工号应该为10位";

}
