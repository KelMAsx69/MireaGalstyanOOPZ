// Book.java
public class Book implements Printable {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void print() {
        System.out.println("Книга: " + title);
    }

    // Статический метод: вывод только книг
    public static void printBooks(Printable[] items) {
        System.out.println("=== Только книги ===");
        for (Printable p : items) {
            if (p instanceof Book) {
                p.print();
            }
        }
    }
}