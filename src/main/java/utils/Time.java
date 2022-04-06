package utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Time {

    private static final float INTERVAL = 1.0f;

    private static float presentTime;
    private static float pastTime;
    private static float updateCounter;
    private static int fpsCounter;
    private static int fps;

    private Time() {
    }

    public static float getPresentTime() {
        return presentTime;
    }

    public static float getPastTime() {
        return pastTime;
    }

    public static void init() {
        pastTime = (float) glfwGetTime();
    }

    public static synchronized float getDeltaTime() {
        presentTime = (float) glfwGetTime();
        float deltaTime = presentTime - pastTime;
        pastTime = presentTime;
        updateCounter += deltaTime;
        return deltaTime;
    }

    public static synchronized void updateFps() {
        fpsCounter++;
    }

    public static synchronized void updateCycle() {
        if (updateCounter >= INTERVAL) {
            fps = fpsCounter;
            fpsCounter = 0;
            updateCounter -= INTERVAL;
        }
    }

    public static int getFps() {
        return fps > 0 ? fps : fpsCounter;
    }

    public static void sync(int FPS) {
        float actualTime = 1.0f / FPS;
        presentTime = (float) glfwGetTime();
        while (presentTime - pastTime < actualTime) {
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
