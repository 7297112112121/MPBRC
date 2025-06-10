package Serve;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Object.User;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/// 数据模型处理
public class ModelProcessServe {
    private  final Logger logger = LogManager.getLogger(ModelProcessServe.class);
    /**
     * 表格添加所有信息
     * @param columnNames 表格列名
     * @param defaultt 表格模型
     * @return void
     * */
    public  void addRow(Object[] columnNames, DefaultTableModel defaultt) {
        //查询所有用户数据
        Collection<User> userMessage = UserMessageManager.upDataUserAllMessageAndQueryAll();
        //将用户数据添加到表格中
        for (User user : userMessage) {
            Object[] rowData = {
                    user.getNameID(),
                    user.getName(),
                    user.getSex(),
                    user.getPhone(),
                    user.getAdminID(),
                    user.getBalance()
            };
            defaultt.addRow(rowData);
        }
    }
    /**
     * 封装：模型数据转换为对象列表
     * @param  defaultt
     * @return List<Object.User>
     * */
    public List<User> createUsersList(DefaultTableModel defaultt) {
        List<List<Object>> dataList = getModelData(defaultt);
        return createUser(dataList);
    }

    /**
     * 获得模型所有数据
     * **/
    public  List<List<Object>> getModelData( DefaultTableModel defaultt) {
        // 获取默认模型
        DefaultTableModel model = defaultt;
        // 获取行数
        int rowCount = model.getRowCount();
        // 获取列数
        int columnCount = model.getColumnCount();
        // 创建数据列表
        List<List<Object>> dataList = new ArrayList<>();

        // 遍历每一行
        for (int row = 0; row < rowCount; row++) {
            // 创建行数据列表
            List<Object> rowData = new ArrayList<>();
            // 遍历每一列
            for (int col = 0; col < columnCount; col++) {
                // 获取单元格数据并添加到行数据列表中
                rowData.add(model.getValueAt(row, col));
            }
            // 将行数据列表添加到数据列表中
            dataList.add(rowData);
        }
        // 返回数据列表
        return dataList;
    }

    /**
     * 二维数据转换为 Object.User 对象列表
     * @param list 表格数据（二维列表）
     * @param object 处理对象类型
     * @return Object.User 对象列表
     */
    public  List<Object> createUser(List<List<Object>> list, String object) {
        List<User> userList = new ArrayList<>();

        // 遍历每一行数据
        for (List<Object> row : list) {
            if (row.size() >= 6) { // 确保有足够的列数据
                try {
                    // 提取每列数据并转换为适当类型
                    Integer nameID = row.get(0) != null ? (Integer) row.get(0) : null;
                    String name = row.get(1) != null ? row.get(1).toString() : null;
                    String sex = row.get(2) != null ? row.get(2).toString() : null;
                    String phone = row.get(3) != null ? row.get(3).toString() : null;
                    Integer adminID = row.get(4) != null ? (Integer) row.get(4) : null;
                    Double balance = row.get(5) != null ? (Double) row.get(5) : null;

                    // 创建 Object.User 对象（根据 Object.User 类的构造函数调整）
                    User user = new User();
                    user.setNameID(nameID);
                    user.setName(name);
                    user.setSex(sex);
                    user.setPhone(phone);
                    user.setAdminID(adminID);
                    user.setBalance(balance);

                    //补充信息

                    userList.add(user);
                } catch (ClassCastException e) {
                    // 处理类型转换异常
                    logger.error("数据类型转换错误: " + e.getMessage());
                } catch (Exception e) {
                    // 处理其他异常
                    logger.error("创建 Object.User 对象失败: " + e.getMessage() + "请检查字段是否相同。");
                }
            }
        }

        return userList;
    }

}
