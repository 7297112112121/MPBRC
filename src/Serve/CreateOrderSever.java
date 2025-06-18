package Serve;

import DAO.powerBank.OrderDAO;
import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Serve.observer.ObserverCabinet;

import java.util.List;

public class CreateOrderSever {

    //查找该充电宝柜是否存在
    public Boolean havaPowerBankCabinet(PowerBankCabinet powerBankCabine) {
        ObserverCabinet cabinet = new ObserverCabinet();
        List<PowerBankCabinet> powerBankCabinets = cabinet.getCabinets();
        for (PowerBankCabinet powerBankCabinet : powerBankCabinets) {
            if (powerBankCabinet.getId() == powerBankCabine.getId()) {
                //找到返回正确
                return true;
            }
        }
        //找不到返回错误
        return false;
    }

    //查找最高电量充电宝
    public PowerBank mainRemainingPower(PowerBankCabinet powerBankCabine) {
        List<PowerBank> powerBanks = powerBankCabine.getPowerBanks();
        PowerBank powerBank = powerBanks.get(0);
        for (int i = 1; i < powerBanks.size(); i++) {
            if (powerBanks.get(i).getRemainingPower() > powerBank.getRemainingPower()) {
                powerBank = powerBanks.get(i);
            }
        }
        return powerBank;
    }

    //生成订单
    public boolean createOrder(PowerBankCabinet powerBankCabinet, PowerBank powerBank, int nameid) {
        OrderDAO orderDAO = new OrderDAO();
        int num = orderDAO.addOrder(powerBankCabinet, powerBank, nameid);
        if (num > 0) {
            return true;
        }
        return false;
    }

    //查询正在进行的订单
    public Order getIngOrder(int nameid) {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.getOrderIng(nameid);
    }
}
