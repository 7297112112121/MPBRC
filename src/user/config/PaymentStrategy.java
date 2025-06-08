package user.config;

public interface PaymentStrategy {
    boolean pay(int userNameID,int bank, double amount);
}
