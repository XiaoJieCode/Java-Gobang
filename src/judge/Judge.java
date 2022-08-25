package judge;


import data.Config;
import data.Game;
import modular.LoadGame;
import modular.SaveGame;
import objects.PlayerBlack;
import objects.PlayerComputer;
import objects.PlayerWhite;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Judge extends KeyAdapter implements java.awt.event.ActionListener, MouseMotionListener, MouseListener, WindowListener {

    public static final String START_NEW_GAME = "��ʼ����Ϸ";
    public static final String REPENTANCE = "����";
    public static final String ADMIT_DEFEAT = "����";
    public static final String ALL_WAR = "���˶�ս";
    public static final String WAR_MACHINE = "�˻�ģʽ";
    public static final String LOAD_LAST_GAME = "�����Ͼִ浵";
    public static final String LOAD_SELECT_GAME = "����ָ���浵";
    public static final String SAVE_OTHER_PATH = "���Ϊ";
    public static final String SAVE_AND_EXIT = "���沢�˳�";
    public static final String EXIT = "�˳�";


    PlayerBlack playerBlack;
    PlayerWhite playerWhite;
    PlayerComputer playerComputer;
    JFrame frame;
    Judge judge;
    JDialog jDialog = null;

    public Judge(JFrame frame) {
        this.judge = this;
        this.frame = frame;
        this.playerBlack = new PlayerBlack();
        this.playerWhite = new PlayerWhite();
        this.playerComputer = new PlayerComputer();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case START_NEW_GAME:
                if (jDialog != null && jDialog.isVisible()) {
                    jDialog.setVisible(false);
                    Game.newGame();
                    return;
                }

                if (jDialog == null || !jDialog.isVisible()) {
                    int meg = JOptionPane.showConfirmDialog(new JFrame(), "ȷ��Ҫ��ʼ����Ϸ��");
                    switch (meg) {
                        case 0:
                            Game.newGame();
                            break;
                        case 1:
                        case 2:
                            return;
                    }
                    return;
                }


                break;

            case REPENTANCE:
                if (Game.pattern == Game.ALL_WAR) {
                    if (Game.gameState == Game.BLACK) {
                        playerBlack.repentanceChess();
                    } else {
                        playerWhite.repentanceChess();
                    }
                } else if (Game.pattern == Game.WAR_MACHINE) {
                    playerComputer.repentanceChess();
                    playerBlack.repentanceChess();
                    playerComputer.chessPositionArray = Util.getChessPositionArray(Game.chessArray);
                }
                Game.ifGameEnd = false;
                break;

            case ADMIT_DEFEAT:
                if (Game.pattern == Game.ALL_WAR) {
                    if (Game.gameState == Game.BLACK) {
                        playerWhite.surrender();
                    } else {
                        playerBlack.surrender();
                    }
                } else if (Game.pattern == Game.WAR_MACHINE) {
                    playerWhite.surrender();
                }
                break;

            case ALL_WAR:
                Game.pattern = Game.ALL_WAR;
                break;

            case WAR_MACHINE:
                Game.pattern = Game.WAR_MACHINE;
                break;

            case LOAD_LAST_GAME:
                jDialog.setVisible(false);
                LoadGame.loadFileDefaultPath();
                Game.ifGameEnd = false;
                break;

            case LOAD_SELECT_GAME:
                jDialog.setVisible(false);
                LoadGame.loadFileOtherPath();
                Game.ifGameEnd = false;
                break;

            case SAVE_OTHER_PATH:
                jDialog.setVisible(false);
                SaveGame.saveFileOtherPath();
                System.exit(1);
                break;

            case SAVE_AND_EXIT:
                SaveGame.saveFileDefaultPath();
                System.exit(1);
                break;

            case EXIT:
                System.exit(1);
                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                Game.mousePointer.y = Game.mousePointer.y - 40;
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                Game.mousePointer.x = Game.mousePointer.x - 40;
                break;

            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                Game.mousePointer.y = Game.mousePointer.y + 40;
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                Game.mousePointer.x = Game.mousePointer.x + 40;
                break;

            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_ENTER:
                if (Game.pattern == Game.ALL_WAR) {
                    if (Game.gameState == Game.BLACK) {
                        playerBlack.writeChess();
                    } else {
                        playerWhite.writeChess();
                    }

                } else if (Game.pattern == Game.WAR_MACHINE) {
                    playerBlack.writeChess();
                    if (Game.gameState == Game.COMPUTER && !Game.ifGameEnd) {
                        playerComputer.writeChess();
                    }
                }
                break;

            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_BACK_SPACE:
                if (Game.pattern == Game.ALL_WAR) {
                    if (Game.gameState == Game.BLACK) {
                        playerBlack.repentanceChess();
                    } else {
                        playerWhite.repentanceChess();
                    }
                } else if (Game.pattern == Game.WAR_MACHINE) {
                    playerComputer.repentanceChess();
                    playerBlack.repentanceChess();
                    playerComputer.chessPositionArray = Util.getChessPositionArray(Game.chessArray);
                }
                break;

            case KeyEvent.VK_F2:
                Config.ifShowWeightChess = !Config.ifShowWeightChess;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {


        if (Game.pattern == 0) {
            if (Game.gameState == Game.BLACK) {
                playerBlack.writeChess();
            } else {
                playerWhite.writeChess();
            }

        } else if (Game.pattern == 1) {
            playerBlack.writeChess();
            if (Game.gameState == Game.COMPUTER && !Game.ifGameEnd) {
                playerComputer.writeChess();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!Game.mousePointer.equals(new Point(e.getX() - 7, e.getY() - 30))) {
            Game.mousePointer = new Point(e.getX() - 7, e.getY() - 30);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        if (!Config.ifFirstGame) {
            Game.ifGameEnd = true;
            jDialog = new JDialog(frame, "�Ƿ���ش浵��");

            JButton jButton1 = new JButton("��ʼ����Ϸ");
            JButton jButton2 = new JButton("�����Ͼִ浵");
            JButton jButton3 = new JButton("����ָ���浵");

            jButton1.setFocusable(false);
            jButton2.setFocusable(false);
            jButton3.setFocusable(false);

            jDialog.add(jButton1);
            jDialog.add(jButton2);
            jDialog.add(jButton3);


            jDialog.setSize(400, 85);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLayout(new FlowLayout());
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);

            jButton1.addActionListener(judge);
            jButton2.addActionListener(judge);
            jButton3.addActionListener(judge);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (Game.chessArray.size() == 0 || Game.ifGameEnd) {
            System.exit(1);
        }
        jDialog = new JDialog(frame, "�Ƿ񱣴�浵��");
        JButton jButton1 = new JButton("���Ϊ");
        JButton jButton2 = new JButton("���沢�˳�");
        JButton jButton3 = new JButton("�˳�");

        jDialog.add(jButton1);
        jDialog.add(jButton2);
        jDialog.add(jButton3);

        jDialog.setSize(300, 80);
        jDialog.setLayout(new FlowLayout());
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
        jButton1.addActionListener(judge);
        jButton2.addActionListener(judge);
        jButton3.addActionListener(judge);
    }


    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
