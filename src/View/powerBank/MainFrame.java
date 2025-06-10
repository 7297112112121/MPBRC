package View.powerBank;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("移动电源管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("移动电源管理", new PowerBankPanel());
        tabbedPane.addTab("订单管理", new OrderPanel());

        add(tabbedPane);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
