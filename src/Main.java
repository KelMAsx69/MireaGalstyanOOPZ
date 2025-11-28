import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // =======================
        // Задание 3
        // =======================
        System.out.println("=== Задание 3 ===");
        int[] arr3 = {5, 10, 15, 20, 25}; // инициализация "как в Си"
        int sum3 = 0;
        for (int i = 0; i < arr3.length; i++) {
            sum3 += arr3[i];
        }
        double avg3 = (double) sum3 / arr3.length;
        System.out.println("Массив: " + java.util.Arrays.toString(arr3));
        System.out.println("Сумма: " + sum3);
        System.out.printf("Среднее арифметическое: %.2f%n%n", avg3);

        // =======================
        // Задание 4
        // =======================
        System.out.println("=== Задание 4 ===");
        System.out.print("Введите размер массива: ");
        int n = sc.nextInt();
        int[] arr4 = new int[n];
        System.out.println("Введите " + n + " целых чисел:");
        for (int i = 0; i < n; i++) {
            arr4[i] = sc.nextInt();
        }

        // Сумма через while
        int sumWhile = 0;
        int i = 0;
        while (i < arr4.length) {
            sumWhile += arr4[i];
            i++;
        }

        // Сумма через do-while
        int sumDoWhile = 0;
        i = 0;
        do {
            sumDoWhile += arr4[i];
            i++;
        } while (i < arr4.length);

        // Min / Max
        int min = arr4[0];
        int max = arr4[0];
        for (int val : arr4) {
            if (val < min) min = val;
            if (val > max) max = val;
        }

        System.out.println("Массив: " + java.util.Arrays.toString(arr4));
        System.out.println("Сумма (while): " + sumWhile);
        System.out.println("Сумма (do-while): " + sumDoWhile);
        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max + "\n");

        // =======================
        // Задание 5
        // =======================
        System.out.println("=== Задание 5 ===");
        if (args.length == 0) {
            System.out.println("Аргументы командной строки: (нет)");
        } else {
            for (int j = 0; j < args.length; j++) {
                System.out.println("args[" + j + "] = " + args[j]);
            }
        }
        System.out.println();

        // =======================
        // Задание 6
        // =======================
        System.out.println("=== Задание 6 ===");
        for (int k = 1; k <= 10; k++) {
            double term = 1.0 / k;
            System.out.printf("1/%d = %.6f%n", k, term);
        }
        System.out.println();

        // =======================
        // Задание 7
        // =======================
        System.out.println("=== Задание 7 ===");
        // Метод factorial определён ниже как статический
        for (int num = 0; num <= 10; num++) {
            System.out.printf("%d! = %d%n", num, factorial(num));
        }

        sc.close();
        System.out.println("\n✅ Все задания выполнены.");
    }

    // Вспомогательный метод для задания 7
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал не определён для отрицательных чисел");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}