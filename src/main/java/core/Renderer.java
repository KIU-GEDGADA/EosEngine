package core;

import graphics.lighting.ILight;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the rendering of all items in the game engine
 */
public class Renderer {
    private static final List<Item> items = new ArrayList<>();
    private static final List<ILight> lights = new ArrayList<>();

    private Renderer() {
    }

    /**
     * This function calls the init() function for every item to be rendered
     */
    public static void init() {
        items.forEach(Item::init);
    }

    /**
     * This function adds an item to the list of those to be rendered
     *
     * @param item the item to be added to the list
     */
    public static void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    /**
     * This function removes an item from the list of items to be rendered
     *
     * @param item the item to be removed
     */
    public static void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * This function calls the render() function for every item to be rendered
     */
    public static void render() {
        if (items.size() < 1) {
            System.out.println("No items to render");
        }
        items.forEach(Item::render);
    }

    public static void addLight(ILight light) {
        if (!lights.contains(light)) {
            lights.add(light);
        }
    }

    public static void removeLight(ILight light) {
        lights.remove(light);
    }

    /**
     * This function clears the list of items to be rendered
     */
    public static void removeAll() {
        items.clear();
    }

}
