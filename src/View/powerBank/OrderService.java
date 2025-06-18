package View.powerBank;

import MyObject.Order;

import java.util.List;

public interface OrderService {


        /** 执行归还操作（更新结束时间和费用） */
        void returnOrder(int orderId, double totalCost) throws Exception;


}
