package objects;

import java.awt.*;
import java.awt.geom.*;

public class Train {
    private int x;
    private int y;
    private int speed;
    private boolean isHonking = false;
    private int honkTimer = 0;
    private int screenWidth;
    private boolean isOpposite;
    private Color trainColor;
    private Color wagonColor;
    private int trackOffset;

    // Константы для позиционирования относительно базовой линии
    public static final int BASE_Y = 450;
    public static final int TRACK_SPACING = 20;

    public Train(int screenWidth) {
        this(screenWidth, false, BASE_Y, 3, Color.RED, Color.BLUE, 0);
    }

    public Train(int screenWidth, boolean isOpposite, int y, int speed,
                 Color trainColor, Color wagonColor, int trackOffset) {
        this.screenWidth = screenWidth;
        this.isOpposite = isOpposite;
        this.y = y;
        this.speed = speed;
        this.trainColor = trainColor;
        this.wagonColor = wagonColor;
        this.trackOffset = trackOffset;

        if (isOpposite) {
            this.x = -200;
        } else {
            this.x = screenWidth + 200;
        }
    }

    public void move() {
        if (isOpposite) {
            x += speed;

            // Если поезд полностью уехал за правый край экрана
            if (x > screenWidth + 400) {
                x = -400;
            }
        } else {
            x -= speed;

            // Если поезд полностью уехал за левый край экрана
            if (x < -400) {
                x = screenWidth + 400;
            }
        }
    }

    public void honk() {
        isHonking = true;
        honkTimer = 30;
    }

    public void draw(Graphics2D g2d) {
        if (isOpposite) {
            drawOppositeTrain(g2d);
        } else {
            drawMainTrain(g2d);
        }
    }

    private void drawMainTrain(Graphics2D g2d) {
        int drawY = y + trackOffset;

        // Сначала рисуем колеса
        drawMainTrainWheels(g2d, drawY);

        // Затем рисуем сам поезд
        drawMainTrainBody(g2d, drawY);
    }

    private void drawMainTrainWheels(Graphics2D g2d, int drawY) {
        // Колеса локомотива
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            g2d.fillOval(x + 5 + i * 25, drawY - 1, 12, 12); // Справа от локомотива
        }

        // Колеса вагонов
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                g2d.fillOval(x + 120 + i * 65 + j * 25, drawY - 1, 12, 12); // Справа от локомотива
            }
        }
    }

    private void drawMainTrainBody(Graphics2D g2d, int drawY) {
        // Локомотив
        g2d.setColor(trainColor);
        g2d.fillRoundRect(x, drawY - 25, 100, 30, 10, 10);

        // Кабина машиниста
        g2d.setColor(trainColor.darker());
        g2d.fillRect(x + 80, drawY - 35, 20, 10);

        // Окно кабины
        g2d.setColor(new Color(200, 255, 255));
        g2d.fillRect(x + 82, drawY - 33, 16, 6);

        // Труба
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x + 20, drawY - 35, 12, 10);

// Дым из трубы
        if (Math.random() > 0.3) {
            g2d.setColor(new Color(150, 150, 150, 200));
            g2d.fillOval(x + 15, drawY - 50, 16, 12); // Дым справа от трубы
            g2d.fillOval(x + 10, drawY - 60, 12, 10); // Дым справа от трубы
        }

        // Вагоны
        g2d.setColor(wagonColor);
        for (int i = 0; i < 5; i++) {
            g2d.fillRoundRect(x + 110 + i * 65, drawY - 25, 60, 30, 8, 8);

            // Окна вагонов
            g2d.setColor(new Color(200, 255, 255));
            for (int j = 0; j < 2; j++) {
                g2d.fillRect(x + 120 + i * 65 + j * 25, drawY - 20, 15, 10);
            }
            g2d.setColor(wagonColor);
        }

        // Соединения между вагонами
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < 5; i++) {
            g2d.drawLine(x + 100 + i * 65, drawY - 10, x + 110 + i * 65, drawY - 10);
        }

        // Сигнал
        if (isHonking) {
            g2d.setColor(Color.YELLOW);
            if (honkTimer % 5 < 3) {
                g2d.fillOval(x + 90, drawY - 35, 8, 8);
            }
            honkTimer--;
            if (honkTimer <= 0) {
                isHonking = false;
            }
        }

        // Фары
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x + 5, drawY - 20, 6, 6);
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x + 5, drawY - 10, 6, 6);
    }

    private void drawOppositeTrain(Graphics2D g2d) {
        int drawY = y + trackOffset;

        // Сначала рисуем колеса
        drawOppositeTrainWheels(g2d, drawY);

        // Затем рисуем сам поезд
        drawOppositeTrainBody(g2d, drawY);
    }

    private void drawOppositeTrainWheels(Graphics2D g2d, int drawY) {
        // Колеса локомотива
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            g2d.fillOval(x + 80 - i * 25, drawY - 1, 12, 12); // Было x + 68, drawY - 10
        }

        // Колеса вагонов
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                g2d.fillOval(x - 55  - i * 65 + j * 25, drawY - 1, 12, 12); // Было x - 50, drawY - 10
            }
        }
    }

    private void drawOppositeTrainBody(Graphics2D g2d, int drawY) {
        // Локомотив
        g2d.setColor(trainColor);
        g2d.fillRoundRect(x, drawY - 25, 100, 30, 10, 10);

        // Кабина машиниста
        g2d.setColor(trainColor.darker());
        g2d.fillRect(x, drawY - 35, 20, 10);

        // Окно кабины
        g2d.setColor(new Color(200, 255, 255));
        g2d.fillRect(x + 2, drawY - 33, 16, 6);

        // Труба
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x + 68, drawY - 35, 12, 10);

        // Дым из трубы
        if (Math.random() > 0.3) {
            g2d.setColor(new Color(150, 150, 150, 200));
            g2d.fillOval(x + 66, drawY - 50, 16, 12);
            g2d.fillOval(x + 62, drawY - 60, 12, 10);
        }

        // Вагоны
        g2d.setColor(wagonColor);
        for (int i = 0; i < 3; i++) {
            g2d.fillRoundRect(x - 65 - i * 65, drawY - 25, 60, 30, 8, 8);

            // Окна вагонов
            g2d.setColor(new Color(200, 255, 255));
            for (int j = 0; j < 2; j++) {
                g2d.fillRect(x - 57 - i * 65 + j * 25, drawY - 20, 15, 10);
            }
            g2d.setColor(wagonColor);
        }

        // Соединения между вагонами
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < 3; i++) {
            g2d.drawLine(x - i * 65, drawY - 10, x - 10 - i * 65, drawY - 10);
        }

        // Сигнал
        if (isHonking) {
            g2d.setColor(Color.YELLOW);
            if (honkTimer % 5 < 3) {
                g2d.fillOval(x + 2, drawY - 35, 8, 8);
            }
            honkTimer--;
            if (honkTimer <= 0) {
                isHonking = false;
            }
        }

        // Фары
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x + 89, drawY - 20, 6, 6);
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x + 89, drawY - 10, 6, 6);
    }

    // Метод для проверки, находится ли поезд в зоне клика
    public boolean contains(int mouseX, int mouseY) {
        if (isOpposite) {
            return mouseX >= x - 195 && mouseX <= x + 100 &&
                    mouseY >= y + trackOffset - 35 && mouseY <= y + trackOffset + 5;
        } else {
            return mouseX >= x && mouseX <= x + 300 &&
                    mouseY >= y + trackOffset - 35 && mouseY <= y + trackOffset + 5;
        }
    }

}