package graphics;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

/**
 * This class handles the texture of an item
 */
public class Texture {
    private final String filepath;
    private int id;

    /**
     * Class creator, creates the class using a filepath to a texture or image
     *
     * @param filepath the filepath of the desired texture/image
     */
    public Texture(String filepath) {
        this.filepath = filepath;
    }

    /**
     * This function initializes the texture under the settings:
     * -Wrap horizontally and vertically
     * -The image taken in must follow the RGBA format
     */
    public void init() {
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(filepath, width, height, channels, 4);

        if (image != null) {
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glGenerateMipmap(GL_TEXTURE_2D);
        } else {
            assert false : "Error: could not load image" + filepath + " ; ";
        }

        stbi_image_free(image);
    }

    /**
     * This function binds the texture in glfw
     */
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    /**
     * This function unbinds the texture in glfw
     */
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    /**
     * This function destroys the current texture
     */
    public void destroy() {
        glDeleteTextures(id);
    }


    public String getFilePath() {
        return filepath;
    }
}
