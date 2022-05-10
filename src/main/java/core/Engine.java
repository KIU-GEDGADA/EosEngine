package core;

import io.Input;
import utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    private Window window;
    private List<Entity> entities = new ArrayList<>();

    public Engine(Window window) {
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
        Renderer.initAll();
    }

    public void loop() {
        while (window.isRunning()) {

            /* Updating delta Time for correct interval Calculation */
            TimeUtils.updateDeltaTime();

            /* Rendering and actually updating Game */
            while (TimeUtils.checkCycle()) {
                if (update()) {
                    render();
                }
            }

            Input.update();

            System.out.println(TimeUtils.getFps());

            if (!window.isVSync()) {
                TimeUtils.sync();
            }
        }
    }

    private boolean update() {
        TimeUtils.updateFps();
        TimeUtils.updateCycle();
        window.update();
        entities.forEach(Entity::update);
        return true;
    }

    private void render() {
        window.clear();
        for (Entity entity : entities) {
            entity.render();
        }
        Renderer.renderAll();
        window.render();
    }

    public void stop() {
        entities.forEach(Entity::destroy);
        window.destroy();
        Renderer.removeAll();
    }


}
