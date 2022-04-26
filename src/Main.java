

import java.awt.*;

import javax.swing.*;

public class Main extends JFrame {

    Main() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
    }

    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        Main main = new Main();
        main.add(canvas, BorderLayout.CENTER);
        main.setVisible(true);
    }

}