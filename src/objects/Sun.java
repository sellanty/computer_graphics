package objects;

import java.awt.*;
import java.awt.geom.*;

public class Sun {
    private int x, y, size;
    private float brightness = 1.0f;

    public Sun(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void pulse(double time) {
        brightness = 0.8f + 0.2f * (float)Math.sin(time * 2);
    }

    public void draw(Graphics2D g2d) {
        // Лучи
        g2d.setColor(new Color(1.0f, 1.0f, 0.0f, brightness * 0.6f));
        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30);
            int x1 = x + size/2 + (int)(size * 0.7 * Math.cos(angle));
            int y1 = y + size/2 + (int)(size * 0.7 * Math.sin(angle));
            int x2 = x + size/2 + (int)(size * 1.2 * Math.cos(angle));
            int y2 = y + size/2 + (int)(size * 1.2 * Math.sin(angle));

            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Солнце
        GradientPaint gradient = new GradientPaint(
                x, y, new Color(1.0f, 1.0f, 0.0f, brightness),
                x + size, y + size, new Color(1.0f, 0.5f, 0.0f, brightness)
        );
        g2d.setPaint(gradient);
        g2d.fillOval(x, y, size, size);
    }
}