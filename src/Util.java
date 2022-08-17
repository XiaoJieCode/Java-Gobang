import java.awt.*;
import java.util.ArrayList;

public class Util {
    public static int[][] getChessPositionArray(ArrayList<Chess> chess) {
        int[][] chessPositionArray = new int[15][15];
        for (Chess chess1 : chess) {
            chessPositionArray[chess1.row][chess1.column] = chess1.getColor();
        }
        return chessPositionArray;
    }

    public static int round(int a) {
        int result = 0;
        if (a > 595) {
            return 580;
        } else if (a < 0) {
            return 0;
        }
        for (int i = 0; i < 800; i = i + 40) {
            if (i <= a && a <= i + 40) {
                return i + 20;
            }
        }
        return a;
    }

    public static void newGame() {
        Data.chessArray.clear();
        Data.gameEnd = 0;
        Data.gameState = 0;
    }

    public static Point getPointer() {
        return Data.mousePointer;
    }

    public static Point getPointerAtChessboard() {
        Point point = new Point(300, 300);
        point.x = round(getPointer().x) / 40 - 1;
        point.y = round(getPointer().y) / 40 - 1;
        return point;
    }
}
