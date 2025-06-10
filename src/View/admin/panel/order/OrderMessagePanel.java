package View.admin.panel.order;

import Util.View.MyJPanel;
import Util.View.MyRenderingPanel;

import javax.swing.*;
import java.awt.*;

public class OrderMessagePanel extends MyJPanel {
    private final MyRenderingPanel rp;            //渲染面板
    private final JPanel buttonPanel;       //按钮面板
    private final JButton allOrderButton;       //所有订单

    public OrderMessagePanel() {
        setLayout(new BorderLayout());

        //按钮面板
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.NORTH);

        //所有订单
        allOrderButton = new JButton("所有订单信息");
        buttonPanel.add(allOrderButton);

        //渲染面板
        rp = new MyRenderingPanel();

        //显示所有组件
        setVisible(true);
    }
}
