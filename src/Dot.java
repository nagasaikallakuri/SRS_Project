public class Dot {
    boolean consideredAsRoot = false;
    boolean connected = false;
    public int x;
    public int y;
    public PointModel pointModel;
    String dotId;

    public static String getIDForDot(int x, int y) {
        return String.format("x:%d,y:%d", x, y);
    }

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
        this.dotId = getIDForDot(x, y);
    }

    public Dot(PointModel point) {
        this(point.getX(), point.getY());
        this.pointModel = point;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public boolean getConnected() {
        return this.connected;
    }

    public void setConsideredAsRoot(Boolean consideredAsRoot) {
        this.consideredAsRoot = consideredAsRoot;
    }

    public boolean getConsideredAsRoot() {
        return this.consideredAsRoot;
    }

    public String getDotId() {
        return dotId;
    }
}
