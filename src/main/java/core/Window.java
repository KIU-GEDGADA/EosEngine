package core;

import io.Input;
import math.Vector4f;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import utils.Time;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryUtil.NULL;


public class Window {
    private int height;
    private int width;
    private long window;
    private long monitor;
    private boolean isFullScreen;
    private String name;
    private GLFWVidMode mode;
    private Vector4f color;
    private boolean isvSync;

    public Window(int height, int width, String name, Vector4f color, boolean isvSync) {
        this.height = height;
        this.width = width;
        this.name = name;
        this.color = color;
        this.isvSync = isvSync;
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

    public void setvSync(boolean vSync) {
        this.isvSync = vSync;
    }

    public boolean isvSync() {
        return isvSync;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        setupCallback();
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

        setupCallback();
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);

    }

    public void initializeWindow() {
        Input.init();
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

        setupCallback();


        glfwShowWindow(window);
        GL.createCapabilities();
    }

    private void setupCallback() {
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
        glfwSetKeyCallback(window, Input.getKeyboard());
        glfwSetMouseButtonCallback(window, Input.getMbtn());
        glfwSetCursorPosCallback(window, Input.getMouse());
        glfwMakeContextCurrent(window);
        if (isvSync) glfwSwapInterval(1);
        else glfwSwapInterval(0);
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

        setupCallback();
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

    public void update() {
        if (glfwWindowShouldClose(window)) {
            destroyWindow();
            return;
        }

        glClearColor(color.x, color.y, color.z, color.w);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwSwapBuffers(window);


        glfwPollEvents();

    }

    protected boolean isRunning() {
        return !glfwWindowShouldClose(window);
    }

}

