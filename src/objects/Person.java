package objects;

import java.awt.*;
import java.awt.geom.*;

public class Person {
    private int x, y;
    private double phase;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
        this.phase = Math.random() * Math.PI * 2;
    }

    public void animate(double time) {
        // Легкое покачивание
        phase += 0.05;
    }

    public void draw(Graphics2D g2d) {
        int headY = y + (int)(5 * Math.sin(phase));

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
}