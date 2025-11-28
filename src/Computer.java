// Computer.java
public class Computer {
    private Brand brand;
    private Processor processor;
    private Memory memory;
    private Monitor monitor;
    private double price;

    public Computer(Brand brand, Processor processor, Memory memory, Monitor monitor, double price) {
        this.brand = brand;
        this.processor = processor;
        this.memory = memory;
        this.monitor = monitor;
        this.price = price;
    }

    // Геттеры
    public Brand getBrand() { return brand; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s: %s, %s, %s, $%.2f", brand, processor, memory, monitor, price);
    }
}