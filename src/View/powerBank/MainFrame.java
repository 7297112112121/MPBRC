package View.powerBank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class MainFrame extends JFrame {
    private Logger logger = LogManager.getLogger(MainFrame.class);
    public MainFrame() {
        logger.info("加载电源测试界面");
        setTitle("移动电源管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("移动电源管理", new PowerBankPanel());
//        tabbedPane.addTab("订单管理", new OrderPanel());

        add(tabbedPane);
        setLocationRelativeTo(null);

        setVisible(true);
        logger.info("电源测试界面加载完成");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
