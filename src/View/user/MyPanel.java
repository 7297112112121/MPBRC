package View.user;


import Util.factoryPanel.FactoryPanel;
import View.FatherJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends FatherJPanel {
    private Logger logger = LogManager.getLogger(MyPanel.class);
    private UserFrame frame;
    public MyPanel(UserFrame frame) {
        logger.info("“我的”界面加载中");
        this.frame = frame;
        setLayout(new GridLayout(4,1));
        FactoryPanel factoryPanel = new FactoryPanel();
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "我的信息;message"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "账号余额;money"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "返回;return"));

        //信息事件
        JButton message = (JButton) factoryPanel.getJComponent("message");
        message.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new MyMessagePanel(frame));
            }
        });
        //账户余额事件
        JButton money = (JButton) factoryPanel.getJComponent("money");
        money.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new CheckMoneyPanel(frame));
            }
        });
        //返回事件
        JButton ret = (JButton) factoryPanel.getJComponent("return");
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new HomePanel(frame));
            }
        });
        logger.info("“我的”界面加载完成");
    }
}
