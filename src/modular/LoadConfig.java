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
                // ����Ƿ���������ļ���������������ǵ�һ�δ���Ϸ
                Config.ifFirstGame = true;
                SaveConfig.saveConfig();
                return;
            }
            config = new Properties();
            config.load(Files.newInputStream(file.toPath()));
            Config.initConfig(config);



        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(), "��ȡ�����ļ�ʧ��");
        }
    }
}
