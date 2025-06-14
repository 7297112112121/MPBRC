package View.user;


import Serve.auth.CommonLogin;
import View.MyFrame;
import View.MyJPanel;
import Util.factoryPanel.FactoryPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Util.factoryPanel.FactoryPanel.MyJPanelType.*;

public class LoginPanel extends MyJPanel {
    private static final Logger logger = LogManager.getLogger(LoginPanel.class);

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
    private final MyFrame frame;
    public LoginPanel(MyFrame userFrame) {
        logger.info("用户登陆界面正在加载");
        this.frame = userFrame;

        setLayout(new GridLayout(13,1));

        FactoryPanel factoryPanel = new FactoryPanel();
        //设置面板
        add(factoryPanel.createPanel(JLABLE_JTEXTFIELD_JLABLE, "昵称:", "10;名字输入框", ""));
        add(factoryPanel.createPanel(JLABLE, ";名字提示标签"));
        add(factoryPanel.createPanel(JLABLE_JPASSWORDFIELD_JBUTTON, "密码:", "10;密码输入框", "显示"));
        add(factoryPanel.createPanel(JLABLE, ";密码提示标签"));

        //提取组件
        JTextField nickNameInput = (JTextField)factoryPanel.getJComponent("名字输入框");
        JLabel nickName = (JLabel)factoryPanel.getJComponent("名字提示标签");

        JPasswordField password = (JPasswordField)factoryPanel.getJComponent("密码输入框");
        JLabel rimind = (JLabel)factoryPanel.getJComponent("密码提示标签");

        //登录按钮
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(login);
        buttonPanel.add(register);
        add(buttonPanel);

        setVisible(true);

        //注册事件
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommonLogin lo = new CommonLogin();
                lo.login(nickNameInput, nickName, password, rimind);

            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new RegisterJPane(frame));
            }
        });
        logger.info("用户登陆界面加载完成");
    }


}
