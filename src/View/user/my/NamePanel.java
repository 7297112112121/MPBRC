package View.user.my;

import Serve.auth.UserCommonSet;
import Util.factory.FactoryPanel;
import View.FatherFrame;
import View.FatherJPanel;
import View.user.UserFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NamePanel extends FatherJPanel {
    private static final Logger logger = LogManager.getLogger(NamePanel.class);
    private UserFrame frame;
    public NamePanel(FatherFrame frame) {
        logger.info("修改名字界面加载中");
        this.frame = (UserFrame) frame;
        FactoryPanel factoryPanel = new FactoryPanel();
        setLayout(new GridLayout(4,1));

        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, "昵称：", ";name", ""));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JTEXTFIELD_JLABLE, "新昵称", "20;名称输入框", ""));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, ";提示框"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "确认修改;sure", "取消;return"));

        //设置信息
        JLabel name1 = (JLabel) factoryPanel.getJComponent("name");
        name1.setText(this.frame.getUser().getName());

        //取消事件
        JButton retu = (JButton) factoryPanel.getJComponent("return");
        retu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new MyMessagePanel(frame));
            }
        });

        //确认事件
        JButton sure = (JButton) factoryPanel.getJComponent("sure");
        JTextField name = (JTextField) factoryPanel.getJComponent("名称输入框");
        JLabel rimid = (JLabel) factoryPanel.getJComponent("提示框");
        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean fa = new UserCommonSet().setName(name, rimid, frame);
                if (fa) {
                    frame.update(new MyMessagePanel(frame));
                    logger.info("修改名字成功");
                }else {
                    logger.error("修改名字失败");
                }

            }
        });
        logger.info("修改名字界面加载完成");
    }
}
