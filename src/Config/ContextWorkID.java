package Config;

public class ContextWorkID {
    private WorkIDConfig workIDConfig;

    //设置配置
    public void setWorkIDConfig(WorkIDConfig workIDConfig){
        this.workIDConfig = workIDConfig;
    }

    //使用方法
    public void getWorkID(){
        workIDConfig.getWorkID();
    }
}
