package objects;

import java.awt.*;
import java.awt.geom.*;

public class Person {
    private int x, y;
    private double phase;
    private boolean isOnPlatform;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
        this.phase = Math.random() * Math.PI * 2;
        this.isOnPlatform = true;
    }

    public void animate(double time) {
        // Легкое покачивание на месте
        phase += 0.05;
    }

    public void draw(Graphics2D g2d) {
        int headY = y + (int)(2 * Math.sin(phase));

        // Голова
        g2d.setColor(new Color(255, 204, 153));
        g2d.fillOval(x, headY, 10, 10);

        // Тело
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x + 3, headY + 10, 4, 15);

        // Ноги
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x + 3, headY + 25, x, headY + 35);
        g2d.drawLine(x + 7, headY + 25, x + 10, headY + 35);

        // Руки
        g2d.drawLine(x, headY + 15, x + 10, headY + 15);
    }


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setOnPlatform(boolean onPlatform) {
        this.isOnPlatform = onPlatform;
    }

    public boolean isOnPlatform() {
        return isOnPlatform;
    }

    // Метод для проверки, находится ли человек в допустимой зоне перрона
    public boolean isInPlatformZone(int platformStartX, int platformEndX, int platformY) {
        return x >= platformStartX && x <= platformEndX &&
                y >= platformY - 50 && y <= platformY + 20;
    }

    // Метод для корректировки позиции, если человек находится на путях
    public void adjustPositionIfOnTracks(Station station) {
        if (y > 400) {
            y = 380;
            isOnPlatform = true;
        }

        // Если человек находится за пределами зоны вокзала, перемещаем его в допустимую зону
        if (x < 50 || x > 350) {
            x = 100 + (int)(Math.random() * 200); // Случайная позиция в зоне вокзала
        }
    }
}