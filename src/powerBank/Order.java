package powerBank;

import user.User;

/*
* 订单编号
* 移动电源的用户对象
* 被租凭的移动电源对象
* 订单开始时间
* 订单结束时间
* 订单计算费用
* */


public class Order {
    //声明变量
    private int idOrder;
    private User user;
    private PowerBank powerBank;
    private long startTime;
    private long endTime;

    public Order(int idOrder, User user, PowerBank powerBank, long startTime) {
        this.idOrder = idOrder;
        this.user = user;
        this.powerBank = powerBank;
        this.startTime = startTime;
    }

    public void Order(long endTime) {
        this.endTime = endTime;
    }

    public double calculateCost(){
        long duration = (endTime - startTime)/(1000*60*60);
        return duration*1.5;
    }
}
