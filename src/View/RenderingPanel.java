package View;


import Serve.observer.observer_frame.AllObserverOfFrame;
import Serve.observer.observer_frame.ObserverMessagePanel;
import Serve.observer.observer_frame.ObserverOrderPanel;
import View.user.MessagePane;
import View.user.order.OrderPanel;

import java.awt.*;
import java.util.List;

/**
 * 渲染面板
 * 使用卡片布局渲染新面板
 * */
public class RenderingPanel extends FatherJPanel {

    private FatherFrame frame ;
    private CardLayout cardLayout;
    private FatherJPanel[] showPanel = new FatherJPanel[2]; //面板历史记录，最大保留记录1
    private boolean saveShowPanel = true;           //记录保留开关

    public RenderingPanel(FatherFrame frame) {
        super();
        this.frame = frame;
        cardLayout = new CardLayout();
        setLayout(cardLayout); // 关键修复：设置CardLayout为当前面板的布局管理器
    }

    /**
     * 使用卡片布局渲染新面板
     * @param showPanel 要显示的面板
     */
    public void update(FatherJPanel showPanel) {
        //判断若为此前创建的消息面板，则显示此前创建的消息面板
        MessagePane messagePane = skipSetObserverMessagePanel(showPanel);
        if (messagePane != null) {
            showPanel = messagePane;
        }

        //判断是否是此前创建过的订单面板，是这显示此前创建的消息面板
        OrderPanel orderPanel = skipSetObserverOrderPanel(showPanel);
        if (orderPanel != null) {
            showPanel = orderPanel;
        }

        // 获取showPanel的类名
        String panelName = showPanel.getClass().getName();

        // 检查面板是否已存在，如果不存在则添加
        if (getComponentCount() > 0) {
            boolean panelExists = false;
            //添加组件
            if (!panelExists) {
                add(showPanel, panelName);
            }
        } else {
            // 如果容器为空，直接添加面板
            add(showPanel, panelName);
        }

        // 显示指定面板
        cardLayout.show(this, panelName);
        if (saveShowPanel) {
            this.showPanel[0] = showPanel;
            saveShowPanel = false;
        } else {
            this.showPanel[1] = showPanel;
            saveShowPanel = true;
        }

        // 验证并重绘容器
        revalidate();
        repaint();
    }

    //获得历史记录上一个面板
    public FatherJPanel getShowPanel() {
        if (saveShowPanel) {
            return showPanel[0];
        }else {
            return showPanel[1];
        }
    }

    //识别信息面板是否被创建
    private MessagePane skipSetObserverMessagePanel(FatherJPanel showPanel) {
        //判断传入面板是不是信息面板
        if (!(showPanel instanceof MessagePane)) {
            return null;
        }
        //转换为MessagePane
        MessagePane messagePanel = (MessagePane) showPanel;
        //获得frame下的消息面板的观察者
        ObserverMessagePanel message = (ObserverMessagePanel) AllObserverOfFrame.getObserverByFrame(frame, AllObserverOfFrame.Type.OBSERVER_MESSAGE_PANEL);
        //获取所有消息面板
        List<MessagePane> messagePanels = message.getAllMessagePanel();
        //对比，判断此前是否已经创建过该面板
        for (MessagePane panel : messagePanels) {
            if (panel == messagePanel) {
                //之前已经创建，返回之前创建的消息面板
                return panel;
            }
        }
        //之前没有创建，返回null
        return null;
    }

    private OrderPanel skipSetObserverOrderPanel(FatherJPanel showPanel) {
        //判断是不是订单面板
        if (!(showPanel instanceof OrderPanel)) {
            return null;
            }
        //转换为OrderPanel
        OrderPanel orderPanel = (OrderPanel) showPanel;
        ObserverOrderPanel observerOrderPanel = new ObserverOrderPanel();
        boolean fa = observerOrderPanel.isOrderPanelAlreadyCreated(frame);
        if (fa) {
            return orderPanel;
        }
        return null;
    }
}