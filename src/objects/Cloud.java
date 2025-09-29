package objects;

import java.awt.*;
import java.awt.geom.*;

public class Cloud {
    private int x, y;
    private int speed;

    public Cloud(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move() {
        x += speed;
        if (x > 1000) {
            x = -100;
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);

        // Рисуем облако из нескольких эллипсов
        g2d.fillOval(x, y, 40, 30);
        g2d.fillOval(x + 20, y - 10, 50, 35);
        g2d.fillOval(x + 50, y, 40, 30);
        g2d.fillOval(x + 30, y + 10, 45, 25);
        g2d.fillOval(x + 30, y + 10, 45, 25);
        g2d.fillOval(x + 30, y + 10, 45, 25);
    }
}