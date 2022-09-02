package core;

import enums.WindowState;
import io.Input;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL45C.*;

/**
 * This class implements the window where the game engine instance should be run
 */
public class Window {
    private static Window instance;
    private final int width;
    private final int height;
    private final String title;
    private final boolean vSync;
    private long window;
    private long monitor;
    private GLFWVidMode mode;
    private WindowState state;

    /**
     * this function returns the instance of the window if it exists, if not creates a new instance and returns it
     *
     * @param width  width in pixels of the window to be created if it does not already exist
     * @param height height in pixels of the window to be created if it does not already exist
     * @param title  name of the window to be created if it does not already exist
     * @param vSync  if the window should have vSync turned on or off
     * @return returns the instance of the window
     */
    public static Window getInstance(int width, int height, String title, boolean vSync) {
        if (instance == null) {
            instance = new Window(width, height, title, vSync);
        }
        return instance;
    }

    /**
     * class Constructor, initializes the window object to the given parameters
     *
     * @param width  the width in pixels of the window to be created
     * @param height height in pixels of the window to be created
     * @param title  name of the window to be created
     * @param vSync  if the window should have vSync turned on or off
     */
    private Window(int width, int height, String title, boolean vSync) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.vSync = vSync;
    }

    /**
     * This function initializes glfw and the window class object,
     * throws an exception if it failes to initialize glfw or fails to create a glfw window
     */
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

        glFrontFace(GL_CCW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_STENCIL_TEST);
        glEnable(GL_TEXTURE_2D);

        setupCallbacks();
    }

    /**
     * this function sets up keyboard and mouse callbacks for the current window object
     */
    private void setupCallbacks() {
        glfwSetKeyCallback(window, Input.getKeyboard());
        glfwSetMouseButtonCallback(window, Input.getMbtn());
        glfwSetCursorPosCallback(window, Input.getMouse());
    }

    /**
     * This window handles updating the window based on input given.
     * Keyboard:
     * -f11: sets window to full screen if currently in windowed and to windowed if currently in full screen
     */
    public void update() {
        if (Input.isKeyPressed(GLFW_KEY_F11)) {
            if (state == WindowState.WINDOWED) {
                this.setMode(WindowState.FULLSCREEN);
            } else {
                this.setMode(WindowState.WINDOWED);
            }
        }
    }

    /**
     * This function renders objects by swapping buffers of the current window object
     */
    public void render() {
        glfwSwapBuffers(window);
    }

    /**
     * this function clears the window of all rendered objects, textures and color
     */
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.0f, 0.5f, 0.5f, 1.0f);
    }

    /**
     * this function releases all callbacks of the window, destroys the window and terminates glfw
     */
    public void destroy() {
        glfwSetErrorCallback(null).free();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    /**
     * This function handles switching between window modes, it recives the window state to be switched to. Window States:
     * -FULLSCREEN: sets the window to full screen
     * -WINDOWED: sets the window to windowed mode based on its height and width
     * -BORDERLESS: sets the window to a maximized window without borders
     * -HIDDEN: hides the window
     * -SHOWN: shows the window
     *
     * @param windowMode the window mode to which the window must be switched
     */
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

    /**
     * this function returns wherever the current window object is running
     *
     * @return true if window running, false if window no longer running and should close
     */
    public boolean isRunning() {
        return !glfwWindowShouldClose(window);
    }

    /**
     * this function returns wherever vSync is turned on for the current window
     *
     * @return true if vSync is turned off, false otherwise
     */
    public boolean isVSync() {
        return vSync;
    }

    /**
     * Getter, returns the current window object
     *
     * @return the current window object
     */
    public long getWindow() {
        return window;
    }

    /**
     * Getter, returns the width of the window object
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter, returns the height of the window object
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }
}
