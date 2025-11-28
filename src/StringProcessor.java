// StringProcessor.java
public interface StringProcessor {
    int countChars(String s);
    String oddChars(String s);      // символы на нечётных позициях (1,3,5...) → индексы 0,2,4...
    String reverse(String s);
}