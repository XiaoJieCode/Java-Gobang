package modular;

import data.Config;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.util.Properties;

public class SaveConfig {
    public synchronized static void saveConfig(){
        File file = new File("config.properties");
        try {
            Properties config = Config.getConfig();
            config.store(Files.newOutputStream(file.toPath()), "config.properties");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "±£¥Ê≈‰÷√Œƒº˛ ß∞‹£°");
        }
    }
}
