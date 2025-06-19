package MyObject;

import java.sql.Timestamp;
import java.util.Date;

public class Order {
    private int id;
    private int powerBankId;
    private Timestamp startTime;
    private Timestamp endTime;
    private double totalCost;
    private int cabinet;
    private int cabinetPowerID;
    private double price;//记录套餐价格
    private String plan;//记录套餐
    private String status;//订单状态

    public Order(int id, int powerBankId, Timestamp startTime) {
        this.id = id;
        this.powerBankId = powerBankId;
        this.startTime = startTime;
    }

    public Order(int id, int powerBankId, Timestamp startTime, Timestamp endTime, double totalCost, int cabinet, int cabinetPowerID) {
        this.id = id;
        this.powerBankId = powerBankId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.cabinet = cabinet;
        this.cabinetPowerID = cabinetPowerID;
    }

    public Order(int id, int powerBankId, Timestamp startTime, Timestamp endTime, double totalCost, int cabinet, int cabinetPowerID, double price) {
        this.id = id;
        this.powerBankId = powerBankId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.cabinet = cabinet;
        this.cabinetPowerID = cabinetPowerID;
        this.price = price;
    }

    public Order(int id, int powerBankId, Timestamp startTime, Timestamp endTime, double totalCost, int cabinet, int cabinetPowerID, double price, String plan) {
        this.id = id;
        this.powerBankId = powerBankId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.cabinet = cabinet;
        this.cabinetPowerID = cabinetPowerID;
        this.price = price;
        this.plan = plan;
    }

    public Order(int id, int powerBankId, Timestamp startTime, Timestamp endTime, double totalCost, int cabinet, int cabinetPowerID, double price, String plan, String status) {
        this.id = id;
        this.powerBankId = powerBankId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.cabinet = cabinet;
        this.cabinetPowerID = cabinetPowerID;
        this.price = price;
        this.plan = plan;
        this.status = status;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public int getPowerBankId() {
        return powerBankId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
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

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getCabinet() {
        return cabinet;
    }

    public void setCabinet(int cabinet) {
        this.cabinet = cabinet;
    }

    public int getCabinetPowerID() {
        return cabinetPowerID;
    }

    public void setCabinetPowerID(int cabinetPowerID) {
        this.cabinetPowerID = cabinetPowerID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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