package Util.View;

import Config.PasswordConfig;

import javax.swing.*;

public abstract class MyJFrame extends JFrame implements PasswordConfig {

    public MyJFrame() {

    }

    public abstract MyRenderingPanel getRendering();

}
