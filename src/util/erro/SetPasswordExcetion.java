package util.erro;

import javax.swing.*;

public class SetPasswordExcetion extends RuntimeException {
    public SetPasswordExcetion(String message) {
        super(message);
    }
    /**
     * 返回消息
     * */
    public String getMessage() {
        return super.getMessage();
    }

    /**
     * 弹出消息窗口
     * */
    public void show() {
        JOptionPane.showMessageDialog(null, getMessage());
    }
}
