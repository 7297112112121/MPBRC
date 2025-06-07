package admin.View.entrance.panel;

import admin.Serve.AdminRouterServe;
import admin.Serve.LoginAdminServe;
import admin.Serve.RegisterServe;
import util.config.PasswordConfig;
import util.erro.NameException;
import util.erro.PhoneException;
import util.erro.WorkIDException;
import util.view_tool.MyJPanel;
import admin.Admin;
import admin.DAO.RegisterDAO;
import util.erro.SetPasswordExcetion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminRegisterPanel extends MyJPanel implements PasswordConfig, ActionListener {
    private final JTextField phone;                     //手机号码输入框
    private final JButton register, login;              //用户注册按钮
    private final JTextField username;                  //用户名输入框
    private final JPasswordField userPassWord;          //密码输入框
    private final JTextField workIDField;

    private final JLabel nameRemind;                    //用户名提示信息
    private final JLabel passwordRemind;                //密码提示信息
    private final JLabel phoneRemind;                   //手机号提示信息
    private final JLabel workIDRemind;                  //工号提示信息

    public AdminRegisterPanel() {
        super();
        setLayout(new GridLayout(12, 1));


        //用户名
        JLabel userLable = new JLabel("用户名：");
        userLable.setHorizontalAlignment(JLabel.RIGHT);
        username = new JTextField(30);
        JPanel namePanel = new JPanel(new GridLayout(1,3));
        namePanel.add(userLable);
        namePanel.add(username);
        namePanel.add(new JLabel(""));
        add(namePanel);

        //用户名提示信息
        nameRemind = new JLabel();
        nameRemind.setForeground(Color.GRAY);
        nameRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel nameRemindPanel = new JPanel(new GridLayout(1,1));
        nameRemindPanel.add(nameRemind);
        add(nameRemindPanel);

        //密码
        JLabel passWordLable = new JLabel("密码：");
        passWordLable.setHorizontalAlignment(JLabel.RIGHT);
        userPassWord = new JPasswordField(30);
        JPanel passWordPanel = new JPanel(new GridLayout(1,3));
        passWordPanel.add(passWordLable);
        passWordPanel.add(userPassWord);
        passWordPanel.add(new JLabel(""));
        add(passWordPanel);

        //密码提示信息
        passwordRemind = new JLabel();
        passwordRemind.setForeground(Color.GRAY);
        passwordRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel passwordRemindPanel = new JPanel(new GridLayout(1,1));
        passwordRemindPanel.add(passwordRemind);
        add(passwordRemindPanel);

        //手机号码
        JLabel phoneLabel = new JLabel("手机号：");
        phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
        phone = new JTextField(30);
        JPanel panelPhone = new JPanel(new GridLayout(1,3));
        panelPhone.add(phoneLabel);
        panelPhone.add(phone);
        panelPhone.add(new JLabel(""));
        add(panelPhone);

        //手机号提示信息
        phoneRemind = new JLabel();
        phoneRemind.setForeground(Color.GRAY);
        phoneRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel phoneRemindPanel = new JPanel(new GridLayout(1,1));
        phoneRemindPanel.add(phoneRemind);
        add(phoneRemindPanel);

        //工号
        JLabel workID = new JLabel("工号:");
        workID.setHorizontalAlignment(JLabel.RIGHT);
        workIDField= new JTextField(30);
        JPanel jp = new JPanel(new GridLayout(1,3));
        jp.add(workID);
        jp.add(workIDField);
        jp.add(new JLabel(""));
        add(jp);

        //工号提示信息
        workIDRemind = new JLabel();
        workIDRemind.setForeground(Color.GRAY);
        workIDRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel workIDRemindPanel = new JPanel(new GridLayout(1,1));
        workIDRemindPanel.add(workIDRemind);
        add(workIDRemindPanel);

        //按钮
        JPanel file = new JPanel();
        register = new JButton("用户注册");
        login = new JButton("返回登录");
        file.add(register);
        file.add(login);
        add(file);

        //协议
        JPanel text = new JPanel();
        JRadioButton radioButton = new JRadioButton("同意《管理员规定》");
        text.add(radioButton);
        add(text);

        //注册事件
        register.addActionListener(this);
        login.addActionListener(this);

        //显示所有组件
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == register) {
            //重置提示标签
            nameRemind.setText("");
            passwordRemind.setText("");
            phoneRemind.setText("");
            workIDRemind.setText("");
            try {
                //验证名字
                RegisterServe.isName(getAdminNameText());
                //验证密码
                RegisterServe.isPassword(getAdminPassWordText());
                //验证手机号
                RegisterServe.isPhone(getPhoneText());
                //验证工号
//            RegisterServe.isWorkID(getWorkIDFieldText());
                //注册
                Admin admin = new Admin(getAdminNameText(), getAdminPassWordText(), getPhoneText(), getWorkIDText());
                RegisterServe.register(admin);
                //自动登陆
                LoginAdminServe.login(getAdminNameText(), getAdminPassWordText());
                //以下异常显示输入错误原因
            }catch (NameException name) {
                nameRemind.setText(name.getMessage());
            }catch (SetPasswordExcetion password) {
                passwordRemind.setText(password.getMessage());
            }catch (PhoneException phone) {
                phoneRemind.setText(phone.getMessage());
            }catch (WorkIDException workID) {
                workIDRemind.setText(workID.getMessage());
            }

        }
        if (source == login) {
            AdminRouterServe.getRouter().getAdminJFrame().getRendering().update(new AdminLoginPanel());
        }
    }

    public String getAdminNameText() {
        return username.getText().trim();
    }

    public String getAdminPassWordText() {
        return userPassWord.getText().trim();
    }

    public String getWorkIDText() {
        return workIDField.getText().trim();
    }

    public String getPhoneText() {
        return phone.getText().trim();
    }
}
