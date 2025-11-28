// UndoableStringBuilder.java
import java.util.*;

public class UndoableStringBuilder {
    private StringBuilder sb = new StringBuilder();
    private Stack<Command> history = new Stack<>();

    // Метод append с поддержкой undo
    public UndoableStringBuilder append(String str) {
        if (str == null) str = "null";
        int previousLength = sb.length();

        String finalStr = str;
        Command appendCommand = new Command() {
            @Override
            public void execute() {
                sb.append(finalStr);
            }

            @Override
            public void undo() {
                sb.setLength(previousLength); // Восстанавливаем длину
            }
        };

        appendCommand.execute();
        history.push(appendCommand);
        return this;
    }

    // Метод delete с поддержкой undo
    public UndoableStringBuilder delete(int start, int end) {
        if (start < 0 || end > sb.length() || start > end) {
            throw new IndexOutOfBoundsException("Некорректные индексы");
        }
        String deletedText = sb.substring(start, end);

        Command deleteCommand = new Command() {
            @Override
            public void execute() {
                sb.delete(start, end);
            }

            @Override
            public void undo() {
                sb.insert(start, deletedText);
            }
        };

        deleteCommand.execute();
        history.push(deleteCommand);
        return this;
    }

    // Отмена последней операции
    public void undo() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Нет операций для отмены.");
        }
    }

    // Геттер текущего значения строки (для вывода)
    @Override
    public String toString() {
        return sb.toString();
    }
}