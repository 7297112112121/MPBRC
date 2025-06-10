
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Router.RouterConfig;
import Util.db.DataBase;


/**
* 程序入口
 * */
public class Start implements  LogConfig , RouterConfig {
    private static final Logger logger = LogManager.getLogger(Start.class);
//    final static File pathFile = new File(USER_INFO_LOG_FILE);
    public static void main(String[] args) {
        logger.info("程序开始运行");

        //连接数据库，检查是否畅通
        DataBase.DBcheck();

        //加载用户数据

        //加载管理员数据

        //加载订单数据

        //启动手机模拟器

    }
}
