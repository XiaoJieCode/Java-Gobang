import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Data {
    public static final String[] messageArray = {"开始新游戏", "悔棋", "认输", "对战模式：", "人人对战", "人机模式"};
    public static int pattern = 1;    //记录选择的模式 0为人人对战 1为人机对战
    // 是否第一次游戏
    public static int ifFirstGame = 0;
    // 当前哪方下棋, 0为黑方  1 为白方  2为电脑
    public static int gameState = 0;

    // 是否显示权值分数  0 不显示， 1 显示【、

    public static int ifShowWeightChess = 1;

    // 权值分数列表
    public static int[][] weightArray = new int[15][15];

    // 记录电脑下的棋子
    public static int lastChessPositionAIX = -1;
    public static int lastChessPositionAIY = -1;
    // 棋子动态数组
    public static ArrayList<Chess> chessArray = new ArrayList<>();
    // 是否以决出胜负
    public static int gameEnd = 0;

    public static SaverAndLoader saverAndLoader;
    // 操作模式 0 为鼠标操作   1为键盘操作
    public static Point mousePointer = new Point(300, 300);
    public static Panel panel;
    public static Menu menu;
    public static JFrame frame;

    public static PlayerComputer playerComputer;
}
