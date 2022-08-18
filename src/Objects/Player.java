package Objects;

import Data.Data;
import Util.Util;

import javax.swing.*;
import java.awt.*;

public class Player {
    public String name;
    public Color color;
    public int count;
    public int row;
    public int column;

    public Player() {
    }

    public void writeChess() {

    }

    public void repentanceChess() {

    }

    public void surrender() {
        JOptionPane.showMessageDialog(Data.panel, name + "赢得游戏");
        Data.gameEnd = 1;
    }

    public void ifWin() {
        // 游戏结束不判断
        if (Data.gameEnd == 0) {
            // 当棋子总数大于等于9时才判断
            if (Data.chessArray.size() >= 1) {
                // 初始化变量
                int[][] chessPositionArray = Util.getChessPositionArray(Data.chessArray);

                int positionX = row;
                int positionY = column;

                // 检查同一行右方向是否有相同颜色棋子
                if (!(positionX == 14)) {
                    while (chessPositionArray[row][column] == chessPositionArray[positionX + 1][positionY]) {
                        count++;
                        positionX++;
                        if (positionX == 14) {
                            break;
                        }
                    }
                }
                // 检查同一行左方向

                positionX = row;
                if (!(positionX == 0)) {
                    while (chessPositionArray[row][column] == chessPositionArray[positionX - 1][positionY]) {
                        count++;
                        positionX--;
                        if (positionX == 0) {
                            break;
                        }
                    }
                }
                winOrLose();
                count = 0;


                // 检查同一列下方向
                if (!(positionY == 14)) {
                    while (chessPositionArray[row][column] == chessPositionArray[positionX][positionY + 1]) {
                        count++;
                        positionY++;
                        if (positionY == 14) {
                            break;
                        }
                    }
                }

                // 检查同一列上方向
                positionY = column;
                if (!(positionY == 0)) {
                    while (chessPositionArray[row][column] == chessPositionArray[row][positionY - 1]) {
                        count++;
                        positionY--;
                        if (positionY == 0) {
                            break;
                        }
                    }
                }

                winOrLose();
                count = 0;
                // 检查右下方向
                positionX = row;
                positionY = column;
                if (!(positionX == 14 || positionY == 14))
                    while (chessPositionArray[row][column] == chessPositionArray[positionX + 1][positionY + 1]) {
                        count++;
                        positionX++;
                        positionY++;
                        if (positionX == 14 || positionY == 14) {
                            break;
                        }
                    }

                // 检查方左上方向
                positionX = row;
                positionY = column;
                if (!(positionX == 0 || positionY == 0)) {
                    while (chessPositionArray[row][column] == chessPositionArray[positionX - 1][positionY - 1]) {
                        count++;
                        positionX--;
                        positionY--;
                        if (positionX == 0 || positionY == 0) {
                            break;
                        }
                    }
                }
                winOrLose();
                count = 0;


                // 检查右上方向
                positionX = row;
                positionY = column;
                if (!(positionX == 14 || positionY == 0)) {
                    while (chessPositionArray[row][column] == chessPositionArray[positionX + 1][positionY - 1]) {
                        count++;
                        positionX++;
                        positionY--;
                        if (positionX == 14 || positionY == 0) {
                            break;
                        }
                    }
                }


                // 检查左下方向
                positionX = row;
                positionY = column;
                if (!(positionX == 0 || positionY == 14)) {
                    while (chessPositionArray[row][column] == chessPositionArray[positionX - 1][positionY + 1]) {
                        count++;
                        positionX--;
                        positionY++;
                        if (positionX == 0 || positionY == 14) {
                            break;
                        }
                    }
                }
                winOrLose();
            }
        }
    }


    public void winOrLose() {
        if (Data.gameEnd == 0) {
            if (count >= 4) {
                JOptionPane.showMessageDialog(Data.frame, this.name + "赢得游戏");
                Data.gameEnd = 1;
            }
        }
        count = 0;

    }

}
