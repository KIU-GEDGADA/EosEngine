package utils;

import math.Matrix4x4;

import java.nio.FloatBuffer;

public class MathUtils {

    private MathUtils() {
    }

    public static float clamp(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }

    public static float[] flatten2DArray(float[][] cells){
        float[] flat = new float[16];
        for(int i = 0; i < cells.length; i++){
            System.arraycopy(cells[i],0,flat,i*4,4);
        }
        return flat;
    }

    public static FloatBuffer matrixToFloatBuffer(Matrix4x4 matrix){
        return FloatBuffer.wrap(flatten2DArray(Matrix4x4.zero().getMatrix()));
    }
}
