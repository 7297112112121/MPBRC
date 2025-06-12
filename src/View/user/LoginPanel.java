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
    private final MyFrame userFrame;
    public LoginPanel(MyFrame userFrame) {
        this.userFrame = userFrame;

        setLayout(new GridLayout(3,1));

        FactoryPanel factoryPanel = new FactoryPanel();

        add(factoryPanel.createPanel(NO_Button_INPUT_JPANEL, "昵称:", "10"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.PASSWORD_INPUT_JPANEL, "密码:", "10"));

        JLabel nickName = (JLabel)factoryPanel.getPanel(NO_Button_INPUT_JPANEL,REMIND);
        JTextField nickNameInput = (JTextField)factoryPanel.getPanel(NO_Button_INPUT_JPANEL,CENTER);

        JLabel rimind = (JLabel)factoryPanel.getPanel(PASSWORD_INPUT_JPANEL,REMIND);
        JPasswordField password = (JPasswordField)factoryPanel.getPanel(PASSWORD_INPUT_JPANEL,CENTER);

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
    }


}
