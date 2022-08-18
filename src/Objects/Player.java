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
        JOptionPane.showMessageDialog(Data.panel, name + "Ӯ����Ϸ");
        Data.gameEnd = 1;
    }

    public void ifWin() {
        // ��Ϸ�������ж�
        if (Data.gameEnd == 0) {
            // �������������ڵ���9ʱ���ж�
            if (Data.chessArray.size() >= 1) {
                // ��ʼ������
                int[][] chessPositionArray = Util.getChessPositionArray(Data.chessArray);

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


    public void winOrLose() {
        if (Data.gameEnd == 0) {
            if (count >= 4) {
                JOptionPane.showMessageDialog(Data.frame, this.name + "Ӯ����Ϸ");
                Data.gameEnd = 1;
            }
        }
        count = 0;

    }

}
