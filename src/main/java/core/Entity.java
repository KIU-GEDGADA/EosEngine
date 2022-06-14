package core;

/**
 * The interface of an entity object of the game engine, every object to be handled and rendered should implement this interface
 */
public interface Entity {
    /**
     * Initialization function
     */
    void init();

    /**
     * Update functions
     */
    void update();

    /**
     * Render function
     */
    void render();

    /**
     * Destroy function
     */
    void destroy();
}
