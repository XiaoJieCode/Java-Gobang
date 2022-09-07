package modular;

import data.Config;


import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Properties;

public class LoadConfig {
    public synchronized static void loadConfig() {
        try {
            Properties config;
            File file = new File("config.properties");
            if (!file.exists()) {
                // 检查是否存在配置文件，如果不存在则是第一次打开游戏
                Config.ifFirstGame = true;
                SaveConfig.saveConfig();
                return;
            }
            config = new Properties();
            config.load(Files.newInputStream(file.toPath()));
            Config.initConfig(config);



        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "读取配置文件失败");
        }
    }
}
