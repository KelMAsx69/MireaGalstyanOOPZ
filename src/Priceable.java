public interface Priceable {
    double getPrice();
}

class Book implements Priceable {
    private double price;
    public Book(double price) { this.price = price; }
    @Override public double getPrice() { return price; }
}

class Phone implements Priceable {
    private double price;
    public Phone(double price) { this.price = price; }
    @Override public double getPrice() { return price; }
}