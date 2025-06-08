
import admin.Serve.AdminRouterServe;
import data.AdminForm;
import data.OrderForm;
import data.UserForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import user.Service.UserRouteMessage;
import user.Service.UserRouter;
import util.db.DataBase;
import util.log.LogExportMessage;
import util.tset.UserPhoneMessage;

import static admin.Serve.AdminRouterMessage.AdminJFrame;

/**
* 程序入口
 * */
public class Start implements UserRouteMessage, LogExportMessage {
    private static final Logger logger = LogManager.getLogger(Start.class);
//    final static File pathFile = new File(USER_INFO_LOG_FILE);
    public static void main(String[] args) {
        logger.info("程序开始运行");

        //连接数据库，检查是否畅通
        DataBase.DBcheck();

        //加载用户数据
        UserForm.loadUsers();

        //加载管理员数据
        AdminForm.loadAdmins();

        //加载订单数据
        OrderForm.loadOrders();

        //启动用户登录界面
        UserRouter.getRouter().newJFrame(UserJFrame);

        //启动管理员登录界面
        AdminRouterServe.getRouter().newJFrame(AdminJFrame);

        //实时日志监控
//        try{
//            LogView view = new LogView();
//            view.realtimeShowLog(pathFile);
//        }catch (NullPointerException e){
//            logger.error("文件对象不存在，请检查pathFile变量的指定目录路径是否正确",e);
//        }
        //启动手机短信模拟器
        UserPhoneMessage.getUserPhoneMessage();

        //等待用户结束操作，执行结束操作
//        Thread.currentThread().join();
    }
}
