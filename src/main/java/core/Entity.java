package core;

/**
 * The interface of an entity object of the game engine, every object to be handled and rendered should implement this interface
 */
public interface Entity {
    void init();

    void update();

    void render();

    void destroy();
}
