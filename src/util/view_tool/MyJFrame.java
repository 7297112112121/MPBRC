package util.view_tool;

import admin.View.AdminRouterMessage;
import user.View.jframe.UserRouteMessage;
import user.View.jframe.Form;

import javax.swing.*;

public abstract class MyJFrame extends JFrame implements Form, UserRouteMessage, AdminRouterMessage {
    public MyJFrame() {
    }
    public abstract RenderingPanel getRendering() ;
}
