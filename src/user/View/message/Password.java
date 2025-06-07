package user.View.message;

import user.User;
import util.view_tool.MyJPanel;
import user.View.jframe.Form;
import util.view_tool.JFrameLayoutCenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.View.jframe.UserRouter;
import user.DAO.UpData_User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Password extends MyJPanel implements ActionListener, Form {
    private static final Logger logger = LogManager.getLogger(Password.class);
    private static final Marker USER = MarkerManager.getMarker("USER");

    private final JPasswordField orPassword;        //旧密码
    private final JLabel orText;                    //旧密码错误提示
    private final JButton orLook;                   //查看密码
    private boolean orLookBool;                     //查看密码状态器、

    private final JPasswordField newPassword;       //新密码
    private final JLabel newText;                   //新密码错误提示
    private final JButton newLook;                  //查看密码
    private boolean newLookBool;                    //查看密码状态器

    private final JPasswordField rePassword;        //确认密码
    private final JLabel reText;                    //确认密码错误提示
    private final JButton reLook;                   //查看密码
    private boolean reLookBool;                     //查看密码状态器

    private final static int width = 500;           //窗口宽度
    private final static int height = 400;          //窗口高度
    private final JButton submit;                   //提交表单
    private final JButton cancel;                   //取消提交表单

    public Password() {
        //窗口设置
        setLayout(new BorderLayout());
        setBounds(JFrameLayoutCenter.setCenter_X(width), JFrameLayoutCenter.setCenter_Y(height), width, height);//对准主窗口居中

        //创建组件
        orPassword = new JPasswordField(20);
        orPassword.setEchoChar('●');
        orText = new JLabel();
        orText.setForeground(Color.RED);
        newPassword = new JPasswordField(20);
        newPassword.setEchoChar('●');
        newText = new JLabel();
        newText.setForeground(Color.RED);
        rePassword = new JPasswordField(20);
        rePassword.setEchoChar('●');
        reText = new JLabel();
        reText.setForeground(Color.red);
        JLabel orPasswordL = new JLabel("原密码:");
        JLabel newPasswordL = new JLabel("新密码:");
        JLabel rePasswordL = new JLabel("确认新密码:");
        orLook = new JButton("查看");
        newLook = new JButton("查看");
        reLook = new JButton("查看");
        submit = new JButton("确认");
        cancel = new JButton("取消");

        JPanel main = new JPanel(new GridLayout(8, 2));
        JLabel kong = new JLabel();         //空标签
        //旧密码
        JPanel orPasswordP = new JPanel(new GridLayout(1, 3));
        orPasswordP.add(orPasswordL);
        orPasswordP.add(orPassword);
        orPasswordP.add(orLook);
        main.add(orPasswordP);

        //旧密码错误提示
        JPanel o = new JPanel(new GridLayout(1, 3));
        o.add(kong);
        o.add(orText);
        main.add(o);

        //新密码
        JPanel newPasswordP = new JPanel(new GridLayout(1, 3));
        newPasswordP.add(newPasswordL);
        newPasswordP.add(newPassword);
        newPasswordP.add(newLook);
        main.add(newPasswordP);

        //新密码错误提示
        JPanel n = new JPanel();
        n.add(kong);
        n.add(newText);
        main.add(n);

        //确认新密码
        JPanel rePasswordP = new JPanel(new GridLayout(1, 2));
        rePasswordP.add(rePasswordL);
        rePasswordP.add(rePassword);
        rePasswordP.add(reLook);
        main.add(rePasswordP);

        //确认新密码提示
        JPanel r = new JPanel();
        r.add(kong);
        r.add(reText);
        main.add(r);

        //确认按钮
        JPanel buttonP = new JPanel(new GridLayout(1, 2));
        buttonP.add(submit);
        buttonP.add(cancel);
        main.add(buttonP);

        //添加布局
        add(main);

        //显示所有组件
        setVisible(true);

        //注册事件
        submit.addActionListener(this);
        cancel.addActionListener(this);
        orLook.addActionListener(this);
        newLook.addActionListener(this);
        reLook.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == submit) {
            //提交表单
            String or = orPassword.getText();
            String nw = newPassword.getText();
            String re = rePassword.getText();
            //提交表单
            submit(or, nw, re);
        }else if (source == cancel) {
            //返回用户中心
            UserRouter.getRouter().getUserMainJFrame().getRendering().update(new MessageMain());
            logger.info(USER,"返回用户中心");
        }else if (source == orLook) {
            //查看密码
            if (!orLookBool) {
                orPassword.setEchoChar('\0');   //显示密码
                orLookBool = !orLookBool;
                orLook.setText("隐藏");
            }else {
                orPassword.setEchoChar('●');   //隐藏密码
                orLookBool = !orLookBool;
                orLook.setText("查看");
            }
        } else if (source == newLook) {
            if (!newLookBool) {
                newPassword.setEchoChar('\0');   //显示密码
                newLookBool = !newLookBool;
                newLook.setText("隐藏");
            }else {
                newPassword.setEchoChar('●');   //隐藏密码
                newLookBool = !newLookBool;
                newLook.setText("查看");
            }
        } else if (source == reLook) {
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
    }


    /**
     * 确认输入框为非空
     */
    public void submit(String or, String nw, String re) {
        //清楚提示
        orText.setText("");
        newText.setText("");
        reText.setText("");
        //输入框非空
        if (isNull(or, nw, re)) {
            //密码不重复
            if (isPassword(or, nw, re)) {
                //密码是否符合强度
                if (isPasswordStrength(nw)) {
                    if (UpData_User.upDataPassword(nw)) {            //更新成功
                        JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);//退出窗口
                    } else {
                        reText.setText("程序遇到一点小问题，请重新确认，谢谢亲！");//数据库返回错误
                    }
                }
            }
        }
    }

    public boolean isNull(String or, String nw, String re) {
        boolean fals = true;       //默认返回true
        if (or.isEmpty()) {
            fals = false;
            orText.setText("请输入原密码！");
        }
        if (nw.isEmpty()) {
            fals = false;
            newText.setText("请输入新密码！");
        }
        if (re.isEmpty()) {
            fals = false;
            reText.setText("请确认新密码！");
        }
        return fals;
    }

    /**
     * 确认修改密码逻辑正确
     * 1、原密码与原本相同
     * 2、新密码与原密码不相同
     * 3、确认密码与新密码相同
     */
    public boolean isPassword(String or, String nw, String re) {
        boolean fals = true;       //默认返回true
        if (!or.equals(User.getUser().getPassword())) {
            fals = false;
            orText.setText("原密码错误！");
        } else if (nw.equals(User.getUser().getUser().getPassword())) {
            fals = false;
            newText.setText("新密码不能与原密码相同！");
        } else if (!re.equals(nw)) {
            reText.setText("确认密码与新密码不相同！");
            fals = false;
        }
        return fals;
    }

    /**
     * 是否符合要求的密码强度
     */
    public boolean isPasswordStrength(String nw) {
        boolean fals = nw.matches(PWD_B);
        if (!fals) {
            newText.setText(PWD_B_TEXT);
        }
        return fals;
    }

    /**
     * 查看密码
     * */
}