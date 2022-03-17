import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.*;

public class Input {
    private static boolean[] keys = new boolean[350];//more than the number of codes in glfw library. Every true means the key is pressed.//if number of mouse buttons exceeds the default amount;
    private static boolean[] keysLast = new boolean[350];
    private static double[] cursorX = new double[1];
    private static double[] cursorY = new double[1];
    private static int btns = 3;
    private static boolean[] mouseBtns = new boolean[btns];
    private static boolean[] mouseBtnsLast = new boolean[btns];
    public static void setMouseBtns(int buttons) {
        btns = buttons;
    }

    public static boolean isKeyPressed(int key) {
        return glfwGetKey(TestClass.window, key) == GLFW_PRESS;//GLFW_PRESS = 1, true.
    }

    public static boolean isKeyDown(int key) {
        return isKeyPressed(key) && !keysLast[key]; //if the key is pressed and not in the list of pressed keys, it's held.
    }

    public static boolean isKeyUp(int key) {
        return !isKeyPressed(key) && keysLast[key];//if the key is not pressed and in the list of pressed keys, it has been released.
    }

    public static boolean isMousePressed(int btn) {
        return glfwGetMouseButton(TestClass.window, btn) == GLFW_PRESS;//same as isKeyPressed
    }

    public static boolean isMouseDown(int btn) {
        return isMousePressed(btn) && !mouseBtnsLast[btn];
    }

    public static boolean isMouseUp(int btn) {
        return !isMousePressed(btn) && mouseBtnsLast[btn];
    }

    public static ArrayList<double[]> getMouseCoordinates() {
        ArrayList<double[]> coordinates = new ArrayList<>();//outputting an arraylist of two [1] doubles, X and Y coordinates.
        glfwGetCursorPos(TestClass.window, cursorX, cursorY);
        coordinates.add(cursorX);
        coordinates.add(cursorY);
        return coordinates;
    }

    void update() {
        for(int i = 0; i < 350; i++) {
            keysLast[i] = keys[i];
        }
        for(int i = 0; i < 7; i++) {
            mouseBtnsLast[i] = mouseBtns[i];
        }
    }
}