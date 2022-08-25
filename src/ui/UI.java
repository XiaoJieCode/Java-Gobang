package ui;


import data.Config;
import data.Game;
import judge.Judge;
import modular.Indicator;
import objects.Chess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class UI extends JPanel implements ActionListener {
    JFrame frame;

    public UI() {

        initFrame();
        initMenu();

        Timer timer = new Timer(1 / Config.FPS, this);
        timer.start();
        frame.setVisible(true);
    }

    // 初始化各种参数
    public synchronized void initFrame() {
        frame = new JFrame("五子棋");

        frame.setSize(780, 635);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.add(this, BorderLayout.CENTER);
    }

    public synchronized void initMenu() {
        new Menu(frame, new Judge(frame));
    }

    // 绘制图形
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        drawChessboard(g);
        drawChess(g);
        drawIndicator(g);
        drawWeightChessGrade(g);
    }

    // 各种组件绘制方法
    public void drawBackground(Graphics ignoredG) {
        setBackground(new Color(209, 146, 17));
    }

    public void drawChessboard(Graphics g) {
        for (int i = 0; i < 15; i++) {
            g.drawLine(20, 20 + 40 * i, 20 + 40 * (15 - 1), 20 + 40 * i);// 横线 //格子40
            g.drawLine(20 + 40 * i, 20, 20 + 40 * i, 20 + 40 * (15 - 1));// 竖线 //格子40
        }
        //五个黑点绘制
        int x = 136;
        int y = 136;
        g.fillArc(x, y, 8, 8, 0, 360);
        g.fillArc(x + 320, y + 320, 8, 8, 0, 360);
        g.fillArc(x + 320, y, 8, 8, 0, 360);
        g.fillArc(x, y + 320, 8, 8, 0, 360);
        g.fillArc(x + 160, y + 160, 8, 8, 0, 360);
    }

    public void drawChess(Graphics g) {
        for (Chess chess : Game.chessArray) {

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
        if (Config.ifShowWeightChess) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    g.setColor(Color.green);
                    g.setFont(new Font("黑体", Font.BOLD, 15));
                    int number = Game.weightArray[j][i];
                    for (Chess chess : Game.chessArray) {
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

    // 自动刷新棋盘
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
