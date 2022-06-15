package io;

import enums.Constants;
import math.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

/**
 * This class implements receiving input from Keyboard and mouse
 */
public class Input {
    private static GLFWKeyCallback keyboard;
    private static GLFWCursorPosCallback mouse;
    private static GLFWMouseButtonCallback mbtn;
    private static Vector2f mousePos;
    private static final int[] keys = new int[GLFW_KEY_LAST];
    private static final boolean[] activeKeys = new boolean[GLFW_KEY_LAST];
    private static final boolean[] activeMouseBts = new boolean[Constants.MOUSEBTNS];
    private static final int[] buttons = new int[Constants.MOUSEBTNS];

    /**
     * This function initializes the Input class
     */
    public static void init() {
        mousePos = new Vector2f();
        Input.update();
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                activeKeys[key] = action != GLFW_RELEASE;
                keys[key] = action;
            }
        };
        mouse = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mousePos.x = (float) xpos;
                mousePos.y = (float) ypos;
            }
        };
        mbtn = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                activeMouseBts[button] = action != GLFW_RELEASE;
                buttons[button] = action;
            }
        };
    }

    /**
     * This function checks if a key is pressed, it returns true if it is, false else
     * @param key integer number assigned to a keyboard key in glfw
     * @return true if key is pressed, false else
     */
    public static boolean isKeyPressed(int key) {
        return keys[key] == GLFW_PRESS;
    }

    /**
     * This function checks if a key is being held down, returns true if it is, false if not
     * @param key integer number assigned to a keyboard key in glfw
     * @return true if key is held down, false else
     */
    public static boolean isKeyDown(int key) {
        return !isKeyPressed(key) && activeKeys[key];//if key is NOT currently pressed but the corresponding boolean is true, key is held
    }

    /**
     * This function checks if a key was released, returns true if it is, false if not
     * @param key integer number assigned to a keyboard key in glfw
     * @return true if key was released, false else
     */
    public static boolean isKeyUp(int key) {
        return keys[key] == GLFW_RELEASE;
    }

    /**
     * This function checks if a mouse button is pressed, it returns true if it is, false else
     * @param btn integer number assigned to a mouse button in glfw
     * @return true if a mouse button is pressed, false else
     */
    public static boolean isMousePressed(int btn) {
        return buttons[btn] == GLFW_PRESS;
    }

    /**
     * This function checks if a mouse button is being held down, returns true if it is, false if not
     * @param btn integer number assigned to a mouse button in glfw
     * @return true if mouse button is held down, false else
     */
    public static boolean isMouseDown(int btn) {
        return !isMousePressed(btn) && activeMouseBts[btn];
    }

    /**
     * This function checks if a mouse button was released, returns true if it is, false if not
     * @param btn integer number assigned to a mouse button in glfw
     * @return true if mouse button  was released, false else
     */
    public static boolean isMouseUp(int btn) {
        return buttons[btn] == GLFW_RELEASE;
    }

    /**
     * Getter, this function returns the glfw callback for the keyboard
     * @return the glfw callback for the keyboard
     */
    public static GLFWKeyCallback getKeyboard() {
        return keyboard;
    }

    /**
     * Getter, this function returns the glfw callback for the mouse buttons
     * @return the glfw callback for the mouse buttons
     */
    public static GLFWMouseButtonCallback getMbtn() {
        return mbtn;
    }

    /**
     * Getter, returns the glfw callback for the mouse
     * @return the glfw callback for the mouse
     */
    public static GLFWCursorPosCallback getMouse() {
        return mouse;
    }

    /**
     *This function resets all the keyboard buttons and sets them to -1
     */
    public static void resetKeyboard() {
        for (int i = 0; i < GLFW_KEY_LAST; i++) {
            keys[i] = -1;
        }
    }

    /**
     * This function resets all the mouse buttons and sets them to -1
     */
    public static void resetMouse() {
        for (int i = 0; i < Constants.MOUSEBTNS; i++) {
            buttons[i] = -1;
        }
    }

    /**
     * This function resets both the keyboard keys and the mouse buttons
     */
    public static void update() {
        resetKeyboard();
        resetMouse();
        glfwPollEvents();
    }

    /**
     * Getter, this function returns the x and y coordinates of the mouse on the screen
     * @return a 2 float vector containing x and y coordinates of the mouse onscreen, first element in the vector - X, second element - Y
     */
    public static Vector2f getMousePos() {
        return mousePos;
    }

}
