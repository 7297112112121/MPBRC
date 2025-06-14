package Serve.auth;

import Config.CaptchaGlobal;
import Config.Global;


import Util.CountDown;
import View.MyFrame;
import View.observer.AllObserverOfFrame;
import View.observer.ObserverMessagePanel;

import javax.swing.*;

import static Serve.auth.phone.PhoneConfig.PHONE_NUM;

public class Verify {

    public Verify() {
    }

    public boolean isName(String name) {
        return !name.isEmpty();
    }

    public String setNameRimd() {
        return "用户名不能为空";
    }

    public boolean isPassword(String password) {
        if (password.isEmpty()) {
            return false;
        }
        String chosePassword = Global.getPasswordRegular();
        return password.matches(chosePassword);
    }

    public String setPasswordRimd() {
        return Global.getPasswordRemind();
    }

    public boolean isConfirmPassword(String password, String ConfirmPassword) {
        return password.equals(ConfirmPassword);
    }

    public String setConfirmPasswordRimd() {
        return "两次密码不一致";
    }

    public boolean isPhone(String phone) {
        return phone.matches(PHONE_NUM);
    }

    public String setPhoneRimd() {
        return "请填写11位有效手机号码";
    }

    private void createCaptcha(String phone) {
        CaptchaGlobal captchaGlobal = new CaptchaGlobal();
        captchaGlobal.createCaptcha(phone);
    }

    public String getCapcha(String phone) {
        CaptchaGlobal captchaGlobal = new CaptchaGlobal();
        return captchaGlobal.getCapcha(phone);
    }

    public boolean isCaptcha(String phone, String captcha) throws RuntimeException {
        if (captcha.isEmpty()) {
            throw new RuntimeException("未发送验证码");
        }
        return captcha.equals(getCapcha(phone));
    }

    public String setCaptchaRimd() {
        return "验证码错误";
    }

    public boolean isWorkNumber(String workNumber) {
        if (workNumber.isEmpty()) {
            return false;
        }
        return workNumber.matches(Global.getWorkIDRegular());
    }

    public String setWorkNumberRimd() {
        return "工号格式错误";
    }

    // 新增生成验证码并处理界面交互的方法，供外部调用（比如 GUI 相关类）
    // 这里把原来和界面组件交互的逻辑放到这里，后续在实际使用时传入对应组件即可
    public void createCaptchaWithUI(String phone, JButton phoneButton, JLabel phoneRimd, MyFrame frame) {
        if (isPhone(phone)) {
            createCaptcha(phone);
            phoneRimd.setText("验证码已发送");
            phoneButton.setEnabled(false);

            // 通知用户验证码号码
            ObserverMessagePanel obserMe = (ObserverMessagePanel) AllObserverOfFrame.getObserverByFrame(frame, AllObserverOfFrame.Type.OBSERVER_MESSAGE_PANEL);
            if (obserMe != null) {
                obserMe.addMessage("用户界面顶部消息",
                        "尊敬的" + phone + "用户，\n" +
                                "\n" +
                                "您的验证码是：" + getCapcha(phone) + "。\n" +
                                "\n" +
                                "请勿将验证码泄露给他人，以确保您的账户安全。\n" +
                                "\n" +
                                "谢谢！");
            }

            // 创建倒计时线程
            CountDown countDown = new CountDown(60);
            countDown.setCountDownListener(new CountDown.CountDownListener() {
                @Override
                public void onSecondUpdate(int seconds) {
                    // 倒计时中，更新按钮文本
                    SwingUtilities.invokeLater(() -> phoneButton.setText(seconds + "秒"));
                }

                @Override
                public void onCountDownFinish() {
                    // 倒计时结束，恢复按钮状态
                    SwingUtilities.invokeLater(() -> {
                        phoneButton.setText("获取验证码");
                        phoneButton.setEnabled(true);
                    });
                }
            });

            // 启动倒计时线程
            new Thread(countDown).start();
        } else {
            phoneRimd.setText(setPhoneRimd());
        }
    }

}