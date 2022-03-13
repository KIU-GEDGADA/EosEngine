import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.ArrayList;
import static org.lwjgl.glfw.GLFW.*;
public class InputExperimental {
    private static GLFWKeyCallback keyboard;
    private static GLFWCursorPosCallback mouse;
    private static GLFWMouseButtonCallback mbtn;
    private double xPos, yPos;
    private static int[] keys = new int[350];
    private static boolean[] activeKeys = new boolean[350];
    private static boolean[] activeMouseBts = new boolean[7];
    private static int[] buttons = new int[7];
    public InputExperimental() {
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
    };
    public static boolean isKeyPressed(int key){
        return keys[key]==GLFW_PRESS;
    }
    public static boolean isKeyDown(int key){
        return activeKeys[key];
    }
    public static boolean isKeyUp(int key){
        return keys[key]==GLFW_RELEASE;
    }
    public static boolean isMousePressed(int btn){
        return buttons[btn]==GLFW_PRESS;
    }
    public static boolean isMouseDown(int btn){
        return activeMouseBts[btn];
    }
    public static boolean isMouseUp(int btn){
        return buttons[btn]==GLFW_RELEASE;
    }
    public GLFWKeyCallback getKeyboard(){
        return keyboard;
    }
    public GLFWMouseButtonCallback getMbtn(){
        return mbtn;
    }
    public GLFWCursorPosCallback getMouse(){
        return mouse;
    }
    public static void resetKeyboard(){
        for(int i =0;i<349;i++){
            keys[i]=-1;
        }
    }
    public static void resetMouse(){
        for(int i = 0;i<6;i++){
            buttons[i]=-1;
        }
    }
    public static void update(){
        resetKeyboard();
        resetMouse();
    }
}
