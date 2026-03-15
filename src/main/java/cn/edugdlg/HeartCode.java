package cn.edugdlg;

/**
 * 终端爱心代码：运行后会打印一个由字符组成的爱心。
 */
public class HeartCode {
    public static void main(String[] args) {
        printHeart();
        System.out.println("\n送你一颗小心心 ❤️");
    }

    private static void printHeart() {
        for (double y = 1.5; y > -1.5; y -= 0.1) {
            StringBuilder line = new StringBuilder();
            for (double x = -1.5; x < 1.5; x += 0.05) {
                double equation = Math.pow(x * x + y * y - 1, 3) - x * x * Math.pow(y, 3);
                line.append(equation <= 0 ? "❤" : " ");
            }
            System.out.println(line);
        }
    }
}
