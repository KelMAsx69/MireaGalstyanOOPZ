// Memory.java
public class Memory {
    private int capacityGB;

    public Memory(int capacityGB) {
        this.capacityGB = capacityGB;
    }

    public int getCapacityGB() { return capacityGB; }

    @Override
    public String toString() {
        return capacityGB + " GB RAM";
    }
}