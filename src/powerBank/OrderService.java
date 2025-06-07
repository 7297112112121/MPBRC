package powerbank;

import java.util.List;

public interface OrderService {

        /** 获取订单详情 */
        Order getOrderById(int orderId);

        /** 执行归还操作（更新结束时间和费用） */
        void returnOrder(int orderId, double totalCost) throws Exception;

        /** 获取所有订单 */
        List<Order> getAllOrders();

}
