import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class PointCanvas extends Canvas {

    private List<PointModel> points = new LinkedList<>();

    public void addPoints(PointModel pointModel) {
        points.add(pointModel);
        this.repaint();
    }

    public void removePoints() {
        points.clear();
        this.repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        for(PointModel point : points) {
            point.fillColor(graphics);
        }
    }
}