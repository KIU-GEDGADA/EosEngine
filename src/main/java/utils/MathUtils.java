package utils;

import math.Matrix4x4;

/**
 * This class handles mathematical operations
 */
public class MathUtils {

    private MathUtils() {
    }

    /**
     * This function clamps a value within the range, returning the maximum if value is out of range or the value if it is in range
     * @param value the value to clamp
     * @param min the minimum value
     * @param max the maximum value
     * @return the clamped value
     */
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }

    /**
     * This function flattens a Matrix4x4 to a float array
     * @param matrix the Matrix4x4 to flatten
     * @return a float array representation of the Matrix4x4
     */
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
