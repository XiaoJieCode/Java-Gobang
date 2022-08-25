package starter;

import modular.LoadConfig;

import javax.swing.*;


public class GameStart {
    public static void main(String[] args) {
        // ����UI���
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ignored) {

        }

        // ���������ļ�
        LoadConfig.loadConfig();
        new ui.UI();
    }
}
