package core;

import io.Input;
import utils.TimeUtils;

public class Engine{

    public Window window;
    public Entity entity;

    public Engine(Window window, Entity entity){
        this.window = window;
        this.entity = entity;
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
        entity.init();
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
        entity.update();
        return true;
    }

    private void render() {
        window.clear();
        entity.render();
        window.render();
    }

    public void stop(){
        entity.destroy();
        window.destroy();
    }
}
