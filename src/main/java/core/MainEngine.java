package core;

import graphics.Renderer;
import utils.Time;

public class MainEngine implements Runnable {


    public static final int TARGET_FPS = 60;

    private final Thread gameLoopThread;
    protected MainBehaviour behaviour;
    protected Renderer renderer;
    protected Window window;

    public MainEngine(String windowName, int width, int height, MainBehaviour mainBehaviour) {
        gameLoopThread = new Thread(this, "Game_Loop_Thread");
        renderer = new Renderer();
        window = null; //new core.Window(windowName, width, height); UNCOMMENT THIS
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
        window.destroy();
    }

    protected void input() {
        behaviour.input(window);
    }

    protected void update(float interval) {
        behaviour.update(interval);
    }
    protected void update() {
        behaviour.update();
    }
    protected void render() {
        behaviour.render(window);
        window.update();
    }

    public void gameLoop() {
        float delta;
        while(true) {
            if(!window.isRunning()) break;

            delta =(float) Time.getDeltaTime();

            /* Input Handling */
            input();

            /* Rendering and actually updating Game */
            update(delta);
            render();

            /*Updating Time */
            Time.updateFps();
            Time.updateCycle();


            if(!window.isVSyncActivated()) Time.sync(TARGET_FPS);
        }

    }


}
