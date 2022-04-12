package graphics;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final ArrayList<Shader> attachedShaders = new ArrayList<>();

    private int shaderProgramID;

    public ShaderProgram() {

    }

    public void init(){
        this.shaderProgramID = glCreateProgram();
    }

    public boolean attachShader(Shader shader) {
        if (attachedShaders.stream().anyMatch(x -> x.getShaderID() == shader.getShaderID())) {
            System.out.println("Shader already attached");
            return false;
        } else {
            attachedShaders.add(shader);
            glAttachShader(shaderProgramID, shader.getShaderID());
            return true;
        }
    }

    public boolean detachShader(Shader shader) {
        if (attachedShaders.removeIf(x -> x.getShaderID() == shader.getShaderID())) {
            glDetachShader(shaderProgramID, shader.getShaderID());
            return true;
        } else {
            System.out.println("Shader is not attached attached");
            return false;
        }
    }

    private boolean detachAll(){
        for(Shader shader : attachedShaders){
            glDetachShader(shaderProgramID, shader.getShaderID());
        }
        return true;
    }

    public void link() {
        System.out.println("There are " + attachedShaders.size() + " shaders attached");
        glLinkProgram(shaderProgramID);
        if (glGetProgrami(shaderProgramID, GL_LINK_STATUS) == GL_FALSE) {
            int len = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);
            System.out.println("Error: Failed to link shaderProgram - " + shaderProgramID);
            System.out.println(glGetProgramInfoLog(shaderProgramID, len));
            System.exit(-1);
        }
        glValidateProgram(shaderProgramID);
        if (glGetProgrami(shaderProgramID, GL_VALIDATE_STATUS) == GL_FALSE) {
            int len = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);
            System.out.println("Error: Failed to validate shaderProgram - " + shaderProgramID);
            System.out.println(glGetProgramInfoLog(shaderProgramID, len));
            System.exit(-1);
        }
        System.out.println("ShaderProgram linked successfully");
    }

    public void bind() {
        glUseProgram(shaderProgramID);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void destroy(){
        unbind();
        detachAll();
        glDeleteProgram(shaderProgramID);
    }

    public int getShaderProgramID() {
        return shaderProgramID;
    }
}