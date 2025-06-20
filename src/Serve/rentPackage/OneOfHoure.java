package Serve.rentPackage;

import java.util.ArrayList;
import java.util.List;

public class OneOfHoure implements RentPackage{
    //每小时3元，不满一小时按一小时算
    String title = "1元套餐";
    Double price = 1.00;
    String name = "1.0元/60分钟";
    String introduce = "不足60分钟按60分钟计费，每24小时封顶30.0元，总封顶99.0元";
    private List<String> list = new ArrayList<>();

    @Override
    public List<String> getIntroduceText() {
        list.add(name);
        list.add(introduce);
        return list;
    }

    @Override
    public double getPriceText() {
        return price;
    }

    @Override
    public String getPackageName() {
        return title;
    }
}
