package net;

import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class NetJudge {
    Socket socket = null;
    Integer tag = null;
    Integer color = null;
    JFrame mainUI = null;
    public static NetJudge netJudge = null;
    public NetJudge(Socket socket, int TAG) {
        netJudge = this;
        this.socket = socket;
        this.tag = TAG;
        if (TAG==0) {
            chooseColor();

        }else{
            JOptionPane.showMessageDialog(new JFrame(),"�ȴ�����ѡ��������ɫ");
        }

    }

    private void chooseColor() {
        JFrame frame = new JFrame("��ѡ�����������ɫ");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 130);
        frame.setLocationRelativeTo(null);
        JButton block = new JButton("��ɫ");
        JButton white = new JButton("��ɫ");

        class Listener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("��ɫ")){
                    color = 0;
                } else if (e.getActionCommand().equals("��ɫ")) {
                    color = 1;
                }
                frame.setVisible(false);
                UI.frame.setVisible(true);
            }
        }
        Listener listener= new Listener();
        block.addActionListener(listener);
        white.addActionListener(listener);
        frame.add(block);
        frame.add(white);
        frame.setVisible(true);
    }


    public void writeChess() {
        if (tag==0){
            writeChessForServer();
        }else {
            writeChessForClient();
        }
    }


    private void writeChessForServer() {

    }

    private void writeChessForClient() {

    }

}
