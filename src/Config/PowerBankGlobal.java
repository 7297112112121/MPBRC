package Config;

import MyObject.PowerBank;

public class PowerBankGlobal {
    //充电宝设置（配置）单
    //允许出借电源最低线度
    private static double minPower = 50.00;

    //获得允许出借电源最低线度
    public static double getMinPower() {
        return minPower;
    }

    //设置允许出借电源最低线度
    public static void setMinPower(double minPower) {
        PowerBankGlobal.minPower = minPower;
    }
}
