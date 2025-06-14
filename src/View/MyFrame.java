package View;

import javax.swing.*;

public abstract class MyFrame extends JFrame{
    public MyFrame() {
        super();
    }
    public abstract void update(MyJPanel panel);

    public abstract MyJPanel getShowPanel() ;

}
