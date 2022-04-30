package View;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        JFrame frame = new JFrame();

        Canvas canvas = new Canvas();
        canvas.setSize(500, 500);
        canvas.setVisible(true);
        canvas.setForeground(Color.DARK_GRAY);

        JButton saveButton = new JButton();
        saveButton.setVisible(true);
        saveButton.setText("Save");

        JButton randomGenButton = new JButton();
        randomGenButton.setVisible(true);
        randomGenButton.setText("Random Generator");

        JButton clearButton = new JButton();
        clearButton.setVisible(true);
        clearButton.setText("Clear");

        JPanel canvasPanel = new JPanel();
        canvasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        canvasPanel.setLayout(new GridLayout(0, 1));
        canvasPanel.setSize(500, 500);
        canvasPanel.add(canvas);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonsPanel.setLayout(new GridLayout(0, 5));
        buttonsPanel.setSize(500, 50);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(randomGenButton);
        buttonsPanel.add(clearButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(canvasPanel);
        mainPanel.add(buttonsPanel);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setTitle("SRSproject");
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);


    }
    public static void main(String[] args) {
        Main main = new Main();
        //main.setSize(800, 800);
        //main.setVisible(true);
    }
}