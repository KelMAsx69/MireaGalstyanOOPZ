import java.util.*;

// Основной класс
public class Main {

    // === ЗАДАНИЕ 1 и 2: MovablePoint, MovableCircle, MovableRectangle ===
    interface Movable {
        void moveUp();
        void moveDown();
        void moveLeft();
        void moveRight();
    }

    static class MovablePoint implements Movable {
        int x, y, xSpeed, ySpeed;
        public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
            this.x = x; this.y = y; this.xSpeed = xSpeed; this.ySpeed = ySpeed;
        }
        @Override public void moveUp() { y -= ySpeed; }
        @Override public void moveDown() { y += ySpeed; }
        @Override public void moveLeft() { x -= xSpeed; }
        @Override public void moveRight() { x += xSpeed; }
        @Override public String toString() {
            return String.format("Point(x=%d, y=%d, vx=%d, vy=%d)", x, y, xSpeed, ySpeed);
        }
    }

    static class MovableCircle implements Movable {
        private MovablePoint center;
        private int radius;
        public MovableCircle(int x, int y, int xSpeed, int ySpeed, int radius) {
            this.center = new MovablePoint(x, y, xSpeed, ySpeed);
            this.radius = radius;
        }
        @Override public void moveUp() { center.moveUp(); }
        @Override public void moveDown() { center.moveDown(); }
        @Override public void moveLeft() { center.moveLeft(); }
        @Override public void moveRight() { center.moveRight(); }
        @Override public String toString() {
            return "Circle{" + center + ", r=" + radius + "}";
        }
    }

    static class MovableRectangle implements Movable {
        private MovablePoint topLeft;
        private MovablePoint bottomRight;
        public MovableRectangle(int x1, int y1, int x2, int y2, int xSpeed, int ySpeed) {
            this.topLeft = new MovablePoint(x1, y1, xSpeed, ySpeed);
            this.bottomRight = new MovablePoint(x2, y2, xSpeed, ySpeed);
            if (topLeft.xSpeed != bottomRight.xSpeed || topLeft.ySpeed != bottomRight.ySpeed) {
                throw new IllegalArgumentException("Скорости точек должны совпадать!");
            }
        }
        @Override public void moveUp() { topLeft.moveUp(); bottomRight.moveUp(); }
        @Override public void moveDown() { topLeft.moveDown(); bottomRight.moveDown(); }
        @Override public void moveLeft() { topLeft.moveLeft(); bottomRight.moveLeft(); }
        @Override public void moveRight() { topLeft.moveRight(); bottomRight.moveRight(); }
        @Override public String toString() {
            return "Rect{" + topLeft + " -> " + bottomRight + "}";
        }
    }

    // === ЗАДАНИЕ 3: Nameable ===
    interface Nameable {
        String getName();
    }
    static class Planet implements Nameable {
        private String name;
        public Planet(String name) { this.name = name; }
        @Override public String getName() { return name; }
    }
    static class Car implements Nameable {
        private String model;
        public Car(String model) { this.model = model; }
        @Override public String getName() { return model; }
    }
    static class Animal implements Nameable {
        private String species;
        public Animal(String species) { this.species = species; }
        @Override public String getName() { return species; }
    }

    // === ЗАДАНИЕ 4: Priceable ===
    interface Priceable {
        double getPrice();
    }
    static class BookItem implements Priceable {
        private double price;
        public BookItem(double price) { this.price = price; }
        @Override public double getPrice() { return price; }
    }
    static class Phone implements Priceable {
        private double price;
        public Phone(double price) { this.price = price; }
        @Override public double getPrice() { return price; }
    }

    // === ЗАДАНИЯ 6–9: Printable ===
    interface Printable {
        void print();
    }
    static class Book implements Printable {
        private String title;
        public Book(String title) { this.title = title; }
        @Override public void print() { System.out.println("Книга: " + title); }
    }
    static class Magazine implements Printable {
        private String title;
        public Magazine(String title) { this.title = title; }
        @Override public void print() { System.out.println("Журнал: " + title); }
    }
    static class Shop implements Printable {
        private String name;
        public Shop(String name) { this.name = name; }
        @Override public void print() { System.out.println("Магазин: " + name); }
    }

    // === ЗАДАНИЕ 10: Интернет-магазин компьютеров ===
    enum Brand { DELL, HP, ASUS, APPLE, LENOVO }

    static class Processor {
        String model; double frequency;
        public Processor(String model, double frequency) {
            this.model = model; this.frequency = frequency;
        }
    }
    static class Memory {
        int capacityGB;
        public Memory(int capacityGB) { this.capacityGB = capacityGB; }
    }
    static class Monitor {
        int diagonal;
        public Monitor(int diagonal) { this.diagonal = diagonal; }
    }
    static class Computer {
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
            return brand + ": " + processor.model + ", " + memory.capacityGB + "GB RAM, "
                    + monitor.diagonal + "\" monitor, $" + price;
        }
    }
    static class ComputerShop {
        private List<Computer> computers = new ArrayList<>();
        public void addComputer(Computer comp) { computers.add(comp); }
        public void removeComputer(Computer comp) { computers.remove(comp); }
        public Computer findComputer(Brand brand, double maxPrice) {
            return computers.stream()
                    .filter(c -> c.brand == brand && c.price <= maxPrice)
                    .findFirst()
                    .orElse(null);
        }
        public void printAll() {
            computers.forEach(System.out::println);
        }
    }

    // === ЗАДАНИЕ 11: Convertable ===
    interface Convertable {
        double convert(double value, String targetUnit);
    }
    static class TemperatureConverter implements Convertable {
        @Override
        public double convert(double celsius, String targetUnit) {
            switch (targetUnit.toLowerCase()) {
                case "kelvin": return celsius + 273.15;
                case "fahrenheit": return celsius * 9 / 5 + 32;
                default: throw new IllegalArgumentException("Unsupported unit: " + targetUnit);
            }
        }
    }

    // === ЗАДАНИЕ 12: UndoableStringBuilder (паттерн Команда) ===
    interface Command {
        void execute(); void undo();
    }
    static class UndoableStringBuilder {
        private StringBuilder sb = new StringBuilder();
        private Stack<Command> history = new Stack<>();
        public UndoableStringBuilder append(String str) {
            int prev = sb.length();
            Command cmd = new Command() {
                @Override public void execute() { sb.append(str); }
                @Override public void undo() { sb.setLength(prev); }
            };
            cmd.execute(); history.push(cmd); return this;
        }
        public void undo() {
            if (!history.isEmpty()) history.pop().undo();
        }
        @Override public String toString() { return sb.toString(); }
    }

    // === ЗАДАНИЕ 13: ObservableStringBuilder (паттерн Наблюдатель) ===
    interface Observer {
        void update(String newState);
    }
    static class ObservableStringBuilder {
        private StringBuilder sb = new StringBuilder();
        private List<Observer> observers = new ArrayList<>();
        public void addObserver(Observer obs) { observers.add(obs); }
        private void notifyObservers() {
            for (Observer o : observers) o.update(sb.toString());
        }
        public ObservableStringBuilder append(String str) {
            sb.append(str); notifyObservers(); return this;
        }
        public ObservableStringBuilder delete(int start, int end) {
            sb.delete(start, end); notifyObservers(); return this;
        }
        @Override public String toString() { return sb.toString(); }
    }
    static class ConsoleLogger implements Observer {
        @Override public void update(String newState) {
            System.out.println("[Observer] State: " + newState);
        }
    }

    // === ОСНОВНОЙ МЕТОД ===
    public static void main(String[] args) {
        System.out.println("=== ЗАДАНИЕ 1–2: Movable ===");
        MovableCircle circle = new MovableCircle(0, 0, 2, 3, 5);
        System.out.println("До: " + circle);
        circle.moveRight(); circle.moveUp();
        System.out.println("После moveRight и moveUp: " + circle);

        MovableRectangle rect = new MovableRectangle(0, 0, 4, 3, 1, 1);
        System.out.println("Прямоугольник: " + rect);
        rect.moveDown(); rect.moveRight();
        System.out.println("После движения: " + rect);

        System.out.println("\n=== ЗАДАНИЕ 3: Nameable ===");
        Nameable[] nameables = { new Planet("Земля"), new Car("Тойота"), new Animal("Кот") };
        for (Nameable n : nameables) System.out.println(n.getName());

        System.out.println("\n=== ЗАДАНИЕ 4: Priceable ===");
        Priceable[] priceables = { new BookItem(29.99), new Phone(899.99) };
        for (Priceable p : priceables) System.out.println("Цена: " + p.getPrice());

        System.out.println("\n=== ЗАДАНИЯ 6–9: Printable ===");
        Printable[] printables = {
                new Book("1984"),
                new Magazine("National Geographic"),
                new Shop("BookStore")
        };
        for (Printable p : printables) p.print();

        System.out.println("\n=== ЗАДАНИЕ 10: Internet Shop ===");
        ComputerShop shop = new ComputerShop();
        shop.addComputer(new Computer(Brand.DELL, new Processor("i7", 3.2), new Memory(16), new Monitor(15), 950.0));
        shop.addComputer(new Computer(Brand.HP, new Processor("Ryzen5", 3.0), new Memory(8), new Monitor(14), 600.0));
        System.out.println("Ассортимент:");
        shop.printAll();
        Computer found = shop.findComputer(Brand.DELL, 1000);
        System.out.println("Найдено: " + (found != null ? found : "Нет"));

        System.out.println("\n=== ЗАДАНИЕ 11: Temperature Conversion ===");
        Convertable tc = new TemperatureConverter();
        double c = 25.0;
        System.out.printf("%.1f°C = %.2f K\n", c, tc.convert(c, "Kelvin"));
        System.out.printf("%.1f°C = %.2f°F\n", c, tc.convert(c, "Fahrenheit"));

        System.out.println("\n=== ЗАДАНИЕ 12: UndoableStringBuilder ===");
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("Привет").append(" Мир");
        System.out.println("Текущее: " + usb);
        usb.undo();
        System.out.println("После undo: " + usb);

        System.out.println("\n=== ЗАДАНИЕ 13: ObservableStringBuilder ===");
        ObservableStringBuilder osb = new ObservableStringBuilder();
        osb.addObserver(new ConsoleLogger());
        osb.append("Hello");
        osb.append(" Java");
        osb.delete(5, 11); // удаляет " Java"

        System.out.println("\n=== ВСЁ ВЫПОЛНЕНО ===");
    }
}