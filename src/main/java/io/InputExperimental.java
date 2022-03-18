package io;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;
public class InputExperimental {
    private static GLFWKeyCallback keyboard;
    private static GLFWCursorPosCallback mouse;
    private static GLFWMouseButtonCallback mbtn;
    private static double xPos, yPos;
    private static final int MOUSEBTNS = 7;
    private static final int[] keys = new int[GLFW_KEY_LAST];//every key with its corresponding state, 1 or 0
    private static final boolean[] activeKeys = new boolean[GLFW_KEY_LAST];//currently pressed keys list
    private static final boolean[] activeMouseBts = new boolean[MOUSEBTNS];//same as above list
    private static final int[] buttons = new int[MOUSEBTNS];//same as keys
    public static void init(){
        InputExperimental.update();
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                activeKeys[key]=action!=GLFW_RELEASE;
                keys[key]=action;
            }
        };
        mouse = new GLFWCursorPosCallback(){
            @Override
            public void invoke(long window, double xpos, double ypos) {
                xPos=xpos;
                yPos=ypos;
            }
        };
        mbtn = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                activeMouseBts[button]=action!=GLFW_RELEASE;
                buttons[button]=action;
            }
        };
    }
    public static boolean isKeyPressed(int key){
        return keys[key]==GLFW_PRESS;
    }
    public static boolean isKeyDown(int key){
        return !isKeyPressed(key)&&activeKeys[key];//if key is
    }
    public static boolean isKeyUp(int key){
        return keys[key]==GLFW_RELEASE;
    }
    public static boolean isMousePressed(int btn){
        return buttons[btn]==GLFW_PRESS;
    }
    public static boolean isMouseDown(int btn){
        return !isMousePressed(btn)&&activeMouseBts[btn];
    }
    public static boolean isMouseUp(int btn){
        return buttons[btn]==GLFW_RELEASE;
    }
    public static GLFWKeyCallback getKeyboard(){
        return keyboard;
    }
    public static GLFWMouseButtonCallback getMbtn(){
        return mbtn;
    }
    public static GLFWCursorPosCallback getMouse(){
        return mouse;
    }
    public static void resetKeyboard(){
        for(int i =0;i<GLFW_KEY_LAST;i++){
            keys[i]=-1;
        }
    }
    public static void resetMouse(){
        for(int i = 0;i<MOUSEBTNS;i++){
            buttons[i]=-1;
        }
    }
    public static void update(){
        resetKeyboard();
        resetMouse();
    }
    public static double getXPos(){
        return xPos;
    }
    public static double getyPos(){
        return yPos;
    }
}
