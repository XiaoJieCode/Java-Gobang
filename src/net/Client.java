package net;

import objects.Chess;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    Socket socket;
    ArrayList<Chess> chessArray;
    public int port = 7103;
    public String ip = null;
    JFrame frame;
    public Client(String ip, int port, JFrame frame) {
        this.frame = frame;
        if (ip.equals("")){
            this.ip = "localhost";
        } else {
            this.ip = ip;
        }
        this.port = port;
        if(joinRoom()){
            JOptionPane.showMessageDialog(new JFrame(), "加入房间成功");
            frame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "加入房间失败");
        }

    }

    private boolean joinRoom(){
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
            return false;
        }
        return true;
    }

    private void sendMsg(String msg){
        
    }

    public Socket getSocket() {
        return socket;
    }

    public ArrayList<Chess> getChessArray() {
        return chessArray;
    }

}
