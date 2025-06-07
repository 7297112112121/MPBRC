package RealUser;

import user.User;

public class RealUser extends User {
    private double wechat_balance;      //微信金额
    private double alipay_balance;      //支付宝金额

    //模拟用户手机使用
    public RealUser(int nameID, String name, String password, String phoneNum, double wechat_balance, double alipay_balance) {
        super(nameID, name, password, phoneNum);
        this.wechat_balance = wechat_balance;
        this.alipay_balance = alipay_balance;
    }

    public double getWechat_balance() {
        return wechat_balance;
    }

    public void setWechat_balance(double wechat_balance) {
        this.wechat_balance = wechat_balance;
    }

    public double getAlipay_balance() {
        return alipay_balance;
    }

    public void setAlipay_balance(double alipay_balance) {
        this.alipay_balance = alipay_balance;
    }
}

