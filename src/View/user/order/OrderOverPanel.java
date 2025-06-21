package View.user.order;

import MyObject.Order;
import Serve.OrderSever;
import Serve.rentPackage.LoadAllPlanRent;
import Serve.rentPackage.RentPackage;
import View.FatherJPanel;
import View.user.HomePanel;
import View.user.UserFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.time.LocalDateTime;

public class OrderOverPanel extends FatherJPanel {
        private UserFrame frame;
        private Order order;
        private OrderSever orderSever;
        private int hours;
        private int minutes;
        private double price;
        private RentPackage rentPackage;
    public OrderOverPanel(UserFrame frame, Order order) {
        this.frame = frame;
        this.order =order;
        orderSever = new OrderSever();
        //获得小时
        hours = orderSever.returnHours(order.getStartTime().toLocalDateTime(), order.getEndTime().toLocalDateTime());
        //获得分钟
        minutes = orderSever.returnMinutes(order.getStartTime().toLocalDateTime(), order.getEndTime().toLocalDateTime());
        //获得价格
        price = order.getTotalCost();
        //获得套餐
        getPlan();

        //设置该面板布局
        setLayout(new BorderLayout());

        // 主面板，使用垂直布局
        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        // 标题
        JLabel titleLabel = new JLabel("订单完成");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        rootPanel.add(titleLabel, gbc);
        gbc.gridy++;

        // 副标题
        JLabel subTitleLabel = new JLabel("充电宝已归还，感谢使用");
        subTitleLabel.setForeground(new Color(0x999999));
        rootPanel.add(subTitleLabel, gbc);
        gbc.gridy++;

        // 使用时长和订单金额面板
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 0, 5));
        JLabel durationLabel = new JLabel(hours + "小时" + minutes + "分钟");

        durationLabel.setFont(new Font("宋体", Font.BOLD, 18));
        JLabel durationDescLabel = new JLabel("使用时长");
        durationDescLabel.setForeground(new Color(0x999999));
        durationDescLabel.setFont(new Font("宋体", Font.PLAIN, 16));

        JLabel amountLabel = new JLabel(price + "元");
        amountLabel.setFont(new Font("宋体", Font.BOLD, 18));
        JLabel amountDescLabel = new JLabel("订单金额");
        amountDescLabel.setForeground(new Color(0x999999));
        amountDescLabel.setFont(new Font("宋体", Font.PLAIN, 16));

        infoPanel.add(durationLabel);
        infoPanel.add(durationDescLabel);
        infoPanel.add(amountLabel);
        infoPanel.add(amountDescLabel);

        rootPanel.add(infoPanel, gbc);
        gbc.gridy++;

        // 价格明细标题
        JLabel priceDetailTitle = new JLabel("价格明细");
        priceDetailTitle.setFont(new Font("宋体", Font.BOLD, 16));
        rootPanel.add(priceDetailTitle, gbc);
        gbc.gridy++;

        // 订单金额
        JLabel orderAmountLabel = new JLabel("订单金额：" + price +"元");
        rootPanel.add(orderAmountLabel, gbc);
        gbc.gridy++;

        // 计费规则
        JTextArea ruleTextArea = new JTextArea();
        for (String s : rentPackage.getIntroduceText()) {
            ruleTextArea.append(s + '\n');
        }
        ruleTextArea.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        ruleTextArea.setForeground(new Color(0x666666));
        ruleTextArea.setFont(new Font("宋体", Font.PLAIN, 16));
        ruleTextArea.setEditable(false); // 设置为不可编辑
        ruleTextArea.setLineWrap(true);  // 启用自动换行
        ruleTextArea.setWrapStyleWord(true); // 按单词换行
        ruleTextArea.setOpaque(false); // 设为透明背景保持一致外观
        ruleTextArea.setPreferredSize(new Dimension(360, 60));
        rootPanel.add(ruleTextArea, gbc);
        gbc.gridy++;

        // 租借信息标题
        JLabel rentInfoTitle = new JLabel("租借信息");
        rentInfoTitle.setFont(new Font("宋体", Font.BOLD, 16));
        rootPanel.add(rentInfoTitle, gbc);
        gbc.gridy++;

        // 租借信息内容
        JLabel rentTimeLabel = new JLabel("租借时间：" + order.getStartTime());
        rootPanel.add(rentTimeLabel, gbc);
        gbc.gridy++;

        JLabel rentWayLabel = new JLabel("租借方式：押金");
        rootPanel.add(rentWayLabel, gbc);
        gbc.gridy++;

        JLabel rentAddrLabel = new JLabel("租借地点：广州大学华软软件学院");
        rootPanel.add(rentAddrLabel, gbc);
        gbc.gridy++;

        JLabel returnTimeLabel = new JLabel("归还时间：" + order.getEndTime());
        rootPanel.add(returnTimeLabel, gbc);
        gbc.gridy++;

        JLabel returnAddrLabel = new JLabel("归还地点：广州软件学院");
        rootPanel.add(returnAddrLabel, gbc);
        gbc.gridy++;

        JLabel snLabel = new JLabel("充电宝ID：" + order.getPowerBankId()) ;
        rootPanel.add(snLabel, gbc);
        gbc.gridy++;

        JLabel orderNoLabel = new JLabel("订单编号:" + order.getId());
        rootPanel.add(orderNoLabel, gbc);
        gbc.gridy++;

        // 已支付信息
        JLabel paidLabel = new JLabel("已支付 "+ order.getTotalCost() + "￥");
        paidLabel.setFont(new Font("宋体", Font.BOLD, 16));
        paidLabel.setForeground(new Color(0xff5722));
        rootPanel.add(paidLabel, gbc);
        gbc.gridy++;

        //返回按钮
        JButton returnButton = new JButton("返回");
        returnButton.setPreferredSize(new Dimension(getWidth(), 60));
        returnButton.setFont(new Font("宋体", Font.BOLD, 16));
        //返回首页事件
        returnButton.addActionListener(e -> {
            frame.update(new HomePanel(frame));
        });

        add(rootPanel, BorderLayout.CENTER);
        add(returnButton, BorderLayout.SOUTH);
        //显示组件
        setVisible(true);

    }

    private void getPlan() {
        rentPackage = LoadAllPlanRent.getPackageName(order.getPlan());
    }
}