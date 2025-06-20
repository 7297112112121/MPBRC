package View.user;


import MyObject.User;
import Serve.auth.UserCommonLogin;
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

public class LoginPanel extends FatherJPanel {
    private static final Logger logger = LogManager.getLogger(LoginPanel.class);

    private boolean checkPassw = false;
    private int nameID = 0;

    /**
     * 昵称输入框
     * 昵称输入提醒
     * 密码输入框
     * 密码提醒
     * 登录按钮
     * 注册按钮
     * */
    private final JButton login = new JButton("登录");
    private final JButton register = new JButton("注册");
    private final UserFrame frame;
    public LoginPanel(FatherFrame userFrame) {
        logger.info("用户登陆界面正在加载");
        this.frame = (UserFrame) userFrame;

        setLayout(new GridLayout(13,1));

        FactoryPanel factoryPanel = new FactoryPanel();
        //设置面板
        add(factoryPanel.createPanel(JLABLE_JTEXTFIELD_JLABLE, "昵称:", "10;名字输入框", ""));
        add(factoryPanel.createPanel(JLABLE, ";名字提示标签"));
        add(factoryPanel.createPanel(JLABLE_JPASSWORDFIELD_JBUTTON, "密码:", "10;密码输入框", "显示;查看密码"));
        add(factoryPanel.createPanel(JLABLE, ";密码提示标签"));

        //提取组件
        JTextField nickNameInput = (JTextField)factoryPanel.getJComponent("名字输入框");
        JLabel nickName = (JLabel)factoryPanel.getJComponent("名字提示标签");

        JPasswordField password = (JPasswordField)factoryPanel.getJComponent("密码输入框");
        password.setEchoChar('●');
        JLabel rimind = (JLabel)factoryPanel.getJComponent("密码提示标签");
        JButton showPassword = (JButton)factoryPanel.getJComponent("查看密码");

        //登录按钮
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(login);
        buttonPanel.add(register);
        add(buttonPanel);

        setVisible(true);

        //登录事件
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserCommonLogin lo = new UserCommonLogin();
                 User use = lo.loginAndCreateUserOnline(nickNameInput, nickName, password, rimind);
                //登录成功跳转到首页
                if (use != null) {
                    //跳转到主页
                    frame.setUser(use);
                    frame.update(new HomePanel(frame));

                }
            }
        });
        //注册事件
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new RegisterJPane(frame));
            }
        });
        //查看密码事件
        showPassword.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPassw = !checkPassw;
                if (checkPassw) {
                    password.setEchoChar((char) 0);
                    showPassword.setText("隐藏");
                } else {
                    password.setEchoChar('●');
                    showPassword.setText("显示");
                }
            }
        });
        logger.info("用户登陆界面加载完成");
    }


}
