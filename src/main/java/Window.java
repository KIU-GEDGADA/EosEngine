import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    int height;
    int width;
    String name;
    String windowHexColor;
    long window;

    //normal constructor for the window
    public Window(int height, int width, String name,String windowHexColor)
    {
        this.height=height;
        this.width=width;
        this.name = name;
        this.windowHexColor = windowHexColor;
    }

    //constructor that initialises the window alongside creating it
    public void WindowWithInit(int height, int width, String name,String windowHexColor)
    {
        this.height=height;
        this.width=width;
        this.name = name;
        this.windowHexColor = windowHexColor;

        //try...catch block in case window creation fails and a runtime exception needs to be handled
        try{
            window = glfwCreateWindow(width, height, name, NULL, NULL);
            if(window==NULL)
            {
                throw new RuntimeException("Failed to create the GLFW window");
            }
        }catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
            //TODO handle a failed window creation
        }

    }


}
