package Judge;

import Data.Data;
import Objects.PlayerBlack;
import Objects.PlayerComputer;
import Objects.PlayerWhite;
import Util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Judge extends KeyAdapter implements java.awt.event.ActionListener, MouseMotionListener, MouseListener, WindowListener {
    PlayerBlack playerBlack;
    PlayerWhite playerWhite;
    PlayerComputer playerComputer;
    Panel panel;

    public Judge(Panel panel) {
        this.panel = panel;
        this.playerBlack = new PlayerBlack();
        this.playerWhite = new PlayerWhite();
        this.playerComputer = new PlayerComputer();
        Data.playerComputer = this.playerComputer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(Data.messageArray[0])) {
            Util.newGame();
        } else if (e.getActionCommand().equals(Data.messageArray[1])) {

            if (Data.pattern == 0) {
                if (Data.gameState == 0) {
                    playerBlack.repentanceChess();
                } else if (Data.gameState == 1) {
                    playerWhite.repentanceChess();
                }
            } else if (Data.pattern == 1) {
                playerComputer.repentanceChess();
                playerBlack.repentanceChess();
                playerComputer.chessPositionArray = Util.getChessPositionArray(Data.chessArray);
            }
            Data.gameEnd = 0;
        } else if (e.getActionCommand().equals(Data.messageArray[2])) {
            if (Data.pattern == 0) {
                if (Data.gameState == 0) {
                    playerWhite.surrender();
                } else {
                    playerBlack.surrender();
                }
            } else if (Data.pattern == 1) {
                playerWhite.surrender();
            }
        } else if (e.getActionCommand().equals(Data.messageArray[4])) {
            Data.pattern = 0;
        } else if (e.getActionCommand().equals(Data.messageArray[5])) {
            Data.pattern = 1;
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
                Data.mousePointer.y = Data.mousePointer.y - 40;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                Data.mousePointer.x = Data.mousePointer.x - 40;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                Data.mousePointer.y = Data.mousePointer.y + 40;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                Data.mousePointer.x = Data.mousePointer.x + 40;
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_ENTER:
                if (Data.pattern == 0) {
                    if (Data.gameState == 0) {
                        playerBlack.writeChess();
                    } else if (Data.gameState == 1) {
                        playerWhite.writeChess();
                    }

                } else if (Data.pattern == 1) {
                    playerBlack.writeChess();
                    if (Data.gameState == 2 && Data.gameEnd != 1) {
                        playerComputer.writeChess();
                    }
                }
                break;
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_BACK_SPACE:
                if (Data.pattern == 0) {
                    if (Data.gameState == 0) {
                        playerBlack.repentanceChess();
                    } else if (Data.gameState == 1) {
                        playerWhite.repentanceChess();
                    }
                } else if (Data.pattern == 1) {
                    playerComputer.repentanceChess();
                    playerBlack.repentanceChess();
                    playerComputer.chessPositionArray = Util.getChessPositionArray(Data.chessArray);
                }
                break;
            case KeyEvent.VK_F2:
                if (Data.ifShowWeightChess == 0) {
                    Data.ifShowWeightChess = 1;
                } else {
                    Data.ifShowWeightChess = 0;
                }
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

        // 根据当前模式决定下棋方法

        if (Data.pattern == 0) {
            if (Data.gameState == 0) {
                playerBlack.writeChess();
            } else if (Data.gameState == 1) {
                playerWhite.writeChess();
            }

        } else if (Data.pattern == 1) {
            playerBlack.writeChess();
            if (Data.gameState == 2 && Data.gameEnd != 1) {
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
        if (!Data.mousePointer.equals(new Point(e.getX() - 7, e.getY() - 30))) {
            Data.mousePointer = new Point(e.getX() - 7, e.getY() - 30);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

        JFrame frame = Data.frame;
        if (Data.ifFirstGame == 0) {
            Data.gameEnd = 1;
            JDialog jDialog = new JDialog(frame, "是否加载存档？");
            JButton jButton1 = new JButton("开始新游戏");
            JButton jButton2 = new JButton("加载上局存档");
            JButton jButton3 = new JButton("加载指定存档");

            jDialog.add(jButton1);
            jDialog.add(jButton2);
            jDialog.add(jButton3);


            jDialog.setSize(400, 75);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLayout(new FlowLayout());
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);

            jButton1.addActionListener(new Judge(panel) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("开始新游戏")) {
                        jDialog.setVisible(false);
                        Data.gameEnd = 0;

                    }
                }
            });
            jButton2.addActionListener(new Judge(panel) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("加载上局存档")) {
                        Data.saverAndLoader.loadDefaultFile();
                        jDialog.setVisible(false);
                        Data.gameEnd = 0;

                    }
                }
            });
            jButton3.addActionListener(new Judge(panel) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("加载指定存档")) {

                        jDialog.setVisible(false);
                        Data.saverAndLoader.loadFileOtherPath();
                        Data.gameEnd = 0;

                    }
                }
            });
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (Data.chessArray.size() == 0 || Data.gameEnd == 1) {
            System.exit(1);
        }
        JFrame frame = Data.frame;
        JDialog jDialog = new JDialog(frame, "是否保存存档？");

        JButton jButton1 = new JButton("另存为");
        JButton jButton2 = new JButton("保存并退出");
        JButton jButton3 = new JButton("退出");

        jDialog.add(jButton1);
        jDialog.add(jButton2);
        jDialog.add(jButton3);

        jDialog.setSize(300, 80);
        jDialog.setLayout(new FlowLayout());
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
        jButton1.addActionListener(new Judge(panel) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("另存为")) {
                    Data.saverAndLoader.saveFileOtherPath();
                    System.exit(1);
                }
            }
        });
        jButton2.addActionListener(new Judge(panel) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("保存并退出")) {
                    Data.saverAndLoader.saveFileDefaultPath();
                    System.exit(1);
                }
            }
        });

        jButton3.addActionListener(new Judge(panel) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("退出")) {
                    System.exit(1);
                }
            }
        });
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
