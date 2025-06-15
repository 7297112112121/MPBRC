package View.user;

import Util.factoryPanel.FactoryPanel;
import View.FatherFrame;
import View.FatherJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends FatherJPanel {
    private static final Logger logger = LogManager.getLogger(HomePanel.class);
    private FatherFrame frame ;
    public HomePanel(FatherFrame frame) {
        super();
        logger.info("首页加载中");
        this.frame = frame;
        setLayout(new GridLayout(2,1));
        FactoryPanel factoryPanel = new FactoryPanel();
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "我的;我的", "订单", "客服", "1元宝", "福利"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "扫码充电"));

        setVisible(true);

        //进入用户中心界面事件
        JButton my = (JButton) factoryPanel.getJComponent("我的");
        my.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new MyPanel((UserFrame) frame));
            }
        });
        logger.info("首页加载完成");
    }
}
