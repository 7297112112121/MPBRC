package admin.View.panel.powerBank;

import global.view_tool.MyJPanel;
import global.view_tool.RenderingPanel;

import javax.swing.*;
import java.awt.*;

public class PowerBankMessagePanel extends MyJPanel {
    private final RenderingPanel rp;        //渲染面板
    private final JPanel buttonJPanel;      //面板
    private final JButton allPowerBankMessage;//所有充电宝信息
    public PowerBankMessagePanel() {
        setLayout(new BorderLayout());
         ///按钮 面板
         buttonJPanel = new JPanel();
         add(buttonJPanel, BorderLayout.NORTH);

         //充电宝所有信息
         allPowerBankMessage = new JButton("所有充电宝信息");
         allPowerBankMessage.setHorizontalTextPosition(JButton.CENTER);
         buttonJPanel.add(allPowerBankMessage);

        //渲染面板
        rp = new RenderingPanel();
        add(rp, BorderLayout.CENTER);

        //显示所有组件
        setVisible(true);
     }

}

