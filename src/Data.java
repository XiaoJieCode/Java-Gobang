import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Data {
    public static final String[] messageArray = {"��ʼ����Ϸ", "����", "����", "��սģʽ��", "���˶�ս", "�˻�ģʽ"};
    public static int pattern = 1;    //��¼ѡ���ģʽ 0Ϊ���˶�ս 1Ϊ�˻���ս
    // �Ƿ��һ����Ϸ
    public static int ifFirstGame = 0;
    // ��ǰ�ķ�����, 0Ϊ�ڷ�  1 Ϊ�׷�  2Ϊ����
    public static int gameState = 0;

    // �Ƿ���ʾȨֵ����  0 ����ʾ�� 1 ��ʾ����

    public static int ifShowWeightChess = 1;

    // Ȩֵ�����б�
    public static int[][] weightArray = new int[15][15];

    // ��¼�����µ�����
    public static int lastChessPositionAIX = -1;
    public static int lastChessPositionAIY = -1;
    // ���Ӷ�̬����
    public static ArrayList<Chess> chessArray = new ArrayList<>();
    // �Ƿ��Ծ���ʤ��
    public static int gameEnd = 0;

    public static SaverAndLoader saverAndLoader;
    // ����ģʽ 0 Ϊ������   1Ϊ���̲���
    public static Point mousePointer = new Point(300, 300);
    public static Panel panel;
    public static Menu menu;
    public static JFrame frame;

    public static PlayerComputer playerComputer;
}
