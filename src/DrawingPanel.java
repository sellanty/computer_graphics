import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import objects.*;
import util.*;

public class DrawingPanel extends JPanel {
    private ArrayList<Cloud> clouds;
    private ArrayList<Tree> trees;
    private ArrayList<Person> people;
    private Station station;
    private Train mainTrain;
    private Train oppositeTrain;
    private Sun sun;
    private boolean isAnimating = false;
    private javax.swing.Timer animationTimer;
    private double animationTime = 0;

    public DrawingPanel() {
        setBackground(new Color(135, 206, 235));
        setPreferredSize(new Dimension(1000, 700)); // Добавляем предпочтительный размер
        generateScene();
        setupMouseListeners();
    }

    private void generateScene() {
        Random random = new Random();
        int width = getWidth() > 0 ? getWidth() : 1000; // Запасная ширина

        clouds = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int x = random.nextInt(800);
            int y = 50 + random.nextInt(100);
            int speed = 1 + random.nextInt(3);
            clouds.add(new Cloud(x, y, speed));
        }

        trees = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(900);
            int size = 30 + random.nextInt(40);
            trees.add(new Tree(x, size));
        }

        people = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            int x = 200 + random.nextInt(600);
            int y = 350 + random.nextInt(50);
            people.add(new Person(x, y));
        }

        // Передаем ширину экрана в конструкторы
        station = new Station(width);
        mainTrain = new Train(width, false, 495, 3, Color.RED, Color.BLUE, 0); // 4-й путь (центр на 495)
        oppositeTrain = new Train(width, true, 455, 3, Color.GREEN, Color.ORANGE, 0); // 2-й путь (центр на 455)
        sun = new Sun(800, 80, 60);
    }

    private void setupMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Используем метод contains для проверки клика по главному поезду
                if (mainTrain.contains(e.getX(), e.getY())) {
                    mainTrain.honk();
                    repaint();
                }
            }
        });
    }

    public void toggleAnimation() {
        isAnimating = !isAnimating;

        if (isAnimating) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    private void startAnimation() {
        animationTimer = new javax.swing.Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animationTime += 0.01;
                updateAnimations();
                repaint();
            }
        });
        animationTimer.start();
    }

    private void stopAnimation() {
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    private void updateAnimations() {
        for (Cloud cloud : clouds) {
            cloud.move();
        }

        mainTrain.move();
        oppositeTrain.move();
        sun.pulse(animationTime);

        for (Person person : people) {
            person.animate(animationTime);
        }
    }

    public void regenerateScene() {
        generateScene();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawSky(g2d);
        drawGround(g2d);

        sun.draw(g2d);

        for (Cloud cloud : clouds) {
            cloud.draw(g2d);
        }

        for (Tree tree : trees) {
            tree.draw(g2d);
        }

        station.draw(g2d);
        mainTrain.draw(g2d);
        oppositeTrain.draw(g2d);

        for (Person person : people) {
            person.draw(g2d);
        }
    }

    private void drawSky(Graphics2D g2d) {
        GradientPaint skyGradient = new GradientPaint(
                0, 0, new Color(135, 206, 235),
                0, getHeight()/2, new Color(176, 226, 255)
        );
        g2d.setPaint(skyGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight()/2);
    }

    private void drawGround(Graphics2D g2d) {
        g2d.setColor(new Color(115, 72, 57));
        g2d.fillRect(0, getHeight()/2, getWidth(), getHeight()/2);


    }

    // Добавляем метод для получения предпочтительного размера
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 700);
    }
}
