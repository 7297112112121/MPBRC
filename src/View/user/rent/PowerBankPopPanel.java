package View.user.rent;

import MyObject.PowerBankCabinet;
import Util.factory.FactoryPanel;
import View.FatherJPanel;
import View.user.UserFrame;
import View.user.order.RentDingPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PowerBankPopPanel extends FatherJPanel {
    private static Logger logger = LogManager.getLogger(PowerBankPopPanel.class);
    private UserFrame frame;
    private PowerBankCabinet cabinet;
    private JLabel[] portLabels;
    private JPanel progressPanel;
    private List<JLabel> progressLabels = new ArrayList<>();
    private int currentProgressIndex = 0;
    private final int PROGRESS_STEP_COUNT = 4; // 明确进度步骤总数

    public PowerBankPopPanel(UserFrame frame, PowerBankCabinet cabinet) {
        this.frame = frame;
        this.cabinet = cabinet;
        setLayout(new BorderLayout());
        initCabinetPanel();
        initProgressPanel();
        startProgressAnimation();
    }

    private void initCabinetPanel() {
        JPanel cabinetPanel = new JPanel();
        add(cabinetPanel, BorderLayout.CENTER);

        int capacity = cabinet.getCapacity();
        int[] portNumberID = cabinet.getPortNumberID();
        portLabels = new JLabel[capacity];
        Border border = new LineBorder(Color.BLACK, 2);
        int rows = (capacity + 1) / 2;
        cabinetPanel.setLayout(new GridLayout(rows, 2, 5, 5));

        for (int i = 0; i < capacity; i++) {
            portLabels[i] = new JLabel("端口" + (i + 1));
            portLabels[i].setBorder(border);
            portLabels[i].setForeground(Color.darkGray);
            portLabels[i].setHorizontalAlignment(JLabel.CENTER);
            portLabels[i].setOpaque(true);
            portLabels[i].setBackground(Color.LIGHT_GRAY);
            cabinetPanel.add(portLabels[i]);
        }

        for (int i = 0; i < capacity; i++) {
            if (portNumberID[i] != 0) {
                portLabels[i].setForeground(Color.white);
                portLabels[i].setBackground(Color.GREEN);
            }
        }
    }

    private void initProgressPanel() {
        FactoryPanel factoryPanel = new FactoryPanel();
        // 先获取 factoryPanel 创建的面板，再遍历筛选 JLabel
        JPanel createdPanel = (JPanel) factoryPanel.createPanel(
                FactoryPanel.MyJPanelType.JLABLE,
                "等待回复;1", "查找满电宝;2", "正在弹出;3", "弹出成功;4"
        );
        Component[] components = createdPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                progressLabels.add((JLabel) comp);
                ((JLabel) comp).setForeground(Color.GRAY);
            }
        }
        // 若不足 4 个，补充默认 JLabel（可选，根据需求调整）
        while (progressLabels.size() < PROGRESS_STEP_COUNT) {
            JLabel defaultLabel = new JLabel("默认步骤");
            defaultLabel.setForeground(Color.GRAY);
            progressLabels.add(defaultLabel);
            createdPanel.add(defaultLabel);
        }
        progressPanel = createdPanel;
        add(progressPanel, BorderLayout.SOUTH);
    }

    private void startProgressAnimation() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            animateProgress(executor);
        }, 300, TimeUnit.MILLISECONDS);
    }

    private void animateProgress(ScheduledExecutorService executor) {
        try {
            if (currentProgressIndex < progressLabels.size()) {
                SwingUtilities.invokeLater(() -> {
                    progressLabels.get(currentProgressIndex).setForeground(Color.YELLOW);
                });
                if (currentProgressIndex < progressLabels.size() ) {
                    currentProgressIndex++;
                }
                executor.schedule(() -> {
                    animateProgress(executor);
                }, 500, TimeUnit.MILLISECONDS);
            } else {
                executor.shutdown();
                SwingUtilities.invokeLater(() -> {
                    try {
                        Thread.sleep(500);
                        frame.update(new RentDingPanel(frame));
                    } catch (InterruptedException ex) {
                        logger.error("跳转订单页面时被中断", ex);
                    }
                });
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error("进度动画执行出错", e);
        }
    }
}