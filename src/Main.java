// Main.java
public class Main {
    public static void main(String[] args) {
        // === Задание 1–2: MovableRectangle ===
        System.out.println("=== MovableRectangle ===");
        MovableRectangle rect = new MovableRectangle(0, 0, 4, 3, 2, 1);
        System.out.println("До: " + rect);
        rect.moveRight(); rect.moveUp();
        System.out.println("После движения: " + rect);
        System.out.println("Скорости совпадают: " + rect.speedTest() + "\n");

        // === Задание 3–4: MathCalculable ===
        System.out.println("=== MathCalculable ===");
        MathCalculable calc = new MathFunc();
        System.out.println("2^10 = " + calc.power(2, 10));
        System.out.println("|3 + 4i| = " + calc.complexAbs(3, 4));
        MathFunc mf = (MathFunc) calc;
        System.out.println("Длина окружности (r=5): " + mf.circleLength(5) + "\n");

        // === Задание 5–6: StringProcessor ===
        System.out.println("=== StringProcessor ===");
        StringProcessor sp = new ProcessStrings();
        String test = "Программирование";
        System.out.println("Строка: " + test);
        System.out.println("Длина: " + sp.countChars(test));
        System.out.println("Символы на нечётных позициях: " + sp.oddChars(test));
        System.out.println("Инверсия: " + sp.reverse(test) + "\n");

        // === Задание 7–8: Printable, Book, Magazine ===
        System.out.println("=== Printable (Book, Magazine) ===");
        Printable[] items = {
                new Book("1984"),
                new Magazine("Science"),
                new Book("Мастер и Маргарита"),
                new Magazine("PC Magazine")
        };

        // Общий вывод
        for (Printable p : items) {
            p.print();
        }
        System.out.println();

        // Только книги
        Book.printBooks(items);

        // Только журналы
        Magazine.printMagazines(items);

        System.out.println("\n✅ Все задания выполнены.");
    }
}