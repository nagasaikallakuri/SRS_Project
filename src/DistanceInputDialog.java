import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceInputDialog extends JFrame {

    static int distanceToRun = 100;

    public DistanceInputDialog() {
        this.setUpDialogBox();
    }

    private void setUpDialogBox() {
        JOptionPane optionPane = new JOptionPane();
        String result = (String) optionPane.showInputDialog(
                null,
                "Enter the distance in Pixel",
                "",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "" + distanceToRun
        );
        int distanceInputed = 0;
        try {
            distanceInputed = Integer.parseInt(result);
            if (distanceInputed > 0) {
                distanceToRun = distanceInputed;
            } else {
                this.showErrorMessage();
            }
        } catch (NumberFormatException e) {
            this.showErrorMessage();
        }
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(
                null,
                "Input is not valid. Please enter an integer",
                "Try Again",
                JOptionPane.ERROR_MESSAGE,
                null
        );
    }
}
