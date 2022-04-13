package utils;

public class MathUtils {

    private MathUtils() {
    }

    public static float clamp(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }
}
