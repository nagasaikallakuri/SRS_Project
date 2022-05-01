import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main extends JFrame implements ActionListener{

    static PointCanvas canvas;

    public Main() {
        JFrame frame = new JFrame();

        canvas = new PointCanvas();
        //CanvasPanel canvas = new CanvasPanel();
        this.canvas.setSize(500, 500);
        this.canvas.setVisible(true);
        this.canvas.setForeground(Color.DARK_GRAY);


        JButton saveButton = new JButton();
        saveButton.setVisible(true);
        saveButton.setText("Save");
        saveButton.addActionListener(this);
        saveButton.setActionCommand("save");

        JButton randomGenButton = new JButton();
        randomGenButton.setVisible(true);
        randomGenButton.setText("Random Generator");
        randomGenButton.addActionListener(this);
        randomGenButton.setActionCommand("randomGenerator");

        JButton runButton = new JButton();
        runButton.setVisible(true);
        runButton.setText("Run");
        runButton.addActionListener(this);
        runButton.setActionCommand("run");

        JButton clearButton = new JButton();
        clearButton.setVisible(true);
        clearButton.setText("Clear");
        clearButton.addActionListener(this);
        clearButton.setActionCommand("clear");

        JPanel canvasPanel = new JPanel();
        canvasPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        canvasPanel.setLayout(new GridLayout(0, 1));
        canvasPanel.setSize(500, 500);
        canvasPanel.add(canvas);
        canvas.addMouseListener(new PointClickListener(canvas));


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonsPanel.setLayout(new GridLayout(0, 5));
        buttonsPanel.setSize(500, 50);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(randomGenButton);
        buttonsPanel.add(runButton);
        buttonsPanel.add(clearButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(canvasPanel);
        mainPanel.add(buttonsPanel);


        frame.add(mainPanel, BorderLayout.CENTER);;

        frame.setTitle("SRSproject");
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);


    }


    public static void main(String[] args) {
        Main main = new Main();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command == "run") {
            new DistanceInputDialog();
        } else if(command == "clear") {
            canvas.removePoints();
        } else if(command == "randomGenerator") {
            canvas.generateDotsRandomly();
        } else if(command == "save") {
            canvas.saveToFile();
        }
    }
}