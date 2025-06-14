package Util.auth.workID;

public class ContextWorkID {
    private WorkIDConfig workIDConfig;

    //设置配置
    public void setWorkIDConfig(WorkIDConfig workIDConfig){
        this.workIDConfig = workIDConfig;
    }

    //使用方法
    public String getWorkID(){
        return workIDConfig.getWorkID();
    }

    public String setWorkIDText() {
        return workIDConfig.setWorkIDText();
    }
}
