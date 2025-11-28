// Command.java
public interface Command {
    void execute();  // Выполняет операцию
    void undo();     // Отменяет операцию
}