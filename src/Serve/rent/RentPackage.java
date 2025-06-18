package Serve.rent;

import java.util.List;

public interface RentPackage {
    //介绍信息
    List<String> getIntroduceText();

    //套餐价格
    double getPriceText();

    //套餐名称
    String getPackageName();
}
