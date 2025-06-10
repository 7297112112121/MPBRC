package View.user.index;

import Util.View.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class IndexMain extends MyJPanel {
    private final Text userTextPanel;  //文本面板
    private final Nav topPanel;            //导航面板
    private final BorrowButton borrowPanel;      //租凭面板
    private final Map bottomPanel;      //位置面板
    public IndexMain() {
        setLayout(new GridLayout(4, 1));

        //顶部信息面板
        userTextPanel = new Text();
        JPanel textP = new JPanel(new GridLayout(1,2));
        textP.add(userTextPanel);
        add(textP);

        //位置面板
        bottomPanel = new Map();
        JPanel bottomP = new JPanel(new GridLayout(1,1));
        bottomP.add(bottomPanel);
        add(bottomP);

        //导航面板
        topPanel = new Nav();
        JPanel topP = new JPanel(new GridLayout(1, 1));
        topP.add(topPanel);
        add(topP);

        //租凭面板
        borrowPanel = new BorrowButton();
        JPanel borrowP = new JPanel(new GridLayout(1, 1));
        borrowP.add(borrowPanel);
        add(borrowP);

        setVisible(true);
    }
}
