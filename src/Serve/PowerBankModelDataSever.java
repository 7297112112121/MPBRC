package Serve;

import DAO.PowerBankDAO;
import MyObject.PowerBank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PowerBankModelDataSever {
    private static Logger logger = LogManager.getLogger(PowerBankModelDataSever.class);

    //生成充电包信息数据模型
    public Object[][] powerBankModel() {
        List<PowerBank> powerBanks = PowerBankDAO.getPowerBanksList();
        if (powerBanks == null || powerBanks.isEmpty()) {
            logger.warn("没有充电宝详细数据，请检查数据库表是否为空");
            return null;
        }
        Object[][] data = new Object[powerBanks.size()][];
        int i = 0;
        for (PowerBank pb : powerBanks) {

            data[i++] = new Object[]{
                    pb.getId(),
                    pb.getRemainingPower(),
                    pb.getStatus(),
                    pb.getBrand(),
                    pb.getCabinetID(),
                    pb.getPowerID(),
                    pb.getShelf()
            };
        }
        return data;
    }

    //新增多条充电宝模型数据
    public Object[][] addPowerBankModels(List<List<String>> textFields) {
        List<PowerBank> powerBanks = new ArrayList<>();
        for (List<String> list : textFields) {
            for (String s : list) {
                PowerBank powerBank = new PowerBank();
                powerBank.setRemainingPower(Double.parseDouble(list.get(1)));
                powerBank.setStatus(list.get(2));
                powerBank.setBrand(list.get(3));
                powerBank.setCabinetID(Integer.parseInt(list.get(4)));
                powerBank.setPowerID(Integer.parseInt(list.get(5)));
                powerBank.setShelf(list.get(6));
                powerBanks.add(powerBank);
            }
        }
        //添加充电宝数据
        PowerBankDAO.addPowerBank(powerBanks);
        //添加数据
        return powerBankModel();
    }
    //新增单条充电宝模型数据
    public Object[][] addPowerBankModel(List<String> textFields) {
        List<PowerBank> result = new ArrayList<>();
        PowerBank powerBank = new PowerBank();
        powerBank.setRemainingPower(Double.parseDouble(textFields.get(0)));
        powerBank.setStatus(textFields.get(1));
        powerBank.setBrand(textFields.get(2));
        powerBank.setCabinetID(Integer.parseInt(textFields.get(3)));
        powerBank.setPowerID(Integer.parseInt(textFields.get(4)));
        powerBank.setShelf(textFields.get(5));
        result.add(powerBank);
            // 添加充电宝数据
        PowerBankDAO.addPowerBank(result);
        return powerBankModel();
    }

    //修改充电宝模型数据
    public void setPowerBank(  Object[] rowData) {
        PowerBank powerBank = new PowerBank();
        //数据转换为充电宝对象
        powerBank.setId((Integer)(rowData[0]));
        powerBank.setRemainingPower((Double) (rowData[1]));
        powerBank.setStatus((String) rowData[2]);
        powerBank.setBrand((String)rowData[3]);
        powerBank.setCabinetID((Integer)(rowData[4]));
        powerBank.setPowerID((Integer)(rowData[5]));
        powerBank.setShelf((String) rowData[6]);
        PowerBankDAO.updatePowerBankAllMessage(powerBank);
    }

    //删除数据库中单行模型数据
    public void deletePowerBankModelRow(int powerID) {
        PowerBankDAO.deletePowerBank(powerID);
    }


}
