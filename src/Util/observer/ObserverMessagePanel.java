package Util.observer;

import View.MyFrame;
import View.user.MessagePane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Util.observer.AllObserverOfFrame.Type.OBSERVER_MESSAGE_PANEL;

public class ObserverMessagePanel extends Observer {
    private MyFrame frame;
    private static HashMap<String, MessagePane> messagePaneHashMap = new HashMap<>();

    public ObserverMessagePanel(MyFrame frame) {
        this.frame = frame;
        //添加到观察者列表
        AllObserverOfFrame.add(frame, OBSERVER_MESSAGE_PANEL, this);
    }

    //添加到观察列表
    public void addMessagePanel(String name, MessagePane messagePane) {
        String names = name.trim();
        messagePaneHashMap.put(names, messagePane);
    }

    //获得指定消息面板对象
    public MessagePane getMessagePanel(String name) {
        String names = name.trim();
        return messagePaneHashMap.get(names);
    }

    //获得所有消息面板对象
    public List<MessagePane> getAllMessagePanel() {
        return new ArrayList<>(messagePaneHashMap.values());
    }

    //移除观察列表
    public void removeMessagePanel(String name) {
        String names = name.trim();
        messagePaneHashMap.remove(names);
    }

    //向指定消息面板添加消息
    public void addMessage(String id, String message) {
        String name = id.trim();
        getMessagePanel(name).addMessage(message);
    }

    //向所有消息面板添加消息
    public void addMessageToAll(String message) {
        for (MessagePane messagePane : getAllMessagePanel())
            messagePane.addMessage(message);
    }
}
