package modular;

import data.Data;
import objects.Chess;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class SaverAndLoader {

    public SaverAndLoader() {
    }

    public void loadConfig() {
        try {
            File config = new File("config.obj");
            if (!config.exists()) {
                Data.ifFirstGame = 1;
                saveConfig();
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(config);

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<Object, Object> file = (HashMap<Object, Object>) objectInputStream.readObject();
            Data.pattern = (int) file.get("pattern");
            if (Data.pattern == 0) {
                Data.menu.button5.setSelected(true);

            } else if (Data.pattern == 1) {
                Data.menu.button6.setSelected(true);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("文件加载错误");

        } catch (NullPointerException nullPointerException) {
            System.out.println("文件加载错误");
        }
    }

    public void saveConfig() {
        try {
            File config = new File("config.obj");
            HashMap<Object, Object> file = new HashMap<>();
            file.put("pattern", Data.pattern);
            if (!config.exists()) {
                if (config.createNewFile()) {
                    System.out.println("第一次游戏，成功创建配置文件");
                } else {
                    System.out.println("创建配置文件失败，请检查运行目录下是否有与 \"config.gobang\" 重名的文件");
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(config);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(file);

        } catch (IOException e) {
            System.out.println("储存配置错误");
        }
    }


    public void saveFileDefaultPath() {
        File file = new File("file.gobang");
        try {
            HashMap<Object, Object> data = new HashMap<>();
            data.put("pattern", Data.pattern);
            data.put("gameState", Data.gameState);
            data.put("lastChessPositionAIX", Data.lastChessPositionAIX);
            data.put("lastChessPositionAIY", Data.lastChessPositionAIY);
            if (!file.exists()) {
                file.createNewFile();
            }
            data.put("chessArray", Data.chessArray);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            System.out.println("存档错误");
        }
    }

    public void saveFileOtherPath() {
        String fileName = "file.gobang";
        File file = new File(fileName);
        JFileChooser jFileChooser = new JFileChooser("./");
        int i = 1;
        while (file.exists()) {
            fileName = "file" + "(" + i + ")" + ".gobang";
            file = new File(fileName);
            i++;
        }
        jFileChooser.setSelectedFile(file);
        jFileChooser.showSaveDialog(Data.frame);
        file = jFileChooser.getSelectedFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("创建文件失败");
            }
        }
        try {
            HashMap<Object, Object> data = new HashMap<>();
            data.put("pattern", Data.pattern);
            data.put("gameState", Data.gameState);
            data.put("lastChessPositionAIX", Data.lastChessPositionAIX);
            data.put("lastChessPositionAIY", Data.lastChessPositionAIY);
            data.put("data", data);
            data.put("chessArray", Data.chessArray);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            System.out.println("存档错误");
        }
    }

    public void loadFileOtherPath() {
        JFileChooser jFileChooser = new JFileChooser("./");
        jFileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String name = f.getName();

                    if (name.endsWith("gobang")) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "需要\".gobang\" 文件";
            }
        });
        jFileChooser.showOpenDialog(Data.frame);
        File file = jFileChooser.getSelectedFile();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<Object, Object> data = (HashMap<Object, Object>) objectInputStream.readObject();

            Data.chessArray = (ArrayList<Chess>) data.get("chessArray");
            Data.pattern = (int) data.get("pattern");
            Data.gameState = (int) data.get("gameState");
            Data.lastChessPositionAIX = (int) data.get("lastChessPositionAIX");
            Data.lastChessPositionAIY = (int) data.get("lastChessPositionAIY");

        } catch (IOException ioException) {
            System.out.println("文件读取错误");
        } catch (ClassNotFoundException e) {
            System.out.println("文件加载错误");
        } catch (NullPointerException nullPointerException) {
            System.out.println("未选择文件");
        }


    }

    public void loadDefaultFile() {
        try {
            File config = new File("file.gobang");
            FileInputStream fileInputStream = new FileInputStream(config);
            if (!config.exists()) {
                saveConfig();
            }
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<Object, Object> file = (HashMap<Object, Object>) objectInputStream.readObject();

            Data.pattern = (int) file.get("pattern");
            Data.gameState = (int) file.get("gameState");
            Data.lastChessPositionAIX = (int) file.get("lastChessPositionAIX");
            Data.lastChessPositionAIY = (int) file.get("lastChessPositionAIY");

            Data.chessArray = (ArrayList<Chess>) file.get("chessArray");
            if (Data.pattern == 0) {
                Data.menu.button5.setSelected(true);

            } else if (Data.pattern == 1) {
                Data.menu.button6.setSelected(true);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("配置文件未发现");
        } catch (NullPointerException nullPointerException) {
            System.out.println("文件加载错误");
        }
    }
}
