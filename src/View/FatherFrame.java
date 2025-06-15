package View;

import javax.swing.*;

public abstract class FatherFrame extends JFrame{
    public FatherFrame() {
        super();
    }
    public abstract void update(FatherJPanel panel);

    public abstract FatherJPanel getShowPanel() ;

}
