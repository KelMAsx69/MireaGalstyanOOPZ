// Main.java
public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЗАДАНИЕ 4: Компьютеры ===");
        Computer comp1 = new Computer(
                Brand.DELL,
                new Processor("Intel i7-12700H", 4.7),
                new Memory(32),
                new Monitor(17),
                1499.99
        );
        System.out.println(comp1);

        Computer comp2 = new Computer(
                Brand.APPLE,
                new Processor("M2 Pro", 3.5),
                new Memory(16),
                new Monitor(14),
                2199.00
        );
        System.out.println(comp2);

        System.out.println("\n=== ЗАДАНИЕ 4.1: Абстрактные фигуры ===");

        Shape circle = new Circle(5.0, "red", true);
        Shape rect = new Rectangle(4.0, 6.0, "blue", false);
        Shape square = new Square(3.0, "green", true);

        Shape[] shapes = {circle, rect, square};
        for (Shape s : shapes) {
            System.out.println(s);
            System.out.printf("Площадь: %.2f, Периметр: %.2f\n\n", s.getArea(), s.getPerimeter());
        }

        // Полиморфизм: вызов через ссылку типа Shape
        System.out.println("Полиморфный вызов (upcast):");
        Shape s1 = new Circle(2.0, "yellow", false);
        System.out.println("Площадь круга через Shape: " + s1.getArea());
        System.out.println("toString: " + s1);
    }
}