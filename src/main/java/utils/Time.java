package utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Time {

    private static final double INTERVAL = 1.0f;

    private static double presentTime;
    private static double pastTime;
    private static double updateCounter;
    private static int fpsCounter;
    private static int fps;

    private Time(){}

    public static double getPresentTime() {
        return presentTime;
    }

    public static double getPastTime() {
        return pastTime;
    }

    public static void init(){
        pastTime = glfwGetTime();
    }

    public static double getDeltaTime(){
        presentTime = glfwGetTime();
        double deltaTime = presentTime - pastTime;
        pastTime = presentTime;
        updateCounter += deltaTime;
        return deltaTime;
    }

    public static void updateFps(){
        fpsCounter++;
    }

    public static void updateCycle(){
        if (updateCounter >= INTERVAL){
            fps = fpsCounter;
            fpsCounter = 0;
            updateCounter -= INTERVAL;
        }
    }

    public static int getFps(){
        return fps > 0 ? fps : fpsCounter;
    }

    public static void sync(int FPS){
        double actualTime = 1.0f / FPS;
        presentTime = glfwGetTime();
        while(presentTime - pastTime < actualTime){
            Thread.yield();
            try {
                Thread.sleep(1);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            presentTime = glfwGetTime();
        }
    }
}
