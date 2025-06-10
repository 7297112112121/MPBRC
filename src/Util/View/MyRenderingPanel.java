package Util.View;

import java.awt.*;
/**
 * 渲染面板
 * 使用卡片布局渲染新面板
 * */
public class MyRenderingPanel extends MyJPanel {

    private CardLayout cardLayout;

    public MyRenderingPanel() {
        super();
        cardLayout = new CardLayout();
    }

    /**
     * 使用卡片布局渲染新面板
     * @param showPanel 要显示的面板
     */
    public void update(MyJPanel showPanel) {
        // 获取showPanel的类名
        String panelName = showPanel.getClass().getName();

        // 检查面板是否已存在，如果不存在则添加
        if (getComponentCount() > 0) {
            boolean panelExists = false;
            for (Component comp : getComponents()) {
                if (comp.getClass().getName().equals(panelName)) {
                    panelExists = true;
                    break;
                }
            }
            if (!panelExists) {
                add(showPanel, panelName);
            }
        } else {
            // 如果容器为空，直接添加面板
            add(showPanel, panelName);
        }

        // 显示指定面板（参数一：要渲染的位置，参数二：要渲染面板的名称）
        cardLayout.show(this, panelName);

        // 验证并重绘容器
        revalidate();
        repaint();
    }
}