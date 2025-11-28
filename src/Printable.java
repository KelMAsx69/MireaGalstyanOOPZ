interface Printable {
    void print();
}

class Book implements Printable {
    private String title;
    public Book(String title) { this.title = title; }
    @Override
    public void print() {
        System.out.println("Книга: " + title);
    }
}

class Magazine implements Printable {
    private String title;
    public Magazine(String title) { this.title = title; }
    @Override
    public void print() {
        System.out.println("Журнал: " + title);
    }
}

class Shop implements Printable {
    private String name;
    public Shop(String name) { this.name = name; }
    @Override
    public void print() {
        System.out.println("Магазин: " + name);
    }
}