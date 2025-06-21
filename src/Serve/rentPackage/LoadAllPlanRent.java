package Serve.rentPackage;

import java.util.ArrayList;
import java.util.List;

public class LoadAllPlanRent {
    public static List<RentPackage> list = new ArrayList<>();
    public static void loadAllPlanRent() {
        list.add(new OneOfHoure());
        list.add(new ThreeOfHoure());
    }

    public static List<RentPackage> getList() {
        return list;
    }

    //返回对应名字套餐的对象
    public static RentPackage getPackageName(String name) {
        for (RentPackage rentPackage : list) {
            if (rentPackage.getPackageName().equals(name)) {
                return rentPackage;
            }
        }
        return null;
    }
}
