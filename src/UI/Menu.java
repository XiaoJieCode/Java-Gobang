package UI;

import Data.Data;
import Judge.Judge;

import javax.swing.*;
import java.awt.*;


public class Menu extends Panel {
    public JRadioButton button5;
    public JRadioButton button6;
    Judge actionListener;

    public Menu(JFrame frame) {
        actionListener = new Judge(this);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(150, 0));
        addButtons();
        Data.panel = this;
        frame.add(this, BorderLayout.EAST);
        frame.addMouseMotionListener(actionListener);
        frame.addMouseListener(actionListener);
        frame.addWindowListener(actionListener);
        frame.addKeyListener(actionListener);

    }

    public void addButtons() {
        ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < Data.messageArray.length - 2; i++) {
            if (i < 3) {
                JButton button = new JButton(Data.messageArray[i]);
                button.setPreferredSize(new Dimension(140, 80));
                add(button);
                button.addActionListener(actionListener);
                button.setFocusable(false);
            }
            if (i == 3) {
                JLabel label = new JLabel(Data.messageArray[i]);
                label.setFont(new Font("黑体", Font.BOLD, 18));

                add(label);
            }
        }
        button5 = new JRadioButton(Data.messageArray[4]);
        button6 = new JRadioButton(Data.messageArray[5]);
        button5.setFocusable(false);
        button6.setFocusable(false);

        if (Data.pattern == 0) {
            button5.setSelected(true);
        } else if (Data.pattern == 1) {
            button6.setSelected(true);
        }

        add(button5);
        add(button6);
        bg.add(button5);
        bg.add(button6);
        button5.addActionListener(actionListener);
        button6.addActionListener(actionListener);
        JLabel labelTip1 = new JLabel("  操作方法：");
//        labelTip1.setFont(new Font("黑体", Font.BOLD, 12));
//        labelTip1.setPreferredSize(new Dimension(95,60));
        JLabel labelTip2 = new JLabel("使用鼠标进行移");
//        labelTip2.setFont(new Font("黑体", Font.BOLD, 12));
        JLabel labelTip3 = new JLabel("动,左键或右键");
        JLabel labelTip4 = new JLabel("点击下棋,亦可");
        JLabel labelTip5 = new JLabel("使用键盘 W A ");
        JLabel labelTip6 = new JLabel("S D 或 ↑ ↓ ");
        JLabel labelTip7 = new JLabel("← → 键控制指");
        JLabel labelTip8 = new JLabel("示器移动,空格 ");
        JLabel labelTip9 = new JLabel("或回车键在指示");
        JLabel labelTip10 = new JLabel("位置进行下棋,Esc");
        JLabel labelTip11 = new JLabel("键进行悔棋操作");
        labelTip1.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip2.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip3.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip4.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip5.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip6.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip7.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip8.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip9.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip10.setFont(new Font("黑体", Font.BOLD, 12));
        labelTip11.setFont(new Font("黑体", Font.BOLD, 12));
//        labelTip1.setPreferredSize(new Dimension(95,50));
//        labelTip2.setPreferredSize(new Dimension(95,60));
//        labelTip3.setPreferredSize(new Dimension(95,60));
//        labelTip4.setPreferredSize(new Dimension(95,60));
//        labelTip5.setPreferredSize(new Dimension(95,60));
//        labelTip6.setPreferredSize(new Dimension(95,60));
//        labelTip7.setPreferredSize(new Dimension(95,60));
//        labelTip8.setPreferredSize(new Dimension(95,60));
//        labelTip9.setPreferredSize(new Dimension(95,60));
//        labelTip10.setPreferredSize(new Dimension(95,60));
//        labelTip11.setPreferredSize(new Dimension(95,60));
        add(labelTip1);
        add(labelTip2);
        add(labelTip3);
        add(labelTip4);
        add(labelTip5);
        add(labelTip6);
        add(labelTip7);
        add(labelTip8);
        add(labelTip9);
        add(labelTip10);
        add(labelTip11);


    }

    public void lockButtons() {
        button5.removeActionListener(actionListener);
        button6.removeActionListener(actionListener);
    }

    public void unlockButtons() {
        button5.addActionListener(actionListener);
        button6.addActionListener(actionListener);

    }
}
