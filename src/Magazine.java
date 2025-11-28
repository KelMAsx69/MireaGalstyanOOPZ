// Magazine.java
public class Magazine implements Printable {
    private String title;

    public Magazine(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void print() {
        System.out.println("Журнал: " + title);
    }

    // Статический метод: вывод только журналов
    public static void printMagazines(Printable[] items) {
        System.out.println("=== Только журналы ===");
        for (Printable p : items) {
            if (p instanceof Magazine) {
                p.print();
            }
        }
    }
}