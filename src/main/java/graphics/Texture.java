package graphics;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    private String filepath;
    private int id;

    public Texture(String filepath){
        this.filepath = filepath;

        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D,id);

        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_REPEAT);

        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        ByteBuffer image = stbi_load(filepath,width,height,channels,0);

        if(image != null){
            glTexImage2D(GL_TEXTURE_2D,0, GL_RGBA, width.get(0),height.get(0),0,GL_RGBA,GL_UNSIGNED_BYTE, image);

        }else{
            assert false : "Error: could not load image" + filepath + " ; ";
        }

        stbi_image_free(image);
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D,id);
    }

    public void unBind(){
        glBindTexture(GL_TEXTURE_2D,0);
    }

}
