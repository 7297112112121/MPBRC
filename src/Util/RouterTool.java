package Util;

import Util.View.MyJFrame;
import Util.erro.NullException;

import javax.swing.*;

public class RouterTool{
    /**
     * 保持唯一窗口
     * */
    public void checkJFrame(MyJFrame obj) {
        if (!(obj == null)) {
            // 将窗口最小化
            obj.setExtendedState(JFrame.ICONIFIED);
            // 将窗口恢复到正常大小
            obj.setExtendedState(JFrame.NORMAL);
            throw new NullException("该窗口已存在");
        }
    }

}
