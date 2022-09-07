package ui.newUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashSet;

public class Main extends JFrame {

    State state;

    HashSet<StateListener> listeners = new HashSet<>();

    public Main() {

        state = new State();

        addComponentListener(new ComponentAdapter() {

            @Override

            public void componentResized(ComponentEvent e) {

                state.setSize(getSize(), true);

            }

            @Override

            public void componentMoved(ComponentEvent e) {

            }

            @Override

            public void componentShown(ComponentEvent e) {

            }

            @Override

            public void componentHidden(ComponentEvent e) {

            }

        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }

    private void addListener(StateListener sl) {

        listeners.add(sl);

    }

    public void associateWith(Main other) {

        other.addListener(new MirrorStateListener());

    }

    public static void main(String[] args) {

        Main f1 = new Main();

        Main f2 = new Main();

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    f1.repaint();
                    f2.repaint();
                    try {
                        sleep((1000/165));
                    } catch (Exception ignored) {

                    }
                }
            }
        };
        thread.start();
        f1.associateWith(f2);

        f2.associateWith(f1);

        f1.setSize(300, 300);

        f2.setLocation(600, 10);

    }

    class State {

        Dimension size;

        public void setSize(Dimension newSize, boolean fireEvent) {

            if (newSize.equals(size)) {

                return;

            }

            size = newSize;

            Main.this.setSize(size);

            if (fireEvent) {

                for (StateListener sl : listeners) {

                    sl.sizeChanged(size);

                }

            }

        }

    }

    class MirrorStateListener implements StateListener {

        @Override

        public void sizeChanged(Dimension newSize) {

            state.setSize(newSize, false);

        }

    }

}

interface StateListener {

    void sizeChanged(Dimension newSize);

}
