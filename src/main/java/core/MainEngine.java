package core;

import graphics.Renderer;
import io.Input;
import math.Vector4f;
import utils.Time;
import utils.TimeTest;

public class MainEngine implements Runnable {


    public static final int TARGET_FPS = 60;

    private final Thread gameLoopThread;
    protected MainBehaviour behaviour;
    protected Renderer renderer;
    protected Window window;
    protected TimeTest timer;


    public MainEngine(int height, int width, String name, Vector4f color, boolean isvSync, MainBehaviour mainBehaviour) {
        gameLoopThread = new Thread(this, "Game_Loop_Thread");
        renderer = new Renderer();
        this.window = new Window(height, width, name, color, isvSync);
        this.behaviour = mainBehaviour;
        timer = new TimeTest();
    }

    public void init() throws Exception {
        window.initializeWindow();
        timer.init();
        renderer.init();
        behaviour.init();

    }

    public void start() {
        gameLoopThread.start();
        //   windowThread.start();
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
    }

    protected void update(float interval) {
        behaviour.update();

        /*Updating Time */
        //   Time.getDeltaTime();
        //  Time.updateCycle();

        /* Input handling */
        Input.update();
    }

    protected void render() {
        behaviour.render(window);
        window.update();
    }

    public void gameLoop() throws InterruptedException {
        int counter = 0;
        while (window.isRunning()) {
            float deltaTime =(float) timer.getDeltaTime();

             System.out.println("Thread sleeps");
           //  gameLoopThread.sleep(1000);

            counter++;
            /* Rendering and actually updating Game */
            while (timer.checkCycle()) {
                update(deltaTime);
                timer.updateFps();
                timer.updateCycle();
            }

            render();

            System.out.printf("FPS: %d%n", counter);


            if (!window.isvSync()) {
                timer.sync(TARGET_FPS);
            }
            counter = 0;
        }

    }


}
