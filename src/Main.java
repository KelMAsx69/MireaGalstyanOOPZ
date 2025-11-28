import java.util.*;

// ==============================
// КЛАСС Person
// ==============================
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}

// ==============================
// СОБСТВЕННАЯ КОЛЛЕКЦИЯ MyArrayList<E>
// ==============================
class MyArrayList<E> {
    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity < 0");
        data = new Object[capacity];
        size = 0;
    }

    // Добавление в конец
    public void add(E element) {
        if (size == data.length) {
            ensureCapacity();
        }
        data[size++] = element;
    }

    // Добавление по индексу (новый метод!)
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (size == data.length) {
            ensureCapacity();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    // Получение по индексу
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) data[index];
    }

    // Удаление по индексу
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E removed = (E) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return removed;
    }

    // Проверка наличия элемента
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) return true;
        }
        return false;
    }

    // Размер коллекции
    public int size() {
        return size;
    }

    // Пуста ли коллекция?
    public boolean isEmpty() {
        return size == 0;
    }

    // Установка элемента по индексу
    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        data[index] = element;
    }

    // Увеличение ёмкости
    private void ensureCapacity() {
        int newCapacity = data.length * 2;
        data = Arrays.copyOf(data, newCapacity);
    }

    // Преобразование в массив
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    // Для удобного вывода
    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}

// ==============================
// ГЛАВНЫЙ КЛАСС — ДЕМОНСТРАЦИЯ
// ==============================
public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЗАДАНИЕ 1: ArrayList ===");
        testArrayList();

        System.out.println("\n=== ЗАДАНИЕ 2: LinkedList ===");
        testLinkedList();

        System.out.println("\n=== ЗАДАНИЕ 3: Своя коллекция MyArrayList ===");
        testMyArrayList();

        System.out.println("\n✅ Все задания выполнены.");
    }

    // === ЗАДАНИЕ 1: ArrayList ===
    public static void testArrayList() {
        ArrayList<String> states = new ArrayList<>();
        states.add("Германия");
        states.add("Франция");
        states.add("Великобритания");
        states.add("Испания");
        states.add(1, "Италия"); // вставка по индексу

        System.out.println("Список: " + states);
        System.out.println("Элемент [1]: " + states.get(1));
        states.set(1, "Дания");
        System.out.println("После set(1, 'Дания'): " + states);
        System.out.println("Размер: " + states.size());
        System.out.println("Содержит 'Германия'? " + states.contains("Германия"));

        states.remove("Германия");
        states.remove(0); // удаляем первый элемент
        System.out.println("После удалений: " + states);

        Object[] array = states.toArray();
        System.out.print("toArray(): ");
        for (Object o : array) System.out.print(o + " ");
        System.out.println();

        // Работа с объектами Person
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Mike"));
        people.add(new Person("Tom"));
        people.add(new Person("Nick"));
        System.out.println("Люди: " + people);
    }

    // === ЗАДАНИЕ 2: LinkedList ===
    public static void testLinkedList() {
        LinkedList<String> states = new LinkedList<>();
        states.add("Германия");
        states.add("Франция");
        states.addLast("Великобритания");
        states.addFirst("Испания");
        states.add(1, "Италия");

        System.out.println("Список: " + states);
        System.out.println("Первый: " + states.getFirst());
        System.out.println("Последний: " + states.getLast());

        states.removeFirst();
        states.removeLast();
        System.out.println("После удаления первого и последнего: " + states);

        LinkedList<Person> people = new LinkedList<>();
        people.add(new Person("Mike"));
        people.addFirst(new Person("Tom"));
        people.addLast(new Person("Nick"));
        people.remove(1);
        System.out.println("Люди (LinkedList): " + people);
        System.out.println("Первый человек: " + people.getFirst());
    }

    // === ЗАДАНИЕ 3: Своя коллекция ===
    public static void testMyArrayList() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add(1, "Blueberry"); // теперь работает!

        System.out.println("MyArrayList: " + list);
        System.out.println("Размер: " + list.size());
        System.out.println("Элемент [2]: " + list.get(2));
        System.out.println("Содержит 'Banana'? " + list.contains("Banana"));

        list.set(0, "Apricot");
        System.out.println("После set(0, 'Apricot'): " + list);

        String removed = list.remove(1);
        System.out.println("Удалено: " + removed);
        System.out.println("После удаления: " + list);

        Object[] arr = list.toArray();
        System.out.print("toArray(): ");
        for (Object o : arr) System.out.print(o + " ");
        System.out.println();

        // С объектами
        MyArrayList<Person> myPeople = new MyArrayList<>();
        myPeople.add(new Person("Alice"));
        myPeople.add(new Person("Bob"));
        System.out.println("MyArrayList<Person>: " + myPeople);
    }
}