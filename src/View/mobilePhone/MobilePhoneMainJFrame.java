package View.mobilePhone;

import Util.*;
import Util.View.MobilePhoneJFrame;
import Util.View.MyRenderingPanel;


public class MobilePhoneMainJFrame extends MobilePhoneJFrame {

    private final MyRenderingPanel rp;

    public MobilePhoneMainJFrame(int doorID) {
        super(doorID);

        setTitle("手机模拟");
        setSize(400, 800);

        rp = new DoorMyRenderingPanel(doorID);
        add(rp);
        rp.update(new HomePanel());

        setVisible(true);
    }


    public MyRenderingPanel getRendering() {
        return rp;
    }

}
