package net;


import data.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    Thread acceptThread;
    ServerSocket serverSocket;
    public int port = 7103;
    Socket socket;
    JFrame frame;

    public Server(int port, JFrame frame) throws IOException {
        this.frame = frame;
        this.port = port;
        Game.isServer = true;
        initServerSocket();
        createRoom();

    }
    void createRoom() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        JOptionPane.showMessageDialog(new JFrame(), "等待玩家加入");
        while (socket == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {

            }
        }
        if (socket != null) {
            JOptionPane.showMessageDialog(new JFrame(), "玩家加入成功");
        }
        frame.setVisible(false);
    }

    private void initServerSocket() throws IOException {
        serverSocket = new ServerSocket(port);

    }



    public void closeGetSocket(){
        if (acceptThread != null){
            acceptThread.interrupt();
        }
    }

}
