package Serve;

import Config.CaptchaGlobal;
import Config.Global;
import MyObject.PowerBankCabinet;
import Serve.observer.ObserverCabinet;
import Serve.rent.LoadAllPlanRent;
import Util.db.DataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * 定时更新数据线程
 * */
public class LoadDataThead implements Runnable{
    private static final Logger logger = LogManager.getLogger(LoadDataThead.class);
    @Override
    public void run() {

        while (true) {
            //加载所有存在的充电宝柜
            List<PowerBankCabinet> powerBankCabinets = new CabinetServer().loadCabinets();
            ObserverCabinet observerCabinet = new ObserverCabinet();
            //加载套餐
            LoadAllPlanRent.loadAllPlanRent();
            //连接数据库，检查是否畅通
            DataBase.DBcheck();
            logger.info("数据库连接畅通");

            //加载验证配置信息
            new Global();
            new CaptchaGlobal();
            logger.info("验证配置信息加载成功");
            try {
                Thread.sleep(5*60*1000);
            } catch (InterruptedException e) {
                logger.warn("定时更新数据线程异常",e);
            }
        }
    }
}
