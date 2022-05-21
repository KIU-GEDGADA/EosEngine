import core.*;
import graphics.*;
import io.Input;
import math.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Preview {
    public static void main(String[] args) {
        Window w = new Window(1000, 1000, "Preview", false);

        Entity e = new Entity() {
            Item item1;
            Item item3;
            Item item2;

            @Override
            public void init() {
                Vertex v1;
                Vertex v2;
                Vertex v3;
                Vertex v4;
                v1 = new Vertex(new Vector3f(-0.5f, 0.5f, 0f), Color.RED);
                v2 = new Vertex(new Vector3f(-0.5f, -0.5f, 0f), Color.GREEN);
                v3 = new Vertex(new Vector3f(0.5f, -0.5f, 0f), Color.BLUE);
                v4 = new Vertex(new Vector3f(0.5f, 0.5f, 0f), Color.YELLOW);
                Vertex[] vertices1 = {v1, v2, v3, v4};
                int[] indices1 = {0, 1, 3, 3, 1, 2};
                Shader vs = new Shader("res/shaders/v3vs.glsl");
                Shader fs = new Shader("res/shaders/v3fs.glsl");
                List<Shader> shaders = new ArrayList<>();
                shaders.add(vs);
                shaders.add(fs);
                item1 = new Item("Mesh 1", new Mesh(vertices1, indices1), shaders);
                item3 = new Item("Cube", new Mesh("res/models/gordon.obj", false), shaders);

                Renderer.addItem(item3);
            }

            @Override
            public void update() {
                if (item3.getTransform().getRotation().y < -360f) {
                    item3.getTransform().getRotation().y = 0f;
                }
                item3.getTransform().getRotation().y -= 1f;
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
        ee.start();
    }
}
