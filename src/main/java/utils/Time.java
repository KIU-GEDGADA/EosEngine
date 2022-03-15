package utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Time {

    private final double INTERVAL = 1.0f;

    private double presentTime;
    private double pastTime;
    private double updateCounter;
    private int fpsCounter;
    private int fps;

    public void init(){
        pastTime = glfwGetTime();
    }

    public double getDeltaTime(){
        this.presentTime = glfwGetTime();
        double deltaTime = presentTime - pastTime;
        pastTime = presentTime;
        updateCounter += deltaTime;
        return deltaTime;
    }

    public void updateFPS(){
        fpsCounter++;
    }

    public void updateCycle(){
        if (updateCounter >= INTERVAL){
            fps = fpsCounter;
            fpsCounter = 0;
            updateCounter -= INTERVAL;
        }
    }

    public int getFps(){
        return fps > 0 ? fps : fpsCounter;
    }

    public void sync(int FPS){
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
