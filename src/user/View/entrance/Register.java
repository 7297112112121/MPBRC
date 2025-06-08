package user.View.entrance;

import user.Service.LoginerMessageServe;
import user.Service.RegisterServe;
import user.User;
import global.erro.*;
import global.PasswordConfig;
import global.view_tool.MyJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.Service.UserRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends MyJPanel implements ActionListener, PasswordConfig {
    private final static Logger logger = LogManager.getLogger(Register.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private final JTextField phone;                     //手机号码输入框
    private final JLabel phoneR;                        //手机号提示信息
    private final JButton phoneYanzheng;                //手机号验证码按钮
    private final JTextField phoneYanzhengma;           //手机号验证码输入框

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

    private final JLabel remind;                            //提示信息

    private final  JRadioButton radioButton;        //用户协议单项选择


    public Register() {
        super();
        setLayout(new GridLayout(15, 1));

        //空格标签
        JLabel space = new JLabel("");

        //用户名
        JLabel userLable = new JLabel("用户名：");
        userLable.setHorizontalAlignment(JLabel.RIGHT);
        username = new JTextField(20);
        JPanel namePanel = new JPanel(new GridLayout(1,3));
        namePanel.add(userLable);
        namePanel.add(username);
        namePanel.add(new JLabel(""));

        //用户名提示信息
        userR = new JLabel();
        userR.setForeground(Color.red);
        JPanel userRPanel = new JPanel();
        userRPanel.add(userR);

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
        passWordR.setText(PWD_B_TEXT);
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

        //提示是否注册信息
        JPanel remindPanel = new JPanel(new GridLayout(1,1));
        remind = new JLabel();
        remindPanel.add(remind);


        //按钮
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

        add(remindPanel);

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

        //重置提示信息
        userR.setText("");
        passWordR.setText(PWD_B_TEXT);
        passWordR.setForeground(Color.red);
        reText.setText("");
        phoneR.setText("");
        remind.setText("");

        if (source == register) {
            //登陆
            try {
                //用户名验证
                RegisterServe.isName(getNameText());
                //密码验证
                RegisterServe.isPassword(getUserPassWord(), getRePassword());
                //手机号码验证
                RegisterServe.isPhone(getPhoneText());
                //验证码验证
                RegisterServe.isYangZhengMa(phoneYanzhengma.getText().trim());
                //注册
                RegisterServe.register(getNameText(), getUserPassWord(), getPhoneText());
                //直接登录，打开用户界面
                UserRouter.getRouter().removeJFrame(UserJFrame);
                User user = new User(getNameText(), getRePassword());
                LoginerMessageServe.login(user);
                UserRouter.getRouter().newJFrame(USERMAINFRAME);
            }catch (NameException name) {
                userR.setText(name.getMessage());
            } catch (SetPasswordExcetion form) {
                passWordR.setText(form.getMessage());
            }catch (QueryPasswordException query) {
                reText.setText(query.getMessage());
            }catch (PhoneException phone) {
                phoneR.setText(phone.getMessage());
            }catch (UserObjectException user) {
                remind.setText(user.getMessage());
            }
        }

        if (source == login) {
            //注册
            UserRouter.getRouter().getUserJFrame().getRendering().update(new Login());
        }

        if (source == phoneYanzheng ) {
            //手机号码验证
            RegisterServe.isPhone(getPhoneText());
            //发送验证码
            RegisterServe.sendYangZhenMa(30,phoneYanzheng);
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

    /**
     * 格式化信息
     * */
    public String getNameText() {
        return username.getText().trim();
    }

    public String getPhoneText() {
        return phone.getText().trim();
    }

    public String getUserPassWord() {
        return userPassWord.getText().trim();
    }

    public String getRePassword() {
        return rePassword.getText().trim();
    }
}