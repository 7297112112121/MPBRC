package Serve.observer;

import MyObject.PowerBank;
import MyObject.PowerBankCabinet;

import java.util.*;

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

    //获得所有的充电宝柜
    public List<PowerBankCabinet> getCabinets() {
        return new ArrayList<>(cabinetCache.values());
    }
    // 随机获取指定数量的充电柜
    public List<PowerBankCabinet> getCabinets(int num) {
        List<PowerBankCabinet> allCabinets = new ArrayList<>(cabinetCache.values());
        int size = allCabinets.size();

        // 处理指定数量超过现有数量的情况
        if (num >= size) {
            return allCabinets;
        }

        // 随机抽取指定数量的元素
        List<PowerBankCabinet> result = new ArrayList<>(num);
        Random random = new Random();
        Set<Integer> selectedIndices = new HashSet<>();

        while (selectedIndices.size() < num) {
            int randomIndex = random.nextInt(size);
            if (!selectedIndices.contains(randomIndex)) {
                result.add(allCabinets.get(randomIndex));
                selectedIndices.add(randomIndex);
            }
        }

        return result;
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

    //删除所有充电柜实例
    public void removeAllCabinet() {
        cabinetCache.clear();
        nameToID.clear();
    }

    //获得指定充电柜中指定的充电宝实例
    public PowerBank getPowerBank(int cabinetId, int powerBankId) {
        return cabinetCache.get(cabinetId).getPowerBank(powerBankId);
    }

    //获得指定充电柜中指定充电宝的所有实例
    public List<PowerBank> getPowerBanks(int cabinetId) {
        return cabinetCache.get(cabinetId).getPowerBanks();
    }

    //删除指定充电柜中指定的充电宝实例
    public void removePowerBank(int cabinetId, int powerBankId) {
        cabinetCache.get(cabinetId).removePowerBank(powerBankId);
    }

    //删除指定充电柜中指定充电宝的所有实例
    public void removePowerBanks(int cabinetId) {
        cabinetCache.get(cabinetId).removeAllPowerBank();
    }

    //获得现有充电柜的数量
    public int getCabinetSize() {
        return cabinetCache.size();
    }


}
