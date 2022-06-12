package core;

import enums.WindowState;
import io.Input;
import math.Matrix4x4;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.*;

public class Window {

    private final int width;
    private final int height;
    private final String title;
    private final boolean vSync;
    private long window;
    private long monitor;
    private GLFWVidMode mode;
    private WindowState state;

    public Window(int width, int height, String title, boolean vSync) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.vSync = vSync;
    }

    public void init() {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        GLFWErrorCallback.createPrint(System.err).set();
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        this.monitor = glfwGetPrimaryMonitor();
        this.mode = glfwGetVideoMode(monitor);
        window = glfwCreateWindow(width, height, title, 0, 0);
        setMode(WindowState.WINDOWED);
        if (window == 0) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        glfwShowWindow(this.window);
        glfwMakeContextCurrent(this.window);
        glfwSwapInterval(vSync ? 1 : 0);
        GL.createCapabilities();

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_STENCIL_TEST);

        setupCallbacks();
    }

    private void setupCallbacks() {
        glfwSetKeyCallback(window, Input.getKeyboard());
        glfwSetMouseButtonCallback(window, Input.getMbtn());
        glfwSetCursorPosCallback(window, Input.getMouse());
    }

    public void update() {
        if (Input.isKeyPressed(GLFW_KEY_F11)) {
            if (state == WindowState.WINDOWED) {
                this.setMode(WindowState.FULLSCREEN);
            } else {
                this.setMode(WindowState.WINDOWED);
            }
        }
    }

    public void render() {
        glfwSwapBuffers(window);
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
        glEnable(GL_DEPTH_TEST);
    }

    public void destroy() {
        glfwSetErrorCallback(null).free();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public void setMode(WindowState windowMode) {
        switch (windowMode) {
            case FULLSCREEN:
                glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
                glfwSetWindowMonitor(window, monitor, (mode.width() - width) / 2, (mode.height() - height) / 2, width, height, mode.refreshRate());
                glfwSetWindowSize(window, mode.width(), mode.height());
                this.state = WindowState.FULLSCREEN;
                break;
            case WINDOWED:
                glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
                glfwSetWindowMonitor(window, 0, (mode.width() - width) / 2, (mode.height() - height) / 2, width, height, mode.refreshRate());
                glfwSetWindowSize(window, width, height);
                this.state = WindowState.WINDOWED;
                glfwWindowHint(GLFW_DECORATED, GLFW_TRUE);
                break;
            case BORDERLESS:
                glfwSetWindowMonitor(window, 0, 0, 0, mode.width(), mode.height(), mode.refreshRate());
                glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
                this.state = WindowState.BORDERLESS;
                break;
            case HIDDEN:
                glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
                this.state = WindowState.HIDDEN;
                break;
            case SHOWN:
                glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
                this.state = WindowState.SHOWN;
                break;
        }
    }

    public boolean isRunning() {
        return !glfwWindowShouldClose(window);
    }

    public boolean isVSync() {
        return vSync;
    }

    public long getWindow() {
        return window;
    }
}

