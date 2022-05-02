import java.awt.*;
import java.util.*;

import static java.util.Collections.shuffle;

public class DBScanAlgorithm {
    double userDefinedDistance;
    ArrayList<Dot> clickedDots = new ArrayList<>();
    Set<String> notConsideredAsRootDotIDsArray = new LinkedHashSet<>();

    HashMap<String, ArrayList<String>> clusteredDotsIdsMap = new HashMap<>();
    HashMap<Dot, ArrayList<Dot>> clusteredDotsMap = new HashMap<>();

    //For console visualise:
    Set<String> printedDotIdsArray = new LinkedHashSet<>();

    public DBScanAlgorithm(double distance, ArrayList<PointModel> clickedPointsArray) {
        this.userDefinedDistance = distance;
        for (PointModel pointModel : clickedPointsArray) {
            this.clickedDots.add(new Dot(pointModel));
        }

        //Shuffling Clicked Dots array to randomly choose dots as roots.
        shuffle(clickedDots);
        for (Dot dot : clickedDots) {
            if (null != dot) {
                notConsideredAsRootDotIDsArray.add(dot.getDotId());
            }
        }
    }

    public DBScanAlgorithm(double distance, Canvas canvas, ArrayList<Dot> clickedDots) {
        this.userDefinedDistance = distance;
        this.clickedDots = clickedDots;
        //Shuffling Clicked Dots array to randomly choose dots as roots.
        shuffle(clickedDots);
        for (Dot dot : clickedDots) {
            if (null != dot) {
                notConsideredAsRootDotIDsArray.add(dot.getDotId());
            }
        }
    }

    /**
     * Runs Clustering algorithm on all dots. Shuffled Array is used for randomness and is traversed.
     */
    public void runClusteringAlgorithmForAllDots(ArrayList<Dot> dotsArray) {
        if (null == dotsArray) {
            dotsArray = this.clickedDots;
        }
        for (Dot dot : dotsArray) {
            if (notConsideredAsRootDotIDsArray.contains(dot.getDotId())) {
//                System.out.println("Dot considered For Clustering: " + dot.getDotId());
                updateSelectedDotValues(dot);
                runClusteringForADot(dot);
            } else {
//                System.out.println("Dot already considered as root");
            }
        }

    }

    private void updateSelectedDotValues(Dot selectedDot) {
        if (null != selectedDot) {
            selectedDot.setConsideredAsRoot(true);
//            selectedDot.setConnected(true);
            notConsideredAsRootDotIDsArray.remove(selectedDot.getDotId());
        }
    }

    /**
     * Runs clustering Algo for a dot.
     *
     * @param selectedDot Dot object should be passed
     */
    void runClusteringForADot(Dot selectedDot) {

        if (null != selectedDot) {
            ArrayList<Dot> connectedToThisDot = new ArrayList<>();
            //For testing console visualisation
            ArrayList<String> connectedIDsToThisDot = new ArrayList<>();
            for (Dot dot : clickedDots) {
                if (dot != null && dot != selectedDot && (!dot.consideredAsRoot && !dot.connected)) {
                    double euclideanDistance = getEuclideanDistance(dot, selectedDot);
                    if (euclideanDistance <= userDefinedDistance) {
                        connectedToThisDot.add(dot);
                        connectedIDsToThisDot.add(dot.getDotId());
                        dot.setConnected(true);
                        System.out.println("Dot " + selectedDot.getDotId() + "dot " + dot.getDotId() + "are closer. Eucli Distance " + euclideanDistance);
                    } else {
//                        System.out.println("Dot " + selectedDot.getDotId() + "dot " + dot.getDotId() + " are not closer. Eucli distance " + euclideanDistance);
                    }
                }
            }
            clusteredDotsMap.put(selectedDot, connectedToThisDot);
            //For testing console visualisation
            clusteredDotsIdsMap.put(selectedDot.getDotId(), connectedIDsToThisDot);

            runClusteringAlgorithmForAllDots(connectedToThisDot);
        }

    }

    private double getEuclideanDistance(Dot dotOne, Dot dotTwo) {
        return Math.pow(Math.pow((dotOne.x - dotTwo.x), 2.0) + Math.pow((dotOne.y - dotTwo.y), 2.0), 1.0 / 2.0);
    }

    private void testEuclidean(ArrayList<Dot> dp, DBScanAlgorithm obj) {
        for (Dot dot : dp) {
            System.out.println("For Dot " + dot.getDotId());
            for (Dot dot2 : dp) {
                System.out.println("Distance between " + dot.getDotId() + "and " + dot2.getDotId() + "is " + obj.getEuclideanDistance(dot, dot2));
            }
        }
    }

    public void canvasVisualisationOfClustering(Graphics g) {
        for (Dot dot : clickedDots) {
            if (!printedDotIdsArray.contains(dot.getDotId())) {
                printTreeMapForADotInCanvas(dot, g);
                // To Delay picking the next random root dot for clustering
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void printTreeMapForADotInCanvas(Dot dot, Graphics g) {
        printedDotIdsArray.add(dot.getDotId());
        for (Dot connectedDot : clusteredDotsMap.get(dot)) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawLine(dot.x, dot.y, connectedDot.x, connectedDot.y);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            printTreeMapForADotInCanvas(connectedDot, g);
        }
        for (Dot connectedDot : clusteredDotsMap.get(dot)) {
            printTreeMapForADotInCanvas(connectedDot, g);
        }

    }


    //For console Visualisation of clusters tree
    private void consoleVisualisationOfClustering() {
        for (Dot dot : clickedDots) {
//            System.out.println(dot.getDotId() + "->");
            if (!printedDotIdsArray.contains(dot.getDotId())) {
                printTreeMapForADotInConsole(dot.getDotId(), 0);
            }

        }
    }

    //For console Visualisation of clusters tree
    private void printTreeMapForADotInConsole(String dotId, int iteration) {
        for (int i = 0; i < iteration; i++) {
            System.out.print("\t");
        }
        System.out.println("|" + dotId);
        printedDotIdsArray.add(dotId);

        for (String connectedDotId : clusteredDotsIdsMap.get(dotId)) {
            printTreeMapForADotInConsole(connectedDotId, iteration + 1);
        }

    }

    public static void main(String[] args) {
//      //testing
        ArrayList<Dot> dp = new ArrayList<>();
        dp.add(new Dot(1, 1));
        dp.add(new Dot(4, 1));
        dp.add(new Dot(6, 3));
        dp.add(new Dot(8, 1));
        dp.add(new Dot(10, 3));
        dp.add(new Dot(1, 6));
        dp.add(new Dot(1, 2));
        dp.add(new Dot(2, 1));
        dp.add(new Dot(10, 2));
        dp.add(new Dot(1, 8));
        dp.add(new Dot(10, 10));
        dp.add(new Dot(10, 12));
        dp.add(new Dot(10, 15));
        dp.add(new Dot(1, 12));
        dp.add(new Dot(9, 1));


        DBScanAlgorithm obj = new DBScanAlgorithm(3.0, null, dp);

        obj.runClusteringAlgorithmForAllDots(dp);
//        obj.consoleVisualisationOfClustering();
        System.out.println("Dot cluster Map");
        System.out.println(obj.clusteredDotsIdsMap);
        System.out.println("\nConsole Representation of clustering\n");

        obj.consoleVisualisationOfClustering();
    }
}
