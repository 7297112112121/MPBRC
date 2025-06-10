package View;

import Util.View.MyJFrame;
import Util.View.MyRenderingPanel;

import javax.swing.*;
import java.awt.*;

public class ControlFrame extends MyJFrame {
    public ControlFrame() {
        setTitle("模拟控制台");
        setBounds(0,0,400,100);
        setLayout(new BorderLayout());

        //以被创建的手机
        JPanel allPhonePanel = new JPanel(new FlowLayout());
        for () {
            allPhonePanel.add();
        }
        JPanel allPhonePanel2 = new JPanel(new FlowLayout());

        JPanel allPhonePanel3 = new JPanel(new FlowLayout());

        //创建手机
        JButton createPhone = new JButton("创建新手机");
        add(createPhone, BorderLayout.SOUTH);

    }

    @Override
    public MyRenderingPanel getRendering() {
        return null;
    }
}
