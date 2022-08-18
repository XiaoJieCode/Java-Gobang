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
        this.name = "�׷�";
        this.color = Color.white;
    }

    // Ϊ���Է���д���巽��
    @Override
    public void writeChess() {
        // ������Ȩ������ÿ��Ԫ�س�ʼ��Ϊ0
        weightArray = new int[15][15];
        // ת�����ӱ�﷽ʽ�������
        chessPositionArray = Util.getChessPositionArray(Data.chessArray);
        max = -1;
        // �ж�����״��
        chessWeightA();
        // ��������Ȩ��ѡ������λ��
        for (int i = 0; i < weightArray.length; i++) {
            for (int j = 0; j < weightArray[i].length; j++) {
                // ��ȡȨ��ֵ�����û�����ӵ�λ��,
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

        //ÿ�λ�����Ѱ����λ�ã����ֶ�������һ�飨��Ȼ���˺ܶ����ģ���Ϊ�ϴ�����ʱ����Ĵ�඼û�䣩
        //�ȶ���һЩ����
        int humanChessmanNum = 0;//��Ԫ���еĺ�������
        int machineChessmanNum = 0;//��Ԫ���еİ�������
        int tupleScoreTmp = 0;//��Ԫ��÷���ʱ����


        //1.ɨ������15����
        for (int i = 0; i < 15; i++) {
            // ����ÿ����Ԫ��   -4����Ϊ���4���ղ�����Ԫ��
            for (int j = 0; j < 15 - 4; j++) {
                int k = j;
                // ��Ԫ��  ������塢��������
                while (k < j + 5) {
                    if (chessPositionArray[i][k] == -1) {
                        machineChessmanNum++;  // ��������
                    } else if (chessPositionArray[i][k] == 1) {
                        humanChessmanNum++;  // ��������
                    }
                    k++;
                }
                // һ����Ԫ��ĵ÷�
                tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                //Ϊ����Ԫ���ÿ��λ����ӷ���
                for (k = j; k < j + 5; k++) {
                    weightArray[i][k] += tupleScoreTmp;
                }
                //����
                machineChessmanNum = 0;//��Ԫ���еİ�������
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����
            }
        }

        //2.ɨ������15��
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
                //Ϊ����Ԫ���ÿ��λ����ӷ���
                for (k = j; k < j + 5; k++) {
                    weightArray[k][i] += tupleScoreTmp;
                }
                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����
            }
        }

        //3.ɨ�����Ͻǵ����½��ϲಿ��
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
                //ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
                if (m == k - 5) {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //Ϊ����Ԫ���ÿ��λ����ӷ���
                    for (m = k, n = j; m > k - 5; m--, n++) {
                        weightArray[m][n] += tupleScoreTmp;
                    }
                }

                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����

            }
        }

        //4.ɨ�����Ͻǵ����½��²ಿ��
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
                //ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //Ϊ����Ԫ���ÿ��λ����ӷ���
                    for (m = k, n = j; m < k + 5; m++, n--) {
                        weightArray[n][m] += tupleScoreTmp;
                    }
                }
                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����

            }
        }

        //5.ɨ�����Ͻǵ����½��ϲಿ��
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
                //ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //Ϊ����Ԫ���ÿ��λ����ӷ���
                    for (m = k, n = j; m < k + 5; m++, n++) {
                        weightArray[m][n] += tupleScoreTmp;
                    }
                }

                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����

            }
        }

        //6.ɨ�����Ͻǵ����½��²ಿ��
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
                //ע��б���жϵ�ʱ�򣬿��ܹ�������Ԫ�飨�����ĸ����䣩�������������Ҫ���Ե�
                if (m == k + 5) {
                    tupleScoreTmp = tupleScore(humanChessmanNum, machineChessmanNum);
                    //Ϊ����Ԫ���ÿ��λ����ӷ���
                    for (m = k, n = j; m < k + 5; m++, n++) {
                        weightArray[n][m] += tupleScoreTmp;
                    }
                }

                //����
                humanChessmanNum = 0;//��Ԫ���еĺ�������
                machineChessmanNum = 0;//��Ԫ���еİ�������
                tupleScoreTmp = 0;//��Ԫ��÷���ʱ����

            }
        }
        Data.weightArray = weightArray;
    }

    public int tupleScore(int humanChessmanNum, int machineChessmanNum) {
        //1.�����������ӣ����л������ӣ��з�Ϊ0
        if (humanChessmanNum > 0 && machineChessmanNum > 0) {
            return 0;
        }
        //2.ȫ��Ϊ�գ�û�����ӣ��з�Ϊ7
        if (humanChessmanNum == 0 && machineChessmanNum == 0) {
            return 7;
        }
        //3.������1�ӣ��з�Ϊ35
        if (machineChessmanNum == 1) {
            return 35;
        }
        //4.������2�ӣ��з�Ϊ800
        if (machineChessmanNum == 2) {
            return 800;
        }
        //5.������3�ӣ��з�Ϊ15000
        if (machineChessmanNum == 3) {
            return 15000;
        }
        //6.������4�ӣ��з�Ϊ800000
        if (machineChessmanNum == 4) {
            return 800000;
        }
        //7.������1�ӣ��з�Ϊ15
        if (humanChessmanNum == 1) {
            return 15;
        }
        //8.������2�ӣ��з�Ϊ400
        if (humanChessmanNum == 2) {
            return 400;
        }
        //9.������3�ӣ��з�Ϊ1800
        if (humanChessmanNum == 3) {
            return 1800;
        }
        //10.������4�ӣ��з�Ϊ10+
        //
        // 0000
        if (humanChessmanNum == 4) {
            return 100000;
        }
        return -1;//������������϶������ˡ����д������������ִ��
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
        // ��Ϸ�������ж�
        if (Data.gameEnd == 0) {
            // �������������ڵ���9ʱ���ж�
            if (Data.chessArray.size() >= 1) {
                // ��ʼ������


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

    @Override
    public void winOrLose() {
        super.winOrLose();
    }
}