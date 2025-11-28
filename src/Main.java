// Main.java
public class Main {
    public static void main(String[] args) {
        // Создаём автора
        Author author = new Author("Сидорова Алиса", "alice.sidorova@example.com", 'f');

        // Выводим информацию
        System.out.println(author); // Алиса Сидорова(f) at alice.sidorova@example.com

        // Меняем email
        author.setEmail("new.email@domain.org");
        System.out.println("После смены email: " + author);

        // Чтение полей
        System.out.println("Имя: " + author.getName());
        System.out.println("Пол: " + author.getGender());

        // Попытка задать некорректный email — вызовет исключение
        // author.setEmail("invalid-email"); // раскомментируйте, чтобы проверить ошибку

        // Попытка изменить имя или пол — невозможна (нет сеттеров)
    }
}