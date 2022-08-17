import java.awt.*;

public class PlayerBlack extends Player {
    public PlayerBlack() {
        this.name = "�ڷ�";
        this.color = Color.black;
    }

    int[][] chessPositionArray;

    @Override
    public void writeChess() {
        if (Data.gameEnd==0) {
            // ��ǰ�������
            int x = Data.mousePointer.x;
            int y = Data.mousePointer.y;
            // ת������������
            row = (Util.round(x)) / 40;
            column = (Util.round(y)) / 40;

            // ��鵱ǰλ���Ƿ���������
            for (Chess chess : Data.chessArray) {
                if (chess.equal(new Chess(row, column, Color.black))) {
                    return;
                }
            }

            // �����������������
            Data.chessArray.add(new Chess(row, column, Color.black));

            // ���ݵ�ǰģʽ�������巽
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
        // ��Ϸ�������ж�
        if (Data.gameEnd==0) {
            // �������������ڵ���9ʱ���ж�
            if (Data.chessArray.size() >= 1) {
                // ��ʼ������
                // ����Util�ľ�̬���������Ӷ���ת��Ϊһ����ά���飬�������ʤ���ж�
                chessPositionArray = Util.getChessPositionArray(Data.chessArray);

                int positionX = row;
                int positionY = column;

                // ����0 �����λ  1�������   2�������
                /*    1  2  3  4  5  x ��
                  1  [0][0][0][0][0]
                  2  [0][1][1][1][1]
                  3  [0][0][2][2][0]
                  4  [0][2][0][0][0]
                  5  [0][0][0][0][0]
                  y
                  ��
                 ��ǰ���к����ҷ��жϵķ���
                 ���������ڣ�1,2����������ʱ(1,2)�������ֱ�Ϊ1
                 �ж����꣨1,2���ұ��������꣨2,2���������Ƿ�Ϊ1
                 ���Ϊ1����������һ �ٽ��ж����꣨3,2�����ж�
                 ���Ϊ2��������жϣ�
                 �Լ���������ֵ�����жϣ�������������ڵ���4 �� �൱�ں᷽���������ͬ��ɫ����������
                 ��Ϸ������
                 �����Ǵ���
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
                    ��Ϸ����
                }


                ��Ҫ�жϰ˸���������
                        �I �� �J
                        �� �� ��
                        �L �� �K

                 */



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