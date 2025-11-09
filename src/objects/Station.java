package objects;

import java.awt.*;
import java.awt.geom.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Station {
    private int screenWidth;
    private List<Point> trackLights;

    public Station(int screenWidth) {
        this.screenWidth = screenWidth;
        this.trackLights = new ArrayList<>();
        initializeTrackLights();
    }

    private void initializeTrackLights() {
        // Создаем список позиций фонарей вдоль путей
        for (int i = 0; i < screenWidth / 100; i++) {
            trackLights.add(new Point(i * 100 + 50, 420));
        }
    }

    public void draw(Graphics2D g2d) {
        // Перрон и пути
        drawPlatformAndTracks(g2d);

        // Основное здание вокзала
        drawMainBuilding(g2d);

        // Табло с расписанием
        drawScheduleBoard(g2d);

        // Освещение
        drawLighting(g2d);
    }

    private void drawMainBuilding(Graphics2D g2d) {
        // Основное здание
        g2d.setColor(new Color(180, 180, 180));
        g2d.fillRect(100, 250, 200, 150);

        // Крыша
        int[] roofXPoints = {80, 320, 220};
        int[] roofYPoints = {250, 250, 200};
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillPolygon(roofXPoints, roofYPoints, 3);

        // Карниз крыши
        g2d.setColor(new Color(100, 50, 30));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(80, 250, 320, 250);

        // Окна
        g2d.setColor(new Color(200, 230, 255));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                g2d.fillRect(120 + i * 50, 280 + j * 40, 30, 25);

                // Рамы окон
                g2d.setColor(new Color(80, 80, 80));
                g2d.drawRect(120 + i * 50, 280 + j * 40, 30, 25);
                g2d.drawLine(135 + i * 50, 280 + j * 40, 135 + i * 50, 305 + j * 40);
                g2d.drawLine(120 + i * 50, 292 + j * 40, 150 + i * 50, 292 + j * 40);
                g2d.setColor(new Color(200, 230, 255));
            }
        }

        // Двери
        g2d.setColor(new Color(101, 67, 33));
        g2d.fillRect(160, 350, 30, 50);

        // Ручка двери
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(175, 375, 5, 5);

        // Вывеска "ВОКЗАЛ"
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("ВОКЗАЛ", 160, 240);
    }

    private void drawPlatformAndTracks(Graphics2D g2d) {
        // Перрон (платформа)
        g2d.setColor(new Color(120, 120, 120));
        g2d.fillRect(50, 400, 300, 25);

        // Бордюр перрона
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(50, 400, 350, 400);

        // Железнодорожные пути (до краев экрана)
        drawRailwayTracks(g2d);


    }

    private void drawRailwayTracks(Graphics2D g2d) {
        // Рельсы
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(3));

        // Первый путь
        g2d.drawLine(0, 430, screenWidth, 430);
        g2d.drawLine(0, 440, screenWidth, 440);

        // Второй путь
        g2d.drawLine(0, 450, screenWidth, 450);
        g2d.drawLine(0, 460, screenWidth, 460);

        // Третий путь
        g2d.drawLine(0, 470, screenWidth, 470);
        g2d.drawLine(0, 480, screenWidth, 480);

        // Четвертрый путь
        g2d.drawLine(0, 490, screenWidth, 490);
        g2d.drawLine(0, 500, screenWidth, 500);

        // Пятый путь
        g2d.drawLine(0, 510, screenWidth, 510);
        g2d.drawLine(0, 520, screenWidth, 520);

        // Поперечные связи между рельсами
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < screenWidth / 35 + 1; i++) {
            g2d.drawLine(i * 35, 430, i * 35, 440);
            g2d.drawLine(i * 35, 450, i * 35, 460);
            g2d.drawLine(i * 35, 470, i * 35, 480);
            g2d.drawLine(i * 35, 490, i * 35, 500);
            g2d.drawLine(i * 35, 510, i * 35, 520);
        }
    }


    private void drawScheduleBoard(Graphics2D g2d) {
        // Основание столба
        g2d.setColor(new Color(100, 100, 100));
        g2d.fillRect(365, 395, 30, 10);

        // Столб для табло
        g2d.setColor(new Color(120, 120, 120));
        g2d.fillRect(370, 360, 20, 40); // Столб под таблом

        // Дополнительные детали столба
        g2d.setColor(new Color(80, 80, 80));
        g2d.drawRect(370, 360, 20, 40);

        // Основание табло
        g2d.setColor(new Color(50, 50, 50));
        g2d.fillRect(320, 280, 120, 80);

        // Табло расписания
        g2d.setColor(new Color(0, 100, 0));
        g2d.fillRect(325, 285, 110, 70);

        // Текст расписания
        g2d.setColor(Color.GREEN);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 12));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());

        g2d.drawString("РАСПИСАНИЕ", 330, 300);
        g2d.drawString("МОСКВА: " + currentTime, 330, 320);
        g2d.drawString("СПБ: " + addHours(currentTime, 4), 330, 335);
        g2d.drawString("КАЗАНЬ: " + addHours(currentTime, 2), 330, 350);

        // Подсветка табло
        g2d.setColor(new Color(0, 255, 0, 50));
        g2d.fillRect(325, 285, 110, 70);
    }

    private void drawLighting(Graphics2D g2d) {
        // Фонари на перроне
        for (int i = 0; i < 3; i++) {
            drawStreetLight(g2d, 130 + i * 80, 380);
        }

    }

    private void drawStreetLight(Graphics2D g2d, int x, int y) {
        // Столб фонаря
        g2d.setColor(new Color(100, 100, 100));
        g2d.fillRect(x, y, 5, 30);

        // Фонарь
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x - 5, y - 10, 15, 15);

        // Свет от фонаря
        GradientPaint lightGradient = new GradientPaint(
                x, y, new Color(255, 255, 200, 100),
                x, y + 50, new Color(255, 255, 200, 0)
        );
        g2d.setPaint(lightGradient);
        g2d.fillOval(x - 15, y, 30, 30);
    }



    private String addHours(String time, int hours) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date date = sdf.parse(time);
            date.setTime(date.getTime() + hours * 3600000);
            return sdf.format(date);
        } catch (Exception e) {
            return "12:00";
        }
    }

    public List<Point> getTrackLights() {
        return new ArrayList<>(trackLights);
    }

    public List<Rectangle> getTrackLightCollisionBoxes() {
        List<Rectangle> collisionBoxes = new ArrayList<>();
        for (Point light : trackLights) {
            collisionBoxes.add(new Rectangle(light.x, light.y, 3, 40));
        }
        return collisionBoxes;
    }

    public int getTrackYPosition() {
        return 445; // Центр между рельсами
    }


    public Rectangle getPlatformZone() {
        return new Rectangle(50, 360, 300, 40); // x, y, width, height
    }

    public Rectangle getShelterZone() {
        return new Rectangle(100, 360, 210, 20); // x, y, width, height
    }

    public Point getRandomPlatformPosition() {
        int x = 60 + (int)(Math.random() * 280); // От 60 до 340
        int y = 370; // Уровень перрона
        return new Point(x, y);
    }

    public Point getRandomShelterPosition() {
        int x = 110 + (int)(Math.random() * 190); // От 110 до 300
        int y = 370; // Уровень перрона под навесом
        return new Point(x, y);
    }

    public boolean isValidPersonPosition(int x, int y) {
        Rectangle platformZone = getPlatformZone();
        Rectangle shelterZone = getShelterZone();

        return platformZone.contains(x, y) || shelterZone.contains(x, y);
    }
}