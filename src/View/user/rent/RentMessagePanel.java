package View.user.rent;

import Util.factoryPanel.FactoryPanel;
import View.FatherJPanel;
import View.user.UserFrame;
import View.user.HomePanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentMessagePanel extends FatherJPanel {
    private Logger logger = LogManager.getLogger(RentMessagePanel.class);
    private UserFrame frame;
    private int scheme = 1;  //用户选择的套餐,默认选套餐一
    public RentMessagePanel(UserFrame frame) {
        logger.info("租借充电宝信息界面加载中");
        this.frame = frame;
        setLayout(new GridLayout(5,1));
        FactoryPanel factoryPanel = new FactoryPanel();

        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS,"返回;return"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE,"广州软件学院"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "3.0元/60分钟" + "\n" + "不足60分钟按60分钟计费，每24小时封顶30.0元，总封顶99.0元;套餐1"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, "三线设计，适配所有手机型号，安卓，苹果皆适用"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "押金租凭;rent"));

        //返回事件
        JButton returnButton = (JButton) factoryPanel.getJComponent("return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new HomePanel(frame));
            }
        });

        //套餐一事件
        JButton scheme1 = (JButton) factoryPanel.getJComponent("套餐1");
        scheme1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheme = 1;
            }
        });

        //租借事件
        JButton rent = (JButton) factoryPanel.getJComponent("rent");
        logger.info("租借充电宝信息界面加载完成");
        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //通知窗口用户选择的套餐
                frame.setScheme(scheme);
            }
        });
    }
}
