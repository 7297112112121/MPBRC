
import Config.CaptchaGlobal;
import Config.Global;
import DAO.LoadFormDataThead;

import Util.observer.AllObserverOfFrame;
import Util.observer.ObserverFrame;
import View.user.UserFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Util.db.DataBase;


/**
* 程序入口
 * */
public class Start {
    private static final Logger logger = LogManager.getLogger(Start.class);
    public static void main(String[] args) {
        logger.info("程序开始运行");

        //连接数据库，检查是否畅通
        DataBase.DBcheck();
        logger.info("数据库连接畅通");

        //加载验证配置信息
        new Global();
        new CaptchaGlobal();
        logger.info("验证配置信息加载成功");

        //线程加载数据
        LoadFormDataThead loadFormDataThead = new LoadFormDataThead();
        Thread loadDataThead = new Thread(loadFormDataThead);
        loadDataThead.start();
        logger.info("线程加载数据启动");

        //初始化观察者对象
        new ObserverFrame();
        new AllObserverOfFrame();

        //用户登陆
        new UserFrame();
        logger.info("用户登陆界面加载完成");

        //启动手机模拟器

    }
}
