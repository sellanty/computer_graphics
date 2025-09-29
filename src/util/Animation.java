package util;

public class Animation {
    public static double easeInOut(double t) {
        return t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t;
    }

    public static double bounce(double t) {
        return Math.sin(t * Math.PI * 2);
    }
}