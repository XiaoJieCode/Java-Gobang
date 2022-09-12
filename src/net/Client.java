package net;

import objects.Chess;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    Socket socket;
    public int port = 7103;
    public String ip = null;
    JFrame frame;
    public static final int TAG = 1;
    public Client(String ip, Integer port, JFrame frame) {
        this.frame = frame;
        if (ip.equals("")||ip.equals("请输入IP")){
            this.ip = "localhost";
        } else {
            this.ip = ip;
        }
        if (port!=null) {
            this.port = port;
        }
        if(joinRoom()){
            JOptionPane.showMessageDialog(frame, "加入房间成功");
            frame.setVisible(false);
            new NetJudge(socket, TAG);
        } else {
            JOptionPane.showMessageDialog(frame, "加入房间失败");
        }

    }

    private boolean joinRoom(){
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame,"房间不存在");
            return false;
        }
        return true;
    }



}
