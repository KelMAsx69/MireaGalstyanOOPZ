// Main.java
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // =======================
        // ЗАДАНИЕ 1
        // =======================
        System.out.println("=== Задание 1: Информация о разработчике ===");
        String developer = "Иванов Иван Иванович";
        Date assignmentReceived = new Date(); // текущее время
        // Сдача — через 7 дней
        Calendar cal = Calendar.getInstance();
        cal.setTime(assignmentReceived);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date assignmentDue = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        System.out.println("Разработчик: " + developer);
        System.out.println("Дата получения задания: " + sdf.format(assignmentReceived));
        System.out.println("Дата сдачи задания:       " + sdf.format(assignmentDue));
        System.out.println();

        // =======================
        // ЗАДАНИЕ 2
        // =======================
        System.out.println("=== Задание 2: Сравнение дат ===");
        System.out.print("Введите дату в формате dd.MM.yyyy: ");
        String inputDateStr = sc.nextLine();
        try {
            Date userDate = new SimpleDateFormat("dd.MM.yyyy").parse(inputDateStr);
            Date now = new Date();
            if (userDate.after(now)) {
                System.out.println("Введённая дата — в будущем.");
            } else if (userDate.before(now)) {
                System.out.println("Введённая дата — в прошлом.");
            } else {
                System.out.println("Введённая дата — сегодня.");
            }
        } catch (ParseException e) {
            System.out.println("Неверный формат даты!");
        }
        System.out.println();

        // =======================
        // ЗАДАНИЕ 3
        // =======================
        System.out.println("=== Задание 3: Student с датой рождения ===");
        try {
            Date birth = new SimpleDateFormat("dd.MM.yyyy").parse("15.03.2000");
            Student stud = new Student("Петров Алексей", birth);
            System.out.println(stud);
            System.out.println("Короткий формат: " + stud.formatBirthDate("short"));
            System.out.println("Средний формат:  " + stud.formatBirthDate("medium"));
            System.out.println("Полный формат:   " + stud.formatBirthDate("full"));
        } catch (ParseException e) {
            System.out.println("Ошибка парсинга даты рождения.");
        }
        System.out.println();

        // =======================
        // ЗАДАНИЕ 4
        // =======================
        System.out.println("=== Задание 4: Создание Date и Calendar из ввода ===");
        System.out.print("Введите: Год Месяц(1-12) День Часы Минуты (через пробел): ");
        try {
            int year = sc.nextInt();
            int month = sc.nextInt(); // 1-12
            int day = sc.nextInt();
            int hour = sc.nextInt();
            int minute = sc.nextInt();

            // Calendar (месяцы с 0!)
            Calendar calIn = Calendar.getInstance();
            calIn.set(Calendar.YEAR, year);
            calIn.set(Calendar.MONTH, month - 1); // Calendar.MONTH — 0-based!
            calIn.set(Calendar.DAY_OF_MONTH, day);
            calIn.set(Calendar.HOUR_OF_DAY, hour);
            calIn.set(Calendar.MINUTE, minute);
            calIn.set(Calendar.SECOND, 0);
            calIn.set(Calendar.MILLISECOND, 0);

            Date dateFromCal = calIn.getTime();
            System.out.println("Созданный Date: " + dateFromCal);
            System.out.println("Созданный Calendar: " + calIn.getTime());
        } catch (Exception e) {
            System.out.println("Ошибка ввода данных!");
        }
        System.out.println();

        // =======================
        // ЗАДАНИЕ 5
        // =======================
        System.out.println("=== Задание 5: Сравнение ArrayList vs LinkedList ===");
        int N = 100_000;

        // --- Добавление в конец ---
        List<Integer> al = new ArrayList<>();
        List<Integer> ll = new LinkedList<>();

        long start = System.nanoTime();
        for (int i = 0; i < N; i++) al.add(i);
        long timeALAddEnd = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < N; i++) ll.add(i);
        long timeLLAddEnd = System.nanoTime() - start;

        // --- Добавление в начало ---
        al.clear(); ll.clear();

        start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) al.add(0, i); // уменьшаем N, иначе долго
        long timeALAddStart = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 10_000; i++) ll.add(0, i);
        long timeLLAddStart = System.nanoTime() - start;

        // --- Удаление из начала ---
        start = System.nanoTime();
        while (!al.isEmpty()) al.remove(0);
        long timeALRemoveStart = System.nanoTime() - start;

        start = System.nanoTime();
        while (!ll.isEmpty()) ll.remove(0);
        long timeLLRemoveStart = System.nanoTime() - start;

        // --- Поиск по значению (в середине) ---
        al = new ArrayList<>();
        ll = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            al.add(i);
            ll.add(i);
        }
        int searchValue = N / 2;

        start = System.nanoTime();
        al.indexOf(searchValue);
        long timeALSearch = System.nanoTime() - start;

        start = System.nanoTime();
        ll.indexOf(searchValue);
        long timeLLSearch = System.nanoTime() - start;

        // Вывод результатов
        System.out.printf("Операция                 | ArrayList       | LinkedList\n");
        System.out.printf("--------------------------|-----------------|----------------\n");
        System.out.printf("Добавление в конец (%d)   | %12d нс | %12d нс\n", N, timeALAddEnd, timeLLAddEnd);
        System.out.printf("Добавление в начало (10k)| %12d нс | %12d нс\n", timeALAddStart, timeLLAddStart);
        System.out.printf("Удаление из начала       | %12d нс | %12d нс\n", timeALRemoveStart, timeLLRemoveStart);
        System.out.printf("Поиск (indexOf)          | %12d нс | %12d нс\n", timeALSearch, timeLLSearch);

        sc.close();
        System.out.println("\n✅ Все задания выполнены.");
    }
}