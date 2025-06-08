package powerBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class PowerBank {
    private int id;
    private String brand;
    private double remainingPower;
    private String status;

    public PowerBank(int id,double remainingPower,String brand) {
        this.id = id;
        this.brand = brand;
        this.remainingPower = remainingPower;
        this.status = remainingPower > 50? "可租赁" : "不可租赁";
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



}