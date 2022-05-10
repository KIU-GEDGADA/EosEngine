package core;

import graphics.ShaderProgram;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private static final List<Item> items = new ArrayList<>();
    private static ShaderProgram shaderProgram;

    private Renderer() {
    }

    public static void initAll() {
        items.forEach(Item::init);
    }

    public static void addItem(Item item) {;
        if(!items.contains(item)) {
            items.add(item);
        }
    }

    public static void removeItem(Item item) {
        items.remove(item);
    }

    public static void renderAll() {
        if(items.size() < 1) {
            System.out.println("No items to render");
        }
        shaderProgram.bind();
        items.forEach(Item::render);
        shaderProgram.unbind();
    }

    public static void removeAll() {
        items.clear();
    }

    public static void setShader(ShaderProgram shaderProgram) {
        Renderer.shaderProgram = shaderProgram;
    }
}
