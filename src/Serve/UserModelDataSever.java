package Serve;

import DAO.PowerBankDAO;
import DAO.UserMessageDAO;
import MyObject.PowerBank;
import MyObject.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserModelDataSever  {
    private static Logger logger = LogManager.getLogger(UserModelDataSever.class);

    //生成用户信息数据模型
    public Object[][] usersModel() {
        List<MyObject.User> users = UserMessageDAO.getUserList();
        if (users == null || users.isEmpty()) {
            logger.warn("没有用户数据详细数据，请检查数据库表是否为空");
            return null;
        }
        Object[][] data = new Object[users.size()][];
        int i = 0;
        for (MyObject.User us : users) {

            data[i++] = new Object[]{
                    us.getNameID(),
                    us.getSex(),
                    us.getName(),
                    us.getPhone(),
                    us.getAccount()
            };
        }
        return data;
    }

    //新增用户数据
    public Object[][] addPowerBankModels(List<List<String>> textFields) {
        List<MyObject.User> users = new ArrayList<>();
        for (List<String> list : textFields) {
            for (String s : list) {
                MyObject.User us = new MyObject.User();
                us.setNameID(Integer.parseInt(list.get(0)));
                us.setName(list.get(1));
                us.setSex(list.get(2));
                //us.setPassword(list.get(3));
                us.setPhone(list.get(4));
                us.setAdminID(Integer.parseInt(list.get(5)));
                //index：6  是用户被创建的时间
                us.setAccount(Double.parseDouble(list.get(7)));
                users.add(us);
            }
        }
        //添加充电宝数据
        UserMessageDAO.addUserM(users);
        //添加数据
        return usersModel();
    }
    //新增单条用户模型数据
    public Object[][] addUserModel(List<String> textFields) {
        List<MyObject.User> result = new ArrayList<>();
        MyObject.User user = new MyObject.User();
        user.setName(textFields.get(1));
        user.setSex(textFields.get(0));
        user.setPhone(textFields.get(2));
        user.setAccount(Double.parseDouble(textFields.get(3)));
        result.add(user);
        // 添加充电宝数据
        UserMessageDAO.addUserM(result);
        return usersModel();
    }

    //修改充电宝模型数据
    public void setPowerBank(  Object[] rowData) {
        MyObject.User user = new User();
        //数据转换为用户对象
        user.setNameID((int) rowData[0]);
        user.setName((String) rowData[2]);
        user.setSex((String) rowData[1]);
        user.setPhone((String) rowData[3]);
        user.setAccount((Double) rowData[4]);
        UserMessageDAO.updatePowerBankAllMessage(user);
    }

    //删除数据库中单行模型数据
    public void deleteUserModelRow(int nameID) {
        UserMessageDAO.deleteUser(nameID);
    }

}
