import java.awt.*;

public class PlayerBlack extends Player {
    public PlayerBlack() {
        this.name = "黑方";
        this.color = Color.black;
    }

    int[][] chessPositionArray;

    @Override
    public void writeChess() {
        if (Data.gameEnd==0) {
            // 当前鼠标坐标
            int x = Data.mousePointer.x;
            int y = Data.mousePointer.y;
            // 转换成棋盘坐标
            row = (Util.round(x)) / 40;
            column = (Util.round(y)) / 40;

            // 检查当前位置是否已有棋子
            for (Chess chess : Data.chessArray) {
                if (chess.equal(new Chess(row, column, Color.black))) {
                    return;
                }
            }

            // 向棋盘数组添加棋子
            Data.chessArray.add(new Chess(row, column, Color.black));

            // 根据当前模式交换下棋方
            if (Data.pattern == 0) {
                Data.gameState = 1;
            } else if (Data.pattern == 1) {
                Data.gameState = 2;
            }
        }
        ifWin();

    }

    @Override
    public void repentanceChess() {
        if (Data.chessArray.size() > 2) {
            Data.chessArray.remove(Data.chessArray.size() - 1);
            if (Data.pattern == 0) {
                Data.gameState = 1;
            } else if (Data.pattern == 1) {
                Data.gameState = 2;
            }
        }
    }

    @Override
    public void ifWin() {
        // 游戏结束不判断
        if (Data.gameEnd==0) {
            // 当棋子总数大于等于9时才判断
            if (Data.chessArray.size() >= 1) {
                // 初始化变量
                // 利用Util的静态方法把棋子对象转化为一个二维数组，方便进行胜负判断
                chessPositionArray = Util.getChessPositionArray(Data.chessArray);

                int positionX = row;
                int positionY = column;

                // 其中0 代表空位  1代表黑棋   2代表白棋
                /*    1  2  3  4  5  x →
                  1  [0][0][0][0][0]
                  2  [0][1][1][1][1]
                  3  [0][0][2][2][0]
                  4  [0][2][0][0][0]
                  5  [0][0][0][0][0]
                  y
                  ↓
                 当前进行横向右方判断的方法
                 当黑棋手在（1,2）坐标下棋时(1,2)坐标数字变为1
                 判断坐标（1,2）右边相邻坐标（2,2）的数字是否为1
                 如果为1，计数器加一 再进行对坐标（3,2）的判断
                 如果为2，则结束判断，
                 对计数器的数值进行判断，如果计数器大于等于4 则 相当于横方向有五个相同颜色的棋子相连
                 游戏结束；
                 下面是代码
                 int x = 1;
                 int y = 2;
                 int posX = x;
                 int posY = y;
                 int count = 0;
                while (chessPositionArray[x][y]
                == chessPositionArray[posX + 1][posY]) {
                        count++;
                        posX++;
                    }
                if (count>=4){
                    游戏结束
                }


                需要判断八个方向，如下
                        ↖ ↑ ↗
                        ← 棋 →
                        ↙ ↓ ↘

                 */



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