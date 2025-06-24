package Serve;

import DAO.OrderDAO;
import DAO.PowerBankDAO;
import MyObject.Order;
import MyObject.PowerBank;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderModelDataServer {
    private static Logger logger = LogManager.getLogger(OrderModelDataServer.class);

    //生成充电包信息数据模型
    public Object[][] orderModel() {
        List<MyObject.Order> orders = OrderDAO.getOrderList();
        if (orders == null || orders.isEmpty()) {
            logger.warn("没有订单数据，请检查数据库表是否为空");
            return null;
        }
        Object[][] data = new Object[orders.size()][];
        int i = 0;
        for (MyObject.Order or : orders) {

            data[i++] = new Object[]{
                    or.getId(),
                    or.getPowerBankId(),
                    or.getStartTime(),
                    or.getEndTime(),
                    or.getTotalCost(),
                    or.getCabinet(),
                    or.getCabinetPowerID(),
                    or.getNameid(),
                    or.getPrice(),
                    or.getPlan(),
                    or.getStatus()
            };
        }
        return data;
    }

    //新增多条充电宝模型数据
    public Object[][] addOrderModels(List<List<String>> textFields) {
        List<MyObject.Order> orders = new ArrayList<>();
        for (List<String> list : textFields) {
            for (String s : list) {
                MyObject.Order or = new MyObject.Order();
                or.setPowerBankId(Integer.parseInt(list.get(0)));
                or.setStartTime(Timestamp.valueOf(list.get(1)));
                or.setEndTime(Timestamp.valueOf(list.get(2)));
                or.setTotalCost(Double.parseDouble(list.get(3)));
                or.setCabinet(Integer.parseInt(list.get(4)));
                or.setCabinetPowerID(Integer.parseInt(list.get(5)));
                or.setNameid(Integer.parseInt(list.get(6)));
                or.setPrice(Double.parseDouble(list.get(7)));
                or.setPlan(list.get(8));
                or.setStatus(list.get(9));
                orders.add(or);
            }
        }
        //添加充电宝数据
        OrderDAO.addOrderMessage(orders);
        //添加数据
        return orderModel();
    }

    //新增单条充模型数据
    public Object[][] addOrder(List<String> textFields) {
        List<MyObject.Order> orders = new ArrayList<>();
        MyObject.Order or = new MyObject.Order();
        or.setPowerBankId(Integer.parseInt(textFields.get(0)));
        or.setStartTime(Timestamp.valueOf(textFields.get(1)));
        or.setEndTime(Timestamp.valueOf(textFields.get(2)));
        or.setTotalCost(Double.parseDouble(textFields.get(3)));
        or.setCabinet(Integer.parseInt(textFields.get(4)));
        or.setCabinetPowerID(Integer.parseInt(textFields.get(5)));
        or.setNameid(Integer.parseInt(textFields.get(6)));
        or.setPrice(Double.parseDouble(textFields.get(7)));
        or.setPlan(textFields.get(8));
        or.setStatus(textFields.get(9));
        orders.add(or);
        // 添加充电宝数据
        OrderDAO.addOrderMessage(orders);
        return orderModel();
    }

    //修改订单模型数据
    public void setOrder(  Object[] rowData) {
        MyObject.Order or = new Order();
        //数据转换为充电宝对象
        or.setId(Integer.parseInt(rowData[0].toString()));
        or.setPowerBankId(Integer.parseInt(rowData[1].toString()));
        or.setStartTime(Timestamp.valueOf(rowData[2].toString()));
        or.setEndTime(Timestamp.valueOf(rowData[3].toString()));
        or.setTotalCost(Double.parseDouble(rowData[4].toString()));
        or.setCabinet(Integer.parseInt(rowData[5].toString()));
        or.setCabinetPowerID(Integer.parseInt(rowData[6].toString()));
        or.setNameid(Integer.parseInt(rowData[7].toString()));
        or.setPrice(Double.parseDouble(rowData[8].toString()));
        or.setPlan(rowData[9].toString());
        or.setStatus(rowData[10].toString());
        OrderDAO.updateOrderMessage(or);
    }

    //删除数据库中单行模型数据
    public void deleteOrderModelRow(int orderID) {
        OrderDAO.deleteOrder(orderID);
    }


}
