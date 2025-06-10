package View.user.index;

import Util.View.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class Map extends MyJPanel {
    private final JLabel res;
    public Map(){
        res = new JLabel("可借地点");//插入图片

        //布局
        setLayout(new GridLayout(1,1));
        add(res);

        setVisible(true);
    }

}
