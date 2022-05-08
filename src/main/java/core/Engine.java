package core;

import io.Input;
import utils.TimeUtils;

import java.util.ArrayList;

public class Engine{

    public Window window;
    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public Engine(Window window){
        this.window = window;
    }

    public void start(){
        init();
        loop();
        stop();
    }

    public void init(){
        TimeUtils.init(60);
        Input.init();
        window.init();
        for (Entity entity : entities) {
            entity.init();
        }
        Renderer.initAll();
    }

    public void loop() {
        while (window.isRunning()) {

            /* Updating delta Time for correct interval Calculation */
            TimeUtils.updateDeltaTime();

            /* Rendering and actually updating Game */
            while (TimeUtils.checkCycle()) {
                if(update()){
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

    private boolean update(){
        TimeUtils.updateFps();
        TimeUtils.updateCycle();
        window.update();
        for (Entity entity : entities) {
            entity.update();
        }
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

    public void stop(){
        for (Entity entity : entities) {
            entity.destroy();
        }
        window.destroy();
        Renderer.removeAll();
    }
}
