package MyObject;

import java.util.Date;

public class Order {
    private int id;
    private int powerBankId;
    private Date startTime;
    private Date endTime;
    private double totalCost;

    public Order(int id, int powerBankId, Date startTime) {
        this.id = id;
        this.powerBankId = powerBankId;
        this.startTime = startTime;
    }
    public Order() {

    }

    public int getId() {
        return id;
    }

    public int getPowerBankId() {
        return powerBankId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPowerBankId(int powerBankId) {
        this.powerBankId = powerBankId;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    // 计算使用时长（单位：小时）
    public long calculateDuration() {
        if (endTime != null) {
            long diff = endTime.getTime() - startTime.getTime();
            return diff / (60 * 60 * 1000);
        }
        return 0;
    }

    // 计算租赁费用（示例：1.5元/小时）
    public double calculateCost() {
        long duration = calculateDuration(); // 获取使用时长（小时）
        return duration * 1.5;
    }
}