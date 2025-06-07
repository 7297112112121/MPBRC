package util.view_tool;

import admin.Serve.AdminRouterMessage;
import user.Service.UserRouteMessage;
import util.config.PasswordConfig;

import javax.swing.*;

public abstract class MyJFrame extends JFrame implements PasswordConfig, UserRouteMessage, AdminRouterMessage {
    public MyJFrame() {
    }
    public abstract RenderingPanel getRendering() ;
}
