package util.tset;

// 策略接口
interface PaymentStrategy {
    void pay(double amount);
}

// 具体策略
class CreditCardStrategy implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("使用信用卡支付：" + amount);
    }
}

class AlipayStrategy implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("使用支付宝支付：" + amount);
    }
}

// 上下文
class PaymentContext {
    private PaymentStrategy strategy;

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        strategy.pay(amount); // 执行选定策略
    }
}

// 使用示例
//PaymentContext context = new PaymentContext();
//context.setStrategy(new CreditCardStrategy());
//        context.executePayment(1000); // 信用卡支付
//context.setStrategy(new AlipayStrategy());
//        context.executePayment(500);  // 支付宝支付