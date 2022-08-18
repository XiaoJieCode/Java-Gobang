package objects;

import data.Data;
import util.Util;

import java.awt.*;

public class PlayerComputer extends Player {
    public int row = 0;
    public int column = 0;
    public int[][] chessPositionArray = new int[15][15];
    int[][] weightArray = new int[15][15];
    int max;

    public PlayerComputer() {
        this.name = "白方";
        this.color = Color.white;
    }

    // 为电脑方重写下棋方法
    @Override
    public void writeChess() {
        // 将棋盘权重数组每个元素初始化为0
        weightArray = new int[15][15];
        // 转换棋子表达方式方便计算
        chessPositionArray = Util.getChessPositionArray(Data.chessArray);
        max = -1;
        // 判断棋盘状况
        chessWeightA();
        // 根据棋盘权重选择下棋位置
        for (int i = 0; i < weightArray.length; i++) {
            for (int j = 0; j < weightArray[i].length; j++) {
                // 获取权重值最大且没有棋子的位置,
                if (weightArray[i][j] > max && chessPositionArray[i][j] == 0) {
                    max = weightArray[i][j];
                    row = i;
                    column = j;

                }
            }
        }
        for (Chess chess : Data.chessArray) {
            if (chess.equal(new Chess(row, column, Color.white))) {
                return;
            }
        }
        Data.chessArray.add(new Chess(row, column, Color.white));
        Data.lastChessPositionAIX = row;
        Data.lastChessPositionAIY = column;
        chessPositionArray[row][column] = -1;
        Data.gameState = 0;
        ifWin();

    }

    public void chessWeightA() {

        //每次机器找寻落子位置，评分都重新算一遍（虽然算了很多多余的，因为上次落子时候算的大多都没变）
        //先定义一些变量
        int humanChessmanNum = 0;//五元组中的黑棋数量
        int machineChessmanNum = 0;//五元组中的白棋数量
        int tupleScoreTmp = 0;//五元组得分临时变量


        //1.扫描横向的15个行
        for (int i = 0; i < 15; i++) {
            // 遍历每个五元组   -4是因为最后4个凑不出五元组
            for (int j = 0; j < 15 - 4; j++) {
                int k = j;
                // 五元组  算出白棋、黑棋数量
                while (k < j + 5) {
                    if (chessPositionArray[i][k] == -1) {
                        machineChessmanNum++;  // 白棋数量
                    } else if (chessPositionArray[i][k] == 1) {
                        humanChessmanNum++;  // 黑棋数量
                    }
                    k++;
                }
                // 一个五元组的得分
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for (k = j; k < j + 5; k++) {
                    weightArray[i][k] += tupleScoreTmp;
                }
                //置零
                machineChessmanNum = 0;//五元组中的白棋数量
                humanChessmanNum = 0;//五元组中的黑棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //2.扫描纵向15行
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15 - 4; j++) {
                int k = j;
                while (k < j + 5) {
                    if (chessPositionArray[k][i] == -1) {
                        machineChessmanNum++;
                    } else if (chessPositionArray[k][i] == 1) {
                        humanChessmanNum++;
                    }
                    k++;
                }
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for (k = j; k < j + 5; k++) {
                    weightArray[k][i] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //3.扫描右上角到左下角上侧部分
        for (int i = 14; i >= 4; i--) {
            for (int k = i, j = 0; j < 15 && k >= 0; j++, k--) {
                int m = k;
                int n = j;
                while (m > k - 5 && k - 5 >= -1) {
                    if (chessPositionArray[m][n] == -1) {
                        machineChessmanNum++;
                    } else if (chessPositionArray[m][n] == 1) {
                        humanChessmanNum++;
                    }

                    m--;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k - 5) {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m > k - 5; m--, n++) {
                        weightArray[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //4.扫描右上角到左下角下侧部分
        for (int i = 1; i < 15; i++) {
            for (int k = i, j = 14; j >= 0 && k < 15; j--, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chessPositionArray[n][m] == -1) {
                        machineChessmanNum++;
                    } else if (chessPositionArray[n][m] == 1) {
                        humanChessmanNum++;
                    }

                    m++;
                    n--;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n--) {
                        weightArray[n][m] += tupleScoreTmp;
                    }
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //5.扫描左上角到右下角上侧部分
        for (int i = 0; i < 11; i++) {
            for (int k = i, j = 0; j < 15 && k < 15; j++, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chessPositionArray[m][n] == -1) {
                        machineChessmanNum++;
                    } else if (chessPositionArray[m][n] == 1) {
                        humanChessmanNum++;
                    }

                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n++) {
                        weightArray[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //6.扫描左上角到右下角下侧部分
        for (int i = 1; i < 11; i++) {
            for (int k = i, j = 0; j < 15 && k < 15; j++, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chessPositionArray[n][m] == -1) {
                        machineChessmanNum++;
                    } else if (chessPositionArray[n][m] == 1) {
                        humanChessmanNum++;
                    }

                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n++) {
                        weightArray[n][m] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }
        Data.weightArray = weightArray;
    }

    public int tupleScore(int humanChessmanNum, int machineChessmanNum) {
        //1.既有人类落子，又有机器落子，判分为0
        if (humanChessmanNum > 0 && machineChessmanNum > 0) {
            return 0;
        }
        //2.全部为空，没有落子，判分为7
        if (humanChessmanNum == 0 && machineChessmanNum == 0) {
            return 7;
        }
        //3.机器落1子，判分为35
        if (machineChessmanNum == 1) {
            return 35;
        }
        //4.机器落2子，判分为800
        if (machineChessmanNum == 2) {
            return 800;
        }
        //5.机器落3子，判分为15000
        if (machineChessmanNum == 3) {
            return 15000;
        }
        //6.机器落4子，判分为800000
        if (machineChessmanNum == 4) {
            return 800000;
        }
        //7.人类落1子，判分为15
        if (humanChessmanNum == 1) {
            return 15;
        }
        //8.人类落2子，判分为400
        if (humanChessmanNum == 2) {
            return 400;
        }
        //9.人类落3子，判分为1800
        if (humanChessmanNum == 3) {
            return 1800;
        }
        //10.人类落4子，判分为10+
        //
        // 0000
        if (humanChessmanNum == 4) {
            return 100000;
        }
        return -1;//若是其他结果肯定出错了。这行代码根本不可能执行
    }

    @Override
    public void repentanceChess() {
        if (Data.chessArray.size() > 2) {
            Data.chessArray.remove(Data.chessArray.size() - 1);
            Data.gameState = 0;
        }
    }

    @Override
    public void ifWin() {
        // 游戏结束不判断
        if (Data.gameEnd == 0) {
            // 当棋子总数大于等于9时才判断
            if (Data.chessArray.size() >= 1) {
                // 初始化变量


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

    @Override
    public void winOrLose() {
        super.winOrLose();
    }
}