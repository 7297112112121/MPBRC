package admin.View.entrance.panel;

import util.other.PasswordForm;
import util.view_tool.MyJPanel;
import admin.Admin;
import admin.Serve.Register;
import admin.View.AdminRouter;
import util.erro.SetPasswordExcetion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_Register extends MyJPanel implements PasswordForm, ActionListener {
    private final JTextField phone;                     //手机号码输入框
    private final JButton register, login;              //用户注册按钮
    private final JTextField username;                  //用户名输入框
    private final JPasswordField userPassWord;          //密码输入框
    private final JTextField workIDField;
    public Admin_Register() {
        super();
        setLayout(new GridLayout(8, 2));
        JLabel userLable = new JLabel("用户名：");
        username = new JTextField(20);
        JPanel namePanel = new JPanel();
        namePanel.add(userLable);
        namePanel.add(username);

        //用户名，密码标签
        JLabel passWordLable = new JLabel("密码：");
        userPassWord = new JPasswordField(20);
        //用户名面板，密码面板
        JPanel passWordPanel = new JPanel();
        passWordPanel.add(passWordLable);
        passWordPanel.add(userPassWord);
        //手机号码标签
        JLabel phoneLabel = new JLabel("手机号：");
        phone = new JTextField(20);
        JPanel panelPhone = new JPanel();
        panelPhone.add(phoneLabel);
        panelPhone.add(phone);

        JPanel file = new JPanel();
        register = new JButton("用户注册");
        login = new JButton("返回登录");
        file.add(register);
        file.add(login);

        //手机号码面板
        JPanel text = new JPanel();
        //返回登录按钮
        JRadioButton radioButton = new JRadioButton("同意《用户协议》");
        text.add(radioButton);

        JLabel workID = new JLabel("工号");
        workIDField= new JTextField();
        JPanel jp = new JPanel(new GridLayout(1,2));
        jp.add(workID);
        jp.add(workIDField);


        //布局
        add(namePanel);
        add(passWordPanel);
        add(panelPhone);
        add(jp);
        add(file);
        add(text);




        //注册事件
        register.addActionListener(this);
        login.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == register) {
            //注册
            if (isForm()) {
                Admin admin = new Admin(getName(), getPassWord());
                if (Register.register(admin)) {
                    AdminRouter.getRouter().getAdminJFrame().getRendering().update(new Admin_Login());
                }else {
                    JOptionPane.showMessageDialog(null, "已注册", "管理员注册", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (source == login) {
            AdminRouter.getRouter().getAdminJFrame().getRendering().update(new Admin_Login());
        }
    }

    /**
     * 注册/返回登陆事件监听
     * */


    public boolean isForm() {
        boolean fals = true;
        try{
            if (getName().isEmpty()) {
                fals = false;
                throw new SetPasswordExcetion("用户名不能为空");
            }else if (getPassWord().isEmpty()) {
                fals = false;
                throw new SetPasswordExcetion("密码不能为空");
            } else if (getPhoneText().isEmpty()) {
                fals = false;
                throw new SetPasswordExcetion("手机号码不能为空");
            } else if (!isPassword()) {                     //密码格式
                fals = false;
                throw new SetPasswordExcetion(PWD_B_TEXT);
            }else if (!isPhoneForm()) {                     //手机号码格式
                fals = false;
                throw new SetPasswordExcetion(PHONE_TEXT);
            }else if (getWorkID().isEmpty()) {
                fals = false;
                throw new SetPasswordExcetion("工号不能为空");
            } else if (isWorkID()) {
                fals = false;
                throw new SetPasswordExcetion(WORK_ID_TEXT);       //工号格式
            }
        }catch (SetPasswordExcetion e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
        return fals;
    }

    /**
     * 获得输入框内容
     * */
    public String getWorkID() {
        return workIDField.getText().trim();
    }

    /**
     * 判断工号是否合法
     * */
    public boolean isWorkID() {
        return getWorkID().matches(WORK_ID_NUM);
    }

    public boolean isPassword(){
        return getPassWord().matches(PWD_B);
    }

    public boolean isPhoneForm(){
        return getPhoneText().matches(PHONE_NUM);
    }

    /**
     * 获取注册界面所有输入框文本
     * 用户名称
     * 密码
     * 手机号码
     * */
    public String getName(){
        return username.getText().trim();
    }

    public String getPassWord(){
        return userPassWord.getText().trim();
    }

    public String getPhoneText() {
        return phone.getText().trim();
    }

}
