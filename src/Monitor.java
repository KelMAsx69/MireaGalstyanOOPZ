// Monitor.java
public class Monitor {
    private int diagonal;

    public Monitor(int diagonal) {
        this.diagonal = diagonal;
    }

    public int getDiagonal() { return diagonal; }

    @Override
    public String toString() {
        return diagonal + "\"";
    }
}