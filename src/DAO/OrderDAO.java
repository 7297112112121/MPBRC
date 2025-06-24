package DAO;

import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import MyObject.User;
import Util.db.DBQuary;
import Util.db.DBUpData;
import View.powerBank.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO implements OrderService {
    private static final Logger logger = LogManager.getLogger(OrderDAO.class);

    @Override
    public void returnOrder(int orderId, double totalCost)  {

    }

    //获取对应状态的订单对象的订单对象
    public List<Order> getOrder(int nameid, String statusFrom) {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM orders WHERE nameid = ? AND status = ?";
            ResultSet resultSet = DBQuary.query(sql,nameid ,statusFrom);
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                //若是需要获取的状态则转换该记录为Order对象，否则跳过该条记录
                if (!status.equals(statusFrom)) {
                    continue;
                }
                //订单没有结束
                int id = resultSet.getInt("id");
                int powerBankId = resultSet.getInt("power_bank_id");
                Timestamp startTime = resultSet.getTimestamp("start_time");
                Timestamp endTime = resultSet.getTimestamp("end_time");
                double totalCost = resultSet.getDouble("total_cost");
                int cabinet = resultSet.getInt("cabinet");
                int cabinetPowerID = resultSet.getInt("cabinet_powerid");
                double price = resultSet.getDouble("price");
                String plan = resultSet.getString("plan");
                String status1 = resultSet.getString("status");
                list.add(new Order(id, powerBankId, startTime, endTime, totalCost, cabinet, cabinetPowerID, price, plan, status1));
            }
            return list;
        } catch (SQLException e) {
            logger.error("查询进行订单失败", e);
            return null;
        }
    }

    //获得已经结束的订单
    public Order getOverOrder(int nameid, int orderID) {
        try {
            String sql =  "SELECT * FROM orders WHERE `nameid` = " + nameid + " AND `id` = " + orderID;
            ResultSet resultSet = DBQuary.query(sql,nameid);
            resultSet.next();
            if (resultSet.getTimestamp("end_time") != null || resultSet.getString("status").equals("已结束")) {

                Integer id = resultSet.getInt("id");
                Integer powerBankId = resultSet.getInt("power_bank_id");
                Timestamp startTime = resultSet.getTimestamp("start_time");
                Timestamp endTime = resultSet.getTimestamp("end_time");
                Double totalCost = resultSet.getDouble("total_cost");
                Integer cabinet = resultSet.getInt("cabinet");
                Integer cabinetPowerID = resultSet.getInt("cabinet_powerid");
                Double price = resultSet.getDouble("price");
                String plan = resultSet.getString("plan");
                String status1 = resultSet.getString("status");
                return new Order(id, powerBankId, startTime, endTime, totalCost, cabinet, cabinetPowerID, price, plan, status1);
                }
        } catch (SQLException e) {
            logger.error("查询结束订单失败", e);
            return null;
        }
        return null;
    }

    //获取已经结束的所有订单
    public List<Order> getOverOrders(int nameid) {
        List<Order> list = new ArrayList<>();
        try {
            String sql =  "SELECT * FROM orders WHERE `nameid` = " + nameid;
            ResultSet resultSet = DBQuary.query(sql,nameid);
            while (resultSet.next()) {
                if (resultSet.getTimestamp("end_time") != null || resultSet.getString("status").equals("已结束")) {
                    Integer id = resultSet.getInt("id");
                    Integer powerBankId = resultSet.getInt("power_bank_id");
                    Timestamp startTime = resultSet.getTimestamp("start_time");
                    Timestamp endTime = resultSet.getTimestamp("end_time");
                    Double totalCost = resultSet.getDouble("total_cost");
                    Integer cabinet = resultSet.getInt("cabinet");
                    Integer cabinetPowerID = resultSet.getInt("cabinet_powerid");
                    Double price = resultSet.getDouble("price");
                    String plan = resultSet.getString("plan");
                    String status = resultSet.getString("status");
                     list.add(new Order(id, powerBankId, startTime, endTime, totalCost, cabinet, cabinetPowerID, price, plan, status));
                }
                return list;
            }
        } catch (SQLException e) {
            logger.error("查询结束订单失败", e);
            return null;
        }
        return null;
    }


    public List<Order> getAllOrders(int nameid) {
        return PowerBankDAO.getAllOrders(nameid);
    }


    //向订单表添加新的订单信息
    public int addOrder(PowerBankCabinet powerBankCabinet, PowerBank powerBank, int nameid, double price, String plan) {
        String sql = "INSERT INTO orders (power_bank_id, cabinet, cabinet_powerid, nameid, price, plan, status) VALUES (? ,?,?, ?, ?, ?, ?)";
        return DBUpData.update(sql, powerBank.getId(), powerBankCabinet.getId(), powerBank.getPowerID(), nameid, price,plan , "租借中");
    }

    //向表单填入结束时间
    public int addEndTime(int orderID) {
        //获得当前系统时间
        Timestamp now = new Timestamp(new Date().getTime());
        //填入数据库orders表
        String sql = "UPDATE orders \n" +
                     "SET end_time = ? \n" +
                     "WHERE id = ?";
        return DBUpData.update(sql, now, orderID);
    }

    //向订单更新订单状态
    public int updateOrderStatus(int nameid, int orderID, String status) {
        String sql = "UPDATE orders SET status = ? WHERE `nameid` = " + nameid + " AND `id` = " + orderID;
        return DBUpData.update(sql, status);
    }

    /**
     * 管理员操作数据库更新
     * */

    //获得所有订单信息
    public static List<Order> getOrderList() {
        try {
            List<Order> all = new ArrayList<>();
            String sql = "SELECT * FROM orders";
            ResultSet resultSet = DBQuary.query(sql);
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setPowerBankId(resultSet.getInt("power_bank_id"));
                order.setStartTime(resultSet.getTimestamp("start_time"));
                order.setEndTime(resultSet.getTimestamp("end_time"));
                order.setTotalCost(resultSet.getDouble("total_cost"));
                order.setCabinet(resultSet.getInt("cabinet"));
                order.setCabinetPowerID(resultSet.getInt("cabinet_powerid"));
                order.setNameid(resultSet.getInt("nameid"));
                order.setPrice(resultSet.getDouble("price"));
                order.setPlan(resultSet.getString("plan"));
                order.setStatus(resultSet.getString("status"));
                all.add(order);
            }
            return all;
        } catch (SQLException e) {
            logger.error("获取所有充电包详情失败",e);
            return null;
        }
    }

    //管理员添加新的信息
    public static void addOrderMessage(List<Order> order) {
        List<Order> all = new ArrayList<>();
        String insert = "INSERT INTO orders (power_bank_id, start_time, end_time, total_cost, cabinet, cabinet_powerid, nameid, price, plan, status) VALUES (?,?,?,?,?,?,?,?,?,?)";
        for (Order or : order) {
            int upNum = DBUpData.update(insert, or.getPowerBankId(), or.getStartTime(), or.getEndTime(), or.getTotalCost(), or.getCabinet(), or.getCabinetPowerID(), or.getNameid(), or.getPrice(), or.getPlan(), or.getStatus());
            if (upNum <= 0) {
                logger.warn("添加订单信息失败");
            } else {
                logger.info("添加订单信息成功");
            }
        }
    }


    //管理员更新订单数据
    public static void updateOrderMessage(Order or) {
        String sql = "UPDATE orders SET power_bank_id =?, start_time =?, end_time =?, total_cost =?, cabinet = ?,cabinet_powerid = ?,nameid = ?,price = ?, plan = ?, status = ?  WHERE id =?";
        int upNum = DBUpData.update(sql, or.getPowerBankId(), or.getStartTime(), or.getEndTime(), or.getTotalCost(), or.getCabinet(), or.getCabinetPowerID(), or.getNameid(), or.getPrice(), or.getPlan(), or.getStatus(), or.getId());
        if (upNum <= 0)
            logger.error("修改用户信息失败");
        else logger.info("修改用户信息成功");
    }

    //删除用户数据
    public static void deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE id = ?";
        int upNum = DBUpData.update(sql, id);
        if (upNum <= 0) {
            logger.error("删除订单失败");
        } else {
            logger.info("删除订单成功");
        }
    }

}