package View.admin;

import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Serve.observer.ObserverCabinet;
import View.FatherJPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class PowerBankCabinetMessageShowPanel extends FatherJPanel {
    private AdminJFrame frame;
    private JPanel contentPanel; // 内容面板
    private JLabel[] portLabels; // 充电宝柜电口显示
    private int[] portNumberID;  // 拥有充电宝的端口

    public PowerBankCabinetMessageShowPanel(AdminJFrame frame) {
        this.frame = frame;

        // 设置主面板布局为BorderLayout
        setLayout(new BorderLayout());

        // 创建内容面板，使用自定义弹性布局
        contentPanel = new JPanel(new FlexibleBoxLayout(BoxLayout.X_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 创建滚动面板，只显示水平滚动条
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // 将滚动面板添加到主面板
        add(scrollPane, BorderLayout.CENTER);

        //添加组件
        addIMG();
    }

    //生成一个充电柜图像
    private void createIMG(PowerBankCabinet cabinet) {
        JPanel cabinetPanel = new JPanel();
        JLabel titleJLable = new JLabel(cabinet.getName());
        titleJLable.setFont(new Font("宋体", Font.BOLD, 20));
        titleJLable.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(cabinetPanel, BorderLayout.CENTER);

        int capacity = cabinet.getCapacity();
        portNumberID = cabinet.getPortNumberID();
        portLabels = new JLabel[capacity];
        Border border = new LineBorder(Color.BLACK, 2);
        int rows = (capacity + 1) / 2;
        cabinetPanel.setLayout(new GridLayout(rows, 2, 5, 5));
        cabinetPanel.setPreferredSize(new Dimension(120, 300)); // 【修改点2】设置充电柜面板的整体尺寸，控制高度并预留宽度空间

        for (int i = 0; i < capacity; i++) {
            portLabels[i] = new JLabel("端口" + (i + 1));
            portLabels[i].setBorder(border);
            portLabels[i].setForeground(Color.darkGray);
            portLabels[i].setHorizontalAlignment(JLabel.CENTER);
            portLabels[i].setOpaque(true);
            portLabels[i].setBackground(Color.LIGHT_GRAY);
            cabinetPanel.add(portLabels[i]);
        }
        //显示存在有的充电宝
        for (int i = 0; i < capacity; i++) {
            if (portNumberID[i] != -1) {
                portLabels[i].setForeground(Color.ORANGE);
                portLabels[i].setBackground(Color.BLACK);
            }
        }
    }

    //生成所有充电柜图像
    private void addIMG() {
        ObserverCabinet observerCabinet = new ObserverCabinet();
        List<PowerBankCabinet> powerBanks = observerCabinet.getCabinets();
        // 遍历充电宝柜列表，为每个充电宝柜创建一个面板
        for (PowerBankCabinet cabinet : powerBanks) {
            createIMG(cabinet);
        }
    }

    // 自定义弹性布局管理器
    private static class FlexibleBoxLayout implements LayoutManager {
        private final int axis;

        public FlexibleBoxLayout(int axis) {
            this.axis = axis;
        }

        @Override
        public void addLayoutComponent(String name, Component comp) {
            // 不需要实现
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            // 不需要实现
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int maxWidth = 0;
                int totalWidth = 0;
                int maxHeight = 0;

                for (Component comp : parent.getComponents()) {
                    if (comp.isVisible()) {
                        Dimension size = comp.getPreferredSize();
                        maxWidth = Math.max(maxWidth, size.width);
                        totalWidth += size.width + 80; // 【修改点3】给每个组件宽度累加时，额外加上 80 像素的间隔
                        maxHeight = Math.max(maxHeight, size.height);
                    }
                }

                if (axis == BoxLayout.X_AXIS) {
                    return new Dimension(
                            insets.left + totalWidth - 80 + insets.right, // 【修改点4】减去最后一个多余的间隔（因为每个组件都加了 80，最后一个不需要额外间隔）
                            insets.top + maxHeight + insets.bottom
                    );
                } else {
                    return new Dimension(
                            insets.left + maxWidth + insets.right,
                            insets.top + totalWidth + insets.bottom
                    );
                }
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return preferredLayoutSize(parent);
        }

        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Insets insets = parent.getInsets();
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - insets.left - insets.right;
                int height = parent.getHeight() - insets.top - insets.bottom;

                for (Component comp : parent.getComponents()) {
                    if (comp.isVisible()) {
                        Dimension size = comp.getPreferredSize();
                        if (axis == BoxLayout.X_AXIS) {
                            comp.setBounds(x, y, size.width, height);
                            x += size.width + 80; // 【修改点5】组件布局后，x 坐标累加组件宽度 + 80 像素间隔
                        } else {
                            comp.setBounds(x, y, width, size.height);
                            y += size.height;
                        }
                    }
                }
            }
        }
    }
}