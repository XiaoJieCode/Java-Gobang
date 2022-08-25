package modular;

import data.Game;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SaveGame {
    public static void saveFileOtherPath() {
        String fileName = "game.gobang";
        File file = new File(fileName);
        JFileChooser jFileChooser = new JFileChooser("./");
        int i = 1;
        while (file.exists()) {
            fileName = "game" + "(" + i + ")" + ".gobang";
            file = new File(fileName);
            i++;
        }

        jFileChooser.setSelectedFile(file);
        jFileChooser.showSaveDialog(new JFrame());
        file = jFileChooser.getSelectedFile();
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) throw new IOException();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(new JFrame(), "¥Êµµ¥ÌŒÛ");
            }
        }
        try {
            HashMap<String, Object> game = Game.getGame();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "¥Êµµ¥ÌŒÛ");
        }
    }

    public static void saveFileDefaultPath() {
        HashMap<String, Object> game = Game.getGame();
        File file = new File("game.gobang");
        try {
            if (!file.exists()) {
                if (!(file.createNewFile())) {
                    JOptionPane.showMessageDialog(new JFrame(), "¥¢¥Ê≈‰÷√¥ÌŒÛ");
                    return;
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(game);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "¥Êµµ¥ÌŒÛ");

        }
    }
}
