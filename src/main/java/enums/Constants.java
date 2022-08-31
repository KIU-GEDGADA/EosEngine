package enums;

import math.Vector3f;
import math.Vector4f;

/**
 * Enumerator, holds constants for different parts of the EosEngine:
 */
public enum Constants {
    ;
    /**
     * VERTEX_SIZE - the size of the coordinates vector in floats
     */
    public static final int VERTEX_SIZE = 3;

    /**
     * COLOR_SIZE - the size of the color vector in floats
     */
    public static final int COLOR_SIZE = 4;
    /**
     * COORDINATE_SIZE - the size of texture coordinate vectors in floats
     */
    public static final int COORDINATE_SIZE = 2;
    /**
     * NORMAL_SIZE - the size of the normals vector in floats
     */
    public static final int NORMAL_SIZE = 3;
    /**
     * WIDTH - the default width in pixels
     */
    public static final int WIDTH = 1000;
    /**
     * HEIGHT - the default height in pixels
     */
    public static final int HEIGHT = 1000;
    /**
     * FPS - the default frames per second
     */
    public static final int FPS = 60;
    /**
     * MOUSE_SENSITIVITY - the default mouse sensitivity
     */
    public static final float MOUSE_SENSITIVITY = 0.2f;
    /**
     * MOUSEBTNS - the glfw max key for mouse buttons
     */
    public static final int MOUSEBTNS = 7;

    public static final Vector4f DEFAULT_COLOR = Vector4f.one();

    public static final Vector3f AMBIENT_LIGHT = new Vector3f(0.3f, 0.3f, 0.3f);
    public static final float SPECULAR_POWER = 10f;
}
