package Util.observer;

import View.MyFrame;

import java.util.HashMap;

public class ObserverFrame extends Observer{
    public static HashMap<String, MyFrame> observerFrame = new HashMap<>();

    public ObserverFrame(){}

    //添加窗口
    public static void addFrame(String name, MyFrame frame){
        observerFrame.put(name, frame);
    }
    //获得窗口
    public static MyFrame getFrame(String name){
        return observerFrame.get(name);
    }
    //获得所有窗口
    public static HashMap<String, MyFrame> getAllFrame(){
        return observerFrame;
    }
    //移除窗口
    public static void removeFrame(String name){
        observerFrame.remove(name);
    }
}
