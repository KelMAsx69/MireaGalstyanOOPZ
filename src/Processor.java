// Processor.java
public class Processor {
    private String model;
    private double frequency; // GHz

    public Processor(String model, double frequency) {
        this.model = model;
        this.frequency = frequency;
    }

    // Геттеры
    public String getModel() { return model; }
    public double getFrequency() { return frequency; }

    @Override
    public String toString() {
        return model + " (" + frequency + " GHz)";
    }
}