import core.MainBehaviour;
import core.MainEngine;
import core.Window;
import graphics.Mesh;
import graphics.Shader;
import graphics.Vertex;
import io.Input;
import math.Vector3f;
import math.Vector4f;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;
import utils.Time;

import java.nio.IntBuffer;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class TestClass {

    // The window handle
    private long window;

    public static void main(String[] args) {
        //   new TestClass().run();
        MainBehaviour game = new MainBehaviour() {
            Vertex[] vertices;
            Mesh mesh;
            int[] indices;
            Shader shader;

            @Override
            public void init() throws Exception {
                vertices = new Vertex[]{
                        new Vertex(new Vector3f(-1f, -1f, 0f), new Vector4f(1f, 0f, 0f, 1f)),
                        new Vertex(new Vector3f(0f, 1f, 0f), new Vector4f(0f, 1f, 0f, 1f)),
                        new Vertex(new Vector3f(1f, -1f, 0f), new Vector4f(0f, 0f, 1f, 1f)),
                };
                indices = new int[]{
                        0, 1, 2
                };
                shader = new Shader("res/shaders/default.glsl");
                mesh = new Mesh(vertices, indices, shader);
            }

            @Override
            public void update() {
                if (Input.isKeyDown(GLFW_KEY_SPACE))
                    System.out.println("Space button pushed");
            }

            @Override
            public void render(Window window) {
                mesh.render();
            }

            @Override
            public void clear() {
                mesh.clear();
            }
        };
        new MainEngine(
                500,
                500,
                "TestGame",
                new Vector4f(0.5f, 0.5f, 0.5f, 1f),
                false,
                game
        ).start();
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private void init() {
        Input.init();
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, Input.getKeyboard());

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            assert vidmode != null;
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(0);

        // Make the window visible
        glfwShowWindow(window);

        // Time.init();
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.

        GL.createCapabilities();

        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            glfwSwapBuffers(window); // swap the color buffers

            Input.update();
            Time.updateFps();
            Time.updateCycle();
            //  Time.sync(60);
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
            System.out.printf("FPS: %d%n", Time.getFps());
        }
    }
}
