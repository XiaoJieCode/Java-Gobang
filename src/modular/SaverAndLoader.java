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
            System.out.println("�ļ����ش���");

        } catch (NullPointerException nullPointerException) {
            System.out.println("�ļ����ش���");
        }
    }

    public void saveConfig() {
        try {
            File config = new File("config.obj");
            HashMap<Object, Object> file = new HashMap<>();
            file.put("pattern", Data.pattern);
            if (!config.exists()) {
                if (config.createNewFile()) {
                    System.out.println("��һ����Ϸ���ɹ����������ļ�");
                } else {
                    System.out.println("���������ļ�ʧ�ܣ���������Ŀ¼���Ƿ����� \"config.gobang\" �������ļ�");
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(config);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(file);

        } catch (IOException e) {
            System.out.println("�������ô���");
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
            System.out.println("�浵����");
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
                System.out.println("�����ļ�ʧ��");
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
            System.out.println("�浵����");
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
                return "��Ҫ\".gobang\" �ļ�";
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
            System.out.println("�ļ���ȡ����");
        } catch (ClassNotFoundException e) {
            System.out.println("�ļ����ش���");
        } catch (NullPointerException nullPointerException) {
            System.out.println("δѡ���ļ�");
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
            System.out.println("�����ļ�δ����");
        } catch (NullPointerException nullPointerException) {
            System.out.println("�ļ����ش���");
        }
    }
}
