package Serve.auth.password;

public class Password_PWD_B implements PasswordConfig {
  /**
   * 用户密码格式--弱
   * 密码必须包含字母和数字，且长度是8-32位
   * */
  String PWD_B = "^(?=.*[A-Za-z])(?=.*\\d).{8,32}$";
  String PWD_B_TEXT = "密码必须包含字母和数字，且长度是8-32位";
  public Password_PWD_B() {
  }
  @Override
  public String getPassword() {
    return PWD_B;
  }

  @Override
  public String setPasswordRemindText() {
    return PWD_B_TEXT;
  }
}
