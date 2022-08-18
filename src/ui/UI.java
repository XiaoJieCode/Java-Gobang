package ui;

import data.Data;
import modular.Indicator;
import modular.SaverAndLoader;
import objects.Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UI extends JPanel implements ActionListener {
    JFrame frame;

    public UI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        initScreen();
        initMenu();
        Data.saverAndLoader = new SaverAndLoader();
        Data.saverAndLoader.loadConfig();
        Timer timer = new Timer(1 / 120, this);
        timer.start();
        frame.setVisible(true);
    }

    // ��ʼ�����ֲ���
    public synchronized void initScreen() {
        frame = new JFrame("������");
        frame.setSize(780, 635);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.add(this, BorderLayout.CENTER);

        Data.frame = frame;
    }

    public synchronized void initMenu() {
        Data.menu = new Menu(frame);
    }

    // ����ͼ��
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawChessboard(g);
        drawChess(g);
        drawIndicator(g);
        drawWeightChessGrade(g);
    }

    // ����������Ʒ���
    public void drawBackground(Graphics g) {
        setBackground(new Color(209, 146, 17));
    }

    public void drawChessboard(Graphics g) {
        for (int i = 0; i < 15; i++) {
            g.drawLine(20, 20 + 40 * i, 20 + 40 * (15 - 1), 20 + 40 * i);// ���� //����40
            g.drawLine(20 + 40 * i, 20, 20 + 40 * i, 20 + 40 * (15 - 1));// ���� //����40
        }
        //����ڵ����
        int x = 136;
        int y = 136;
        g.fillArc(x, y, 8, 8, 0, 360);
        g.fillArc(x + 320, y + 320, 8, 8, 0, 360);
        g.fillArc(x + 320, y, 8, 8, 0, 360);
        g.fillArc(x, y + 320, 8, 8, 0, 360);
        g.fillArc(x + 160, y + 160, 8, 8, 0, 360);
    }

    public void drawChess(Graphics g) {
        for (Chess chess : Data.chessArray) {

            g.setColor(chess.getColor());
            int row = chess.row;
            int column = chess.column;
            g.fillOval(row * 40, column * 40, 40, 40);


        }
    }

    public void drawIndicator(Graphics g) {
        Indicator.drawIndicator(g);
    }

    public void drawWeightChessGrade(Graphics g) {
        if (Data.ifShowWeightChess == 1) {
            int[][] weightArray = new int[15][15];
            weightArray = Data.weightArray;
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    g.setColor(Color.green);
                    g.setFont(new Font("����", Font.BOLD, 15));
                    int number = Data.weightArray[j][i];
                    for (Chess chess : Data.chessArray) {
                        if (chess.equal(new Chess(j, i, Color.black))) {
                            number = 0;
                        }
                    }
                    if (number == 0) {
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.green);
                    }
                    String grade = String.valueOf(number);
                    g.drawString(grade, j * 40 + 20, i * 40 + 20);
                }
            }
        }
    }

    // �Զ�ˢ������
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }
}
