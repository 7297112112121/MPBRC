package Serve;

import DAO.powerBank.OrderDAO;
import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Serve.observer.ObserverCabinet;
import Util.db.set.SimplySet;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class OrderSever {

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
    public boolean createOrder(PowerBankCabinet powerBankCabinet, PowerBank powerBank, int nameid, double price) {
        OrderDAO orderDAO = new OrderDAO();
        int num = orderDAO.addOrder(powerBankCabinet, powerBank, nameid, price);
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

    //计算时间差
    public String calculateTimeDifference(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        if (hours < 0 || minutes < 0) {
            hours = Math.abs(hours);
            minutes = Math.abs(minutes);
            return "负的" + hours + "小时" + minutes + "分钟";
        }

        return hours + "小时" + minutes + "分钟";
    }

    //计算服务费
    public double orderCost(Order order, String calculateTimeDifference, double price) {
        int orderID = order.getId();
        String[] timeText = calculateTimeDifference.split("小时");
        int hours = Integer.parseInt(timeText[0]);
        int minutes = Integer.parseInt(timeText[1].replace("分钟", ""));
        double money = 0;
        if (minutes % 60 == 0) {
            money = hours * price;
        }
        money = (hours + 1) * price;
        //传入数据库
        SimplySet set = new SimplySet();
        set.set("orders", "total_cost", String.valueOf(money), ";" , "id", String.valueOf(orderID));
        return money;
    }
}
