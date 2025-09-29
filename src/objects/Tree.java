package objects;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Tree {
    private int x, size;
    private Color trunkColor;
    private Color leavesColor;

    public Tree(int x, int size) {
        this.x = x;
        this.size = size;

        Random random = new Random();
        trunkColor = new Color(101 + random.nextInt(50), 67 + random.nextInt(30), 33 + random.nextInt(20));
        leavesColor = new Color(0, 100 + random.nextInt(100), 0);
    }

    public void draw(Graphics2D g2d) {
        int y = 350 - size;

        // Ствол
        g2d.setColor(trunkColor);
        g2d.fillRect(x, y + size/2, size/4, size/2);

        // Крона
        g2d.setColor(leavesColor);
        g2d.fillOval(x - size/3, y, size, size * 2/3);
    }
}