package user.Config;

public interface PaymentStrategy {
    boolean pay(int userNameID,int bank, double amount);
}
