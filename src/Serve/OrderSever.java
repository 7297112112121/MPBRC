package Serve;

import DAO.powerBank.OrderDAO;
import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Serve.observer.ObserverCabinet;
import Util.db.set.SimplySet;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class OrderSever {
    private OrderDAO orderDAO = new OrderDAO();

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
    public boolean createOrder(PowerBankCabinet powerBankCabinet, PowerBank powerBank, int nameid, double price, String plan) {
        int num = orderDAO.addOrder(powerBankCabinet, powerBank, nameid, price, plan);
        if (num > 0) {
            return true;
        }
        return false;
    }

    //查询正在进行的订单
    public Order getIngOrder(int nameid) {
        return orderDAO.getOrderIng(nameid);
    }

    //查询已结束的订单
    public List<Order> getOverOrder(int nameid) {
        return orderDAO.getOverOrders(nameid);
    }

    //查询所有的订单
    public List<Order> getAllOrders(int nameid) {
        return orderDAO.getAllOrders(nameid);
    }

    //结束订单
    public void endOrder() {
        //填入结束时间
        orderDAO.addEndTime();
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

    //返回分钟
    public int returnMinutes(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long minutes = duration.toMinutes();
        return (int) minutes;
    }

    //返回小时
    public int returnHours(LocalDateTime startTime, LocalDateTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long hours = duration.toHours();
        return (int) hours;
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
