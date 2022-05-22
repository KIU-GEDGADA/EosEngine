package utils;

import math.Matrix4x4;

import java.util.Arrays;
import java.util.List;

public class MathUtils {

    private MathUtils() {
    }

    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    public static float[] flatten2DArray(Matrix4x4 matrix) {
        float[] result = new float[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i * 4 + j] = matrix.getCell(j, i);
            }
        }
        return result;
    }
}
