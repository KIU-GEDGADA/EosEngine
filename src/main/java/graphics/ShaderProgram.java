package graphics;

import math.Matrix4x4;
import math.Vector2f;
import math.Vector3f;
import math.Vector4f;
import utils.MathUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL33.*;

public class ShaderProgram {
    private final ArrayList<Shader> attachedShaders = new ArrayList<>();

    private int shaderProgramID;

    private Map<String, Integer> uniforms = new HashMap<String,Integer>();

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

    public void addUniform(String uniform){
        int location = glGetUniformLocation(shaderProgramID,uniform);
        if(location != -1){
            uniforms.put(uniform,location);
        }
    }
    public void removeUniform(String uniform){
        int location = glGetUniformLocation(shaderProgramID,uniform);
        if(location == -1){
            uniforms.remove(uniform);
        }
    }

    public Map<String, Integer> getUniforms(){
        return uniforms;
    }

    public void setUniformi(String name, int value){
        int location = uniforms.get(name);
        if(location != -1){
            glUniform1i(location,value);
        }
    }
    public void setUniformf(String name, float value){
        int location = uniforms.get(name);
        if(location != -1){
            glUniform1f(location,value);
        }
    }
    public void setUniformv2f(String name, Vector2f value){
        int location = uniforms.get(name);
        if(location != -1){
            glUniform2f(location,
                    value.coordinateArray()[0],
                    value.coordinateArray()[1]);
        }
    }
    public void setUniformv3f(String name, Vector3f value){
        int location = uniforms.get(name);
        if(location != -1){
            glUniform3f(location,
                    value.coordinateArray()[0],
                    value.coordinateArray()[1],
                    value.coordinateArray()[2]);
        }
    }
    public void setUniformv4f(String name, Vector4f value){
        int location = uniforms.get(name);
        if(location != -1){
            glUniform4f(location,
                    value.coordinateArray()[0],
                    value.coordinateArray()[1],
                    value.coordinateArray()[2],
                    value.coordinateArray()[3]);
        }
    }
    public void setUniformm4f(String name, Matrix4x4 value){
        int location = uniforms.get(name);
        FloatBuffer buffer = MathUtils.matrixToFloatBuffer(value);
        if(location != -1){
            glUniform4fv(location,buffer);
        }
    }

    public void setTexture(String name, int slot){
        int location = uniforms.get(name);
        if(location != -1){
            glUniform1i(location,slot);
        }
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