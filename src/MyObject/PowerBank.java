package MyObject;

public class PowerBank {
    private int id;
    private String brand;
    private double remainingPower; //电量
    private String status;
    private int cabinet; //所在充电宝柜
    private int powerID;   //充电宝所在柜台的端口id
    private String shelf;   //电源上下架情况


    public PowerBank() {
    }

    public PowerBank(int id, double remainingPower, String brand) {
        this.id = id;
        this.brand = brand;
        this.remainingPower = remainingPower;
        this.status = remainingPower > 50? "可租赁" : "不可租赁";
    }

    public PowerBank(int id,double remainingPower,String brand, int cabinet) {
        this.id = id;
        this.brand = brand;
        this.remainingPower = remainingPower;
        this.status = remainingPower > 50? "可租赁" : "不可租赁";
        this.cabinet = cabinet;
    }

    public PowerBank(int powerID, int cabinet, String status, double remainingPower, String brand, int id) {
        this.powerID = powerID;
        this.cabinet = cabinet;
        this.status = status;
        this.remainingPower = remainingPower;
        this.brand = brand;
        this.id = id;
    }

    public PowerBank(int id, String brand, double remainingPower, String status, int cabinet, int powerID, String shelf) {
        this.id = id;
        this.brand = brand;
        this.remainingPower = remainingPower;
        this.status = status;
        this.cabinet = cabinet;
        this.powerID = powerID;
        this.shelf = shelf;
    }

    public int getId() {
        return id;
    }


    public String getBrand() {
        return brand;
    }

    public double getRemainingPower() {
        return remainingPower;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", 品牌: " + brand + ", 电量: " + remainingPower + "%";
    }

    public int getPowerID() {
        return powerID;
    }

    public void setPowerID(int powerID) {
        this.powerID = powerID;
    }

    public int getCabinetID() {
        return cabinet;
    }

    public void setCabinetID(int cabinet) {
        this.cabinet = cabinet;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setRemainingPower(double remainingPower) {
        this.remainingPower = remainingPower;
    }

    public int getCabinet() {
        return cabinet;
    }

    public void setCabinet(int cabinet) {
        this.cabinet = cabinet;
    }
}