package View.admin;

import View.FatherFrame;
import View.FatherJPanel;
import View.RenderingPanel;
import View.user.LoginPanel;

import java.awt.*;

public class AdminLoginFrame extends FatherFrame {
    private final RenderingPanel rp ;                   //渲染组件
    public AdminLoginFrame() {
        super();
        setTitle("管理员登录");
        setBounds(100,100,600,400);
        //主要内容
        rp = new RenderingPanel(this);
        add(rp, BorderLayout.CENTER);
        rp.update(new AdminLoginPanel(this));

        setVisible(true);
    }
    @Override
    public void update(FatherJPanel panel) {
        rp.update(panel);
    }

    @Override
    public FatherJPanel getShowPanel() {
        return null;
    }
}
