package global.tset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardLayoutDemo {
    private JFrame mainFrame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public CardLayoutDemo() {
        // 创建主窗口
        mainFrame = new JFrame("CardLayout 示例");
        mainFrame.setSize(500, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建卡片布局和面板
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 创建按钮面板
        JPanel buttonPanel = createButtonPanel();

        // 创建并添加各个视图面板
        createAndAddPanels();

        // 添加按钮面板和卡片面板到主窗口
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(buttonPanel, BorderLayout.NORTH);
        mainFrame.add(cardPanel, BorderLayout.CENTER);

        // 显示主窗口
        mainFrame.setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        // 创建切换到面板1的按钮
        JButton panel1Btn = new JButton("主页");
        panel1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "HOME");
            }
        });

        // 创建切换到面板2的按钮
        JButton panel2Btn = new JButton("商品");
        panel2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "PRODUCTS");
            }
        });

        // 创建切换到面板3的按钮
        JButton panel3Btn = new JButton("购物车");
        panel3Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CART");
            }
        });

        panel.add(panel1Btn);
        panel.add(panel2Btn);
        panel.add(panel3Btn);

        return panel;
    }

    private void createAndAddPanels() {
        // 创建主页面板
        JPanel homePanel = new JPanel();
        homePanel.setBackground(Color.LIGHT_GRAY);
        JLabel homeLabel = new JLabel("欢迎来到主页", JLabel.CENTER);
        homePanel.add(homeLabel);

        // 创建商品面板
        JPanel productsPanel = new JPanel();
        productsPanel.setBackground(Color.PINK);
        JLabel productsLabel = new JLabel("商品列表", JLabel.CENTER);
        productsPanel.add(productsLabel);

        // 创建购物车面板
        JPanel cartPanel = new JPanel();
        cartPanel.setBackground(Color.CYAN);
        JLabel cartLabel = new JLabel("购物车 (0 项商品)", JLabel.CENTER);
        cartPanel.add(cartLabel);

        // 添加面板到卡片布局，使用字符串标识每个面板
        cardPanel.add(homePanel, "HOME");
        cardPanel.add(productsPanel, "PRODUCTS");
        cardPanel.add(cartPanel, "CART");
    }

    public static void main(String[] args) {
        // 在事件调度线程上创建和显示GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CardLayoutDemo();
            }
        });
    }
}