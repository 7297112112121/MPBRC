package user.Service.pay;

public interface PaymentStrategy {
    boolean pay(int userNameID,int bank, double amount);
}
