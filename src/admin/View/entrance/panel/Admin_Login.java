package admin.View.entrance.panel;


import util.view_tool.MyJPanel;
import admin.Admin;
import admin.Serve.Loginer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import admin.View.AdminRouter;
import util.erro.SetPasswordExcetion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_Login extends MyJPanel implements ActionListener {
    private static final Logger logger = LogManager.getLogger(Admin_Login.class);
    private static final Marker ADMIN = MarkerManager.getMarker("ADMIN");
    private final JButton loginButton, registerButton;
    private final JLabel userLable, passWordLable;
    private final JTextField username;
    private final JPasswordField userPassWord;
    private final JPanel buttonPanel;
    private final JPanel namePanel, passWordPanel;

    public Admin_Login() {
        setLayout(new GridLayout(8, 2));
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");
        userLable = new JLabel("用户名：");
        username = new JTextField(20);
        namePanel = new JPanel();
        namePanel.add(userLable);
        namePanel.add(username);

        passWordLable = new JLabel("密码：");
        userPassWord = new JPasswordField(20);
        passWordPanel = new JPanel();
        passWordPanel.add(passWordLable);
        passWordPanel.add(userPassWord);
        buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        //布局
        add(namePanel);
        add(passWordPanel);
        add(buttonPanel);

        setVisible(true);
        //监听
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
    }


    /**
     * 登录事件
     * 原理：
     * 1、检查输入格式
     * 2、提交登陆表单
     * 3、处理返回结果
     * 4、销毁登录界面用户对象
     * */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (!isForm()){
                return;                                             //判断输入是否为空，为空停止执行
            }
            Object obj = new Admin(getName(), getPassWord());                          //模版，继承该类重写该方法，实现具体实例对象
            try{
                if (Loginer.login(obj)){                                //执行登录
                }else {
                    throw new SetPasswordExcetion("请检查用户名或密码是否输入正确");
                }
            }catch (SetPasswordExcetion e2){
                e2.show();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        } else if (e.getSource() == registerButton) {
            /**
             * 前往注册界面
             * */
            AdminRouter.getRouter().newJFrame(AdminJFrame);
        }
    }

    /**
     * 检查输入框是否为空
     * 按未输入元素节点提示错误
     * 默认返回false
     * */
    public boolean isForm() {
        boolean fals = false;
        try {
            if (getName().isEmpty()) {
                fals = false;
                throw new SetPasswordExcetion("用户名不能为空");
            }else if (getPassWord().isEmpty()) {
                fals = false;
                throw new SetPasswordExcetion("密码不能为空");
            }else {
                fals = true;
            }
        } catch (SetPasswordExcetion e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        return fals;
    }

    /**
     * 获取登录界面所有输入框文本
     * 用户名称
     * 密码
     * */
    public String getName(){
        return username.getText().trim();
    }

    public String getPassWord(){
        return userPassWord.getText().trim();
    }

}
