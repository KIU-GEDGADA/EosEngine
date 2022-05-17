//import core.Renderer;

import core.Engine;
import core.Entity;
import core.Window;
import graphics.*;
import io.Input;
import math.Vector2f;
import math.Vector3f;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13C.glActiveTexture;

public class Preview {
    public static void main(String[] args) {
        Window w = new Window(800, 600, "Preview", false);
        Entity e = new Entity() {
            Vertex v1;
            Vertex v2;
            Vertex v3;
            Vertex v4;
            Mesh m;
            Shader vs;
            Shader fs;
            ShaderProgram sp;
            Texture tex;

            @Override
            public void init() {
                v1 = new Vertex(new Vector3f(0.5f, -0.5f, 0f), Color.RED, new Vector2f(1f,1f));
                v2 = new Vertex(new Vector3f(-0.5f, 0.5f, 0f), Color.GREEN, new Vector2f(0f,0f));
                v3 = new Vertex(new Vector3f(0.5f, 0.5f, 0f), Color.BLUE, new Vector2f(1f,0f));
                v4 = new Vertex(new Vector3f(-0.5f, -0.5f, 0f), Color.YELLOW, new Vector2f(0f,1f));
                Vertex[] vertices = {v1, v2, v3, v4};
                int[] indices = {2, 1, 0, 0, 1, 3};
                vs = new Shader("res/shaders/tv1vs.glsl");
                vs.compile();
                fs = new Shader("res/shaders/tv1fs.glsl");
                fs.compile();
                tex = new Texture("res/textures/Kick Them Out1.png");
                sp = new ShaderProgram();
                sp.init();
                sp.attachShader(vs);
                sp.attachShader(fs);
                sp.link();
                m = new Mesh(vertices, indices);
                m.init();
                sp.bind();
                sp.addUniform("tex");
                sp.setTexture("tex",0);
                glActiveTexture(GL_TEXTURE0);
                tex.bind();
            }

            @Override
            public void update() {
                if (Input.isKeyPressed(GLFW_KEY_SPACE)) {
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
