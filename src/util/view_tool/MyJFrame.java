package util.view_tool;

import admin.View.AdminRouterMessage;
import user.View.jframe.UserRouteMessage;
import util.other.PasswordForm;

import javax.swing.*;

public abstract class MyJFrame extends JFrame implements PasswordForm, UserRouteMessage, AdminRouterMessage {
    public MyJFrame() {
    }
    public abstract RenderingPanel getRendering() ;
}
