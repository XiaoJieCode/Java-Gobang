package net;

import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;


public class ConnectUI extends JPanel {
    JFrame frame = null;
    JPanel panel = null;
    String button1Text = "��������";
    JButton button1;

    public ConnectUI() throws Exception {
        initFrame();
        initUI();
        frame.setVisible(true);
    }

    public synchronized void initFrame() {
        frame = new JFrame("");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(UI.frame);
        frame.setResizable(false);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
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
        });
    }

    public synchronized void initUI() throws Exception {
        panel = new JPanel();

        panel.setLayout(new GridLayout(10, 2));
        JTextField fieldIP = new JTextField();
        JTextField fieldPort = new JTextField("������˿ں�");

        fieldIP.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldIP.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        fieldPort.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                fieldPort.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton jRadioButton1 = new JRadioButton("��������");
        JRadioButton jRadioButton2 = new JRadioButton("���뷿��");


        panel.add(jRadioButton1);
        panel.add(jRadioButton2);

        JLabel jLabel = new JLabel("����IP��" + InetAddress.getLocalHost());

        panel.add(jLabel);
        panel.add(fieldIP);
        panel.add(fieldPort);

        jLabel.setVisible(true);
        fieldIP.setVisible(false);
        fieldPort.setVisible(true);

        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);

        jRadioButton1.setSelected(true);
        class ButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (e.getActionCommand()) {
                    case "��������":
                        jLabel.setVisible(true);
                        fieldIP.setVisible(false);
                        fieldPort.setVisible(true);
                        fieldIP.setText(null);
                        fieldPort.setText("������˿ں�");
                        if (button1 != null) {
                            button1.setText("��������");
                        }
                        break;

                    case "���뷿��":
                        jLabel.setVisible(false);
                        fieldIP.setVisible(true);
                        fieldPort.setVisible(true);
                        fieldIP.setText("������IP");
                        fieldPort.setText("������˿ں�");
                        if (button1 != null) {
                            button1.setText("���뷿��");
                        }
                        break;
                }

            }
        }
        ButtonListener b = new ButtonListener();
        jRadioButton1.addActionListener(b);
        jRadioButton2.addActionListener(b);


        button1 = new JButton(button1Text);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (jRadioButton1.isSelected()){
                        fieldIP.setText("");
                        if (fieldPort.getText().equals("")||fieldPort.getText().equals("������˿ں�"))
                        {
                            new Server(null, frame);
                            return;
                        }
                        int port = Integer.parseInt(fieldPort.getText());
                        new Server(port, frame);
                    } else if (jRadioButton2.isSelected()){
                        String ip = fieldIP.getText();
                        if (fieldPort.getText().equals("")||fieldPort.getText().equals("������˿ں�"))
                        {
                            new Client(ip, null, frame);
                            return;
                        }
                        int port = Integer.parseInt(fieldPort.getText());
                        new Client(ip, port, frame);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(UI.frame, "�˿ڲ��Ϸ�");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(UI.frame, "�ö˿��ѱ�ռ��!");
                }
            }
        });
        panel.add(button1);

        frame.add(panel);
    }

}
