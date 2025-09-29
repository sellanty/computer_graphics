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
        // Основное здание вокзала
        drawMainBuilding(g2d);

        // Перрон и пути
        drawPlatformAndTracks(g2d);

        // Навес над перроном
        drawPlatformShelter(g2d);

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
        g2d.drawString("ВОКЗАЛ", 140, 240);
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

        // Шпалы (до краев экрана)
        g2d.setColor(new Color(101, 67, 33));
        for (int i = 0; i < screenWidth / 20 + 1; i++) {
            g2d.fillRect(i * 20, 435, 10, 5);
        }
    }

    private void drawRailwayTracks(Graphics2D g2d) {
        // Рельсы (до краев экрана)
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

    private void drawPlatformShelter(Graphics2D g2d) {
        // Опоры навеса
        g2d.setColor(new Color(150, 150, 150));
        for (int i = 0; i < 4; i++) {
            g2d.fillRect(120 + i * 60, 380, 10, 20);
        }

        // Крыша навеса
        g2d.setColor(new Color(200, 200, 200, 150));
        int[] shelterXPoints = {100, 310, 280, 130};
        int[] shelterYPoints = {380, 380, 360, 360};
        g2d.fillPolygon(shelterXPoints, shelterYPoints, 4);

        // Металлическая конструкция навеса
        g2d.setColor(new Color(100, 100, 100));
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < 5; i++) {
            g2d.drawLine(100 + i * 50, 380, 130 + i * 40, 360);
        }
    }

    private void drawScheduleBoard(Graphics2D g2d) {
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

        // Фонари вдоль путей
        for (int i = 0; i < screenWidth / 100; i++) {
            drawTrackLight(g2d, i * 100 + 50, 420);
            drawTrackLight(g2d, i * 100 + 50, 440);
            drawTrackLight(g2d, i * 100 + 50, 460);
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

    private void drawTrackLight(Graphics2D g2d, int x, int y) {
        // Столб фонаря вдоль путей
        g2d.setColor(new Color(120, 120, 120));
        g2d.fillRect(x, y, 3, 70);

        // Фонарь
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(x - 4, y - 8, 11, 11);
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

    // Новый метод: получить список фонарей вдоль путей
    public List<Point> getTrackLights() {
        return new ArrayList<>(trackLights); // Возвращаем копию для безопасности
    }

    // Новый метод: получить прямоугольники столбов фонарей для проверки столкновений
    public List<Rectangle> getTrackLightCollisionBoxes() {
        List<Rectangle> collisionBoxes = new ArrayList<>();
        for (Point light : trackLights) {
            // Столб фонаря: x, y, width=3, height=40
            collisionBoxes.add(new Rectangle(light.x, light.y, 3, 40));
        }
        return collisionBoxes;
    }

    // Метод для получения позиции путей
    public int getTrackYPosition() {
        return 445; // Центр между рельсами
    }
}