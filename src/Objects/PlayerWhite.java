package Objects;

import java.awt.*;
import Data.Data;
import Util.Util;
public class PlayerWhite extends Player {
    int[][] chessPositionArray = new int[15][15];

    public PlayerWhite() {
        this.name = "白方";
        this.color = Color.white;
    }

    @Override
    public void repentanceChess() {
        if (Data.chessArray.size() > 2) {
            Data.chessArray.remove(Data.chessArray.size() - 1);
            if (Data.pattern == 0) {
                Data.gameState = 0;
            } else if (Data.pattern == 1) {
                Data.gameState = 2;
            }
        }
    }

    @Override
    public void writeChess() {
        if (Data.gameEnd == 0) {
            // 当前鼠标坐标
            int x = Data.mousePointer.x;
            int y = Data.mousePointer.y;
            // 转换成棋盘坐标
            row = (Util.round(x)) / 40;
            column = (Util.round(y)) / 40;
            // 检查当前位置是否已有棋子
            for (Chess chess : Data.chessArray) {
                if (chess.equal(new Chess(row, column, color))) {
                    return;
                }
            }

            // 向棋盘数组添加棋子
            Data.chessArray.add(new Chess(row, column, color));
            chessPositionArray[row][column] = -1;

            // 根据当前模式交换下棋方
            if (Data.pattern == 0) {
                Data.gameState = 0;
            }

            ifWin();
        }
    }

    @Override
    public void ifWin() {
        // 游戏结束不判断
        if (Data.gameEnd == 0) {
            // 当棋子总数大于等于9时才判断
            if (Data.chessArray.size() >= 1) {
                // 初始化变量
                chessPositionArray = Util.getChessPositionArray(Data.chessArray);

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
}
