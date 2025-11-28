import java.util.Scanner;

public class Main {

    // =============================
    // Задание 5: Сумма цифр числа
    // =============================
    public static int sumOfDigits(int n) {
        if (n == 0) return 0;
        return (n % 10) + sumOfDigits(n / 10);
    }

    // =============================
    // Задание 6: Проверка на простоту (рекурсивно)
    // =============================
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        return isPrimeHelper(n, 3);
    }

    private static boolean isPrimeHelper(int n, int divisor) {
        if (divisor * divisor > n) return true;
        if (n % divisor == 0) return false;
        return isPrimeHelper(n, divisor + 2);
    }

    // =============================
    // Задание 8: Числовой палиндром
    // (проверяем, равно ли число своему обратному)
    // =============================
    public static boolean isPalindrome(int n) {
        if (n < 0) return false;
        return n == reverseNumber(n);
    }

    // Вспомогательный метод (из задания 10)
    public static int reverseNumber(int n) {
        return reverseHelper(n, 0);
    }

    private static int reverseHelper(int n, int acc) {
        if (n == 0) return acc;
        return reverseHelper(n / 10, acc * 10 + n % 10);
    }

    // =============================
    // Задание 10: Разворот числа
    // =============================
    // Уже реализован выше как reverseNumber()

    // =============================
    // Задание 15: Вывод цифр справа налево (по одной, рекурсивно)
    // =============================
    public static void printDigitsReverse(int n) {
        if (n == 0) return;
        System.out.println(n % 10);
        printDigitsReverse(n / 10);
    }

    // =============================
    // ГЛАВНЫЙ МЕТОД — демонстрация
    // =============================
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Задание 5: Сумма цифр числа ===");
        System.out.print("Введите число: ");
        int n5 = sc.nextInt();
        System.out.println("Сумма цифр: " + sumOfDigits(Math.abs(n5)) + "\n");

        System.out.println("=== Задание 6: Простое ли число? ===");
        System.out.print("Введите число (>1): ");
        int n6 = sc.nextInt();
        System.out.println(isPrime(n6) ? "YES" : "NO\n");

        System.out.println("=== Задание 8: Числовой палиндром? ===");
        System.out.print("Введите число: ");
        int n8 = sc.nextInt();
        System.out.println(isPalindrome(Math.abs(n8)) ? "YES" : "NO\n");

        System.out.println("=== Задание 10: Разворот числа ===");
        System.out.print("Введите число (без нулей): ");
        int n10 = sc.nextInt();
        System.out.println("Развёрнутое: " + reverseNumber(n10) + "\n");

        System.out.println("=== Задание 15: Цифры справа налево ===");
        System.out.print("Введите число: ");
        int n15 = sc.nextInt();
        if (n15 == 0) {
            System.out.println(0);
        } else {
            printDigitsReverse(Math.abs(n15));
        }

        sc.close();
        System.out.println("\n✅ Все рекурсивные задания выполнены.");
    }
}