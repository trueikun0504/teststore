package cn.edugdlg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 终端爱心代码（支持撤回/重做）。
 */
public class HeartCode {
    private static final String HEART_ART = buildHeart();

    public static void main(String[] args) {
        System.out.println("爱心编辑器启动：输入命令 heart/msg/undo/redo/show/quit");

        List<String> timeline = new ArrayList<>();
        int cursor = 0; // [0, timeline.size()]，表示当前生效到哪一步

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String command = scanner.nextLine().trim().toLowerCase();
                switch (command) {
                    case "heart":
                        cursor = appendAction(timeline, cursor, HEART_ART);
                        render(timeline, cursor);
                        break;
                    case "msg":
                        cursor = appendAction(timeline, cursor, "送你一颗小心心 ❤️");
                        render(timeline, cursor);
                        break;
                    case "undo":
                        if (cursor == 0) {
                            System.out.println("没有可撤回的操作。\n");
                        } else {
                            cursor--;
                            render(timeline, cursor);
                        }
                        break;
                    case "redo":
                        if (cursor == timeline.size()) {
                            System.out.println("没有可重做的操作。\n");
                        } else {
                            cursor++;
                            render(timeline, cursor);
                        }
                        break;
                    case "show":
                        render(timeline, cursor);
                        break;
                    case "quit":
                        System.out.println("已退出，拜拜～");
                        return;
                    case "":
                        break;
                    default:
                        System.out.println("未知命令，请使用：heart/msg/undo/redo/show/quit\n");
                        break;
                }
            }
        }
    }

    private static int appendAction(List<String> timeline, int cursor, String content) {
        while (timeline.size() > cursor) {
            timeline.remove(timeline.size() - 1);
        }
        timeline.add(content);
        return cursor + 1;
    }

    private static void render(List<String> timeline, int cursor) {
        System.out.println("\n----- 当前画布 -----");
        if (cursor == 0) {
            System.out.println("(空)");
        } else {
            for (int i = 0; i < cursor; i++) {
                System.out.println(timeline.get(i));
            }
        }
        System.out.printf("----- 操作进度：%d/%d -----%n%n", cursor, timeline.size());
    }

    private static String buildHeart() {
        StringBuilder heart = new StringBuilder();
        for (double y = 1.5; y > -1.5; y -= 0.1) {
            StringBuilder line = new StringBuilder();
            for (double x = -1.5; x < 1.5; x += 0.05) {
                double equation = Math.pow(x * x + y * y - 1, 3) - x * x * Math.pow(y, 3);
                line.append(equation <= 0 ? "❤" : " ");
            }
            heart.append(line).append(System.lineSeparator());
        }
        return heart.toString();
    }
}
