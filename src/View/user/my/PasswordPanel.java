package View.user.my;

import Serve.auth.UserCommonSet;
import Util.factoryPanel.FactoryPanel;
import View.FatherFrame;
import View.FatherJPanel;
import View.user.UserFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordPanel extends FatherJPanel {
    private static final Logger log = LogManager.getLogger(PasswordPanel.class);
    private FatherFrame frame;
    private boolean one = false;
    private boolean two = false;
    private boolean three = false;
    public PasswordPanel(FatherFrame frame) {
         super();
         this.frame = frame;
         setLayout(new GridLayout(7,1));
        FactoryPanel factoryPanel = new FactoryPanel();
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JPASSWORDFIELD_JBUTTON,"原密码", "20;password", "显示;00"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE,";0"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JPASSWORDFIELD_JBUTTON,"新密码", "20;password1", "显示;11"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE,";1"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JPASSWORDFIELD_JBUTTON,"确认密码", "20;password2", "显示;22"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE,";2"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "确认;确认", "取消;取消"));

        //设置信息
        JPasswordField OrPassword = (JPasswordField) factoryPanel.getJComponent("password");
        OrPassword.setEchoChar('●');
        JLabel OrPasswordRemind = (JLabel) factoryPanel.getJComponent("0");
        JPasswordField NewPassword = (JPasswordField) factoryPanel.getJComponent("password1");
        NewPassword.setEchoChar('●');
        JLabel NewPasswordRemind = (JLabel) factoryPanel.getJComponent("1");
        JPasswordField ConfirmPassword = (JPasswordField) factoryPanel.getJComponent("password2");
        ConfirmPassword.setEchoChar('●');
        JLabel ConfirmPasswordRemind = (JLabel) factoryPanel.getJComponent("2");

        //显示密码事件
        JButton show = (JButton) factoryPanel.getJComponent("00");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                one = !one;
                if (one) {
                    show.setText("隐藏");
                    OrPassword.setEchoChar('\0');
                } else {
                    show.setText("显示");
                    OrPassword.setEchoChar('●');
                }

            }
        });
        JButton show1 = (JButton) factoryPanel.getJComponent("11");
        show1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                two = !two;
                if (two) {
                    show1.setText("隐藏");
                    NewPassword.setEchoChar('\0');
                }else {
                    show1.setText("显示");
                    NewPassword.setEchoChar('●');
                }
            }
        });
        JButton show2 = (JButton) factoryPanel.getJComponent("22");
        show2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                three = !three;
                if (three) {
                    show2.setText("移除");
                    ConfirmPassword.setEchoChar('\0');
                }else {
                    show2.setText("显示");
                    ConfirmPassword.setEchoChar('●');
                }
            }
        });


        //确认修改密码事件
        JButton confirm = (JButton) factoryPanel.getJComponent("确认");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserCommonSet commonSet = new UserCommonSet();
                boolean fa =commonSet.setPassword(
                        OrPassword, OrPasswordRemind,
                        NewPassword, NewPasswordRemind,
                        ConfirmPassword, ConfirmPasswordRemind,
                        (UserFrame) frame
                );
                if (fa) {
                    frame.update(new MyMessagePanel(frame));
                    log.info("密码修改成功");
                }else log.info("密码修改失败");
            }
        });
        //取消事件
        JButton cancel = (JButton) factoryPanel.getJComponent("取消");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new MyMessagePanel(frame));
            }
        });
    }
}
