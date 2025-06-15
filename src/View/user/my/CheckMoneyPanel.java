package View.user.my;

import MyObject.User;
import Serve.charge.ChargeLocal;
import Util.factory.FactoryPanel;
import View.FatherJPanel;
import View.user.UserFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CheckMoneyPanel extends FatherJPanel {
    private Logger logger = LogManager.getLogger(CheckMoneyPanel.class);
    private UserFrame frame;
    public CheckMoneyPanel(UserFrame frame) {
        super();
        logger.info("查看余额界面加载中");
        this.frame = frame;
        User user = frame.getUser();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        FactoryPanel factoryPanel = new FactoryPanel();


        JPanel first = factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, ";￥");
        first.setPreferredSize(new Dimension(frame.getWidth(), 180));
        add(first);
        JPanel second = factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "充值余额;recharge");
        second.setPreferredSize(new Dimension(frame.getWidth(),50));
        add(second);
        JPanel thr = factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, "账号金额变动明细", ";moneyNum");
        thr.setPreferredSize(new Dimension(frame.getWidth(),50));
        add(thr);
        JPanel four = factoryPanel.createPanel(FactoryPanel.MyJPanelType.JTEXTAREA, "20x40;moneyText");
        four.setPreferredSize(new Dimension(frame.getWidth(),800-180-50-50-50));
        add(four);
        JPanel five = factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "返回;return");
        add(five);

        //设置文本信息

        JTextArea moneyText = (JTextArea) factoryPanel.getJComponent("moneyText");
        // 设置字体大小为16像素，加粗
        Font font = new Font("宋体", Font.BOLD, 16);
        moneyText.setFont(font);
        moneyText.setEditable(false);
        //添加记录
        ChargeLocal chargeLocal = new ChargeLocal();
        List<String> list = chargeLocal.getAccountLogin(user.getNameID());
        if (list.isEmpty()) {
            moneyText.setText("空空如也");
        }else {
            for (String s : list) {
                moneyText.append(s + "\n");
            }
        }


        //设置信息
        JLabel money = (JLabel) factoryPanel.getJComponent("￥");
        money.setText(String.valueOf(user.getAccount()) + "￥");
        Font fontMoney = new Font("宋体", Font.BOLD, 30);
        money.setFont(fontMoney);
        JLabel orderNumber = (JLabel) factoryPanel.getJComponent("moneyNum") ;
        orderNumber.setText("共" + list.size() + "条");

        //充值事件
        JButton reCharge = (JButton) factoryPanel.getJComponent("recharge");
        reCharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new RechargePanel(frame));
            }
        });

        //返回事件
        JButton ret = (JButton) factoryPanel.getJComponent("return") ;
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.update(new MyPanel(frame));
            }
        });
        //显示
        setVisible(true);
        logger.info("查看余额界面加载完成");
    }
}
