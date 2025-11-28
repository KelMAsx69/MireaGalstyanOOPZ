// Author.java
public class Author {
    private String name;
    private String email;
    private char gender;

    // Конструктор
    public Author(String name, String email, char gender) {
        this.name = name;
        setEmail(email); // валидация через сеттер
        // Проверка корректности пола
        if (gender != 'm' && gender != 'f' && gender != 'u') {
            throw new IllegalArgumentException("Gender must be 'm', 'f', or 'u'");
        }
        this.gender = gender;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

    // Сеттер только для email
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return name + "(" + gender + ") at " + email;
    }
}