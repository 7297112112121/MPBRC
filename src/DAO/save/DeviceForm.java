package DAO.save;

import DAO.save.DeviceFieldEnum;
import DAO.save.tool.FormADO;

import java.util.ArrayList;
import java.util.List;

public class DeviceForm {
    //单例模式实现
    private static DeviceForm instance = new DeviceForm();
    private List<List<String>> deviceDataList = new ArrayList<>();   //设备表数据

    // 私有构造函数防止外部实例化
    private DeviceForm() {}

    /**
     * 加载所有表单数据并保存到deviceDataList
     * 第一个参数为储存的二维列表，第二个参数为枚举类型
     */
    public void loadAllFormData() {
        deviceDataList = FormADO.loadDeviceForm(deviceDataList, DeviceFieldEnum.class);
    }

    public List<List<String>> getDeviceDataList() {
        return deviceDataList;
    }

    public static DeviceForm getInstance() {
        return instance;
    }
}