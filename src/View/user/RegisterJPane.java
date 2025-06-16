package View.user;

import Serve.auth.UserCommonRegister;
import View.FatherFrame;
import View.FatherJPanel;
import Util.factory.FactoryPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Util.factory.FactoryPanel.MyJPanelType.*;

public class RegisterJPane extends FatherJPanel {
    private static final Logger logger = LogManager.getLogger(RegisterJPane.class);
    private FatherFrame frame;//储存索引

    private boolean checkPassword = false;
    private boolean checkConfirmPassword = false;
    public RegisterJPane(FatherFrame frame) {
        logger.info("用户注册界面正在加载");
        this.frame = frame;//获得索引
        UserCommonRegister register1 = new UserCommonRegister();
        setLayout(new GridLayout(12,1));
        FactoryPanel factoryPanel = new FactoryPanel();

        add(factoryPanel.createPanel(JLABLE_JTEXTFIELD_JLABLE,"昵称：", "20;名称输入框", ""));
        add(factoryPanel.createPanel(JLABLE, ";名称提示标签"));
        add(factoryPanel.createPanel(JLABLE_JPASSWORDFIELD_JBUTTON,"新密码：", "20;密码输入框", "显示;密码查看"));
        add(factoryPanel.createPanel(JLABLE, register1.setPasswordRemind() + ";密码提示标签"));
        add(factoryPanel.createPanel(JLABLE_JPASSWORDFIELD_JBUTTON, "确认密码：", "20;重复密码输入框", "显示;确认密码查看"));
        add(factoryPanel.createPanel(JLABLE, ";重复密码提示标签"));
        add(factoryPanel.createPanel(JLABLE_JTEXTFIELD_JBUTTON, "手机号：", "20;手机号输入框","获取验证码;发送验证码"));
        add(factoryPanel.createPanel(JLABLE, ";手机号码提示标签"));
        add(factoryPanel.createPanel(JLABLE_JTEXTFIELD_JLABLE,"验证码", "10;验证码输入框", ""));
        add(factoryPanel.createPanel(JLABLE, ";验证码提示标签"));
        add(factoryPanel.createPanel(BUTTONS, ";立即注册;注册按钮", "返回登录;登录按钮"));

        //注册事件
        JButton register = (JButton)factoryPanel.getJComponent( "注册按钮");
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                register1.register(
                        (JTextField) factoryPanel.getJComponent( "名称输入框"),
                        (JLabel) factoryPanel.getJComponent("名称提示标签"),
                        (JPasswordField)factoryPanel.getJComponent( "密码输入框"),
                        (JLabel) factoryPanel.getJComponent("密码提示标签"),
                        (JPasswordField)factoryPanel.getJComponent("重复密码输入框"),
                        (JLabel) factoryPanel.getJComponent("重复密码提示标签"),
                        (JTextField)factoryPanel.getJComponent("手机号输入框"),
                        (JLabel) factoryPanel.getJComponent("手机号码提示标签"),
                        (JTextField)factoryPanel.getJComponent("验证码输入框"),
                        (JLabel) factoryPanel.getJComponent("验证码提示标签")
                );
            }
        });
        //登录事件
        JButton login = (JButton)factoryPanel.getJComponent( "登录按钮");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new LoginPanel(frame));
            }
        });

        //发送验证码事件
        JButton sendCode = (JButton)factoryPanel.getJComponent( "发送验证码");
        sendCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField phoneField = (JTextField)factoryPanel.getJComponent("手机号输入框");
                String phone = phoneField.getText().trim();
                register1.createCaptcha(
                        phone,
                        (JButton) factoryPanel.getJComponent("发送验证码"),
                        (JLabel) factoryPanel.getJComponent("手机号码提示标签"),
                        frame);
            }
        });
        //查看密码事件
        JPasswordField passwordField = (JPasswordField) factoryPanel.getJComponent("密码输入框");
        passwordField.setEchoChar('●');
        JButton showPassword = (JButton)factoryPanel.getJComponent( "密码查看");
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPassword = !checkPassword;
                if (checkPassword) {
                    passwordField.setEchoChar((char) 0);
                    showPassword.setText("隐藏");
                } else {
                    passwordField.setEchoChar('●');
                    showPassword.setText("显示");
                }
            }
        });
        //查看确认密码事件
        JPasswordField confirmPasswordField = (JPasswordField) factoryPanel.getJComponent("重复密码输入框");
        confirmPasswordField.setEchoChar('●');
        JButton showConfirmPassword = (JButton)factoryPanel.getJComponent( "确认密码查看");
        showConfirmPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkConfirmPassword = !checkConfirmPassword;
                if (checkConfirmPassword) {
                    confirmPasswordField.setEchoChar((char) 0);
                    showConfirmPassword.setText("隐藏");
                } else {
                    confirmPasswordField.setEchoChar('●');
                    showConfirmPassword.setText("显示");
                }
            }
        });
        logger.info("用户注册界面加载完成");
    }
}
