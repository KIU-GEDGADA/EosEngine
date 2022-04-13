//import core.Renderer;

import core.Engine;
import core.Entity;
import core.Window;
import graphics.Mesh;
import graphics.Shader;
import graphics.ShaderProgram;
import graphics.Vertex;
import io.Input;
import math.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Preview {
    public static void main(String[] args) {
        Window w = new Window(800, 600, "Preview", false);
        Entity e = new Entity() {
            Vertex v1;
            Vertex v2;
            Vertex v3;
            Mesh m;
            Shader vs;
            Shader fs;
            ShaderProgram sp;

            @Override
            public void init() {
                v1 = new Vertex(new Vector3f(-0.5f, -0.5f, 0f));
                v2 = new Vertex(new Vector3f(0.5f, -0.5f, 0f));
                v3 = new Vertex(new Vector3f(0f, 0.5f, 0f));
                vs = new Shader("res/shaders/v2vs.glsl");
                vs.compile();
                fs = new Shader("res/shaders/v2fs.glsl");
                fs.compile();
                sp = new ShaderProgram();
                sp.init();
                sp.attachShader(vs);
                sp.attachShader(fs);
                sp.link();
                m = new Mesh(new Vertex[]{v1, v2, v3});
                m.init();
                sp.bind();
            }

            @Override
            public void update() {
                if(Input.isKeyPressed(GLFW_KEY_SPACE)) {
                    System.out.println("Space pressed");
                }
            }

            @Override
            public void render() {
                m.render();
            }

            @Override
            public void destroy() {
                sp.destroy();
                m.destroy();
            }
        };
        Engine ee = new Engine(w, e);
        ee.start();
    }
}
