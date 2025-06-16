package Util.factory;

// 按钮组件（示例）
public class Button implements Component {
    private String name;        //按钮名称

    public Button(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}


