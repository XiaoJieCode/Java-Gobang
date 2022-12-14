package objects;

import java.awt.*;
import java.io.Serializable;

public class Chess implements Serializable {

    public int row;
    public int column;
    Color color;

    public Chess(int row, int column, Color color) {
        this.row = row;
        this.column = column;
        this.color = color;
    }


    public boolean equal(Chess obj) {
        return row == obj.row && column == obj.column;
    }

    @Override
    public String toString() {
        return "[" + row + "," + column + "," + color + "]";
    }

    public Color getColor() {

        return this.color;
    }
}
