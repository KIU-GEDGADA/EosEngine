import core.*;
import graphics.*;
import io.Input;
import math.Vector2f;
import math.Vector3f;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;

public class Preview {
    public static void main(String[] args) {
        Window w = new Window(800, 600, "Preview", false);
        Vertex v1;
        Vertex v2;
        Vertex v3;
        Vertex v4;
        v1 = new Vertex(new Vector3f(0.5f, -0.5f, 0f), Color.RED, new Vector2f(1, 0));
        v2 = new Vertex(new Vector3f(-0.5f, 0.5f, 0f), Color.GREEN, new Vector2f(0, 1));
        v3 = new Vertex(new Vector3f(0.5f, 0.5f, 0f), Color.BLUE, new Vector2f(1, 1));
        v4 = new Vertex(new Vector3f(-0.5f, -0.5f, 0f), Color.YELLOW);
        Vertex[] vertices1 = {v1, v2, v3};
        Vertex[] vertices2 = {v1, v2, v4};
        Texture tex = new Texture("res/textures/Kick Them Out1.png");
        int[] indices1 = {2, 1, 0};
        int[] indices2 = {0, 1, 2};
        Shader vs = new Shader("res/shaders/v3vs.glsl");
        Shader fs = new Shader("res/shaders/v3fs.glsl");
        Shader vst = new Shader("res/shaders/tv1vs.glsl");
        Shader fst = new Shader("res/shaders/tv1fs.glsl");
        List<Shader> shaders = new ArrayList<>();
        shaders.add(vs);
        shaders.add(fs);
        List<Shader> shaderst = new ArrayList<>();
        shaders.add(vst);
        shaders.add(fst);
        Item item1 = new Item("Mesh 1", new Mesh(vertices1, indices1), shaders);
        Item item2 = new Item("Mesh 2", new Mesh(vertices2, indices2), shaderst);
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
        Renderer.addItem(item1);
        Renderer.addItem(item2);
        ee.start();
    }
}
