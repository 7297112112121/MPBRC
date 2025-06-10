package DAO;

import DAO.form.FormADO;
import DAO.form.useField.DeviceUseField;
import DAO.form.useField.UseFieldContext;

import java.util.ArrayList;
import java.util.List;

public class SaveDAO {
    private static UseFieldContext context = new UseFieldContext();

    private static List<List<String>> deviceList = new ArrayList<>();   //设备id
    private static List<String> deviceField = new ArrayList<>();        //全部字段
    private static List<String> deviceUseField = new ArrayList<>();


    //加载所有表单并保存
    public static void loadAllFormData() {
        deviceList = FormADO.loadDeviceForm(deviceList, deviceField, new DeviceUseField() );
        context.setUseField(new DeviceUseField());
        deviceUseField = context.getUseField();
    }

    //获得
    public static List<List<String>> getDeviceList() {
        return deviceList;
    }
}
