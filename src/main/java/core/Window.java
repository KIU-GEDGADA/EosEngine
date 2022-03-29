package core;

import math.Vector4f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Window {
    int height;
    int width;
    long window;
    long monitor;
    boolean isFullScreen = false;
    String name;
    GLFWVidMode mode;
    Vector4f color;

    public Window(int height, int width, String name, Vector4f color) {
        this.height = height;
        this.width = width;
        this.name = name;
        this.color = color;
    }

    public Window(int height, int width, String name, String monitor, Vector4f color) {
        this.height = height;
        this.width = width;
        this.name = name;
        this.color = color;
        if (Objects.equals(monitor, "primary")) {
            this.monitor = glfwGetPrimaryMonitor();
            mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        }
    }

    public void windowWithInit(int height, int width, String name, Vector4f color) {
        if (!glfwInit()) {
            throw new IllegalStateException("GLFW not initialized or initialization failed");
        } else {
            GLFWErrorCallback.createPrint(System.err).set();
            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            this.height = height;
            this.width = width;
            this.name = name;
            this.color = color;

            if (monitor != NULL) {
                mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            }
            try {
                window = glfwCreateWindow(width, height, name, NULL, NULL);
                if (window == NULL) {
                    throw new IllegalStateException("Failed to create the GLFW window");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
            if (key == GLFW_KEY_W) {
                fullScreen();
            }
            if (key == GLFW_KEY_S) {
                windowed(100, 100, 100, 100);
            }
            if (key == GLFW_KEY_Q) {
                fullScreenWindow();
            }
        });
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);

    }

    public void windowWithInit(int height, int width, String name, Vector4f color, String monitor) {
        if (!glfwInit()) {
            throw new IllegalStateException("GLFW not initialized or initialization failed");
        } else {
            GLFWErrorCallback.createPrint(System.err).set();
            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            this.height = height;
            this.width = width;
            this.name = name;
            this.color = color;

            try {
                if (Objects.equals(monitor, "primary")) {
                    this.monitor = glfwGetPrimaryMonitor();
                    mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
                    window = glfwCreateWindow(width, height, name, glfwGetPrimaryMonitor(), NULL);
                } else {
                    window = glfwCreateWindow(width, height, name, NULL, NULL);
                }
                if (window == NULL) {
                    throw new IllegalStateException("Failed to create the GLFW window");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
            if (key == GLFW_KEY_W) {
                fullScreen();
            }
            if (key == GLFW_KEY_S) {
                windowed(100, 100, 100, 100);
            }
            if (key == GLFW_KEY_Q) {
                fullScreenWindow();
            }
        });
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);

    }

    public void initializeWindow() {
        if (!glfwInit()) {
            throw new IllegalStateException("GLFW not initialized or initialization failed");
        } else {
            GLFWErrorCallback.createPrint(System.err).set();
            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            try {
                if (monitor != NULL) {
                    this.monitor = glfwGetPrimaryMonitor();
                    mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
                    window = glfwCreateWindow(width, height, name, glfwGetPrimaryMonitor(), NULL);
                    isFullScreen = true;
                } else {
                    window = glfwCreateWindow(width, height, name, NULL, NULL);

                }
                if (window == NULL) {
                    throw new IllegalStateException("Failed to create the GLFW window");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
            if (key == GLFW_KEY_W) {
                fullScreen();
            }
            if (key == GLFW_KEY_S) {
                windowed(100, 100, 100, 100);
            }
            if (key == GLFW_KEY_Q) {
                fullScreenWindow();
            }
        });
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }

    public void initializeWindow(String monitor) {
        if (!glfwInit()) {
            throw new IllegalStateException("GLFW not initialized or initialization failed");
        } else {
            GLFWErrorCallback.createPrint(System.err).set();
            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            try {
                if (Objects.equals(monitor, "primary")) {
                    this.monitor = glfwGetPrimaryMonitor();
                    mode = glfwGetVideoMode(glfwGetPrimaryMonitor());
                    window = glfwCreateWindow(width, height, name, glfwGetPrimaryMonitor(), NULL);
                } else {
                    window = glfwCreateWindow(width, height, name, NULL, NULL);

                }
                if (window == NULL) {
                    throw new IllegalStateException("Failed to create the GLFW window");
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
            if (key == GLFW_KEY_W) {
                fullScreen();
            }
            if (key == GLFW_KEY_S) {
                windowed(100, 100, 100, 100);
            }
            if (key == GLFW_KEY_Q) {
                fullScreenWindow();
            }
        });
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }

    protected void destroyWindow() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
    }

    public void windowed(int width, int height, int x, int y) {
        glfwSetWindowMonitor(window, NULL, x, y, width, height, mode.refreshRate());
    }

    public void fullScreenWindow() {
        glfwSetWindowMonitor(window, NULL, 0, 0, mode.width(), mode.height(), mode.refreshRate());
    }

    public void fullScreen() {
        glfwSetWindowMonitor(window, monitor, 0, 0, mode.width(), mode.height(), 0);
    }

    public void loop() {
        GL.createCapabilities();

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            glClearColor(color.x, color.y, color.z, color.w);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glfwSwapBuffers(window);
        }
        destroyWindow();
    }

    protected boolean isRunning() {
        return !glfwWindowShouldClose(window);
    }
}

