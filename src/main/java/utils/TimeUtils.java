package utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

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

    private TimeUtils() {
    }

    public static float getPresentTime() {
        return presentTime;
    }

    public static float getPastTime() {
        return pastTime;
    }

    public static int getFps() {
        return fps > 0 ? fps : fpsCounter;
    }

    public static float getDeltaTime() {
        return deltaTime;
    }

    public static void init(int fps) {
        INTERVAL = 1.0f / fps;
        pastTime = (float) glfwGetTime();
    }


    public static void updateDeltaTime() {
        presentTime = (float) glfwGetTime();
        deltaTime = presentTime - pastTime;
        pastTime = presentTime;
        updateCounter += deltaTime;
    }

    public static void updateFps() {
        frameCounter += TimeUtils.getDeltaTime();
        fpsCounter++;
    }

    public static boolean checkCycle() {
        return updateCounter >= INTERVAL;
    }

    public static void updateCycle() {
        if (frameCounter >= 1.0f) {
            frameCounter = 0;
            fps = fpsCounter;
            fpsCounter = 0;
        }
        updateCounter -= INTERVAL;
    }

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
