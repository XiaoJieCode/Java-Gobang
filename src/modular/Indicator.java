package modular;

import data.Game;
import util.Util;

import java.awt.*;

public class Indicator {
    public static void drawIndicator(Graphics g) {

        drawIndicatorUser(g);
        drawIndicatorAI(g);
    }

    public static void drawIndicatorUser(Graphics g1) {

        int x = Util.getPointerAtChessboard().x * 40 + 60;
        int y = Util.getPointerAtChessboard().y * 40 + 60;

        g1.setColor(Color.red);
        Graphics2D g = (Graphics2D) g1;
        g.setStroke(new BasicStroke(1.2f));
        g.drawLine(x - 15, y - 15, x - 5, y - 15);
        g.drawLine(x - 15, y - 15, x - 15, y - 5);

        g.drawLine(x + 15, y - 15, x + 5, y - 15);
        g.drawLine(x + 15, y - 15, x + 15, y - 5);

        g.drawLine(x - 15, y + 15, x - 15, y + 5);
        g.drawLine(x - 15, y + 15, x - 5, y + 15);

        g.drawLine(x + 15, y + 15, x + 15, y + 5);
        g.drawLine(x + 15, y + 15, x + 5, y + 15);

    }

    public static void drawIndicatorAI(Graphics g) {
        if (Game.pattern == Game.WAR_MACHINE && Game.chessArray.size() > 1) {
            if (Game.lastChessPosComputer == null) {
                return;
            }

            int x;
            int y;

            x = Game.chessArray.get(Game.chessArray.size() - 1).row;
            y = Game.chessArray.get(Game.chessArray.size() - 1).column;

            Game.lastChessPosComputer = new Point(x, y);

            x = x * 40 - 24;
            y = y * 40 - 24;

            g.setColor(Color.red);
            g.fillArc(x + 40, y + 40, 8, 8, 0, 360);

        }
    }

}
