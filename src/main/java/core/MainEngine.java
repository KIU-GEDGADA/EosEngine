package core;

import graphics.Mesh;
import graphics.Renderer;
import graphics.Shader;
import graphics.Vertex;
import io.Input;
import math.Vector3f;
import math.Vector4f;
import utils.Time;

public class MainEngine implements Runnable {


    public static final int TARGET_FPS = 30;

    private final Thread gameLoopThread;
    protected MainBehaviour behaviour;
    protected Renderer renderer;
    protected Window window;


    public MainEngine(int height, int width, String name, Vector4f color, boolean isVSync, MainBehaviour mainBehaviour) {
        gameLoopThread = new Thread(this, "Game_Loop_Thread");
        renderer = new Renderer();
        this.window = new Window(height, width, name, color, isVSync);
        this.behaviour = mainBehaviour;
    }

    public void init() throws Exception {
        window.init();
        behaviour.init();
        Time.init(TARGET_FPS);
        renderer.init();
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
        behaviour.clear();
    }

    protected void update() {
        behaviour.update();

        /*Updating Time */
        Time.updateFps();
        Time.updateCycle();
        //mesh.clear();
        /* Input handling */
        Input.update();
        window.update();
    }

    protected void render() {
        behaviour.render(window);
        //mesh.render();
    }

    public void gameLoop() {
        while (window.isRunning()) {

            /* Updating delta Time for correct interval Calculation */
            Time.updateDeltaTime();

            /* Rendering and actually updating Game */
            while (Time.checkCycle()) {
                update();
            }

            render();

            System.out.println(Time.getFps());

            if (!window.isVSync()) {
                Time.sync();
            }
        }

    }


}
