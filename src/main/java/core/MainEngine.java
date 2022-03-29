package core;

import graphics.Renderer;
import utils.Time;

public class MainEngine implements Runnable {


    public static final int TARGET_FPS = 60;

    private final Thread gameLoopThread;
    protected MainBehaviour behaviour;
    protected Renderer renderer;
    protected Window window;

    public MainEngine(Window window, MainBehaviour mainBehaviour) {
        gameLoopThread = new Thread(this, "Game_Loop_Thread");
        renderer = new Renderer();
        this.window = window;
        this.behaviour = mainBehaviour;
    }

    public void init() throws Exception {
        renderer.init();
        Time.init();
        behaviour.init();

    }

    public void start() {
        gameLoopThread.start();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
            clear();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void clear() {
        renderer.clear();
        window.destroyWindow();
    }

    protected void update(float interval) {
        behaviour.update(interval);


        /*Updating Time */
        Time.updateFps();
        Time.updateCycle();

    }

    protected void render() {
        behaviour.render(window);
    }

    public void gameLoop() {
        float delta;
        while (window.isRunning()) {

            delta = Time.getDeltaTime();

            /* Rendering and actually updating Game */
            update(delta);
            render();


            Time.sync(TARGET_FPS);
        }

    }


}
