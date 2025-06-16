package MyObject;

public class PowerBank {
    private int id;
    private String brand;
    private double remainingPower; //电量
    private String status;
    private String cabinet; //所在充电宝柜
    private int powerID;   //充电宝所在柜台的端口id

    public PowerBank(int id,double remainingPower,String brand) {
        this.id = id;
        this.brand = brand;
        this.remainingPower = remainingPower;
        this.status = remainingPower > 50? "可租赁" : "不可租赁";
    }

    public PowerBank(int id,double remainingPower,String brand, String cabinet) {
        this.id = id;
        this.brand = brand;
        this.remainingPower = remainingPower;
        this.status = remainingPower > 50? "可租赁" : "不可租赁";
        this.cabinet = cabinet;
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
}