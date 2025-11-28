// ProcessStrings.java
public class ProcessStrings implements StringProcessor {
    @Override
    public int countChars(String s) {
        return s == null ? 0 : s.length();
    }

    @Override
    public String oddChars(String s) {
        if (s == null || s.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i += 2) { // индексы 0, 2, 4...
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    @Override
    public String reverse(String s) {
        if (s == null) return null;
        return new StringBuilder(s).reverse().toString();
    }
}