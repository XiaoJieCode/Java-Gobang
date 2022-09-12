package data;

import objects.Chess;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public static final int COMPUTER = 2;
    public static final int ALL_WAR = 0;
    public static final int WAR_MACHINE = 1;
    public static final int NET_WAR = 2;


    public static int pattern = Config.pattern;

    // 当前哪方下棋, 0为黑方  1 为白方  2为电脑
    public static int gameState = BLACK;

    // 权值分数列表
    public static int[][] weightArray = new int[15][15];

    // 记录电脑方下的棋子位置
    public static Point lastChessPosComputer = null;

    // 棋子数组
    public static ArrayList<Chess> chessArray = new ArrayList<>();

    // 是否以决出胜负
    public static boolean ifGameEnd = true;

    // 游戏鼠标位置
    public static Point mousePointer = new Point(300, 300);

    // 当前主机是否是服务器
    public static boolean isServer = false;


    public synchronized static void newGame() {
        chessArray.clear();
        weightArray = new int[15][15];
        ifGameEnd = false;
        lastChessPosComputer = new Point(-1, -1);
        gameState = BLACK;
        mousePointer = new Point(300, 300);
    }

    public static HashMap<String, Object> getGame(){
        HashMap<String, Object> game = new HashMap<>();
        game.put("pattern", pattern);
        game.put("gameState", gameState);
        game.put("weightArray", weightArray);
        game.put("lastChessPosComputer", lastChessPosComputer);
        game.put("chessArray", chessArray);
        game.put("ifGameEnd", ifGameEnd);
        game.put("mousePointer", mousePointer);
        return game;
    }

    public static void initGame(HashMap<String, Object> game){
        pattern = (int) game.get("pattern");
        gameState = (int) game.get("gameState");
        weightArray = (int[][]) game.get("weightArray");
        lastChessPosComputer = (Point) game.get("lastChessPosComputer");
        chessArray = (ArrayList<Chess>) game.get("chessArray");
        ifGameEnd = (boolean) game.get("ifGameEnd");
        mousePointer = (Point) game.get("mousePointer");

    }


}