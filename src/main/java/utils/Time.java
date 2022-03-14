package utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Time {

    //Time fields
    private double presentTime;
    private double pastTime;

    //Counter fields
    private double updateCounter;
    private int fpsCounter;

    //Other fields
    private int FPS;

    //Methods
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
        if (updateCounter >= 1.0f){
            FPS = fpsCounter;
            fpsCounter = 0;
            updateCounter -= 1.0f;
        }
    }

    public int getFPS(){
        return FPS > 0 ? FPS : fpsCounter;
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
