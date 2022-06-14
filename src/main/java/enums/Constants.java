package enums;

/**
 * Enumerator, holds constants for different parts of the EosEngine:
 * VERTEX_SIZE - the size of the coordinates vector in floats
 * COLOR_SIZE - the size of the color vector in floats
 * COORDINATE_SIZE - the size of texture coordinate vectors in floats
 * NORMAL_SIZE - the size of the normals vector in floats
 * WIDTH - the default width in pixels
 * HEIGHT - the default height in pixels
 * FPS - the default frames per second
 * MOUSE_SENSITIVITY - the default mouse sensitivity
 * MOUSEBTNS - the glfw max key for mouse buttons
 */
public enum Constants {
    ;
    public static final int VERTEX_SIZE = 3;
    public static final int COLOR_SIZE = 4;
    public static final int COORDINATE_SIZE = 2;
    public static final int NORMAL_SIZE = 3;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static final int FPS = 60;
    public static final float MOUSE_SENSITIVITY = 0.2f;
    public static final int MOUSEBTNS = 7;
}
