import java.awt.*;
import java.util.Random;

public class DBScanAlgorithm {
    int userDefinedDistance;
    Dot[] clickedDots;

    public DBScanAlgorithm(int distance, Canvas canvas, Dot[] clickedDots) {
        this.userDefinedDistance = distance;
        this.clickedDots = clickedDots;
    }

    public void runAlgorithm(Dot selectedDot) {
        if (null == selectedDot && clickedDots.length > 0) {
            Random rand = new Random();
            int index = rand.nextInt(clickedDots.length);
            selectedDot = clickedDots[index];
        }

        if (null != selectedDot) {
            getEuclideanDistance(selectedDot, clickedDots[2]);
        }
    }

    private double getEuclideanDistance(Dot dotOne, Dot dotTwo) {
        return Math.pow(Math.pow((dotOne.x - dotTwo.x), 2.0) + Math.pow((dotOne.y - dotTwo.y), 2.0), 1.0 / 2.0);
    }

    public static void main(String[] args) {
        Dot[] dp = new Dot[4];
        dp[0] = new Dot(1, 1);
        dp[1] = new Dot(4, 1);
        dp[2] = new Dot(6, 2);
        dp[3] = new Dot(8, 1);

        DBScanAlgorithm obj = new DBScanAlgorithm(2, null, dp);
        for (Dot dot : dp) {
            System.out.println("For Dot " + dot.getDotId());
            for (Dot dot2 : dp) {
                System.out.println("Distance between " + dot.getDotId() + "and " + dot2.getDotId() + "is " + obj.getEuclideanDistance(dot, dot2));
            }
        }
    }
}
