package Serve;

import Config.CompanyGlobal;
import Config.PowerBankGlobal;
import DAO.OrderDAO;
import DAO.PayDAO;
import DAO.PowerBankDAO;
import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import MyObject.User;
import Serve.observer.ObserverCabinet;
import Serve.payMethods.ChargeLocal;
import Util.db.DBQuary;
import Util.db.set.SimplySet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static Serve.Order.ORDER_ED;
import static Serve.Order.ORDER_ING;

public class OrderSever {
    private static final Logger logger = LogManager.getLogger(OrderSever.class);
    private static OrderDAO orderDAO = new OrderDAO();

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
        //判断是否为空
        if (powerBanks.size() == 0) {
            return null;
        }
        PowerBank powerBank = powerBanks.get(0);
        for (int i = 1; i < powerBanks.size(); i++) {
            if (powerBanks.get(i).getRemainingPower() > powerBank.getRemainingPower()) {
                powerBank = powerBanks.get(i);
            }
        }
        //判断充电宝 电量 是否大于或等于50%
        if (powerBank.getRemainingPower() >= PowerBankGlobal.getMinPower()) {
            return powerBank;
        } else {
            return null;
        }
    }

    //生成订单
    public boolean createOrder(PowerBankCabinet powerBankCabinet ,int id, int nameid, double price, String plan) {
        PowerBank powerBank = PowerBankDAO.getNowPowerBank(id);
        int num = orderDAO.addOrder(powerBankCabinet, powerBank, nameid, price, plan);
        if (num > 0) {
            return true;
        }
        return false;
    }

    //查询正在进行的订单
    public List<Order> getIngOrder(int nameid, String status) {
        return orderDAO.getOrder(nameid, status);
    }

    //查询已结束的所有订单
    public List<Order> getOverOrder(int nameid) {
        return orderDAO.getOverOrders(nameid);
    }

    //查询已结束的订单
    public Order getOverOrder(int nameid, int id) {
        return orderDAO.getOverOrder(nameid, id);
    }

    //查询所有的订单
    public List<Order> getAllOrders(int nameid) {
        return orderDAO.getAllOrders(nameid);
    }

    //结束订单
    /**
     * @param user 在线用户对象
     * @param orderID 归还充电宝的id
     * @param powerBankCabinet 要归还到的充电宝柜
     * @param powerID 用户要归还的端口
     * */
    public Order endOrder(User user ,int orderID, PowerBankCabinet powerBankCabinet, int powerID) {
        int nameID = user.getNameID();
        //订单填入结束时间,修改订单状态
        orderDAO.addEndTime(orderID);
        orderDAO.updateOrderStatus(nameID, orderID, ORDER_ED.getStatus());
        //获取订单
        Order order = orderDAO.getOverOrder(nameID ,orderID);
        //结算租凭金额，返还押金
        int minutes = returnMinutes(order.getStartTime().toLocalDateTime(), order.getEndTime().toLocalDateTime());
        double cost = (minutes / 60 + 1 ) * order.getPrice();
        boolean pay = PayDAO.executePaymentTransaction(PayDAO.PaymentType.LOCAL, nameID, CompanyGlobal.getCompanyID(), cost);
        if (!pay) {
            logger.error("支付失败");
            orderDAO.updateOrderStatus(nameID, orderID, ORDER_ING.getStatus());
            return null;
        }
        //添加消费记录
        new ChargeLocal().consume(cost, nameID, user);
        //扫描充电宝所有信息
        PowerBank powerBank = PowerBankDAO.getNowPowerBank(order.getPowerBankId());
        //依据电量，判断是否可以继续租凭
        if (powerBank.getRemainingPower() > PowerBankGlobal.getMinPower()) {
            //可以继续租凭出去
            powerBank.setStatus(Serve.PowerBank.POWER_RENTAL_YES.getStatus());
        }else {
            //不可以继续租凭出去
            powerBank.setStatus(Serve.PowerBank.POWER_RENTAL_NO.getStatus());
        }
        //判断当前充电柜是否原充电柜
        if (!(powerBankCabinet.getId() == powerBank.getCabinetID())) {
            //如果不是原本充电柜,更新所在充电柜的id
            powerBank.setCabinetID(powerBankCabinet.getId());
        }
        //判断当前是否为该端口

        if (!(powerBank.getPowerID() == powerID)){
            //若果不是修改为新的端口id
            powerBank.setPowerID(powerID);
        }
        //更新该充电宝数据库
        PowerBankDAO.updatePowerBankAllMessage(powerBank);
        return order;
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
