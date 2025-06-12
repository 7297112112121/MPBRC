package View.user;

import Serve.auth.CommonRegister;
import View.MyFrame;
import View.MyJPanel;
import View.factoryPanel.FactoryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static View.factoryPanel.FactoryPanel.MyJPanelType;
import static View.factoryPanel.FactoryPanel.MyJPanelType.*;

public class RegisterJPane extends MyJPanel {
    private MyFrame frame;//储存索引
    public RegisterJPane(MyFrame frame) {
        this.frame = frame;//获得索引
        setLayout(new GridLayout(12,1));
        FactoryPanel factoryPanel = new FactoryPanel();

        add(factoryPanel.createPanel(JLABLE_JTEXTFIELD_JLABLE,"昵称：", "20", ""));
        add(factoryPanel.createPanel(JLABLE, "<html>&nbsp;</html>"));
        add(factoryPanel.createPanel(JLABLE_JPASSWORDFIELD_JBUTTON,"新密码：", "20", "显示"));
        add(factoryPanel.createPanel(JLABLE, "<html>&nbsp;&nbsp;</html>"));
        add(factoryPanel.createPanel(JLABLE_JPASSWORDFIELD_JBUTTON, "确认密码：", "20", "显示"));
        add(factoryPanel.createPanel(JLABLE, "<html>&nbsp;&nbsp;&nbsp;</html>"));
        add(factoryPanel.createPanel(JLABLE_JPASSWORDFIELD_JBUTTON, "手机号：", "20","发送验证码"));
        add(factoryPanel.createPanel(JLABLE, "<html>&nbsp;&nbsp;&nbsp;</html>"));
        add(factoryPanel.createPanel(JLABLE_JTEXTFIELD_JLABLE,"验证码", "10", ""));
        add(factoryPanel.createPanel(JLABLE, "<html>&nbsp;&nbsp;&nbsp;&nbsp;</html>"));
        add(factoryPanel.createPanel(MyJPanelType.BUTTONS, "立即注册", "返回登录"));

        JButton sendCatcha = (JButton) factoryPanel.getPanel(JLABLE_JPASSWORDFIELD_JBUTTON, "发送验证码");

        //按钮面板

//        //事件
        JButton register = (JButton)factoryPanel.getPanel(BUTTONS, "立即注册");
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommonRegister register1 = new CommonRegister();
//                register1.register();
            }
        });
        JButton login = (JButton)factoryPanel.getPanel(BUTTONS, "返回登录");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new LoginPanel(frame));
            }
        });
    }

}
