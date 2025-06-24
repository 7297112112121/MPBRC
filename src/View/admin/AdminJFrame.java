package View.admin;

import Util.factory.FactoryPanel;
import View.FatherFrame;
import View.FatherJPanel;
import View.RenderingPanel;
import View.user.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class AdminJFrame extends FatherFrame {
    private RenderingPanel rp;
    private FactoryPanel factoryPanel;

    public AdminJFrame() {
        setTitle("移动电源管理");
        setLayout(new BorderLayout());
        setBounds(100, 100, 2000, 1100);
        JLabel title = new JLabel("移动电源管理系统");
        title.setFont(new Font("宋体", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setPreferredSize(new Dimension(getWidth(), 100));
        add(title, BorderLayout.NORTH);
        factoryPanel = new FactoryPanel();

        JPanel buttonPanel = new  JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, getHeight()));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        rp = new RenderingPanel(this);
        rp.setPreferredSize(new Dimension(1000, getHeight()));
        add(buttonPanel, BorderLayout.WEST);
        add(rp, BorderLayout.CENTER);

        Font font = new Font("宋体", Font.BOLD, 16);
        int height = 80;

        //充电宝信息管理按钮
        JButton powerBanksButton = new JButton("充电宝信息管理");
        buttonPanel.add(powerBanksButton);
        powerBanksButton.setPreferredSize(new Dimension(getWidth(),height));
        powerBanksButton.setFont(font);
        powerBanksButton.addActionListener(e -> {
            rp.update(new PowerBankMangerPanel(this));
        });
        setVisible(true);
    }


    @Override
    public void update(FatherJPanel panel) {
        rp.update(panel);
    }

    @Override
    public FatherJPanel getShowPanel() {
        return rp.getShowPanel();
    }
}