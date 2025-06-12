package Util;

public class RandNumber {
    /**
     * 生成指定位数的随机数
     * @param digits 随机数的位数
     * @return 指定位数的随机数
     */
    public static int generate(int digits) {
        if (digits <= 0) {
            throw new IllegalArgumentException("位数必须大于0");
        }

        // 计算最小和最大值
        int min = (int) Math.pow(10, digits - 1);
        int max = (int) Math.pow(10, digits) - 1;

        // 生成指定范围内的随机数
        return min + (int) (Math.random() * (max - min + 1));
    }

}

