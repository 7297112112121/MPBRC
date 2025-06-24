package Serve;

import DAO.CabinetDAO;
import MyObject.PowerBankCabinet;
import Serve.observer.ObserverCabinet;

import java.util.List;

public class CabinetServer {
    public CabinetServer() {}

    /**
     * 加载所有的充电宝柜,并添加到观察者中
     * */
    public void loadCabinets() {

        List<PowerBankCabinet> cabinets = new CabinetDAO().getAllCabinets();
        ObserverCabinet observerCabinet = new ObserverCabinet();
        //清空缓存
        observerCabinet.removeAllCabinet();
        //添加最新数据到缓存
        for (PowerBankCabinet cabinet : cabinets) {
            observerCabinet.addCabinet(cabinet);
        }
    }


}
