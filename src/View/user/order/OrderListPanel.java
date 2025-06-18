package View.user.order;

import MyObject.Order;
import Serve.OrderSever;
import View.FatherJPanel;
import View.user.UserFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class OrderListPanel extends FatherJPanel {
    private Logger logger = LogManager.getLogger(OrderListPanel.class);
    private UserFrame frame;
    private List<Order> orders;
    private OrderSever orderSever = new OrderSever();

    public OrderListPanel(UserFrame frame) {
        this.frame = frame;
        // 获取用户的所有订单
        orders = orderSever.getAllOrders(frame.getUser().getNameID());

        // 设置当前面板的布局为垂直流式布局
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 检查订单列表是否为空
        if (orders == null || orders.isEmpty()) {
            add(new JLabel("暂无订单记录"));
        } else {
            // 遍历所有订单并添加订单卡片
            for (Order order : orders) {
                // 计算订单时长
                long hours = 0;
                long minutes = 0;
                if (order.getEndTime() != null) {
                    //若订单结束了
                     hours = orderSever.returnHours(order.getStartTime().toLocalDateTime(),
                            order.getEndTime().toLocalDateTime());
                     minutes = orderSever.returnMinutes(order.getStartTime().toLocalDateTime(),
                            order.getEndTime().toLocalDateTime());
                }else {
                    //若订单没有结束
                     hours = orderSever.returnHours(order.getStartTime().toLocalDateTime(),
                            LocalDateTime.now());
                     minutes = orderSever.returnMinutes(order.getStartTime().toLocalDateTime(),
                            LocalDateTime.now());
                }

                String durationStr = "";
                if (hours > 0) {
                    durationStr += hours + "小时";
                }
                durationStr += minutes + "分钟";

                // 添加订单卡片
                addOrderCard("租借充电宝",
                        order.getStatus(), // 假设Order类有getStatus方法
                        "租借时间：" + order.getStartTime(),
                        "租借地点：" + "广州软件学院",
                        durationStr + " " + order.getTotalCost() + "￥");
            }
        }
    }


    /**
     * 添加单个订单卡片到当前面板
     */
    private void addOrderCard(String orderTitle, String status,
                              String rentTime, String rentAddr, String durationAndPrice) {
        // 订单卡片面板，使用边框布局
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        cardPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120)); // 限制卡片最大高度

        // 标题和状态面板
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(orderTitle);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
        JLabel statusLabel = new JLabel(status);
        statusLabel.setForeground(Color.GRAY);
        titlePanel.add(titleLabel, BorderLayout.WEST);
        titlePanel.add(statusLabel, BorderLayout.EAST);

        // 内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JLabel timeLabel = new JLabel(rentTime);
        JLabel addrLabel = new JLabel(rentAddr);
        contentPanel.add(timeLabel);
        contentPanel.add(addrLabel);

        // 底部信息面板
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel durationPriceLabel = new JLabel(durationAndPrice);
        durationPriceLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
        // 模拟删除图标
        JLabel deleteIcon = new JLabel("删除"); // 实际使用时替换为图标
        deleteIcon.setForeground(Color.BLUE);
        deleteIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bottomPanel.add(durationPriceLabel, BorderLayout.WEST);
        bottomPanel.add(deleteIcon, BorderLayout.EAST);

        // 将各部分面板添加到订单卡片面板
        cardPanel.add(titlePanel, BorderLayout.NORTH);
        cardPanel.add(contentPanel, BorderLayout.CENTER);
        cardPanel.add(bottomPanel, BorderLayout.SOUTH);

        // 添加订单卡片到当前面板，并设置间距
        add(cardPanel);
        add(Box.createVerticalStrut(10));
    }
}