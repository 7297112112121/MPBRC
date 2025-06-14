package Serve.auth.phone;

public interface PhoneConfig {
    /**
     * 手机号码格式
     * 11位手机号码
     * */
    String PHONE_NUM = "^1[3-9]\\d{9}$";
    String PHONE_TEXT = "手机号码应该为11位";
}
