import java.util.Arrays;
import java.util.Date;
import java.util.Random;

// ==============================
// ВСПОМОГАТЕЛЬНЫЙ КЛАСС Circle (из предыдущих работ)
// ==============================
class Circle {
    private double radius;
    private static int numberOfObjects = 0;

    public Circle() {
        this(1.0);
    }

    public Circle(double radius) {
        this.radius = radius;
        numberOfObjects++;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public static int getNumberOfObjects() {
        return numberOfObjects;
    }
}

// ==============================
// ЗАДАНИЕ 6: StopWatch
// ==============================
class StopWatch {
    private long startTime;
    private long endTime;

    public StopWatch() {
        start();
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}

// ==============================
// ГЛАВНЫЙ КЛАСС — ВСЕ ЗАДАНИЯ
// ==============================
public class Main {

    // ЗАДАНИЕ 1: Анализ класса F
    static class F {
        int i;
        static String s = "static field";
        void imethod() { System.out.println("Instance method"); }
        static void smethod() { System.out.println("Static method"); }
    }

    // ЗАДАНИЕ 2: Исправленный класс Test
    static class Test {
        int count;

        public static void main(String[] args) {
            // Это метод не будет вызван, т.к. у нас свой main
        }

        public int getCount() {
            return count;
        }

        public static int factorial(int n) { // <-- static добавлен
            int result = 1;
            for (int i = 1; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }

    // ЗАДАНИЕ 3: Исправленный класс C
    static class C {
        public static void main(String[] args) {
            // Нельзя вызвать method1() напрямую — он не static
            // Поэтому создаём объект:
            C instance = new C();
            instance.method1();
        }

        public void method1() {
            method2();
        }

        public static void method2() {
            // Нужен объект Circle
            Circle c = new Circle(5.0);
            System.out.println("What is area " + c.getArea());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== ЗАДАНИЕ 1: Допустимые обращения к F ===");
        F f = new F();
        f.i = 42;
        // 1. System.out.println(f.i); → ДА
        System.out.println("1. f.i = " + f.i);
        // 2. System.out.println(f.s); → ДА (статическое поле доступно через экземпляр, но лучше через класс)
        System.out.println("2. f.s = " + f.s);
        // 3. f.imethod(); → ДА
        f.imethod();
        // 4. f.smethod(); → ДА
        f.smethod();
        // 5. System.out.println(F.i); → НЕТ (i — не static!)
        // System.out.println(F.i); // ← КОМПИЛЯЦИОННАЯ ОШИБКА
        // 6. System.out.println(F.s); → ДА
        System.out.println("6. F.s = " + F.s);
        // 7. F.imethod(); → НЕТ (метод не static!)
        // F.imethod(); // ← ОШИБКА
        // 8. F.smethod(); → ДА
        F.smethod();

        System.out.println("\n=== ЗАДАНИЕ 2: Test с static ===");
        System.out.println("5! = " + Test.factorial(5));

        System.out.println("\n=== ЗАДАНИЕ 3: Исправленный C ===");
        C.main(args); // вызываем исправленный метод

        System.out.println("\n=== ЗАДАНИЕ 4: Работа с Date[] ===");
        // Оригинал:
        // Date[] dates = new Date[10];
        // System.out.println(dates[0].toString()); // ← NullPointerException!
        // Исправление:
        Date[] dates = new Date[10];
        System.out.println("dates[0] = " + dates[0]); // null
        // dates[0].toString() → ошибка, поэтому инициализируем:
        dates[0] = new Date();
        System.out.println("После инициализации: " + dates[0]);

        System.out.println("\n=== ЗАДАНИЕ 5: Массив Circle, поиск max площади ===");
        Circle[] circles = new Circle[5];
        Random rand = new Random();
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle(rand.nextDouble() * 10 + 1); // радиус от 1 до 11
        }

        Circle maxCircle = circles[0];
        for (Circle c : circles) {
            if (c.getArea() > maxCircle.getArea()) {
                maxCircle = c;
            }
        }
        System.out.println("Круг с максимальной площадью: r = " + maxCircle.getRadius() +
                ", площадь = " + maxCircle.getArea());

        System.out.println("\n=== ЗАДАНИЕ 6: StopWatch + сортировка выбором ===");
        // Генерация 100_000 случайных чисел
        int n = 100_000;
        int[] arr = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt(1000000);
        }

        StopWatch sw = new StopWatch();
        // Сортировка выбором
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Обмен
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        sw.stop();

        System.out.println("Сортировка " + n + " чисел методом выбора заняла: " +
                sw.getElapsedTime() + " мс");

        System.out.println("\n✅ Все задания выполнены.");
    }
}