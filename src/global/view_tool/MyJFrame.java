package global.view_tool;

import admin.Serve.AdminRouterMessage;
import user.config.UserRouteMessage;
import global.PasswordConfig;

import javax.swing.*;

public abstract class MyJFrame extends JFrame implements PasswordConfig, UserRouteMessage, AdminRouterMessage {
    public MyJFrame() {
    }
    public abstract RenderingPanel getRendering() ;
}
