package View;


import java.awt.*;
/**
 * 渲染面板
 * 使用卡片布局渲染新面板
 * */
public class RenderingPanel extends View.MyJPanel {

    private CardLayout cardLayout;
    private MyJPanel[] showPanel = new MyJPanel[2];
    private boolean saveShowPanel = true;

    public RenderingPanel() {
        super();
        cardLayout = new CardLayout();
        setLayout(cardLayout); // 关键修复：设置CardLayout为当前面板的布局管理器
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

    public MyJPanel getShowPanel() {
        if (saveShowPanel) {
            return showPanel[0];
        }else {
            return showPanel[1];
        }
    }
}