package objects;


import data.Game;
import util.Util;

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
        JOptionPane.showMessageDialog(new JFrame(), name + "Ӯ����Ϸ");
        Game.ifGameEnd = true;
    }

    public void ifWin() {
        // ��Ϸ�������ж�
        if (!Game.ifGameEnd) {
            // �������������ڵ���9ʱ���ж�
            if (Game.chessArray.size() >= 1) {
                // ��ʼ������
                int[][] chessPositionArray = Util.getChessPositionArray(Game.chessArray);

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


    public synchronized void winOrLose() {
        if (!Game.ifGameEnd) {
            if (count >= 4) {
                JOptionPane.showMessageDialog(new JFrame(), this.name + "Ӯ����Ϸ");
                Game.ifGameEnd = true;
            }
        }
        count = 0;

    }

}
