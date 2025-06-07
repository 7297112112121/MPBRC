package user.View.entrance;

import util.view_tool.MyJPanel;
import user.Service.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.User;
import user.Service.Loginer;
import util.erro.SetPasswordExcetion;
import user.Service.UserRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Login extends MyJPanel implements ActionListener, Text {
    private final static Logger logger = LogManager.getLogger(Login.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private final JButton loginButton, registerButton;
    private final JLabel userLable, passWordLable;
    private final JTextField username;
    private final JPasswordField userPassWord;
    private final JPanel buttonPanel;
    private final JPanel namePanel, passWordPanel;
    private final ArrayList<JTextField> listCom;         //输入框列表

    private final JLabel nameRemind;            //昵称输入提示
    private final JLabel passWordRemind;        //密码输入提示


    public Login() {
        super();
        setLayout(new GridLayout(8, 1));
        loginButton = new JButton("登录");
        registerButton = new JButton("注册");
        userLable = new JLabel("用户名：");
        username = new JTextField(20);
        namePanel = new JPanel(new GridLayout(1,2));
        namePanel.add(userLable);
        namePanel.add(username);

        passWordLable = new JLabel("密码：");
        userPassWord = new JPasswordField(20);
        passWordPanel = new JPanel(new GridLayout(1,2));
        passWordPanel.add(passWordLable);
        passWordPanel.add(userPassWord);
        buttonPanel = new JPanel(new GridLayout(1,2));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        //昵称提示
        JPanel nameR = new JPanel();
        nameRemind = new JLabel();
        nameRemind.setForeground(Color.red);
        nameR.add(nameRemind);

        //密码提示
        JPanel passR = new JPanel();
        passWordRemind = new JLabel();
        passWordRemind.setForeground(Color.red);
        passR.add(passWordRemind);

        //布局
        add(namePanel);
        add(nameR);
        add(passWordPanel);
        add(passR);
        add(buttonPanel);

        setVisible(true);
        //监听
        listCom = new ArrayList<>();                    //设置输入框聚焦
        listCom.add(username);
        listCom.add(userPassWord);
        username.addActionListener(this);
        userPassWord.addActionListener(this);
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);



    }
    public Object createIdentity(){
        Object obj = new User(getName(), getPassWord());
        return obj;
    }

    /**
     * 事件入口
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getLoginButton()) {
            login();
        } else if (e.getSource() == getRegisterButton()) {
            //前往注册界面
            UserRouter.getRouter().getUserJFrame().getRendering().update(new Register());
        }
    }


    /**
     * 登陆事件
     * */
    public void login() {
        if (!isForm()){
            return;                                                     //判断输入是否为空，为空停止执行
        }
        Object obj = createIdentity();                                  //将输入框中的信息转换为User对象，向上转型Object
        try{
            //登录操作
            if (Loginer.login(obj)){
                //开启用户主窗口
                UserRouter.getRouter().newJFrame(USERMAINFRAME);
                //更新用户主窗口用户信息面板
                //退出登录注册窗口
                UserRouter.getRouter().removeJFrame(UserJFrame);
            }else {
                throw new SetPasswordExcetion("请检查用户名或密码是否输入正确");
            }
        }catch (SetPasswordExcetion e2){
            e2.show();
        }catch (Exception e1){
            logger.error(e1.getMessage(),e1);
        }
    }

    /**
     * 检查输入框是否为空
     * 按未输入元素节点提示错误
     * 默认返回false
     * */
    public boolean isForm() {
        nameRemind.setText("");
        passWordRemind.setText("");
        boolean fals = false;
            if (getName().isEmpty()) {
                fals = false;
                nameRemind.setText("用户名不能为空");
            }else if (getPassWord().isEmpty()) {
                fals = false;
                passWordRemind.setText("密码不能为空");
            }else {
                fals = true;
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

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

}
