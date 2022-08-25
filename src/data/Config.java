package data;

import java.util.Properties;

public class Config {

    public static final int ALL_WAR = 0;
    public static final int WAR_MACHINE = 1;


    // 游戏最大帧数
    public static int FPS = 60;

    // 是否显示权值分数
    public static boolean ifShowWeightChess = false;


    //记录选择的模式
    public static int pattern = WAR_MACHINE;

    // 是否第一次游戏
    public static boolean ifFirstGame = false;

    public static Properties getConfig() {
        Properties config = new Properties();
        config.put("FPS", String.valueOf(FPS));
        config.put("ifShowWeightChess", String.valueOf(ifShowWeightChess));
        config.put("pattern", String.valueOf(pattern));
        return config;
    }

    public static void initConfig(Properties config) {
        FPS = Integer.parseInt((String) config.get("FPS"));
        ifShowWeightChess = Boolean.parseBoolean((String) config.get("ifShowWeightChess"));
        pattern = Integer.parseInt((String) config.get("pattern"));

    }

}
