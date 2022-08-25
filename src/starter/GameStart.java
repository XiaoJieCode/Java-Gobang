package starter;

import modular.LoadConfig;

import javax.swing.*;


public class GameStart {
    public static void main(String[] args) {
        // 设置UI风格
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ignored) {

        }

        // 加载配置文件
        LoadConfig.loadConfig();
        new ui.UI();
    }
}
