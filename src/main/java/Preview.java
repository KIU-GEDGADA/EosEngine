import core.*;
import graphics.*;
import io.Input;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Preview {
    public static void main(String[] args) {
        Window w = new Window(1000, 1000, "Preview", false);
        Mesh obj = new Mesh("res/models/man.obj", false);
        Texture tex2 = new Texture("res/textures/davd.png");
        Shader vs = new Shader("res/shaders/v3vs.glsl");
        Shader fs = new Shader("res/shaders/v3fs.glsl");
        List<Shader> shaders = new ArrayList<>();
        shaders.add(vs);
        shaders.add(fs);
        Item object = new Item("Cube", obj, shaders);
        Entity e = new Entity() {

            @Override
            public void init() {
            }

            @Override
            public void update() {
                if (Input.isKeyPressed(GLFW_KEY_SPACE)) {
                    System.out.println("Space pressed");
                }
            }

            @Override
            public void render() {
            }

            @Override
            public void destroy() {
            }
        };
        Engine ee = new Engine(w);
        ee.getEntities().add(e);
        Renderer.addItem(object);
        ee.start();
    }
}
