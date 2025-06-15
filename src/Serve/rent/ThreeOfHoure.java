package Serve.rent;

import java.util.ArrayList;
import java.util.List;

public class ThreeOfHoure implements RentPackage{
    //每小时3元，不满一小时按一小时算
    private Double price = 3.00;
    private String name = "3.0元/60分钟";
    private String introduce = "不足60分钟按60分钟计费，每24小时封顶30.0元，总封顶99.0元";
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
}
