package View.admin.panel.auth;

import Util.View.DoorJPanel;
import Serve.LoginAdminServe;
import Serve.RegisterServe;
import Config.PasswordConfig;
import Util.erro.*;
import Object.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminRegisterPanel extends DoorJPanel implements PasswordConfig, ActionListener {
    private final JButton register, login;              //用户注册按钮
    private final JTextField username;                  //用户名输入框
    private final JPasswordField userPassWord;          //密码输入框
    private final JPasswordField rePasswordField;       //确认密码输入框
    private final JTextField phone;                     //手机号码输入框
    private final JButton yangZhengMaButton;            //发送验证码按钮
    private final JTextField yangZhengMa;               //验证码输入框
    private final JTextField workIDField;               //工号输入框

    private final JLabel nameRemind;                    //用户名提示信息
    private final JLabel passwordRemind;                //密码提示信息
    private final JLabel rePasswordRemind;              //确认密码提示信息
    private final JLabel phoneRemind;                   //手机号提示信息
    private final JLabel yangZhengMaRemind;             //验证码提示信息
    private final JLabel workIDRemind;                  //工号提示信息

    public AdminRegisterPanel() {
        super();
        setLayout(new GridLayout(14, 1));


        //用户名
        JLabel userLable = new JLabel("管理员名称：");
        userLable.setHorizontalAlignment(JLabel.RIGHT);
        username = new JTextField(30);
        JPanel namePanel = new JPanel(new GridLayout(1,3));
        namePanel.add(userLable);
        namePanel.add(username);
        namePanel.add(new JLabel(""));
        add(namePanel);

        //用户名提示信息
        nameRemind = new JLabel();
        nameRemind.setForeground(Color.RED);
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
        passwordRemind.setForeground(Color.RED);
        passwordRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel passwordRemindPanel = new JPanel(new GridLayout(1,1));
        passwordRemindPanel.add(passwordRemind);
        add(passwordRemindPanel);

        //确认密码
        JLabel rePassWordLable = new JLabel("确认密码：");
        rePassWordLable.setHorizontalAlignment(JLabel.RIGHT);
        rePasswordField = new JPasswordField(30);
        JPanel rePassWordPanel = new JPanel(new GridLayout(1,3));
        rePassWordPanel.add(rePassWordLable);
        rePassWordPanel.add(rePasswordField);
        rePassWordPanel.add(new JLabel(""));
        add(rePassWordPanel);

        //确认密码提示信息
        rePasswordRemind = new JLabel();
        rePasswordRemind.setForeground(Color.RED);
        rePasswordRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel rePasswordRemindPanel = new JPanel(new GridLayout(1,1));
        rePasswordRemindPanel.add(rePasswordRemind);
        add(rePasswordRemindPanel);

        //手机号码
        JLabel phoneLabel = new JLabel("手机号：");
        phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
        phone = new JTextField(30);
        yangZhengMaButton = new JButton("发送验证码");
        JPanel panelPhone = new JPanel(new GridLayout(1,3));
        panelPhone.add(phoneLabel);
        panelPhone.add(phone);
        panelPhone.add(yangZhengMaButton);
        add(panelPhone);

        //手机号提示信息
        phoneRemind = new JLabel();
        phoneRemind.setForeground(Color.RED);
        phoneRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel phoneRemindPanel = new JPanel(new GridLayout(1,1));
        phoneRemindPanel.add(phoneRemind);
        add(phoneRemindPanel);

        //验证码
        JLabel yangZhengMaLabel = new JLabel("验证码：");
        yangZhengMaLabel.setHorizontalAlignment(JLabel.RIGHT);
        yangZhengMa = new JTextField(30);
        JPanel yangZhengMaPanel = new JPanel(new GridLayout(1,3));
        yangZhengMaPanel.add(yangZhengMaLabel);
        yangZhengMaPanel.add(yangZhengMa);
        yangZhengMaPanel.add(new JLabel(""));
        add(yangZhengMaPanel);

        //验证码提示信息
        yangZhengMaRemind = new JLabel();
        yangZhengMaRemind.setForeground(Color.RED);
        yangZhengMaRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel yangZhengMaRemindPanel = new JPanel(new GridLayout(1,1));
        yangZhengMaRemindPanel.add(yangZhengMaRemind);
        add(yangZhengMaRemindPanel);

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
        workIDRemind.setForeground(Color.RED);
        workIDRemind.setHorizontalAlignment(JLabel.CENTER);
        JPanel workIDRemindPanel = new JPanel(new GridLayout(1,1));
        workIDRemindPanel.add(workIDRemind);
        add(workIDRemindPanel);

        //按钮
        JPanel file = new JPanel();
        register = new JButton("管理员注册");
        login = new JButton("返回登录");
        file.add(register);
        file.add(login);
        add(file);


        //注册事件
        register.addActionListener(this);
        login.addActionListener(this);
        yangZhengMaButton.addActionListener(this);

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
                RegisterServe.isPassword(getAdminPassWordText(), getReAdminRePassWordText());
                //验证手机号
                RegisterServe.isPhone(getPhoneText());
                //验证验证码
                RegisterServe.isYangZhengMa(getYangZhengMaText());
                //验证工号
//            RegisterServe.isWorkID(getWorkIDFieldText());
                //注册
                Admin admin = new Admin(getAdminNameText(), getAdminPassWordText(), getPhoneText(), getWorkIDText());
                RegisterServe.register(admin);
                //自动登陆
                LoginAdminServe.login(getAdminNameText(), getAdminPassWordText());
                //以下异常显示输入错误原因
            } catch (NameException name) {
                nameRemind.setText(name.getMessage());
            } catch (SetPasswordExcetion password) {
                passwordRemind.setText(password.getMessage());
            } catch (QueryPasswordException rePassword) {
                rePasswordRemind.setText(rePassword.getMessage());
            } catch (PhoneException phone) {
                phoneRemind.setText(phone.getMessage());
            } catch (YangZhengMaException yangZhengMa) {
                yangZhengMaRemind.setText(yangZhengMa.getMessage());
            } catch (WorkIDException workID) {
                workIDRemind.setText(workID.getMessage());
            }

        }
        if (source == login) {
            AdminRouterServe.getRouter().getAdminJFrame().getRendering().update(new AdminLoginPanel());
        }
        if (source == yangZhengMaButton) {
            try {
                //确认手机号码
                RegisterServe.isPhone(getPhoneText());
                //发送验证码
                RegisterServe.sendYangZhenMa(60, yangZhengMaButton);
            } catch (PhoneException phone) {
                phoneRemind.setText(phone.getMessage());
            }

        }
    }

    public String getAdminNameText() {
        return username.getText().trim();
    }

    public String getAdminPassWordText() {
        return userPassWord.getText().trim();
    }

    public String getReAdminRePassWordText() {
        return rePasswordField.getText().trim();
    }

    public String getWorkIDText() {
        return workIDField.getText().trim();
    }

    public String getPhoneText() {
        return phone.getText().trim();
    }

    public String getYangZhengMaText() {
        return yangZhengMa.getText().trim();
    }
}
