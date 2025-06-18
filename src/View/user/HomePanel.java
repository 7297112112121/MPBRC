package View.user;

import MyObject.Order;
import MyObject.PowerBankCabinet;
import Serve.OrderSever;
import Serve.observer.ObserverCabinet;
import Serve.observer.observer_frame.ObserverOrderPanel;
import Util.factory.FactoryPanel;
import View.FatherJPanel;
import View.user.order.OrderListPanel;
import View.user.my.MyPanel;
import View.user.order.OrderPanel;
import View.user.rent.RentMessagePanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HomePanel extends FatherJPanel {
    private static final Logger logger = LogManager.getLogger(HomePanel.class);
    private UserFrame frame ;
    private JPanel address ;
    public HomePanel(UserFrame frame) {
        super();
        logger.info("首页加载中");
        this.frame = frame;
        setLayout(new GridLayout(3,1));
        FactoryPanel factoryPanel = new FactoryPanel();

        // 显示现有的充电宝柜主机
        address = new JPanel(new GridLayout(4, 4));
        address.setPreferredSize(new Dimension(getWidth(), 600));
        add(address);

        if (frame.getAddress() == null) {
            setMapVIsible();
        }

        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "我的;我的", "订单;订单", "客服", "1元宝", "福利"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "扫码充电;扫码充电"));

        setVisible(true);

        //进入用户中心界面事件
        JButton my = (JButton) factoryPanel.getJComponent("我的");
        my.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new MyPanel((UserFrame) frame));
            }
        });

        //进入订单界面事件
        JButton order = (JButton) factoryPanel.getJComponent("订单");
        order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                frame.update(new OrderListPanel((UserFrame) frame));
            }
        });
        //扫码充电事件
        JButton rent = (JButton) factoryPanel.getJComponent("扫码充电") ;
        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Order order1 = getOrder();
                if (order1 != null) {
                    //已经租凭了充电宝自动跳转到订单详情
                    frame.update(new OrderPanel((UserFrame) frame));
                }else {
                    //没有这跳转到选择套餐服务
                    frame.update(new RentMessagePanel((UserFrame) frame));
                }

            }
        });
        startOrderPanel();
        logger.info("默认选择的充电柜:{}", frame.getPowerBankCabinetDefault().getName());
        logger.info("首页加载完成");
    }

    //随机地图效果生成
    private void setMapVIsible( ) {
        ObserverCabinet observerCabinet = new ObserverCabinet();
        //统一设置地图上不显示
        List<PowerBankCabinet> powerBankCabinetAll = observerCabinet.getCabinets();
        for (PowerBankCabinet powerBankCabinet : powerBankCabinetAll) {
            powerBankCabinet.setVisibleMap(false);
        }
        //设置要显示的充电柜的对象与数量
        List<PowerBankCabinet> powerBankCabinets = observerCabinet.getCabinets(2);
        frame.setPowerBankCabinetsOnMap(powerBankCabinets);
        int start = 0;
        int end = 16;                                   // 可显示主机的上限
        int cabinetCount = observerCabinet.getCabinetSize(); // 主机柜的数量


            // 确保不会尝试显示超过上限的主机
            if (cabinetCount > end) {
                cabinetCount = end; // 限制最多显示end个主机
            }

            // 用于存储已生成的随机位置
            Set<Integer> usedPositions = new HashSet<>();
            Random random = new Random();
            int[] visiblePositions = new int[cabinetCount];

            // 生成不重复的随机位置
            for (int i = 0; i < cabinetCount; i++) {
                int position;
                do {
                    position = random.nextInt(end - start) + start; // 生成[start, end-1]之间的随机数
                } while (usedPositions.contains(position)); // 如果已存在，则重新生成

                usedPositions.add(position);
                visiblePositions[i] = position;
            }
            // 冒泡排序实现，将visiblePositions数组按从小到大排序
            for (int i = 0; i < cabinetCount - 1; i++) {
                for (int j = 0; j < cabinetCount - i - 1; j++) {
                    if (visiblePositions[j] > visiblePositions[j + 1]) {
                        // 交换元素位置
                        int temp = visiblePositions[j];
                        visiblePositions[j] = visiblePositions[j + 1];
                        visiblePositions[j + 1] = temp;
                    }
                }
            }

            // 创建并初始化JLabel数组
            int count = 0;
            JLabel[] addressLabels = new JLabel[end];
            for (int e = start; e < end; e++) {
                addressLabels[e] = new JLabel();
                address.add(addressLabels[e]);
                // 检查当前位置是否是随机选中的位置

                for (int i = 0; i < cabinetCount; i++) {
                    int nu = visiblePositions[count];
                    if (e == nu) {
                        // 显示充电宝柜信息
                        PowerBankCabinet cabinet = powerBankCabinets.get(count);
                        //设置该充电柜在地图上是显示的
                        cabinet.setVisibleMap(true);
                        String name = cabinet.getName();
                        addressLabels[e].setText(name);
                        Font font = new Font("宋体", Font.BOLD, 16);
                        addressLabels[e].setFont(font);
                        addressLabels[e].setForeground(Color.BLACK);
                        //如果是第一个则为默认选择的充电柜，返回其对象
                        if (count == 0) {
                            frame.setPowerBankCabinetDefault(cabinet);
                            addressLabels[e].setForeground(Color.RED);
                        }
                        //防止数组下标越界
                        if (count < visiblePositions.length - 1){
                            count++;
                        }
                        break;
                    } else {
                        addressLabels[e].setText("♣");
                        break;
                    }
                }
            }
        }

    private Order getOrder() {
        OrderSever createOrderSever = new OrderSever();
        return createOrderSever.getIngOrder(frame.getUser().getNameID());
    }

    //创建订单面板，添加到观察者中
    private void startOrderPanel() {
        ObserverOrderPanel orderPanel = new ObserverOrderPanel();
        orderPanel.addOrderPanel(frame, new OrderPanel(frame));
    }
}
