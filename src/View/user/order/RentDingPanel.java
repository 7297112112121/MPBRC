package View.user.order;

import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Serve.CreateOrderSever;
import Util.factory.FactoryPanel;
import View.FatherJPanel;
import View.user.UserFrame;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RentDingPanel extends FatherJPanel {
    private UserFrame frame;
    private PowerBank powerBank;
    private PowerBankCabinet powerBankCabinet;
    private CreateOrderSever createOrderSever = new CreateOrderSever();
    private Order orderIng;
    private LocalDateTime now;
    private JLabel timeLabel;
    private JLabel moneyLabel;
    private ScheduledExecutorService scheduler;
    private FactoryPanel factoryPanel;

    //用于没有创建订单
    public RentDingPanel(UserFrame frame, PowerBankCabinet powerBankCabinet, PowerBank powerBank) {
        this.frame = frame;
        this.powerBank = powerBank;
        this.powerBankCabinet = powerBankCabinet;
        initializeUI();
        initializeOrder();
        initializeListeners();
        startTimer();
    }
    //用于已经拥有了订单
    public RentDingPanel(UserFrame frame) {
        this.frame = frame;
        this.powerBank = powerBank;
        this.powerBankCabinet = powerBankCabinet;
        initializeUI();
        initializeOrder();
        initializeListeners();
        startTimer();
    }

    private void initializeUI() {
        setLayout(new GridLayout(4,1));
        factoryPanel = new FactoryPanel();
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, ";time", ";money"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "售后服务", "归还提醒", "福利中心"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "不还了，留下充电宝"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "返回;return", "归还"));

        timeLabel = (JLabel) factoryPanel.getJComponent("time");
        timeLabel.setFont(new Font("宋体", Font.BOLD, 20));
        moneyLabel = (JLabel) factoryPanel.getJComponent("money");
        moneyLabel.setFont(new Font("宋体", Font.BOLD, 20));
        now = LocalDateTime.now();
    }

    private void initializeOrder() {
        orderIng = getOrder();

        if (orderIng == null) {
            if (createOrder()) {
                orderIng = getOrder();
                updateTimeDisplay("0小时0分钟");
                updateMoneyDisplay("预计 3 元");
            } else {
                handleOrderCreationFailure();
            }
        } else {
            updateTimeDisplayBasedOnOrder();
        }
    }

    private void initializeListeners() {
        JButton returnButton = (JButton) factoryPanel.getJComponent("return");
        returnButton.addActionListener(e -> {
            stopTimer();
            frame.update(new OrderPanel(frame));
        });
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        stopTimer();
    }

    private void startTimer() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> SwingUtilities.invokeLater(() -> {
            if (orderIng != null) {
                updateTimeDisplayBasedOnOrder();
            }
        }), 0, 1, TimeUnit.MINUTES);
    }

    private void stopTimer() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

    private boolean createOrder() {
        return createOrderSever.createOrder(powerBankCabinet, powerBank, frame.getUser().getNameID());
    }

    private Order getOrder() {
        return createOrderSever.getIngOrder(frame.getUser().getNameID());
    }

    private void updateTimeDisplayBasedOnOrder() {
        LocalDateTime startTime = orderIng.getStartTime().toLocalDateTime();
        String timeDiff = calculateTimeDifference(startTime, LocalDateTime.now());
        updateTimeDisplay(timeDiff);
    }

    private void updateTimeDisplay(String time) {
        timeLabel.setText(time);
    }

    private void updateMoneyDisplay(String money) {
        moneyLabel.setText(money);
    }

    private void handleOrderCreationFailure() {
        updateTimeDisplay("订单创建失败");
        updateMoneyDisplay("");
    }

    public String calculateTimeDifference(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        if (hours < 0 || minutes < 0) {
            hours = Math.abs(hours);
            minutes = Math.abs(minutes);
            return "负的" + hours + "小时" + minutes + "分钟";
        }

        return hours + "小时" + minutes + "分钟";
    }
}