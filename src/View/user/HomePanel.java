package View.user;

import MyObject.PowerBankCabinet;
import Serve.observer.ObserverCabinet;
import Util.Rand;
import Util.factory.FactoryPanel;
import View.FatherFrame;
import View.FatherJPanel;
import View.user.order.OrderPanel;
import View.user.my.MyPanel;
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

    public HomePanel(UserFrame frame) {
        super();
        logger.info("首页加载中");
        this.frame = frame;
        setLayout(new GridLayout(3,1));
        FactoryPanel factoryPanel = new FactoryPanel();

        // 获取所有主机柜
        if (frame.getMapVisible()) {
            ObserverCabinet observerCabinet = new ObserverCabinet();
            List<PowerBankCabinet> powerBankCabinets = observerCabinet.getCabinets();
            int start = 0;
            int end = 16;                                   // 可显示主机的上限
            int cabinetCount = observerCabinet.getCabinetSize(); // 主机柜的数量
            // 显示现有的充电宝柜主机
            JPanel address = new JPanel(new GridLayout(4, 4));
            address.setPreferredSize(new Dimension(getWidth(), 600));
            add(address);
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
                        String name = cabinet.getName();
                        addressLabels[e].setText(name);
                        Font font = new Font("宋体", Font.BOLD, 16);
                        addressLabels[e].setFont(font);
                        addressLabels[e].setForeground(Color.BLACK);
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
        frame.setMapVIsible(false);

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
                frame.update(new OrderPanel((UserFrame) frame));
            }
        });
        //扫码充电事件
        JButton rent = (JButton) factoryPanel.getJComponent("扫码充电") ;
        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new RentMessagePanel((UserFrame) frame));
            }
        });
        logger.info("首页加载完成");
    }
}
