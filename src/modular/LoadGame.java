package modular;

import com.sun.security.auth.UnixNumericGroupPrincipal;
import data.Game;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class LoadGame {
    public synchronized static void loadFileOtherPath() {
        JFileChooser jFileChooser = new JFileChooser("./");
        jFileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String name = f.getName();
                    return name.endsWith("gobang");
                }
            }

            @Override
            public String getDescription() {
                return "需要\".gobang\" 文件";
            }
        });
        jFileChooser.showOpenDialog(new JFrame());
        File file = jFileChooser.getSelectedFile();
        try {
            if (file == null) throw new NullPointerException();
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<String, Object> game = (HashMap<String, Object>) objectInputStream.readObject();
            Game.initGame(game);
        } catch (NullPointerException | ClassNotFoundException ignored) {

        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "读取文件失败");
        }
    }

    public synchronized static void loadFileDefaultPath() {
        try {
            FileInputStream fileInputStream = new FileInputStream("game.gobang");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<String, Object> game = (HashMap<String, Object>) objectInputStream.readObject();
            Game.initGame(game);
        } catch (Exception Exception) {
            JOptionPane.showMessageDialog(new JFrame(), "读取文件失败");
        }
    }
}
