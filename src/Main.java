import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Random;

public class Main {

    // ==============================
    // ЗАДАНИЕ 1: Счётчик матчей
    // ==============================
    static class MatchScoreApp extends JFrame {
        private int milanScore = 0;
        private int madridScore = 0;
        private JLabel resultLabel;
        private JLabel lastScorerLabel;
        private JLabel winnerLabel;

        public MatchScoreApp() {
            setTitle("Football Match: AC Milan vs Real Madrid");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(5, 1, 10, 10));
            setResizable(false);

            resultLabel = new JLabel("Result: 0 X 0", JLabel.CENTER);
            lastScorerLabel = new JLabel("Last Scorer: N/A", JLabel.CENTER);
            winnerLabel = new JLabel("Winner: DRAW", JLabel.CENTER);

            JButton milanBtn = new JButton("AC Milan");
            JButton madridBtn = new JButton("Real Madrid");

            milanBtn.addActionListener(e -> {
                milanScore++;
                updateUI("AC Milan");
            });

            madridBtn.addActionListener(e -> {
                madridScore++;
                updateUI("Real Madrid");
            });

            add(milanBtn);
            add(madridBtn);
            add(resultLabel);
            add(lastScorerLabel);
            add(winnerLabel);

            pack();
            setLocationRelativeTo(null);
        }

        private void updateUI(String scorer) {
            resultLabel.setText("Result: " + milanScore + " X " + madridScore);
            lastScorerLabel.setText("Last Scorer: " + scorer);
            if (milanScore > madridScore) {
                winnerLabel.setText("Winner: AC Milan");
            } else if (madridScore > milanScore) {
                winnerLabel.setText("Winner: Real Madrid");
            } else {
                winnerLabel.setText("Winner: DRAW");
            }
        }
    }

    // ==============================
    // ЗАДАНИЕ 2: Случайные фигуры
    // ==============================
    abstract static class Shape {
        protected int x, y;
        protected Color color;

        public Shape(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public abstract void draw(Graphics2D g2);
    }

    static class CircleShape extends Shape {
        private int radius = 20;
        public CircleShape(int x, int y, Color color) {
            super(x, y, color);
        }
        @Override
        public void draw(Graphics2D g2) {
            g2.setColor(color);
            g2.fill(new Ellipse2D.Double(x - radius, y - radius, radius * 2, radius * 2));
        }
    }

    static class RectShape extends Shape {
        private int width = 40, height = 30;
        public RectShape(int x, int y, Color color) {
            super(x, y, color);
        }
        @Override
        public void draw(Graphics2D g2) {
            g2.setColor(color);
            g2.fill(new Rectangle2D.Double(x, y, width, height));
        }
    }

    static class ShapesPanel extends JPanel {
        private Shape[] shapes = new Shape[20];
        private Random rand = new Random();

        public ShapesPanel() {
            setPreferredSize(new Dimension(600, 400));
            for (int i = 0; i < 20; i++) {
                int x = rand.nextInt(550);
                int y = rand.nextInt(350);
                Color c = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                if (rand.nextBoolean()) {
                    shapes[i] = new CircleShape(x, y, c);
                } else {
                    shapes[i] = new RectShape(x, y, c);
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            for (Shape s : shapes) {
                s.draw(g2);
            }
        }
    }

    static class RandomShapesApp extends JFrame {
        public RandomShapesApp() {
            setTitle("20 Random Shapes");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            add(new ShapesPanel());
            pack();
            setLocationRelativeTo(null);
        }
    }

    // ==============================
    // ЗАДАНИЕ 3: Отображение изображения из аргумента
    // ==============================
    static class ImageViewerApp extends JFrame {
        public ImageViewerApp(String imagePath) {
            setTitle("Image Viewer");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            File imgFile = new File(imagePath);
            if (!imgFile.exists()) {
                JOptionPane.showMessageDialog(this, "Файл не найден: " + imagePath, "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ImageIcon icon = new ImageIcon(imagePath);
            JLabel label = new JLabel(icon, JLabel.CENTER);
            add(new JScrollPane(label));
            pack();
            setLocationRelativeTo(null);
        }
    }

    // ==============================
    // ЗАДАНИЕ 4: Простая анимация (движущийся круг)
    // ==============================
    static class AnimationPanel extends JPanel {
        private int x = 0;
        private int y = 100;
        private int dx = 2;
        private Timer timer;

        public AnimationPanel() {
            setPreferredSize(new Dimension(600, 200));
            setBackground(Color.WHITE);
            timer = new Timer(20, e -> {
                x += dx;
                if (x <= 0 || x >= getWidth() - 50) {
                    dx = -dx;
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            g.fillOval(x, y, 50, 50);
        }
    }

    static class AnimationApp extends JFrame {
        public AnimationApp() {
            setTitle("Simple Animation");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            add(new AnimationPanel());
            pack();
            setLocationRelativeTo(null);
        }
    }

    // ==============================
    // ГЛАВНЫЙ МЕТОД — МЕНЮ ВЫБОРА
    // ==============================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] options = {
                    "1. Счётчик матчей",
                    "2. Случайные фигуры",
                    "3. Просмотр изображения",
                    "4. Анимация"
            };

            String choice = (String) JOptionPane.showInputDialog(
                    null,
                    "Выберите задание для запуска:",
                    "Практическая работа №5",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice == null) return;

            switch (choice) {
                case "1. Счётчик матчей":
                    new MatchScoreApp().setVisible(true);
                    break;
                case "2. Случайные фигуры":
                    new RandomShapesApp().setVisible(true);
                    break;
                case "3. Просмотр изображения":
                    String path = JOptionPane.showInputDialog("Введите путь к изображению:");
                    if (path != null && !path.trim().isEmpty()) {
                        new ImageViewerApp(path.trim()).setVisible(true);
                    }
                    break;
                case "4. Анимация":
                    new AnimationApp().setVisible(true);
                    break;
            }
        });
    }
}