package Serve;

import DAO.SaveDAO;

import java.util.List;

public class ClassifyServe {
    public List<String> deuceDeviceID (int start, int end) {
        if (start < 0) {
            throw new RuntimeException("开头数字不能小于0");
        }
        List<List<String>> deviceList = SaveDAO.getDeviceList();
        for(List<String> device : deviceList) {

        }
        return null;
    }
}
