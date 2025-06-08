package admin.View.panel.auth;


import admin.Serve.AdminJFrameManger;
import admin.Serve.LoginAdminServe;
import admin.Serve.SetServe;
import global.erro.NameException;
import global.erro.QueryPasswordException;
import global.view_tool.MyJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginPanel extends MyJPanel implements ActionListener {
    private static final Logger logger = LogManager.getLogger(AdminLoginPanel.class);
    private static final Marker ADMIN = MarkerManager.getMarker("ADMIN");
    private final JButton loginButton, registerButton;
    private final JLabel userLable, passWordLable;
    private final JTextField username;
    private final JPasswordField userPassWord;
    private final JPanel buttonPanel;
    private final JPanel namePanel, passWordPanel;

    private final JLabel nameLabel ;                //用户名提示标签
    private final JLabel passWordLabel ;            //密码提示标签

    public AdminLoginPanel() {
        setLayout(new GridLayout(6, 2));
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");

        //用户名
        userLable = new JLabel("管理员名称：");
        userLable.setHorizontalAlignment(JLabel.RIGHT);
        username = new JTextField(20);
        namePanel = new JPanel(new GridLayout(1,3));
        namePanel.add(userLable);
        namePanel.add(username);
        namePanel.add(new JLabel(""));
        add(namePanel);

        //用户名提示框
        nameLabel = new JLabel();
        nameLabel.setForeground(Color.RED);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel nameLabelPanel = new JPanel(new GridLayout(1,1));
        nameLabelPanel.add(nameLabel);
        add(nameLabelPanel);

        //密码
        passWordLable = new JLabel("密码：");
        passWordLable.setHorizontalAlignment(JLabel.RIGHT);
        userPassWord = new JPasswordField(20);
        passWordPanel = new JPanel(new GridLayout(1,3));
        passWordPanel.add(passWordLable);
        passWordPanel.add(userPassWord);
        passWordPanel.add(new JLabel(""));
        add(passWordPanel);

        //密码提示框
        passWordLabel = new JLabel();
        passWordLabel.setForeground(Color.RED);
        passWordLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel passWordLabelPanel = new JPanel(new GridLayout(1,1));
        passWordLabelPanel.add(passWordLabel);
        add(passWordLabelPanel);

        //按钮
        buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        add(buttonPanel);




        setVisible(true);
        //监听
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {
                //名称验证
                LoginAdminServe.isName(getNameText());
                //密码验证
                LoginAdminServe.isPassword(getPassWordText());
                //登录
                boolean login = LoginAdminServe.login(getNameText(), getPassWordText());
                if (login) {
                    logger.info(ADMIN, "{}管理员登录成功", getNameText());
                    SetServe.createAdminMainJFrame();
                }
            } catch (NameException e1) {
                nameLabel.setText( e1.getMessage());
            } catch (QueryPasswordException e2) {
                passWordLabel.setText( e2.getMessage());
            }

        } else if (e.getSource() == registerButton) {
           //注册
            AdminJFrameManger.getAdminJFrameRenderingPanel().update(new AdminRegisterPanel());
        }
    }


    public String getNameText(){
        return username.getText().trim();
    }

    public String getPassWordText(){
        return userPassWord.getText().trim();
    }

}
