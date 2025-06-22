import Serve.LoadDataThead;
import Serve.observer.observer_frame.AllObserverOfFrame;
import Serve.observer.observer_frame.ObserverFrame;
import View.user.UserFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
* 程序入口
 * */
public class Start {
    private static final Logger logger = LogManager.getLogger(Start.class);
    public static void main(String[] args) {
        logger.info("程序开始运行");

        //线程加载数据
        Thread loadDataThead = new Thread(LoadDataThead.getInstance());
        loadDataThead.start();
        logger.info("线程加载数据启动");

        //初始化观察者对象
        new ObserverFrame();
        new AllObserverOfFrame();

        //用户登陆
        new UserFrame();
        logger.info("用户登陆界面加载完成");
    }
}
