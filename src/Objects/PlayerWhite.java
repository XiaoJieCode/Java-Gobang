package Objects;

import java.awt.*;
import Data.Data;
import Util.Util;
public class PlayerWhite extends Player {
    int[][] chessPositionArray = new int[15][15];

    public PlayerWhite() {
        this.name = "�׷�";
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
            // ��ǰ�������
            int x = Data.mousePointer.x;
            int y = Data.mousePointer.y;
            // ת������������
            row = (Util.round(x)) / 40;
            column = (Util.round(y)) / 40;
            // ��鵱ǰλ���Ƿ���������
            for (Chess chess : Data.chessArray) {
                if (chess.equal(new Chess(row, column, color))) {
                    return;
                }
            }

            // �����������������
            Data.chessArray.add(new Chess(row, column, color));
            chessPositionArray[row][column] = -1;

            // ���ݵ�ǰģʽ�������巽
            if (Data.pattern == 0) {
                Data.gameState = 0;
            }

            ifWin();
        }
    }

    @Override
    public void ifWin() {
        // ��Ϸ�������ж�
        if (Data.gameEnd == 0) {
            // �������������ڵ���9ʱ���ж�
            if (Data.chessArray.size() >= 1) {
                // ��ʼ������
                chessPositionArray = Util.getChessPositionArray(Data.chessArray);

                int positionX = row;
                int positionY = column;

                // ���ͬһ���ҷ����Ƿ�����ͬ��ɫ����
                if (!(positionX == 14)) {
                    while (chessPositionArray[row][column] == chessPositionArray[positionX + 1][positionY]) {

                        count++;
                        positionX++;
                        if (positionX == 14) {
                            break;
                        }
                    }
                }
                // ���ͬһ������

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


                // ���ͬһ���·���
                if (!(positionY == 14)) {
                    while (chessPositionArray[row][column] == chessPositionArray[positionX][positionY + 1]) {
                        count++;
                        positionY++;
                        if (positionY == 14) {
                            break;
                        }
                    }
                }

                // ���ͬһ���Ϸ���
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
                // ������·���
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

                // ��鷽���Ϸ���
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


                // ������Ϸ���
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


                // ������·���
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
