package core;

import graphics.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private static final List<Item> items = new ArrayList<>();

    private Renderer() {
    }

    public static void init() {
        items.forEach(Item::init);
    }
  
    public static void addItem(Item item) {
        if(!items.contains(item)) {
            items.add(item);
        }
    }

    public static void removeItem(Item item) {
        items.remove(item);
    }

    public static void render() {
        if(items.size() < 1) {
            System.out.println("No items to render");
        }
        items.forEach(Item::render);
    }

    public static void removeAll() {
        items.clear();
    }

}
