package View.user;


import Serve.auth.CommonLogin;
import View.MyFrame;
import View.MyJPanel;
import View.factoryPanel.FactoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static View.factoryPanel.FactoryPanel.MyJPanelType.*;

public class LoginPanel extends MyJPanel {

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
        this.frame = userFrame;

        setLayout(new GridLayout(13,1));

        FactoryPanel factoryPanel = new FactoryPanel();
        //设置面板
        add(factoryPanel.createPanel(JLABLE_JTEXTFIELD_JLABLE, "昵称:", "10", ""));
        add(factoryPanel.createPanel(JLABLE, "<html>&nbsp;</html>"));
        add(factoryPanel.createPanel(JLABLE_JPASSWORDFIELD_JBUTTON, "密码:", "10", "显示"));
        add(factoryPanel.createPanel(JLABLE, "<html>&nbsp;&nbsp;</html>"));

        //提取组件
        JTextField nickNameInput = (JTextField)factoryPanel.getPanel(JLABLE_JTEXTFIELD_JLABLE,"10");
        JLabel nickName = (JLabel)factoryPanel.getPanel(JLABLE,"<html>&nbsp;</html>");

        JPasswordField password = (JPasswordField)factoryPanel.getPanel(JLABLE_JPASSWORDFIELD_JBUTTON,"10");
        JLabel rimind = (JLabel)factoryPanel.getPanel(JLABLE,"<html>&nbsp;&nbsp;</html>");

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
    }


}
