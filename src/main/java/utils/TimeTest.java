package utils;


import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class TimeTest {

    public final float INTERVAL = 1.0f / 60f;

    public double presentTime;
    public double pastTime;
    public double updateCounter;
    public int fpsCounter;
    public int fps;

    public TimeTest() {
    }

    public double getPresentTime() {
        return presentTime;
    }

    public double getPastTime() {
        return pastTime;
    }

    public void init() {
        pastTime = glfwGetTime();
    }

    public double getDeltaTime() {
        presentTime = glfwGetTime();
        double deltaTime = presentTime - pastTime;
        pastTime = presentTime;
        updateCounter += deltaTime;
        return deltaTime;
    }

    public void updateFps() {
        fpsCounter++;
    }

    public boolean checkCycle() {
        return updateCounter >= INTERVAL;
    }

    public void updateCycle() {
        fps = fpsCounter;
        fpsCounter = 0;
        updateCounter -= INTERVAL;
    }

    public int getFps() {
        return fps > 0 ? fps : fpsCounter;
    }

    public void sync(int FPS) {
        float actualTime = 1.0f / FPS;
        presentTime = glfwGetTime();
        while (presentTime - pastTime < actualTime) {
            Thread.yield();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            presentTime = glfwGetTime();
        }
    }
}

