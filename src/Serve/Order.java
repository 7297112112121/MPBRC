package Serve;

public enum Order {
    ORDER_PAY_NO("未支付"),
    ORDER_ING("租借中"),
    ORDER_ED("已结束");
    private String status;
    Order(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
