import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Main {

    // ==============================
    // ЗАДАНИЕ 1: Простой калькулятор (2 поля + 4 операции)
    // ==============================
    static class SimpleCalc extends JFrame {
        private JTextField field1 = new JTextField(10);
        private JTextField field2 = new JTextField(10);
        private JTextField result = new JTextField(10);

        public SimpleCalc() {
            setTitle("Задание 1: Простой калькулятор");
            setLayout(new FlowLayout());
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            result.setEditable(false);

            add(new JLabel("Число 1:")); add(field1);
            add(new JLabel("Число 2:")); add(field2);
            add(new JLabel("Результат:")); add(result);

            // Кнопки операций (анонимные ActionListener)
            JButton addBtn = new JButton("+");
            JButton subBtn = new JButton("-");
            JButton mulBtn = new JButton("×");
            JButton divBtn = new JButton("÷");

            addBtn.addActionListener(e -> calculate('+'));
            subBtn.addActionListener(e -> calculate('-'));
            mulBtn.addActionListener(e -> calculate('*'));
            divBtn.addActionListener(e -> calculate('/'));

            add(addBtn); add(subBtn); add(mulBtn); add(divBtn);

            pack();
            setLocationRelativeTo(null);
        }

        private void calculate(char op) {
            try {
                double a = Double.parseDouble(field1.getText().trim());
                double b = Double.parseDouble(field2.getText().trim());
                double res = switch (op) {
                    case '+' -> a + b;
                    case '-' -> a - b;
                    case '*' -> a * b;
                    case '/' -> b != 0 ? a / b : Double.NaN;
                    default -> Double.NaN;
                };
                result.setText(Double.isNaN(res) ? "Ошибка!" : String.valueOf(res));
            } catch (NumberFormatException ex) {
                result.setText("Неверный ввод!");
            }
        }
    }

    // ==============================
    // ЗАДАНИЕ 2: JComboBox — выбор страны
    // ==============================
    static class CountrySelector extends JFrame {
        private JTextArea info = new JTextArea(5, 30);
        private Map<String, String> countryInfo = new HashMap<>();

        public CountrySelector() {
            setTitle("Задание 2: Информация о стране");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());

            // Наполняем данные
            countryInfo.put("Россия", "Столица: Москва\nНаселение: ~144 млн\nЯзык: Русский");
            countryInfo.put("Франция", "Столица: Париж\nНаселение: ~68 млн\nЯзык: Французский");
            countryInfo.put("Япония", "Столица: Токио\nНаселение: ~125 млн\nЯзык: Японский");
            countryInfo.put("Бразилия", "Столица: Бразилиа\nНаселение: ~215 млн\nЯзык: Португальский");

            String[] countries = countryInfo.keySet().toArray(new String[0]);
            JComboBox<String> combo = new JComboBox<>(countries);
            info.setEditable(false);
            info.setLineWrap(true);
            info.setWrapStyleWord(true);

            combo.addActionListener(e -> {
                String selected = (String) combo.getSelectedItem();
                info.setText(countryInfo.get(selected));
            });

            add(combo, BorderLayout.NORTH);
            add(new JScrollPane(info), BorderLayout.CENTER);

            pack();
            setLocationRelativeTo(null);
        }
    }

    // ==============================
    // ЗАДАНИЕ 3: Полноценное меню
    // ==============================
    static class MenuApp extends JFrame {
        private JTextArea textArea = new JTextArea(10, 40);

        public MenuApp() {
            setTitle("Задание 3: Меню");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            add(new JScrollPane(textArea), BorderLayout.CENTER);

            // Меню
            JMenuBar menuBar = new JMenuBar();

            // Файл
            JMenu fileMenu = new JMenu("Файл");
            JMenuItem saveItem = new JMenuItem("Сохранить");
            JMenuItem exitItem = new JMenuItem("Выйти");

            saveItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Сохранено!"));
            exitItem.addActionListener(e -> System.exit(0));

            fileMenu.add(saveItem);
            fileMenu.add(exitItem);

            // Правка
            JMenu editMenu = new JMenu("Правка");
            JMenuItem copyItem = new JMenuItem("Копировать");
            JMenuItem cutItem = new JMenuItem("Вырезать");
            JMenuItem pasteItem = new JMenuItem("Вставить");

            copyItem.addActionListener(e -> textArea.copy());
            cutItem.addActionListener(e -> textArea.cut());
            pasteItem.addActionListener(e -> textArea.paste());

            editMenu.add(copyItem);
            editMenu.add(cutItem);
            editMenu.add(pasteItem);

            // Справка
            JMenu helpMenu = new JMenu("Справка");
            JMenuItem aboutItem = new JMenuItem("О программе");
            aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Практическая работа №15\nВыполнил: студент"));
            helpMenu.add(aboutItem);

            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(helpMenu);

            setJMenuBar(menuBar);
            pack();
            setLocationRelativeTo(null);
        }
    }

    // ==============================
    // ЗАДАНИЕ 4: Графический калькулятор (сетка)
    // ==============================
    static class GridCalc extends JFrame {
        private JTextField display = new JTextField();
        private double first = 0;
        private String operation = "";
        private boolean start = true;

        public GridCalc() {
            setTitle("Задание 4: Калькулятор");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            display.setFont(new Font("Arial", Font.BOLD, 18));
            display.setHorizontalAlignment(JTextField.RIGHT);
            display.setEditable(false);
            display.setText("0");

            setLayout(new BorderLayout());
            add(display, BorderLayout.NORTH);

            JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));

            String[] buttons = {
                    "C", "±", "%", "÷",
                    "7", "8", "9", "×",
                    "4", "5", "6", "-",
                    "1", "2", "3", "+",
                    "0", ".", "=", ""
            };

            for (String text : buttons) {
                if (text.isEmpty()) continue;
                JButton btn = new JButton(text);
                btn.setFont(new Font("Arial", Font.PLAIN, 16));
                btn.addActionListener(new CalcButtonListener(text));
                panel.add(btn);
            }

            add(panel, BorderLayout.CENTER);
            pack();
            setLocationRelativeTo(null);
        }

        private class CalcButtonListener implements ActionListener {
            private String label;

            public CalcButtonListener(String label) {
                this.label = label;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (label.matches("\\d")) {
                    if (start) {
                        display.setText(label);
                        start = false;
                    } else {
                        display.setText(display.getText().equals("0") ? label : display.getText() + label);
                    }
                } else if (label.equals(".")) {
                    if (!display.getText().contains(".")) {
                        display.setText(display.getText() + ".");
                    }
                } else if ("+-×÷".contains(label)) {
                    first = Double.parseDouble(display.getText());
                    operation = label;
                    start = true;
                } else if (label.equals("=")) {
                    try {
                        double second = Double.parseDouble(display.getText());
                        double result = switch (operation) {
                            case "+" -> first + second;
                            case "-" -> first - second;
                            case "×" -> first * second;
                            case "÷" -> second != 0 ? first / second : Double.NaN;
                            default -> second;
                        };
                        display.setText(Double.isNaN(result) ? "Ошибка!" : String.valueOf(result));
                        start = true;
                        operation = "";
                    } catch (Exception ex) {
                        display.setText("Ошибка!");
                    }
                } else if (label.equals("C")) {
                    display.setText("0");
                    first = 0;
                    operation = "";
                    start = true;
                }
            }
        }
    }

    // ==============================
    // ГЛАВНЫЙ МЕТОД — МЕНЮ ВЫБОРА
    // ==============================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] tasks = {
                    "1. Простой калькулятор",
                    "2. Выбор страны",
                    "3. Окно с меню",
                    "4. Графический калькулятор"
            };

            String choice = (String) JOptionPane.showInputDialog(
                    null,
                    "Выберите задание для запуска:",
                    "Практическая работа №15",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    tasks,
                    tasks[0]
            );

            if (choice == null) return;

            switch (choice) {
                case "1. Простой калькулятор" -> new SimpleCalc().setVisible(true);
                case "2. Выбор страны" -> new CountrySelector().setVisible(true);
                case "3. Окно с меню" -> new MenuApp().setVisible(true);
                case "4. Графический калькулятор" -> new GridCalc().setVisible(true);
            }
        });
    }
}