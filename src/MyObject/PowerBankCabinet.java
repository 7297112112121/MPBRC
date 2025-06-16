package MyObject;

import DAO.powerBank.PowerBankServiceImpl;

import java.util.List;

//充电柜对象
public class PowerBankCabinet {
    private int id;
    private String name;
    private int capacity;//能存放多少个充电宝
    private int newPowerBankNum; // 还在充电柜上的充电宝
    private int[] portNumberID;     //充电宝柜的端口id，下标即本机柜端口id，值为充电宝的id
    private String address;
    private List<PowerBank> powerBanks;

    //构造方法
    public PowerBankCabinet(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        //数据库中获得充电宝数据
        PowerBankServiceImpl powerBankService = new PowerBankServiceImpl();
        //获得本机的充电宝
        powerBanks = powerBankService.getAvailablePowerBanks(id);
       //根据充电宝powerID的值，充电宝存入与值相等的下标
        portNumberID = new int[capacity];
        for (int i = 0; i < powerBanks.size(); i++) {
            portNumberID[powerBanks.get(i).getPowerID()] = powerBanks.get(i).getId();
        }

    }

    //构造方法
    public PowerBankCabinet(int id, String name, int capacity, String address) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.address = address;
        //数据库中获得充电宝数据
        PowerBankServiceImpl powerBankService = new PowerBankServiceImpl();
        powerBanks = powerBankService.getAvailablePowerBanks();

    }
    //获得指定充电宝
    public PowerBank getPowerBank(int id){
        for (PowerBank powerBank : powerBanks) {
            if (powerBank.getId() == id) {
                return powerBank;
            }
        }
        return null;
    }

    //获得所有充电宝
    public List<PowerBank> getPowerBanks() {
        return powerBanks;
    }

    //设置指定充电宝
    public void setPowerBank(PowerBank powerBank) {
        for (int i = 0; i < powerBanks.size(); i++) {
            if (powerBanks.get(i).getId() == powerBank.getId()) {
                powerBanks.set(i, powerBank);
                break;
            }
        }
    }

    //设置所有充电宝
    public void setPowerBanks(List<PowerBank> powerBanks) {
        this.powerBanks = powerBanks;
    }

    //删除指定充电宝
    public void removePowerBank(int id) {
        for (int i = 0; i < powerBanks.size(); i++) {
            if (powerBanks.get(i).getId() == id) {
                powerBanks.remove(i);
                break;
            }
        }
    }

    //删除所有充电宝
    public void removeAllPowerBank() {
        powerBanks.clear();
    }


    //获得充电柜id
    public int getId() {
        return id;
    }

    //设置充电柜id
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //获得充电柜上充电宝的数量
    public int getCapacity() {
        return capacity;
    }

    //设置充电柜上充电宝的数量
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    //获得还在充电柜的充电宝数量
    public int getNewPowerBankNum() {
        return newPowerBankNum;
    }

    //设置还在充电柜的充电宝数量
    public void setNewPowerBankNum(int newPowerBankNum) {
        this.newPowerBankNum = newPowerBankNum;
    }

    //获得充电柜端口所有id
    public int[] getPortNumberID() {
        return portNumberID;
    }
}
