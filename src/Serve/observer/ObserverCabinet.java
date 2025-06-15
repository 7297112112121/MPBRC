package Serve.observer;

import MyObject.PowerBankCabinet;

import java.util.HashMap;
import java.util.Map;

public class ObserverCabinet extends Observer{
    private static final Map<Integer, PowerBankCabinet> cabinetCache = new HashMap<>();
    private static final Map<String, Integer> nameToID = new HashMap<>();

    public ObserverCabinet() {}
    //获得指定名称的充电柜实例
    public PowerBankCabinet getCabinetByName(String name) {
        return cabinetCache.get(nameToID.get(name));
    }

    //获得指定id的充电柜实例
    public PowerBankCabinet getCabinetById(int id) {
        return cabinetCache.get(id);
    }

    //添加充电柜实例
    public void addCabinet(PowerBankCabinet cabinet) {
        cabinetCache.put(cabinet.getId(), cabinet);
        nameToID.put(cabinet.getName(), cabinet.getId());
    }

    //删除充电柜实例
    public void removeCabinet(int id) {
        cabinetCache.remove(id);
        nameToID.remove(cabinetCache.get(id).getName());
    }

    //删除指定名称的充电宝柜实例
    public void removeCabinet(String name) {
        cabinetCache.remove(nameToID.get(name));
        nameToID.remove(name);
    }

}
