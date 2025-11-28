// Student.java
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private String fullName;
    private Date birthDate;

    public Student(String fullName, Date birthDate) {
        this.fullName = fullName;
        this.birthDate = birthDate;
    }

    public String formatBirthDate(String style) {
        SimpleDateFormat sdf;
        switch (style.toLowerCase()) {
            case "short":
                sdf = new SimpleDateFormat("dd.MM.yy");
                break;
            case "medium":
                sdf = new SimpleDateFormat("dd MMM yyyy");
                break;
            case "full":
                sdf = new SimpleDateFormat("EEEE, d MMMM yyyy", java.util.Locale.getDefault());
                break;
            default:
                throw new IllegalArgumentException("Style must be 'short', 'medium', or 'full'");
        }
        return sdf.format(birthDate);
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", birthDate=" + (birthDate != null ? formatBirthDate("full") : "null") +
                '}';
    }
}