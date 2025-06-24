package View.user.rent;

import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Serve.OrderSever;
import Util.factory.FactoryPanel;
import View.FatherJPanel;
import View.user.UserFrame;
import View.user.order.OrderPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PowerBankPopPanel extends FatherJPanel {
    private static Logger logger = LogManager.getLogger(PowerBankPopPanel.class);
    private UserFrame frame;
    private PowerBankCabinet cabinet;                           //用户选择了的充电宝柜
    private List<JLabel> progressLabels = new ArrayList<>();    //存放进程标签
    private int currentProgressIndex = 0;                       //进行到的标签
    private OrderSever getPowerSever = new OrderSever();  //获得充电宝服务
    private PowerBank maxPowerRemainingPower;                   //获得最高电量的充电宝
    private int[] portNumberID;                                 //拥有充电宝的端口
    private JLabel[] portLabels;                                //充电宝柜电口显示

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
        portNumberID = cabinet.getPortNumberID();
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
        //显示存在有的充电宝
        for (int i = 0; i < capacity; i++) {
            if (portNumberID[i] != -1) {
                portLabels[i].setForeground(Color.ORANGE);
                portLabels[i].setBackground(Color.GREEN);
            }
        }
    }

    //进度条创建
    private void initProgressPanel() {
        FactoryPanel factoryPanel = new FactoryPanel();
        // 先获取 factoryPanel 创建的面板，再遍历筛选 JLabel
        JPanel createdPanel = (JPanel) factoryPanel.createPanel(
                FactoryPanel.MyJPanelType.JLABLE,
                "等待回复;1", "查找满电宝;2", "正在弹出;3", "弹出成功;4"
        );
        //设置样式
        Component[] components = createdPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JLabel) {
                progressLabels.add((JLabel) comp);
                comp.setForeground(Color.GRAY);
            }
        }
        //显示该面板
        add(createdPanel, BorderLayout.SOUTH);
    }

    //创建动画定时器
    private void startProgressAnimation() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            animateProgress(executor);
        }, 300, TimeUnit.MILLISECONDS);
    }

    //具体动画效果
    private void animateProgress(ScheduledExecutorService executor) {
        try {
            //动画进行
            //currentProgressIndex执行步骤下标
            if (currentProgressIndex < progressLabels.size()) {
                if (currentProgressIndex == 0) {
                    //等待回复
                    getPowerSever.havaPowerBankCabinet(cabinet);
                } else if (currentProgressIndex == 1) {
                    //查找最高电量的充电宝
                    maxPowerRemainingPower = getPowerSever.mainRemainingPower(cabinet);
                    //若没有符合要求的充电宝
                    if (maxPowerRemainingPower == null) {
                        //打断动画 并跳转页面
                        executor.shutdown();
                        frame.update(new RentMessagePanel(frame));
                        //弹出消息窗口
                        JOptionPane.showMessageDialog(frame, "没有符合要求的充电宝,请更换另一台主机", "提示", JOptionPane.INFORMATION_MESSAGE);
                        logger.info("没有找到符合要求的充电宝，返回充电宝柜台选择界面。");
                    }
                } else if (currentProgressIndex == 2) {
                    //弹出充电宝
                    int powerID = maxPowerRemainingPower.getPowerID();
                    for (int i: portNumberID) {
                        if (powerID == i) {
                            startFlashAnimation(portLabels[i]);
                        }
                    }
              }
                //使用SwingUtilities.invokeLater()方法在事件分派线程中更新UI
                SwingUtilities.invokeLater(() -> {
                    //将当前进度标签的前景色设置为红色
                    progressLabels.get(currentProgressIndex - 1).setForeground(Color.RED);
                });
                //当前进度索引加1
                currentProgressIndex++;
                //使用ScheduledExecutorService在500毫秒后再次调用animateProgress()方法
                executor.schedule(() -> {
                    animateProgress(executor);
                }, 500, TimeUnit.MILLISECONDS);
            } else {
                //动画完成跳转到订单页面
                executor.shutdown();
                SwingUtilities.invokeLater(() -> {
                    try {
                        Thread.sleep(500);
                        frame.update(new OrderPanel(frame,cabinet ,maxPowerRemainingPower));
                    } catch (InterruptedException ex) {
                        logger.error("跳转订单页面时被中断", ex);
                    }
                });
            }
        } catch (IndexOutOfBoundsException e) {
            logger.error("进度动画执行出错", e);
        }
    }

    //闪亮动画
    private void startFlashAnimation(JLabel label) {
        final int FLASH_DURATION = 3000; // 总闪烁时长(毫秒)
        final int FLASH_INTERVAL = 200; // 闪烁间隔(毫秒)
        Color originalColor = label.getForeground();
        Timer timer = new Timer(FLASH_INTERVAL, null);

        timer.addActionListener(new ActionListener() {
            private long startTime = System.currentTimeMillis();
            private boolean isRed = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = System.currentTimeMillis() - startTime;

                // 切换颜色
                isRed = !isRed;
                label.setBackground(isRed ? Color.RED : Color.WHITE);

                // 动画结束条件
                if (elapsedTime >= FLASH_DURATION) {
                    label.setForeground(originalColor); // 恢复原始颜色
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
    }

}