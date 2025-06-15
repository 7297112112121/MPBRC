package Serve.auth.workID;

public class WorkID_WORK_ID implements WorkIDConfig {
    String WORK_ID = "\\d{10}";
    @Override
    public String getWorkID() {
        return WORK_ID;
    }

    @Override
    public String setWorkIDText() {
        return "请输入10位的工号" ;
    }
}

