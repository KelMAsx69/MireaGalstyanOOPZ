import java.util.*;

// ==============================
// КЛАСС Student с Comparable
// ==============================
class Student implements Comparable<Student> {
    private int iDNumber;
    private String fullName;
    private double gpa; // Grade Point Average

    public Student(int iDNumber, String fullName, double gpa) {
        this.iDNumber = iDNumber;
        this.fullName = fullName;
        this.gpa = gpa;
    }

    // Геттеры
    public int getIDNumber() { return iDNumber; } // Исправлено: iD)Number → iDNumber
    public double getGpa() { return gpa; }
    public String getFullName() { return fullName; }

    @Override
    public int compareTo(Student other) {
        // Сортировка по iDNumber (возрастание)
        return Integer.compare(this.iDNumber, other.iDNumber);
    }

    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', gpa=%.2f}", iDNumber, fullName, gpa);
    }
}

// ==============================
// ЗАДАНИЕ 2: Comparator по GPA (убывание)
// ==============================
class SortingStudentsByGPA implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.getGpa(), s1.getGpa()); // убывание
    }

    // Быстрая сортировка (in-place)
    public void quickSort(Student[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Student[] arr, int low, int high) {
        Student pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) <= 0) { // arr[j] >= pivot по GPA
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(Student[] arr, int i, int j) {
        Student temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

// ==============================
// ЗАДАНИЕ 3: Сортировка слиянием (по GPA)
// ==============================
class MergeSorter {
    public static Student[] merge(Student[] a, Student[] b) {
        Student[] result = new Student[a.length + b.length];
        int i = 0, j = 0, k = 0;

        // Сортируем по убыванию GPA → сравниваем a[i] >= b[j]
        while (i < a.length && j < b.length) {
            if (a[i].getGpa() >= b[j].getGpa()) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // Опционально: сортировка перед слиянием (если массивы не отсортированы)
    public static void sortDescendingByGPA(Student[] arr) {
        Arrays.sort(arr, new SortingStudentsByGPA());
    }
}

// ==============================
// ЗАДАНИЕ 1: Сортировка вставками (по ID)
// ==============================
class InsertionSorter {
    public static void insertionSortByID(Student[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Student key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}

// ==============================
// ГЛАВНЫЙ КЛАСС — ДЕМОНСТРАЦИЯ
// ==============================
public class Main {
    public static void main(String[] args) {
        // Создаём тестовые данные
        Student[] students = {
                new Student(103, "Иванов Иван", 3.5),
                new Student(101, "Петров Алексей", 4.2),
                new Student(102, "Сидорова Мария", 3.8)
        };

        Student[] group1 = {
                new Student(201, "Кузнецов", 3.9),
                new Student(202, "Васильева", 4.0)
        };

        Student[] group2 = {
                new Student(203, "Морозов", 3.7),
                new Student(204, "Лебедева", 4.1)
        };

        System.out.println("=== Исходный массив студентов ===");
        printArray(students);

        // === ЗАДАНИЕ 1: Сортировка вставками по ID ===
        Student[] studentsByID = students.clone();
        InsertionSorter.insertionSortByID(studentsByID);
        System.out.println("\n=== Задание 1: Сортировка вставками по ID ===");
        printArray(studentsByID);

        // === ЗАДАНИЕ 2: Быстрая сортировка по GPA (убывание) ===
        Student[] studentsByGPA = students.clone();
        SortingStudentsByGPA comp = new SortingStudentsByGPA();
        comp.quickSort(studentsByGPA, 0, studentsByGPA.length - 1);
        System.out.println("\n=== Задание 2: Быстрая сортировка по GPA (убывание) ===");
        printArray(studentsByGPA);

        // === ЗАДАНИЕ 3: Слияние двух групп по GPA ===
        MergeSorter.sortDescendingByGPA(group1);
        MergeSorter.sortDescendingByGPA(group2);
        Student[] merged = MergeSorter.merge(group1, group2);
        System.out.println("\n=== Задание 3: Слияние двух групп по GPA (убывание) ===");
        printArray(merged);

        System.out.println("\n✅ Все задания выполнены.");
    }

    private static void printArray(Student[] arr) {
        for (Student s : arr) {
            System.out.println(s);
        }
    }
}