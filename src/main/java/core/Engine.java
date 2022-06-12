package core;

import io.Input;
import utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    private static Engine instance;
    private Window window;
    private List<Entity> entities = new ArrayList<>();

    public static Engine getInstance(Window window){
        if (instance == null){
            instance = new Engine(window);
        }
        return instance;
    }

    private Engine(Window window) {
        this.window = window;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void start() {
        init();
        loop();
        stop();
    }

    public void init() {
        TimeUtils.init(60);
        Input.init();
        window.init();
        entities.forEach(Entity::init);
        Renderer.init();
    }

    public void loop() {
        while (window.isRunning()) {

            boolean can_render = false;

            /* Updating delta Time for correct interval Calculation */
            TimeUtils.updateDeltaTime();
            /* Rendering and actually updating Game */
            while (TimeUtils.checkCycle()) {
                can_render = update();
            }
            if (can_render) {
                render();
                TimeUtils.updateFps();
            }

            Input.update();

            System.out.println("FPS: " + TimeUtils.getFps());


            if (!window.isVSync()) {
                TimeUtils.sync();
            }
        }
    }

    private boolean update() {
        TimeUtils.updateCycle();
        window.update();
        entities.forEach(Entity::update);
        return true;
    }

    private void render() {
        window.clear();
        entities.forEach(Entity::render);
        Renderer.render();
        window.render();
    }

    public void stop() {
        entities.forEach(Entity::destroy);
        window.destroy();
        Renderer.removeAll();
    }


}
