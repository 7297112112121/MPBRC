package View.user.rent;

import MyObject.PowerBankCabinet;
import Serve.rent.ContextRentPackage;
import Serve.rent.OneOfHoure;
import Serve.rent.ThreeOfHoure;
import Util.factory.FactoryPanel;
import View.FatherJPanel;
import View.user.UserFrame;
import View.user.HomePanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class RentMessagePanel extends FatherJPanel {
    private Logger logger = LogManager.getLogger(RentMessagePanel.class);
    private UserFrame frame;
    private JButton currentSelectedButton = null;// 声明一个变量记录当前选中的按钮
    private ContextRentPackage contextRentPackage = new ContextRentPackage();//选择套餐策略

    public RentMessagePanel(UserFrame frame) {
        logger.info("租借充电宝信息界面加载中");
        this.frame = frame;
        setLayout(new GridLayout(6,1));
        FactoryPanel factoryPanel = new FactoryPanel();

        // 创建下拉菜单
        List<PowerBankCabinet> powerBankCabinetsOnMap = frame.getPowerBankCabinetsOnMap();
        //设置默认选项
        for (PowerBankCabinet powerBankCabinet : powerBankCabinetsOnMap) {
            if (powerBankCabinet == frame.getPowerBankCabinetDefault())
                powerBankCabinetsOnMap.remove(powerBankCabinet);
                powerBankCabinetsOnMap.add(0, powerBankCabinet);
                break;
        }
        //设置菜单
        String[] nams = new String[powerBankCabinetsOnMap.size()];
        for (int i = 0; i < powerBankCabinetsOnMap.size(); i++) {
            nams[i] = powerBankCabinetsOnMap.get(i).getName();
        }
        JComboBox<String> institutionComboBox = new JComboBox<>(nams);
        institutionComboBox.setFont(new Font("宋体", Font.BOLD, 18));
        institutionComboBox.setPreferredSize(new Dimension(getWidth(), 60));
        add(institutionComboBox); // 直接添加下拉菜单组件
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JTEXTAREA_BUTTON,"4x20;套餐介绍1", "套餐1;套餐1"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JTEXTAREA_BUTTON,"4x20;套餐介绍2", "套餐2;套餐2"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, "提醒：三线设计，适配所有手机型号，安卓，苹果皆适用"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "押金租凭;rent"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS,"返回;return"));

        /**
         * 下拉框监听
         * */
        institutionComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //获取用户选择的充电柜，若用户没有选择，默认使用系统预选的充电柜
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    for (PowerBankCabinet po :powerBankCabinetsOnMap) {
                        if (po.getName().equals(selectedItem)) {
                            frame.setPowerBankCabinetDefault(po);
                        }
                    }
                    // 处理选择逻辑...
                }
            }
        });

/**
 * 套餐1信息（默认套餐）
 **/
        //设置默认套餐
        contextRentPackage.setRentPackage(new ThreeOfHoure());  ///可修改
        frame.setPrice(contextRentPackage.getPrice());
        //获取组件
        JTextArea in = (JTextArea) factoryPanel.getJComponent("套餐介绍1");
        in.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // 设置1px黑色边框
        JButton scheme1 = (JButton) factoryPanel.getJComponent("套餐1");
        //文本设置字体
        Font inFont = new Font("宋体", Font.BOLD, 16);
        //设置默认选中颜色显示
        scheme1.setBackground(Color.YELLOW);
        currentSelectedButton = scheme1;
        in.setFont(inFont);
        //添加套餐1的信息
        for (String s : contextRentPackage.getPackageText()) {
            in.append(s + "\n");
        }

        scheme1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 先恢复之前选中按钮的颜色
                if (currentSelectedButton != null && currentSelectedButton != scheme1) {
                    currentSelectedButton.setBackground(null); // 恢复默认颜色
                }

                // 设置当前套餐和价格
                contextRentPackage.setRentPackage(new ThreeOfHoure());  ///可修改
                frame.setPrice(contextRentPackage.getPrice());

                // 设置当前按钮为黄色并更新选中记录
                scheme1.setBackground(Color.YELLOW);
                currentSelectedButton = scheme1;
            }
        });

/**
 * 套餐2信息
 **/
        contextRentPackage.setRentPackage(new OneOfHoure());   ///可修改
        JTextArea in2 = (JTextArea) factoryPanel.getJComponent("套餐介绍2");
        in2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // 设置1px黑色边框
        Font in2Font = new Font("宋体", Font.BOLD, 16);
        in2.setFont(in2Font);
//添加套餐2的信息（注意：原代码中误写为套餐1）
        for (String s : contextRentPackage.getPackageText()) {
            in2.append(s + "\n");
        }
        JButton scheme2 = (JButton) factoryPanel.getJComponent("套餐2");
        scheme2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 先恢复之前选中按钮的颜色
                if (currentSelectedButton != null && currentSelectedButton != scheme2) {
                    currentSelectedButton.setBackground(null); // 恢复默认颜色
                }

                // 设置当前套餐和价格
                contextRentPackage.setRentPackage(new OneOfHoure());  ///可修改
                frame.setPrice(contextRentPackage.getPrice());

                // 设置当前按钮为黄色并更新选中记录
                scheme2.setBackground(Color.YELLOW);
                currentSelectedButton = scheme2;
            }
        });

        //返回事件
        JButton returnButton = (JButton) factoryPanel.getJComponent("return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new HomePanel(frame));
            }
        });

        //租借事件
        JButton rent = (JButton) factoryPanel.getJComponent("rent");
        logger.info("租借充电宝信息界面加载完成");
        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //用户选了套餐，弹出充电宝
                frame.update(new PowerBankPopPanel(frame, frame.getPowerBankCabinetDefault()));
            }
        });
    }
}
