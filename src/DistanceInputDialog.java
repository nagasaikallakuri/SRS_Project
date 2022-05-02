import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceInputDialog extends JFrame {

    static int distanceToRun = 100;

    public boolean setUpDialogBox() {
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
        if(result != null) {
            int distanceInputed = 0;
            try {
                distanceInputed = Integer.parseInt(result);
                if (distanceInputed > 0) {
                    distanceToRun = distanceInputed;
                    return true;
                } else {
                    this.showErrorMessage();
                    return false;
                }
            } catch (NumberFormatException e) {
                this.showErrorMessage();
                return false;
            }
        } else {
            return false;
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
