package ui;

import data.Game;
import judge.Judge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Menu extends JPanel {

    public static final String START_NEW_GAME = "开始新游戏";
    public static final String REPENTANCE = "悔棋";
    public static final String ADMIT_DEFEAT = "认输";

    public static final String BATTLE_MODE = "对战模式";

    public static final String ALL_WAR = "人人对战";
    public static final String WAR_MACHINE = "人机模式";
    public static final String NEW_WAR = "网络对战";

    static ArrayList<String> jButtons;
    static ArrayList<String> Labels;
    static ArrayList<String> jRadioButtons;

    static {
        jButtons = new ArrayList<>();
        jButtons.add(START_NEW_GAME);
        jButtons.add(REPENTANCE);
        jButtons.add(ADMIT_DEFEAT);

        Labels = new ArrayList<>();
        jButtons.add(BATTLE_MODE);

        jRadioButtons = new ArrayList<>();
        jButtons.add(ALL_WAR);
        jButtons.add(WAR_MACHINE);
        jButtons.add(NEW_WAR);

    }



    public JRadioButton button5;
    public JRadioButton button6;
    public JRadioButton button7;

    Judge judge;
    public Menu(JFrame frame, Judge judge) {
        this.judge = judge;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(150, 0));
        addButtons();
        frame.add(this, BorderLayout.EAST);
        frame.addMouseMotionListener(judge);
        frame.addMouseListener(judge);
        frame.addWindowListener(judge);
        frame.addKeyListener(judge);

    }

    public void addButtons() {
        JButton button1 = new JButton(START_NEW_GAME);
        JButton button2 = new JButton(REPENTANCE);
        JButton button3 = new JButton(ADMIT_DEFEAT);

        button1.setPreferredSize(new Dimension(140, 80));
        button2.setPreferredSize(new Dimension(140, 80));
        button3.setPreferredSize(new Dimension(140, 80));

        add(button1);
        add(button2);
        add(button3);

        button1.addActionListener(judge);
        button2.addActionListener(judge);
        button3.addActionListener(judge);

        button1.setFocusable(false);
        button2.setFocusable(false);
        button3.setFocusable(false);

        JLabel label = new JLabel(BATTLE_MODE);
        label.setFont(new Font("黑体", Font.BOLD, 18));
        add(label);

        button5 = new JRadioButton(ALL_WAR);
        button6 = new JRadioButton(WAR_MACHINE);
        button7 = new JRadioButton(NEW_WAR);

        button5.setFocusable(false);
        button6.setFocusable(false);
        button7.setFocusable(false);

        ButtonGroup bg = new ButtonGroup();
        bg.add(button5);
        bg.add(button6);
        bg.add(button7);

        if (Game.pattern == Game.ALL_WAR) {
            button5.setSelected(true);
        } else if (Game.pattern == Game.WAR_MACHINE){
            button6.setSelected(true);
        } else if (Game.pattern == Game.NET_WAR){
            button7.setSelected(true);
        }

        add(button5);
        add(button6);
        add(button7);

        button5.addActionListener(judge);
        button6.addActionListener(judge);
        button7.addActionListener(judge);
    }
}
