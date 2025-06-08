package global.view_tool;

/**
 * 窗口居中
 * */
public class JFrameLayoutCenter implements CreateLayout {
    /**
     * 在主窗口位置居中
     * */
    //x轴上剧中
    public static int setCenter_X(int width) {
        //主窗口x轴坐标 + （主窗口宽度/2 - width窗口的宽度/2 ）
        return X_USER + (WIDTH_USER / 2 - width / 2);
    }

    public static int setCenter_Y(int height) {
        return Y_USER + (HEIGHT_USER / 2 - height / 2);
    }

}
