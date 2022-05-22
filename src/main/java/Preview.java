import core.*;
import graphics.*;
import io.Input;
import math.Vector2f;
import math.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Preview {
    public static void main(String[] args) {
        Window w = new Window(1000, 1000, "Preview", false);

        Entity e = new Entity() {
            Item item1;
            Item item2;
            Texture texture1;
            @Override
            public void init() {
                Vertex v1 = new Vertex(new Vector3f(-0.5f, 0.5f, 0f), Color.RED);
                Vertex v2 = new Vertex(new Vector3f(-0.5f, -0.5f, 0f), Color.GREEN);
                Vertex v3 = new Vertex(new Vector3f(0.5f, -0.5f, 0f), Color.BLUE);
                Vertex v4 = new Vertex(new Vector3f(0.5f, 0.5f, 0f), Color.YELLOW);

                Vertex[] vertices1 = {v1, v2, v3, v4};
                int[] indices1 = {0, 1, 3, 3, 1, 2};
                Vector2f[] texCoords1 = {new Vector2f(0, 1), new Vector2f(0, 1), new Vector2f(1, 0), new Vector2f(1, 0)};

                Shader vs = new Shader("res/shaders/tv2vs.glsl");
                Shader fs = new Shader("res/shaders/tv2fs.glsl");
                List<Shader> shaders = List.of(new Shader[]{vs, fs});

                texture1 = new Texture("res/textures/texture.png");

                item1 = new Item("Mesh 1", new Model(new Mesh(vertices1, texCoords1, new Vector3f[]{}, indices1)), shaders);

                item2 = new Item("Cube", new Model(new Mesh("res/models/cube.obj")), shaders);
                item2.getTransform().getScale().div(2f);
                //item1.getModel().setTexture(texture1);

                Renderer.addItem(item1);
                //Renderer.addItem(item2);
            }

            @Override
            public void update() {

                if (item1.getTransform().getRotation().z < -360f) {
                    item1.getTransform().getRotation().z = 0f;
                }
                item1.getTransform().getRotation().z -= 1f;
            }

            @Override
            public void render() {
            }

            @Override
            public void destroy() {
                item1.destroy();
                //item2.destroy();
            }
        };

        Engine ee = new Engine(w);
        ee.getEntities().add(e);

        ee.start();
    }
}
