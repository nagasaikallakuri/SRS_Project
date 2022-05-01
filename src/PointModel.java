import java.awt.*;

public class PointModel {

    private int X,Y;

    public PointModel(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void fillColor(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(X - 5, Y - 5, 10, 10);
    }

}