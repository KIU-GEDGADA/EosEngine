import org.lwjgl.*;
import org.lwjgl.glfw.*;
import utils.Time;

public class MainEngine implements Runnable {


    private final Thread gameLoopThread;
    private MainBehaviour behaviour;
    private Window window;
    private Time timer;

    public MainEngine(String windowName, int width, int height, MainBehaviour mainBehaviour) {
        gameLoopThread = new Thread(this, "Game_Loop_Thread");
        //     window = new Window(windowName, width, height); UNCOMMENT THIS
        this.behaviour = mainBehaviour;
    }

    public void start() {
        gameLoopThread.start();
    }

    @Override
    public void run() {
        try {
              start();
              gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    protected void input() {
        behaviour.input(window);
    }

    protected void update(float interval) {
        behaviour.update(interval);
    }

    protected void render() {
        behaviour.render(window);
        window.update();
    }
    public void gameLoop() {
        double secsPerUpdate = 1.0d / 30.0d;
        double previous = Time.getPresentTime();
        double steps = 0.0;
        while (true) {
            double loopStartTime = Time.getDeltaTime();
            double elapsed = loopStartTime - previous;
            previous = Time.getPresentTime();
            steps += elapsed;

            //

            while (steps >= secsPerUpdate) {
             //   updateGameState();
                steps -= secsPerUpdate;
            }

            render();
            Time.sync(60);
        }
    }
    private void sync(double loopStartTime) {
        float loopSlot = 1f / 50;
        double endTime = loopStartTime + loopSlot;
        while(timer.getDeltaTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {}
        }
    }

}
