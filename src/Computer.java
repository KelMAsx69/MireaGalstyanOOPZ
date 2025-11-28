class Computer {
    Brand brand;
    Processor processor;
    Memory memory;
    Monitor monitor;
    double price;

    public Computer(Brand brand, Processor processor, Memory memory, Monitor monitor, double price) {
        this.brand = brand;
        this.processor = processor;
        this.memory = memory;
        this.monitor = monitor;
        this.price = price;
    }

    @Override
    public String toString() {
        return brand + ": " + processor.model + ", " + memory.capacityGB + "GB RAM, " +
                monitor.diagonal + "\" monitor, $" + price;
    }
}