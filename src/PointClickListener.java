import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PointClickListener extends MouseAdapter {

    public int xCoordinate;
    public int yCoordinate;

    private PointCanvas pointPanel;

    public PointClickListener(PointCanvas pointPanel) {
        super();
        this.pointPanel = pointPanel;
    }


    PointClickListener() {}

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("In mouseClicked event");
        pointPanel.addPoints(new PointModel(e.getX(),e.getY()));
        System.out.println(e.getX());
        System.out.println(e.getY());

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}