package graphics;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Shader {

    private int shaderProgrammeID;

    private String vertexSource;
    private String fragmentSource;
    private String filePath;

    private float[] vertexArray;
    private int[] elementArray;

    private int vaoID;
    private int vboID;
    private int eboID;

    public Shader(String filePath){
        this.filePath = filePath;
        try{
            String source = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] splitString = source.split("(#type)( )+([a-zA-Z]+)");

            int index = source.indexOf("#type") + 6;
            int endOfLine = source.indexOf("\r\n",index);
            String firstPattern = source.substring(index,endOfLine).trim();

            index = source.indexOf("#type",endOfLine) + 6;
            endOfLine = source.indexOf("\r\n",index);
            String secondPattern = source.substring(index,endOfLine).trim();

            if(firstPattern.equals("vertex")){
                vertexSource = splitString[1];
            }else if(firstPattern.equals("fragment")){
                fragmentSource = splitString[1];
            }else{
                throw new IOException("Unexpected shader type " + firstPattern + "'");
            }

            if(secondPattern.equals("vertex")){
                vertexSource = splitString[2];
            }else if(secondPattern.equals("fragment")){
                fragmentSource = splitString[2];
            }else {
                throw new IOException("Unexpected shader type " + secondPattern + "'");
            }

        }catch (IOException e){
            e.printStackTrace();
            assert false: "Error: Could not Open Shader" + filePath + "'";
        }

    }

    public void compileAndLink(){
        int vertexID;
        int fragmentID;
        int shaderProgramID;

        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID,vertexSource);
        glCompileShader(vertexID);

        if(glGetShaderi(vertexID,GL_COMPILE_STATUS) == GL_FALSE){
            System.out.println("ERROR: vertex shader - " + filePath + "could not be be compiled");
            System.out.println(glGetShaderInfoLog(vertexID,glGetShaderi(vertexID,GL_INFO_LOG_LENGTH)));
            assert false: "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID,fragmentSource);
        glCompileShader(fragmentID);

        if(glGetShaderi(fragmentID,GL_COMPILE_STATUS) == GL_FALSE){
            System.out.println("ERROR: fragment shader - " + filePath +  "could not be be compiled");
            System.out.println(glGetShaderInfoLog(fragmentID,glGetShaderi(fragmentID,GL_INFO_LOG_LENGTH)));
            assert false: "";
        }

        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID,vertexID);
        glAttachShader(shaderProgramID,fragmentID);
        glLinkProgram(shaderProgramID);

        if(glGetProgrami(shaderProgramID,GL_LINK_STATUS) == GL_FALSE){
            System.out.println("ERROR: " + filePath + " shaders could not be linked");
            System.out.println(glGetProgramInfoLog(shaderProgramID,glGetProgrami(fragmentID,GL_INFO_LOG_LENGTH)));
            assert false: "";
        }
    }

    public void setVertexArray(float[] vertexArray){
        this.vertexArray = vertexArray;
    }

    public void setElementArray(int[] elementArray){
        this.elementArray = elementArray;
    }

    public void init(){
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vboID);
        glBufferData(GL_ARRAY_BUFFER,vertexBuffer,GL_STATIC_DRAW);

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,eboID,GL_STATIC_DRAW);

        int positionsSize = 3;
        int colorSize = 4;
        int floatSizeInBytes = 4;
        int vertexSizeInBytes =  (positionsSize + colorSize) * floatSizeInBytes;
        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeInBytes, 0 );
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeInBytes, positionsSize*floatSizeInBytes);
        glEnableVertexAttribArray(1);


    }

    public void use(){
        glUseProgram(shaderProgrammeID);
        glBindVertexArray(vaoID);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length,GL_UNSIGNED_INT, 0);

    }

    public void detach(){
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        glUseProgram(0);
    }
}
