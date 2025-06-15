package View.user.order;

import Util.factory.FactoryPanel;
import View.FatherJPanel;
import View.user.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentDingPanel extends FatherJPanel {
    private UserFrame frame ;
    public RentDingPanel(UserFrame frame) {
        this.frame = frame;
        setLayout(new GridLayout(4,1));
        FactoryPanel factoryPanel = new FactoryPanel();
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, "", "", ""));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "售后服务", "归还提醒", "福利中心"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "不还了，留下充电宝"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "返回;return", "归还"));

        //返回事件
        JButton returnButton = (JButton) factoryPanel.getJComponent("return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new OrderPanel(frame));
            }
        });

        //

    }
}
