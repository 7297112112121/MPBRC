package View.user.order;

import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Serve.OrderSever;
import Util.factory.FactoryPanel;
import View.FatherFrame;
import View.FatherJPanel;
import View.user.UserFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OrderPanel extends FatherJPanel {
    /**
     * 订单详情页
     * */
    private static Logger logger = LogManager.getLogger(OrderPanel.class);
    private UserFrame frame;
    private PowerBank powerBank;
    private PowerBankCabinet powerBankCabinet;
    private OrderSever createOrderSever = new OrderSever();
    private Order orderIng;
    private LocalDateTime now;
    private JLabel timeLabel;
    private JLabel moneyLabel;
    private ScheduledExecutorService scheduler;
    private FactoryPanel factoryPanel;
    private OrderSever orderSever = new OrderSever();
    private String timeDiff;
    private int[] nowPowerBanksPowerID;
    private int PortDefault = -1;
    private JPanel menu;
    private JComboBox<String> portComboBox;

    //用于没有创建订单，创建新的订单
    //用于扫码租凭充电包
    public OrderPanel(UserFrame frame, PowerBankCabinet powerBankCabinet, PowerBank powerBank) {
        logger.info("正在创建订单");
        this.frame = frame;
        this.powerBank = powerBank;
        this.powerBankCabinet = powerBankCabinet;
        initializeUI();
        initializeOrder();
        initializeListeners();
        startTimer();
        overOrder();
        logger.info("创建完成");
    }
    //用于若用户已经拥有了进行的订单，则继续进行计时计费
    //用于主页面
    public OrderPanel(UserFrame frame) {
        logger.info("正在加载用户订单");
        this.frame = frame;
        this.powerBank = powerBank;
        this.powerBankCabinet = powerBankCabinet;
        initializeUI();
        //初始化订单
        initializeOrder(1);
        //事件源
        initializeListeners();
        startTimer();
        overOrder();
        logger.info("用户订单加载成功");
    }

    //创建面板组件
    private void initializeUI() {
        setLayout(new GridLayout(5,1));

        menu = new JPanel(new GridLayout(1,2));
        PowerBankCabinet powerBankCabinetDefault = frame.getPowerBankCabinetDefault();
        // 创建充电柜下拉菜单
        List<PowerBankCabinet> powerBankCabinetsOnMap = frame.getPowerBankCabinetsOnMap();
        //设置默认选项
        for (PowerBankCabinet powerBankCabinet : powerBankCabinetsOnMap) {
            if (powerBankCabinet == powerBankCabinetDefault)
                powerBankCabinetsOnMap.remove(powerBankCabinet);
            powerBankCabinetsOnMap.add(0, powerBankCabinet);
            break;
        }
        //设置菜单
        String[] nams = new String[powerBankCabinetsOnMap.size()];
        for (int i = 0; i < powerBankCabinetsOnMap.size(); i++) {
            nams[i] = powerBankCabinetsOnMap.get(i).getName();
        }
        JComboBox<String> institutionComboBox = new JComboBox<>(nams);
        institutionComboBox.setFont(new Font("宋体", Font.BOLD, 18));
        institutionComboBox.setPreferredSize(new Dimension(getWidth(), 60));
        menu.add(institutionComboBox); // 直接添加下拉菜单组件
        //创建充电柜端口下拉菜单
        menu();
        add(menu);


        factoryPanel = new FactoryPanel();
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE, ";time", ";money"));
//        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "售后服务", "归还提醒", "福利中心"));
//        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "不还了，留下充电宝"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "返回;return", "归还;returnBank"));

        timeLabel = (JLabel) factoryPanel.getJComponent("time");
        timeLabel.setFont(new Font("宋体", Font.BOLD, 20));
        moneyLabel = (JLabel) factoryPanel.getJComponent("money");
        moneyLabel.setFont(new Font("宋体", Font.BOLD, 20));
        now = LocalDateTime.now();

        /**
         * 充电柜菜单下拉框监听
         * */
        institutionComboBox.addItemListener(e -> {
            // 获取用户选择的充电柜，若用户没有选择，默认使用系统预选的充电柜
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) e.getItem();
                for (PowerBankCabinet po : powerBankCabinetsOnMap) {
                    if (po.getName().equals(selectedItem)) {
                        //重新设置用户默认选择的充电柜
                        frame.setPowerBankCabinetDefault(po);
                        //更新端口状态
                        menu();
                    }
                }
            }
        });

    }

    //用于用户已经拥有订单，则继续计时
    private void initializeOrder(int n) {
        orderIng = getIngOrder();
        if (orderIng != null) {
            updateTimeDisplayBasedOnOrder();
        }
    }

    //返回订单列表事件
    private void initializeListeners() {
        JButton returnButton = (JButton) factoryPanel.getJComponent("return");
        returnButton.addActionListener(e -> {
            stopTimer();
            frame.update(new OrderListPanel(frame));
        });
    }

    //归还充电宝事件
    private void overOrder() {
        JButton returnButton = (JButton) factoryPanel.getJComponent("returnBank");
        returnButton.addActionListener(e -> {
            //结束订单
            Order overOrder = orderSever.endOrder(frame.getUser(), orderIng.getId(), frame.getPowerBankCabinetDefault(), PortDefault);
            if (overOrder != null) {
                frame.update(new OrderOverPanel(frame, overOrder));
            } else {
                logger.error("订单结束失败！");
            }
        });


    }

    //初始化订单
    private void initializeOrder() {
        orderIng = getIngOrder();
        if (orderIng == null) {
            if (createOrder()) {
                orderIng = getIngOrder();
                updateTimeDisplay("0小时0分钟");
                updateMoneyDisplay("预计 " + frame.getPrice() + " 元");
            } else {
                handleOrderCreationFailure();
            }
        } else {
            updateTimeDisplayBasedOnOrder();
        }
    }

    private void menu() {
        //准备下拉字段
        PowerBankCabinet powerBankCabinetDefault = frame.getPowerBankCabinetDefault();
        int captity = powerBankCabinetDefault.getCapacity();    //充电柜的容量
        nowPowerBanksPowerID = powerBankCabinetDefault.getPortNumberID();

        // 创建端口选项列表，只包含可用端口
        List<String> availablePorts = new ArrayList<>();
        for (int i = 0; i < captity; i++) {
            if (nowPowerBanksPowerID[i] == -1) {
                availablePorts.add("端口" + (i + 1));
            }
        }

        // 如果没有可用端口，添加提示信息
        if (availablePorts.isEmpty()) {
            availablePorts.add("无可用端口");
            PortDefault = -1;
        } else {
            // 设置默认端口为第一个可用端口
            PortDefault = Integer.parseInt(availablePorts.get(0).substring(2));
        }

        // 将列表转换为数组
        String[] portsArray = availablePorts.toArray(new String[0]);

        // 移除之前的下拉框组件
        if (portComboBox != null) {
            // 移除旧的事件监听器
            ItemListener[] listeners = portComboBox.getItemListeners();
            for (ItemListener listener : listeners) {
                portComboBox.removeItemListener(listener);
            }
            menu.remove(portComboBox);
        }

        // 创建新的下拉菜单
        portComboBox = new JComboBox<>(portsArray);
        portComboBox.setFont(new Font("宋体", Font.BOLD, 18));
        portComboBox.setPreferredSize(new Dimension(getWidth(), 60));
        menu.add(portComboBox);

        // 添加端口选择事件监听器
        portComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedItem = (String) e.getItem();
                if ("无可用端口".equals(selectedItem)) {
                    PortDefault = -1;
                    return;
                }

                try {
                    // 提取端口号
                    String numberPart = selectedItem.substring(selectedItem.indexOf("端口") + 2);
                    PortDefault = Integer.parseInt(numberPart) - 1;
                } catch (NumberFormatException ex) {
                    logger.error("端口号解析失败: " + selectedItem, ex);
                    PortDefault = -1;
                }
            }
        });

        // 刷新面板
        menu.revalidate();
        menu.repaint();
    }

    //停止计时，移除计时器
    @Override
    public void removeNotify() {
        super.removeNotify();
        stopTimer();
    }

    //开始计时
    // 启动定时器
    private void startTimer() {
        // 创建一个单线程调度器
        scheduler = Executors.newSingleThreadScheduledExecutor();
        // 每隔1分钟执行一次任务
        scheduler.scheduleAtFixedRate(() -> SwingUtilities.invokeLater(() -> {
            // 如果订单不为空
            if (orderIng != null) {
                // 更新时间显示
                updateTimeDisplayBasedOnOrder();
                logger.info("更新订单时间");
                // 更新金额显示
                calculateMoney();
                logger.info("更新订单金额");
            }
        }), 0, 1, TimeUnit.MINUTES);
    }

    //停止计时
    private void stopTimer() {
        if (scheduler != null && !scheduler.isShutdown()) {
            // 关闭调度器
            scheduler.shutdownNow();
        }
    }

    //创建订单
    private boolean createOrder() {
        return createOrderSever.createOrder(powerBankCabinet, powerBank.getPowerID(), frame.getUser().getNameID(), frame.getPrice(), frame.getPlanName());
    }

    //查询订单
    private Order getIngOrder() {
        List<Order> list = createOrderSever.getIngOrder(frame.getUser().getNameID(), Serve.Order.ORDER_ING.getStatus());
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    //更新时间显示
    private void updateTimeDisplayBasedOnOrder() {
        LocalDateTime startTime = orderIng.getStartTime().toLocalDateTime();
        timeDiff = calculateTimeDifference(startTime, LocalDateTime.now());
        updateTimeDisplay(timeDiff);
    }


    //更新时间显示
    private void updateTimeDisplay(String time) {
        timeLabel.setText(time);
    }

    //更新金额显示
    private void updateMoneyDisplay(String money) {
        moneyLabel.setText(money + "￥");
    }

    //失败提示
    private void handleOrderCreationFailure() {
        updateTimeDisplay("订单创建失败");
        updateMoneyDisplay("");
    }

    //一次性计算时间差
    private String calculateTimeDifference(LocalDateTime startTime, LocalDateTime endTime) {

        return orderSever.calculateTimeDifference(startTime, endTime);
    }

    //一次性计费计算
    private void calculateMoney() {
        double money = orderSever.orderCost(orderIng, timeDiff, orderIng.getPrice());
        updateMoneyDisplay(String.valueOf(money));
    }
}