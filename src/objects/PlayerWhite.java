package objects;


import data.Game;
import util.Util;

import java.awt.*;

public class PlayerWhite extends Player {
    int[][] chessPositionArray = new int[15][15];

    public PlayerWhite() {
        this.name = "白方";
        this.color = Color.white;
    }

    @Override
    public void repentanceChess() {
        if (Game.chessArray.size() > 2) {
            Game.chessArray.remove(Game.chessArray.size() - 1);
            if (Game.pattern == 0) {
                Game.gameState = 0;
            } else if (Game.pattern == 1) {
                Game.gameState = 2;
            }
        }
    }

    @Override
    public void writeChess() {
        if (!Game.ifGameEnd) {
            // 当前鼠标坐标
            int x = Game.mousePointer.x;
            int y = Game.mousePointer.y;
            // 转换成棋盘坐标
            row = (Util.round(x)) / 40;
            column = (Util.round(y)) / 40;
            // 检查当前位置是否已有棋子
            for (Chess chess : Game.chessArray) {
                if (chess.equal(new Chess(row, column, color))) {
                    return;
                }
            }

            // 向棋盘数组添加棋子
            Game.chessArray.add(new Chess(row, column, color));
            chessPositionArray[row][column] = -1;

            // 根据当前模式交换下棋方
            if (Game.pattern == 0) {
                Game.gameState = 0;
            }

            ifWin();
        }
    }

    @Override
    public void ifWin() {
        // 游戏结束不判断
        if (!Game.ifGameEnd) {
            // 当棋子总数大于等于9时才判断
            if (Game.chessArray.size() >= 1) {
                // 初始化变量
                chessPositionArray = Util.getChessPositionArray(Game.chessArray);

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
