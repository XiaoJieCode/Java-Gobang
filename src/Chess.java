import java.awt.*;
import java.io.Serializable;

public class Chess implements Serializable {

    int row;
    int column;
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

    public int getColor() {

        if (color.equals(Color.black)) {
            return 1;
        } else {
            return -1;
        }
    }
}
