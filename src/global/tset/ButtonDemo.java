package global.tset;

// 按钮点击处理策略接口
interface ClickStrategy {
    void onClick();
}

// 登录按钮策略
class LoginClickStrategy implements ClickStrategy {
    @Override
    public void onClick() {
        System.out.println("执行登录逻辑：验证用户名密码...");
    }
}

// 注册按钮策略
class RegisterClickStrategy implements ClickStrategy {
    @Override
    public void onClick() {
        System.out.println("执行注册逻辑：打开注册表单...");
    }
}

// 退出按钮策略
class ExitClickStrategy implements ClickStrategy {
    @Override
    public void onClick() {
        System.out.println("执行退出逻辑：保存数据并关闭应用...");
    }
}

class Button {
    private String label;
    private ClickStrategy clickStrategy;

    public Button(String label) {
        this.label = label;
    }

    // 设置点击策略
    public void setClickStrategy(ClickStrategy strategy) {
        this.clickStrategy = strategy;
    }

    // 模拟按钮点击
    public void click() {
        System.out.println("[" + label + "] 被点击");
        if (clickStrategy != null) {
            clickStrategy.onClick();
        }
    }
}

public class ButtonDemo {
    public static void main(String[] args) {
        // 创建按钮
        Button loginBtn = new Button("登录");
        Button registerBtn = new Button("注册");
        Button exitBtn = new Button("退出");

        // 设置不同的点击策略
        loginBtn.setClickStrategy(new LoginClickStrategy());
        registerBtn.setClickStrategy(new RegisterClickStrategy());
        exitBtn.setClickStrategy(new ExitClickStrategy());

        // 模拟用户点击
        loginBtn.click();
        System.out.println("---------------");
        registerBtn.click();
        System.out.println("---------------");
        exitBtn.click();
    }
}
