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

public class PhoneJPanel extends FatherJPanel {
    private static final Logger logger = LogManager.getLogger(PhoneJPanel.class);
    private UserFrame frame;
    public PhoneJPanel(FatherFrame frame) {
        logger.info("加载手机号码修改界面");
        this.frame = (UserFrame) frame;
        this.setLayout(null);
        setLayout(new GridLayout(6,1));
        FactoryPanel factoryPanel = new FactoryPanel();
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, "原手机号码：", ";phoneMessage", ""));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, ""));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JTEXTFIELD_JBUTTON, "新手机号码：", "20;待修改", "发送验证码;fa"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, ";提示框"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE_JTEXTFIELD_JLABLE,"验证码：", "20;验证码", ""));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "确认;sure", "取消;return"));

        //设置信息
        JLabel phoneMessage = (JLabel) factoryPanel.getJComponent("phoneMessage");
        phoneMessage.setText(this.frame.getUser().getPhone());

        //发送验证码事件
        JButton sendCa = (JButton) factoryPanel.getJComponent("fa");
        sendCa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserCommonSet commonSet = new UserCommonSet();
                commonSet.createCaptchaWithUI(
                        (JTextField) factoryPanel.getJComponent("待修改"),
                        (JButton) factoryPanel.getJComponent("fa"),
                        (JLabel) factoryPanel.getJComponent("提示框"),
                        frame
                );
            }
        });

        //确认事件
        JButton sure = (JButton) factoryPanel.getJComponent("sure");
        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserCommonSet commonSet = new UserCommonSet();
                boolean fa =commonSet.setPhone(
                        (JTextField)factoryPanel.getJComponent("待修改"),
                        (JLabel) factoryPanel.getJComponent("提示框"),
                        (JTextField) factoryPanel.getJComponent("验证码"),
                        (UserFrame) frame
                );
                if (fa){
                    frame.update(new MyMessagePanel(frame));
                    logger.info("手机号码修改成功");
                } else {
                    logger.info("手机号码修改失败");
                }
            }
        });
        //取消事件
        JButton retur = (JButton) factoryPanel.getJComponent("return");
        retur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new MyMessagePanel(frame));
            }
        });
        logger.info("加载手机号码修改界面完成");

    }
}
