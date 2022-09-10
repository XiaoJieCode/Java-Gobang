package net;

import judge.Judge;
import objects.Player;
import objects.PlayerBlack;
import objects.PlayerWhite;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetJudge {
    public static final int COMMEND_STOP = -1;
    public static final int COMMEND_START = 0;
    public static final int SHOW_MAIN_UI = 1;
    public static final int WRITE_CHESS = 2;
    public static final int WIN = 3;
    public static final int LOSE = 4;
    public static final int IS_CONNECTED = 5;
    public static final int SET_BLACK_COLOR = 6;
    public static final int SET_WHITE_COLOR = 7;


    Socket socket = null;
    Integer tag = null;
    Integer color = null;
    JFrame mainUI = null;
    Integer timeOutCount = 10;
    Player player = null;

    public static NetJudge netJudge = null;

    public NetJudge(Socket socket, int TAG) {

        netJudge = this;
        Judge judge = new Judge(UI.frame);

        this.socket = socket;
        this.tag = TAG;
        getRequest();


        if (TAG == 0) {
            chooseColor();
            initPlayer();
            UI.frame.setTitle("五子棋-服务端");
            if (color == 0) {
                setClientColor(0);
            } else {
                setClientColor(1);
            }
        } else {
            UI.frame.setTitle("五子棋-客户端");
            setIsConnectedThread();
            setTimeOutThread();
            JOptionPane.showMessageDialog(new JFrame(), "等待房主选择棋子颜色");
        }
    }

    private void setClientColor(int i) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            if (i == 0) {
                outputStream.write(SET_BLACK_COLOR);
            } else if (i == 1) {
                outputStream.write(SET_WHITE_COLOR);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTimeOutThread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeOutCount = timeOutCount + 1;

                    if (timeOutCount == 10) {
                        JOptionPane.showMessageDialog(new JFrame(), "超时");
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    private void setIsConnectedThread() {
        try {
            OutputStream stream = socket.getOutputStream();
            stream.write(IS_CONNECTED);
        } catch (IOException ignored) {

        }
    }

    private void reConnect() {

    }

    private void getRequest() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (!socket.isClosed()) {
                            InputStream stream = socket.getInputStream();
                            byte[] commends = {-2};
                            byte commend = commends[0];
                            try {
                                commends = stream.readAllBytes();
                                commend = commends[0];
                            } catch (IOException ignored) {
                            }
                            switch (commend) {
                                case COMMEND_STOP:
                                    break;
                                case COMMEND_START:
                                    break;
                                case SHOW_MAIN_UI:
                                    UI.frame.setVisible(true);
                                    break;
                                case WRITE_CHESS:
                                    int row = commends[1];
                                    int column = commends[2];
                                    int color = commends[3];
                                    writeChess(row,column,color);
                                    break;
                                case SET_BLACK_COLOR:
                                    initPlayer(0);
                                    break;
                                case SET_WHITE_COLOR:
                                    initPlayer(1);
                                    break;
                                case IS_CONNECTED:
                                    timeOutCount = 0;
                                    break;
                                case -2:
                                    break;
                            }
                        }
                        Thread.sleep(200);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

    }

    private void initPlayer(int color) {
        this.color = color;
        if (color == 0) {
            player = new PlayerBlack();
        } else {
            player = new PlayerWhite();
        }
    }

    private void initPlayer() {
        if (color == 0) {
            player = new PlayerBlack();
        } else {
            player = new PlayerWhite();
        }
    }


    private synchronized void chooseColor() {
        JFrame frame = new JFrame("请选择你的棋子颜色");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 130);
        frame.setLocationRelativeTo(null);
        JButton block = new JButton("黑色");
        JButton white = new JButton("白色");

        class Listener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("黑色")) {
                    color = 0;
                } else if (e.getActionCommand().equals("白色")) {
                    color = 1;
                }
                frame.setVisible(false);
                UI.frame.setVisible(true);
                showClientUI();
            }
        }
        Listener listener = new Listener();
        block.addActionListener(listener);
        white.addActionListener(listener);
        frame.add(block);
        frame.add(white);
        frame.setVisible(true);
    }


    public void writeChess(int row, int column,int color) {
        if (tag == 0) {
            writeChessForServer(row,column,color);
        } else {
            writeChessForClient(row,column,color);
        }
    }

    private void showClientUI() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(SHOW_MAIN_UI);
            outputStream.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "弹出客户端UI错误");
        }
    }

    private void writeChessForServer(int row, int column,int color ) {
        player.writeChess();
    }

    private void writeChessForClient(int row, int column,int color) {

    }

    public void addChess(int row, int column, int color) {
    }
}
