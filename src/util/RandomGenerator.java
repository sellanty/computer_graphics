package util;

import java.awt.*;
import java.util.Random;

public class RandomGenerator {
    private static final Random random = new Random();

    public static Color randomPastelColor() {
        return new Color(
                150 + random.nextInt(100),
                150 + random.nextInt(100),
                150 + random.nextInt(100)
        );
    }

    public static Color randomBrightColor() {
        return new Color(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        );
    }

    public static int randomInt(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}