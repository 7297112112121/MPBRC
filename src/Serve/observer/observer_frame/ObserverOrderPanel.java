package Serve.observer.observer_frame;

import Serve.observer.Observer;
import View.FatherFrame;
import View.FatherJPanel;
import View.user.order.OrderPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ObserverOrderPanel extends Observer {
    private FatherFrame frame;
    private static HashMap<FatherFrame, OrderPanel> orderPaneHashMap = new HashMap<>();

    public ObserverOrderPanel() {
    }

    public ObserverOrderPanel(FatherFrame frame) {
        this.frame = frame;
    }

    //添加到观察列表
    public void addOrderPanel(FatherFrame name, OrderPanel messagePane) {
        orderPaneHashMap.put(name, messagePane);
    }

    //获得指定消息面板对象
    public OrderPanel getOrderPanel(String name) {
        String names = name.trim();
        return orderPaneHashMap.get(names);
    }

    //获得所有消息面板对象
    public List<OrderPanel> getAllOrderPanel() {
        return new ArrayList<>(orderPaneHashMap.values());
    }

    //移除观察列表
    public void removeOrderPanel(FatherFrame name) {
        orderPaneHashMap.remove(name);
    }

    //判断是否有此面板
    public boolean isOrderPanelAlreadyCreated(FatherFrame fatherFrame) {
        for (FatherFrame fa : orderPaneHashMap.keySet()) {
            if (fa == fatherFrame) {
                return true;
            }
        }
        return false;
    }

}
