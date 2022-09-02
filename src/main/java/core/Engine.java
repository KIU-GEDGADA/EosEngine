package core;

import io.Input;
import utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main Engine class and handles all aspects of the EosGameEngine.
 */
public class Engine {
    private static Engine instance;
    private Window window;
    private List<Entity> entities = new ArrayList<>();

    /**
     * Getter, returns the instance of the game engine,
     * if one does not exist, creates a new instance using the specified window and returns it
     *
     * @param window window in which the game engine instance should be created, if one does not exist
     * @return the instance of the game engine
     */
    public static Engine getInstance(Window window) {
        if (instance == null) {
            instance = new Engine(window);
        }
        return instance;
    }

    /**
     * Class constructor, specifying which window to initiate the engine in
     *
     * @param window the window where the engine is to be initiated
     */
    private Engine(Window window) {
        this.window = window;
    }

    /**
     * getter, returns the window which the engine instance is in
     *
     * @return the window which the engine instance is in
     */
    public Window getWindow() {
        return window;
    }

    /**
     * setter, sets the window in which the engine instance will be
     *
     * @param window the window in which the engine instance will be
     */
    public void setWindow(Window window) {
        this.window = window;
    }

    /**
     * getter, returns the list of entities that exist in the game engine
     *
     * @return the list of entities that exist in the game engine
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * setter, sets the list of entities that are to be handled by the game engine
     *
     * @param entities the list of entities that are to be handled by the game engine
     */
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    /**
     * this function handles the start, main loop and end of the game engine by calling the appropriate methods
     */
    public void start() {
        init();
        loop();
        stop();
    }

    /**
     * this function initializes the game engine
     * it sets the game engine to 60 frames per second, initializes:
     * -the Input class
     * -the window object of the engine instance
     * -every entity to be handled by the engine
     * -the rendered class
     */
    public void init() {
        TimeUtils.init(60);
        Input.init();
        window.init();
        entities.forEach(Entity::init);
        Renderer.init();
        //  TerrainRenderer.init();
        Camera.getInstance().init((float) window.getWidth() / window.getHeight());
    }

    /**
     * this function is the main loop of the game engine and implements the runtime operations of the engine.
     * the function prints the fps to console every second
     */
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

    /**
     * this function destroies every entity being handled by the game engine, the window of the engine instance and clears the Renderer class
     */
    public void stop() {
        entities.forEach(Entity::destroy);
        window.destroy();
        Renderer.removeAll();
    }


}
