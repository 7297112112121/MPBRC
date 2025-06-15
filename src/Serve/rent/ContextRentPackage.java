package Serve.rent;

import java.util.List;

public class ContextRentPackage {
    private RentPackage rentPackage;

    //设置策略
    public void setRentPackage(RentPackage rentPackage) {
        this.rentPackage = rentPackage;
    }
    //使用策略方法
    //返回套餐介绍信息
    public List<String> getPackageText() {
        return rentPackage.getIntroduceText();
    }

    //返回套餐价格信息
    public double getPrice() {
        return rentPackage.getPriceText();
    }
}
