import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;


public class PointCanvas extends Canvas {

    private ArrayList<PointModel> points = new ArrayList<>();

    DBScanAlgorithm dbScanAlgorithmObj = null;

    public void addPoints(PointModel pointModel) {
        points.add(pointModel);
        this.repaint();
    }

    public void removePoints() {
        points.clear();
        dbScanAlgorithmObj = null;
        this.repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        for (PointModel point : points) {
            point.fillColor(graphics);
        }

        if (null != dbScanAlgorithmObj) {
            dbScanAlgorithmObj.canvasVisualisationOfClustering(graphics);
        }
    }

    public void generateDotsRandomly() {
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int x = rand.nextInt(1400);
            int y = rand.nextInt(700);
            //System.out.println("Random Generator event");
            //System.out.println(x);
            //System.out.println(y);
            addPoints(new PointModel(x, y));

        }
    }

    public void saveToFile() {
        try {
            Timestamp timestamp = Timestamp.from(Instant.now());
            String location = System.getProperty("java.class.path");
            String fileName = "canvas_" + timestamp;
            File myObj = new File(location + fileName + ".txt");
            if (myObj.createNewFile()) System.out.println("File created: " + myObj.getName());
            else System.out.println("File already exists.");

            FileWriter myWriter = new FileWriter(fileName + ".txt");
            for (PointModel point : points) {
                myWriter.write(Integer.toString(point.getX()) + "," + Integer.toString(point.getY()));
                myWriter.write("\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void loadFile() {
        //Loading file
        File selectedFile;
        PointModel point =  null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
        else {
            selectedFile = null;
            return;
        }
        //Reading the file
        try {
            System.out.println("In reading block");
            File selectedFileToWrite = new File(selectedFile.getAbsolutePath());
            Scanner myReader = new Scanner(selectedFileToWrite);
            points.clear();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] coordinates = data.split(",");
                point = new PointModel(Integer.parseInt(coordinates[0]),Integer.parseInt(coordinates[1]));
                points.add(point);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.repaint();
    }

    public void initAndRunDbScan(int distanceToRun) {

        if (!points.isEmpty()) {
            this.dbScanAlgorithmObj = new DBScanAlgorithm((double) distanceToRun, points);
            dbScanAlgorithmObj.runClusteringAlgorithmForAllDots(null);
            this.repaint();
        } else {
            System.out.println("Empty Canvas");
        }
    }
}