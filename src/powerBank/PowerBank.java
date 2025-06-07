package powerBank;

import java.util.List;

public abstract class PowerBank implements PowerBankManage {

/*
* 移动电源编号
* 移动电源品牌
* 移动电源剩余电量
* 租凭状态
* 移动电源上下架
* 修改移动电源信息
* 查看移动电源信息
* */
    private int id;
    private String brand;
    private double remainingBattery;
    private boolean isAvailable;

    public PowerBank(int id,String brand ,double remainingBattery) {
        this.id = id;
        this.brand = brand;
        this.remainingBattery = remainingBattery;
        this.isAvailable = remainingBattery > 0.5;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getRemainingBattery() {
        return remainingBattery;
    }

    //设置移动电源剩余电量，并根据新的电量值更新可用性状态
    public void setRemainingBattery(double remainingBattery) {
        this.remainingBattery = remainingBattery;
        this.isAvailable = remainingBattery > 0.5;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void addPowerBank(List<PowerBank> powerBanks, PowerBank powerBank) {
        powerBanks.add(powerBank);
        System.out.println("移动电源上架成功！");
    }

    // 移动电源下架
    public void removePowerBank(List<PowerBank> powerBanks, PowerBank powerBank) {
        if (powerBanks.remove(powerBank)) {
            System.out.println("移动电源下架成功！");
        } else {
            System.out.println("移动电源下架失败，该电源可能不存在！");
        }
    }




}
