import java.util.Scanner;

public class Main {

    // ==============================
    // ЗАДАНИЕ 1: Деление на ноль
    // ==============================
    static class Exception1 {
        // Шаг 1: без обработки → ошибка
        public void exceptionDemoRaw() {
            System.out.println(2 / 0); // ArithmeticException
        }

        // Шаг 3: с обработкой
        public void exceptionDemo() {
            try {
                System.out.println(2 / 0);
            } catch (ArithmeticException e) {
                System.out.println("Attempted division by zero");
            }
        }
    }

    // ==============================
    // ЗАДАНИЯ 2–4: Ввод числа + finally
    // ==============================
    static class Exception2 {
        // Задание 2 + 3: обработка
        public void exceptionDemo() {
            Scanner myScanner = new Scanner(System.in);
            System.out.print("Enter an integer: ");
            try {
                String intString = myScanner.next();
                int i = Integer.parseInt(intString); // NumberFormatException
                System.out.println(2 / i);          // ArithmeticException
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format!");
            } catch (ArithmeticException e) {
                System.out.println("Cannot divide by zero!");
            } catch (Exception e) { // Задание 3: общий catch
                System.out.println("Unexpected error: " + e.getMessage());
            } finally { // Задание 4
                System.out.println("[finally] Input processed.");
            }
        }
    }

    // ==============================
    // ЗАДАНИЯ 5–6: Собственное исключение
    // ==============================
    static class ThrowsDemo {
        public void printMessage(String key) {
            try {
                String message = getDetails(key);
                System.out.println(message);
            } catch (NullPointerException e) {
                System.out.println("Caught in printMessage: " + e.getMessage());
            }
        }

        private String getDetails(String key) {
            if (key == null) {
                throw new NullPointerException("null key in getDetails");
            }
            return "data for " + key;
        }
    }

    // ==============================
    // ЗАДАНИЯ 7–8: Проброс исключения + повторный ввод
    // ==============================
    static class ThrowsDemo2 {
        public void getKey() {
            Scanner myScanner = new Scanner(System.in);
            while (true) {
                try {
                    System.out.print("Enter key (non-empty): ");
                    String key = myScanner.next();
                    printDetails(key);
                    break; // выход при успехе
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage() + "\nTry again.");
                }
            }
        }

        public void printDetails(String key) throws Exception {
            String message = getDetails(key);
            System.out.println(message);
        }

        private String getDetails(String key) throws Exception {
            if (key == null || key.equals("")) {
                throw new Exception("Key set to empty string");
            }
            return "data for " + key;
        }
    }

    // ==============================
    // ГЛАВНЫЙ МЕТОД — демонстрация
    // ==============================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== ЗАДАНИЕ 1: Деление на ноль ===");
        Exception1 ex1 = new Exception1();
        // ex1.exceptionDemoRaw(); // ← раскомментируйте, чтобы увидеть ошибку
        ex1.exceptionDemo(); // обработанная версия

        System.out.println("\n=== ЗАДАНИЯ 2–4: Ввод числа ===");
        Exception2 ex2 = new Exception2();
        ex2.exceptionDemo(); // попробуйте ввести: "abc", "0", "5"

        System.out.println("\n=== ЗАДАНИЯ 5–6: Собственное исключение ===");
        ThrowsDemo td1 = new ThrowsDemo();
        td1.printMessage("test");    // OK
        td1.printMessage(null);      // NPE → перехвачено

        System.out.println("\n=== ЗАДАНИЯ 7–8: Повторный ввод при ошибке ===");
        ThrowsDemo2 td2 = new ThrowsDemo2();
        td2.getKey(); // введите сначала "" (пусто), потом "hello"

        sc.close();
        System.out.println("\n✅ Все задания выполнены.");
    }
}