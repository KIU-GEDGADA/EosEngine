package graphics;

import math.Matrix4x4;
import math.Vector2f;
import math.Vector3f;
import math.Vector4f;
import org.lwjgl.BufferUtils;
import utils.DataBufferUtils;
import utils.MathUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL33.*;

/**
 * This class handles shaders and uniforms of an item
 */
public class ShaderProgram {
    private final List<Shader> attachedShaders = new ArrayList<>();
    private int shaderProgramID;
    private final Map<String, Integer> uniforms = new HashMap<>();

    /**
     * Class constructor
     */
    public ShaderProgram() {

    }

    /**
     * Getter, this function returns the list of shaders attached to this shaderProgram
     * @return
     */
    public List<Shader> getAttachedShaders() {
        return attachedShaders;
    }

    /**
     * This function initializes the shaderProgram
     */
    public void init() {
        this.shaderProgramID = glCreateProgram();
    }

    /**
     * This function attaches a shader to the shaderProgram, returns true if attachment is successful, false otherwise
     * @param shader the shader to be attached
     * @return true if attachment is successful, false otherwise
     */
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

    /**
     * This function detaches a shader from the shaderProgram,returns true if detachment is successful, false otherwise
     * @param shader the shader to be detached
     * @return true if detachment is successful, false otherwise
     */
    public boolean detachShader(Shader shader) {
        if (attachedShaders.removeIf(x -> x.getShaderID() == shader.getShaderID())) {
            glDetachShader(shaderProgramID, shader.getShaderID());
            return true;
        } else {
            System.out.println("Shader is not attached attached");
            return false;
        }
    }

    /**
     * This function detaches all shaders from the shaderProgram, returns true if detachment is successful, false otherwise
     * @return true if detachment is successful, false otherwise
     */
    private boolean detachAll() {
        for (Shader shader : attachedShaders) {
            glDetachShader(shaderProgramID, shader.getShaderID());
        }
        return true;
    }

    /**
     * This function links the shaderProgram
     */
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

    /**
     * This function binds the shaderProgram
     */
    public void bind() {
        glUseProgram(shaderProgramID);
    }

    /**
     * This function unbinds the shaderProgram
     */
    public void unbind() {
        glUseProgram(0);
    }

    /**
     * This function adds a uniform to the shaderProgram
     * @param uniform a uniform from a shader to be added to the shaderProgram
     */
    public void addUniform(String uniform) {
        int location = glGetUniformLocation(shaderProgramID, uniform);
        if (location != -1) {
            uniforms.put(uniform, location);
        }
    }

    /**
     * This function removes a uniform from the shaderProgram
     * @param uniform the uniform to be removed
     */
    public void removeUniform(String uniform) {
        int location = glGetUniformLocation(shaderProgramID, uniform);
        if (location == -1) {
            uniforms.remove(uniform);
        }
    }

    /**
     * Getter, this function returns a Map of the shaderPrograms uniforms containing the uniform names and locations
     * @return a Map of the shaderPrograms uniforms containing the uniform names and locations
     */
    public Map<String, Integer> getUniforms() {
        return uniforms;
    }

    /**
     * This function sets an int uniform to the given value
     * the uniform must be added to the shaderProgramme before the use of the function
     * @param name the name of the uniform to set
     * @param value the desired value
     */
    public void setUniform(String name, int value) {
        int location = uniforms.get(name);
        if (location != -1) {
            glUniform1i(location, value);
        }
    }

    /**
     * This function sets a float uniform to the given value
     * the uniform must be added to the shaderProgramme before the use of the function
     * @param name the name of the uniform to set
     * @param value the desired value
     */
    public void setUniform(String name, float value) {
        int location = uniforms.get(name);
        if (location != -1) {
            glUniform1f(location, value);
        }
    }

    /**
     * This function sets a boolean uniform to the given value
     * the uniform must be added to the shaderProgramme before the use of the function
     * @param name the name of the uniform to set
     * @param value the desired value
     */
    public void setUniform(String name, boolean value) {
        int location = uniforms.get(name);
        if (location != -1) {
            glUniform1i(location, value ? 1 : 0);
        }
    }

    /**
     * This function sets a Vector2f uniform to the given value
     * the uniform must be added to the shaderProgramme before the use of the function
     * @param name the name of the uniform to set
     * @param value the desired value
     */
    public void setUniform(String name, Vector2f value) {
        int location = uniforms.get(name);
        if (location != -1) {
            glUniform2f(location,
                    value.coordinateArray()[0],
                    value.coordinateArray()[1]);
        }
    }

    /**
     * This function sets a Vector3f uniform to the given value
     * the uniform must be added to the shaderProgramme before the use of the function
     * @param name the name of the uniform to set
     * @param value the desired value
     */
    public void setUniform(String name, Vector3f value) {
        int location = uniforms.get(name);
        if (location != -1) {
            glUniform3f(location,
                    value.coordinateArray()[0],
                    value.coordinateArray()[1],
                    value.coordinateArray()[2]);
        }
    }

    /**
     * This function sets a Vector4f uniform to the given value
     * the uniform must be added to the shaderProgramme before the use of the function
     * @param name the name of the uniform to set
     * @param value the desired value
     */
    public void setUniform(String name, Vector4f value) {
        int location = uniforms.get(name);
        if (location != -1) {
            glUniform4f(location,
                    value.coordinateArray()[0],
                    value.coordinateArray()[1],
                    value.coordinateArray()[2],
                    value.coordinateArray()[3]);
        }
    }

    /**
     * This function sets a Matrix4x4 uniform to the given value
     * the uniform must be added to the shaderProgramme before the use of the function
     * @param name the name of the uniform to set
     * @param value the desired value
     */
    public void setUniform(String name, Matrix4x4 value) {
        int location = uniforms.get(name);
        if (location != -1) {
            glUniformMatrix4fv(location, false, DataBufferUtils.flipMatrix(value));
        }
    }

    /**
     * This function sets a Texture uniform to the given slot
     * the uniform must be added to the shaderProgramme before the use of the function
     * @param name the name of the uniform to set
     * @param slot the desired slot
     */
    public void setTexture(String name, int slot) {
        if (uniforms.isEmpty()) {
            System.out.println("Uniforms not set");
        }
        int location = uniforms.get(name);
        if (location != -1) {
            glUniform1i(location, slot);
        }
    }

    /**
     * This function unbinds the shaderProgramme, detaches all shaders and destroys the shaderProgram
     */
    public void destroy() {
        unbind();
        detachAll();
        glDeleteProgram(shaderProgramID);
    }

    /**
     * Getter, this function returns the shaderProgram's id
     * @return the shaderProgram's id
     */
    public int getShaderProgramID() {
        return shaderProgramID;
    }
}