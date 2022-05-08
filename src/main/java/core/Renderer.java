package core;

import graphics.ShaderProgram;

import java.util.ArrayList;

public class Renderer {
    private static final ArrayList<Item> items = new ArrayList<Item>();
    private static ShaderProgram shaderProgram;

    private Renderer() {
    }

    public static void initAll() {
        for (Item item : items) {
            item.init();
        }
    }

    public static void addItem(Item item) {;
        if(!items.contains(item)) {
            items.add(item);
        }
    }

    public static void removeItem(Item item) {
        if(items.contains(item)) {
            items.remove(item);
        }
    }

    public static void renderAll() {
        if(items.size() < 1) {
            System.out.println("No items to render");
        }
        shaderProgram.bind();
        for (Item item : items) {
            item.render();
        }
        shaderProgram.unbind();
    }

    public static void removeAll() {
        items.clear();
    }

    public static void setShader(ShaderProgram shaderProgram) {
        Renderer.shaderProgram = shaderProgram;
    }
}
