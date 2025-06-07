package user.View.entrance;

import user.DAO.RegisterDAO;
import user.View.jframe.Form;
import util.view_tool.MyJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.View.jframe.UserRouter;
import user.User;
import util.random.Phone_Yangzhengma;
import util.time.PhoneCountdown;
import util.tset.UserPhoneMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends MyJPanel implements ActionListener, Form {
    private final static Logger logger = LogManager.getLogger(Register.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private final JTextField phone;                     //手机号码输入框
    private final JLabel phoneR;                        //手机号提示信息
    private final JButton phoneYanzheng;                //手机号验证码按钮
    private final JTextField phoneYanzhengma;           //手机号验证码输入框
    private String number;                              //生成的验证码号

    private final JButton register, login;              //用户注册按钮
    private final JTextField username;                  //用户名输入框
    private final JLabel userR;                         //用户名提示信息

    private final JPasswordField userPassWord;          //密码输入框
    private final JLabel passWordR;                     //密码提示信息
    private final JButton passWordLook;                 //查看密码
    private boolean passWordLookBool;                   //查看密码状态器


    private final JPasswordField rePassword;        //确认密码
    private final JLabel reText;                    //确认密码错误提示
    private final JButton reLook;                   //查看密码
    private boolean reLookBool;                     //查看密码状态器

    private final  JRadioButton radioButton;        //用户协议单项选择


    public Register() {
        super();
        setLayout(new GridLayout(16, 1));

        //空格标签
        JLabel space = new JLabel("  ");

        //用户名
        JLabel userLable = new JLabel("用户名：");
        userLable.setHorizontalAlignment(JLabel.RIGHT);
        username = new JTextField(20);
        JPanel namePanel = new JPanel(new GridLayout(1,3));
        namePanel.add(userLable);
        namePanel.add(username);
        namePanel.add(space);

        //用户名提示信息
        userR = new JLabel();
        userR.setForeground(Color.red);
        JPanel userRPanel = new JPanel();
        userRPanel.add(userR);
        add(userRPanel);

        //密码
        JLabel passWordLable = new JLabel("密码：");
        passWordLable.setHorizontalAlignment(JLabel.RIGHT);
        userPassWord = new JPasswordField(20);
        userPassWord.setEchoChar('●');
        passWordLook = new JButton("查看");
        JPanel passWordPanel = new JPanel(new GridLayout(1,3));
        passWordPanel.add(passWordLable);
        passWordPanel.add(userPassWord);
        passWordPanel.add(passWordLook);

        //密码提示信息
        passWordR = new JLabel();
        passWordR.setForeground(Color.red);
        JPanel passWordRPanel = new JPanel();
        passWordRPanel.add(passWordR);

        //确认密码
        JLabel rePasswordL = new JLabel("确认密码：");
        rePasswordL.setHorizontalAlignment(JLabel.RIGHT);
        rePassword = new JPasswordField(20);
        rePassword.setEchoChar('●');
        reLook = new JButton("查看");
        JPanel passWordRPanel2 = new JPanel(new GridLayout(1,3));
        passWordRPanel2.add(rePasswordL);
        passWordRPanel2.add(rePassword);
        passWordRPanel2.add(reLook);

        //确认密码提示信息
        reText = new JLabel();
        reText.setForeground(Color.red);
        JPanel reTextPanel = new JPanel();
        reTextPanel.add(reText);


        //手机号提示信息
        phoneR = new JLabel();
        phoneR.setForeground(Color.red);
        JPanel phoneRPanel = new JPanel();
        phoneRPanel.add(phoneR);

        //手机号码
        JLabel phoneLabel = new JLabel("手机号：");
        phoneLabel.setHorizontalAlignment(JLabel.RIGHT);
        phone = new JTextField(20);
        JPanel panelPhone = new JPanel(new GridLayout(1,3));
        panelPhone.add(phoneLabel);
        panelPhone.add(phone);
        panelPhone.add(space);

        //手机号验证码
        JLabel phoneYanzhengLabel = new JLabel("验证码：");
        phoneYanzhengLabel.setHorizontalAlignment(JLabel.RIGHT);
        phoneYanzhengma = new JTextField(20);
        phoneYanzheng = new JButton("获取验证码");
        JPanel phoneYanzhengPanel = new JPanel(new GridLayout(1,3));
        phoneYanzhengPanel.add(phoneYanzhengLabel);
        phoneYanzhengPanel.add(phoneYanzhengma);
        phoneYanzhengPanel.add(phoneYanzheng);

        JPanel button = new JPanel(new GridLayout(1,2));
        register = new JButton("用户注册");
        register.setEnabled(false);
        login = new JButton("返回登录");
        button.add(register);
        button.add(login);

        //协议
        JPanel text = new JPanel();
        radioButton = new JRadioButton("同意《用户协议》");
        text.add(radioButton);

        //提示
        JLabel tips = new JLabel("勾选用户协议代表同意本条款，即可注册");
        tips.setFont(new Font("Dialog", Font.BOLD, 18));
        JPanel tipsPanel = new JPanel();
        tipsPanel.add(tips);

        //布局
        add(namePanel);
        add(userRPanel);

        add(passWordPanel);
        add(passWordRPanel);

        add(passWordRPanel2);
        add(reTextPanel);

        add(panelPhone);
        add(phoneRPanel);

        add(phoneYanzhengPanel);

        add(button);

        add(text);
        add(tipsPanel);

        //注册事件
        register.addActionListener(this);
        login.addActionListener(this);
        phoneYanzheng.addActionListener(this);
        reLook.addActionListener(this);
        passWordLook.addActionListener(this);
        radioButton.addActionListener(this);

        JPanel jp = new JPanel();
        add(jp);
        setVisible(true);
    }
    /**
     * 注册/返回登陆 事件监听
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == register) {
            if (isForm()) {
                User user = new User(getName(), getPassWord(), getPhoneText());
                if (RegisterDAO.register(user)) {
                    //前往注册
                    UserRouter.getRouter().getUserJFrame().getRendering().update(new Login());
                }else {
                    JOptionPane.showMessageDialog(null, "已注册", "管理员注册", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (source == login) {
            //前往注册
            UserRouter.getRouter().getUserJFrame().getRendering().update(new Login());
        }
        if (source == phoneYanzheng ) {
            //检查手机号是否为空或符合格式
            if (!isPhoneForm()) {
                phoneR.setText(PHONE_TEXT);
                return;
            }
            //发送验证码
            phoneR.setText("验证码已发送");
            number = Phone_Yangzhengma.verificationNum(6);
            UserPhoneMessage.getUserPhoneMessage().addYangZhengMa(number);
            logger.info(USER,"发送验证码成功: {}", number);
            //启动计时启，60s后重新设置文字为：发送验证码
            new PhoneCountdown().createCountdown(60,phoneYanzheng);
        }
        if (source == reLook) {
            if (!reLookBool) {
                rePassword.setEchoChar('\0');    //显示密码
                reLookBool = !reLookBool;
                reLook.setText("隐藏");
            }else {
                rePassword.setEchoChar('●');    //隐藏密码
                reLookBool = !reLookBool;
                reLook.setText("查看");
            }
        }
        if (source == passWordLook) {
            if (!passWordLookBool) {
                userPassWord.setEchoChar('\0');   //显示密码
                passWordLookBool = !passWordLookBool;
                passWordLook.setText("隐藏");
            }else {
                userPassWord.setEchoChar('●');   //隐藏密码
                passWordLookBool = !passWordLookBool;
                passWordLook.setText("查看");
            }
        }
        if (source == radioButton) {
            if (radioButton.isSelected()) {
                register.setEnabled(true);
            }else {
                register.setEnabled(false);
            }
        }
    }


    public boolean isForm() {
        boolean fals = true;
            if (getName().isEmpty()) {
                fals = false;
                userR.setText("用户名不能为空");
            }else if (!isPassword()) {
                fals = false;
                passWordR.setText(PWD_B_TEXT);
            }else if (!isPhoneForm()) {
                fals = false;
                phoneR.setText(PHONE_TEXT);
            } else if (!isYanzheng()) {
                fals = false;
                phoneR.setText("验证码错误");
            }
        return fals;
    }


    public boolean isPassword(){
        return getPassWord().matches(PWD_B);
    }

    public boolean isPhoneForm(){
        return getPhoneText().matches(PHONE_NUM);
    }

    private boolean isYanzheng() {
        return phoneYanzhengma.getText().trim().equals(number);
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