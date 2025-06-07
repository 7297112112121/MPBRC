package user.Service.pay;


public class PaymentContext {
    private PaymentStrategy strategy;

    /**
     * 选择策略
     * */
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 使用策略
     * */
    public void executePayment(int userNameID,int bank, double amount) {
        strategy.pay(userNameID, bank, amount); // 执行选定策略
    }
}
