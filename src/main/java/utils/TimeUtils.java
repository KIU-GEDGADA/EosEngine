package utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

/**
 * This class implements all utility functions handling the use, measuring and calculations involving time
 */
public class TimeUtils {

    private static float INTERVAL;

    private static float presentTime;
    private static float pastTime;
    private static float deltaTime;

    // Counters
    private static float updateCounter;
    private static float frameCounter;
    private static int fpsCounter;
    private static int fps;

    /**
     * Class Constructor
     */
    private TimeUtils() {
    }

    /**
     * Getter, returns the time the current frame was drawn
     * @return the time the current frame was drawn
     */
    public static float getPresentTime() {
        return presentTime;
    }

    /**
     * Getter, returns the time the past frame was drawn
     * @return the time the past frame was drawn
     */
    public static float getPastTime() {
        return pastTime;
    }

    /**
     * This function calculates and returns the frames per second of the application
     * @return the frames per second of the application
     */
    public static int getFps() {
        return fps > 0 ? fps : fpsCounter;
    }

    /**
     * Getter, returns the elapsed time since the last frame was drawn
     * @return the elapsed time since the last frame was drawn
     */
    public static float getDeltaTime() {
        return deltaTime;
    }

    /**
     * This function initializes the TimeUtils class and sets the fps at which the game should run
     * @param fps the target fps of the application
     */
    public static void init(int fps) {
        INTERVAL = 1.0f / fps;
        pastTime = (float) glfwGetTime();
    }

    /**
     * This function calculates and updates the deltaTime parameter of the TimeUtils class
     */
    public static void updateDeltaTime() {
        presentTime = (float) glfwGetTime();
        deltaTime = presentTime - pastTime;
        pastTime = presentTime;
        updateCounter += deltaTime;
        frameCounter += deltaTime;
    }

    /**
     * This function updates the fps parameter of the TimeUtils class
     */
    public static void updateFps() {
        fpsCounter++;
    }

    /**
     * This function checks if an update is due
     * @return true if an update is due, false otherwise
     */
    public static boolean checkCycle() {
        return updateCounter >= INTERVAL;
    }

    /**
     * This function updates the frames per second of the application
     */
    public static void updateCycle() {
        updateCounter -= INTERVAL;
        if (frameCounter >= 1.0f) {
            frameCounter = 0;
            fps = fpsCounter;
            fpsCounter = 0;
        }
    }

    /**
     * This function synchronizes the time across different threads
     */
    public static void sync() {
        presentTime = (float) glfwGetTime();
        while (presentTime - pastTime < INTERVAL) {
            Thread.yield();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            presentTime = (float) glfwGetTime();
        }
    }

}
