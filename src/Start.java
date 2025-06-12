
import DAO.LoadFormDataThead;
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

        //加载入口

        //线程加载数据
        LoadFormDataThead loadFormDataThead = new LoadFormDataThead();
        Thread loadDataThead = new Thread(loadFormDataThead);
        loadDataThead.start();

        //用户登陆
        new UserFrame();

        //启动手机模拟器

    }
}
