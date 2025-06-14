package View.user;

import Util.factoryPanel.FactoryPanel;
import View.MyFrame;
import View.MyJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMessagePanel extends MyJPanel {
    private static final Logger logger = LogManager.getLogger(MyMessagePanel.class);

    private UserFrame frame ;
    public  MyMessagePanel(MyFrame frame) {
        logger.info("我的信息面板加载中");
        this.frame = (UserFrame) frame;
        setLayout(new GridLayout(5,1));
        FactoryPanel factoryPanel = new FactoryPanel();

        //微调返回首页部分
        JPanel panel = new JPanel(new GridLayout(1,3));
        panel.add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "返回首页;index"));
        panel.add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, ";1"));
        panel.add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE,";2"));
        add(panel);
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JTEXTFIELD_JBUTTON, "昵称：", "20;name", "修改昵称;修改昵称"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JPASSWORDFIELD_JBUTTON, "密码：", "20;password", "修改密码;修改密码"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JTEXTFIELD_JBUTTON, "手机号码：", "20;phone", "重新绑定手机号码;修改手机号"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "退出登陆;goLogin"));
        setVisible(true);

        //设置信息
        //名字
        JTextField nameText = (JTextField) factoryPanel.getJComponent("name");
        nameText.setEditable(false);
        nameText.setText(((UserFrame) frame).getUser().getName());
        //密码
        JPasswordField passwordText = (JPasswordField) factoryPanel.getJComponent("password");
        passwordText.setEditable(false);
        passwordText.setEchoChar('●');
        passwordText.setText(((UserFrame) frame).getUser().getPassword());
        //电话号码
        JTextField phoneText = (JTextField) factoryPanel.getJComponent("phone");
        phoneText.setEditable(false);
        phoneText.setText(((UserFrame) frame).getUser().getPhone());

        //返回首页事件
        JButton returnHome = (JButton) factoryPanel.getJComponent("index");
        returnHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new HomePanel(frame));
            }
        });

        //退出登陆事件
        JButton goLogin = (JButton)factoryPanel.getJComponent("goLogin");
        goLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new LoginPanel(frame));
            }
        });

        //修改名称事件
        JButton changeName = (JButton) factoryPanel.getJComponent("修改昵称");
        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new NamePanel(frame));
            }
        });

        //修改密码事件
        JButton changePassword = (JButton) factoryPanel.getJComponent("修改密码");
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new PasswordPanel(frame));
            }
        });

        //修改手机号事件
        JButton changePhone = (JButton) factoryPanel.getJComponent("修改手机号");
        changePhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new PhoneJPanel(frame));
            }
        });

        logger.info("我的信息面板加载完成");
    }


}
