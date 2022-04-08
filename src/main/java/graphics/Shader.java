package graphics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL33.*;

public class Shader {

    private int shaderProgrammeID;

    private String vertexSource;
    private String fragmentSource;
    private String filePath;

    public Shader(String filePath) {
        this.filePath = filePath;
        try {
            String source = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] splitString = source.split("(//type)( )+([a-zA-Z]+)");

            int index = source.indexOf("//type") + 6;
            int endOfLine = source.indexOf("\r\n", index);
            String firstPattern = source.substring(index, endOfLine).trim();

            index = source.indexOf("//type", endOfLine) + 6;
            endOfLine = source.indexOf("\r\n", index);
            String secondPattern = source.substring(index, endOfLine).trim();

            if (firstPattern.equals("vertex")) {
                vertexSource = splitString[1];
            } else if (firstPattern.equals("fragment")) {
                fragmentSource = splitString[1];
            } else {
                throw new IOException("Unexpected shader type " + firstPattern + "'");
            }

            if (secondPattern.equals("vertex")) {
                vertexSource = splitString[2];
            } else if (secondPattern.equals("fragment")) {
                fragmentSource = splitString[2];
            } else {
                throw new IOException("Unexpected shader type " + secondPattern + "'");
            }

        } catch (IOException e) {
            e.printStackTrace();
            assert false : "Error: Could not Open Shader" + filePath + "'";
        }
    }

    public void compileAndLink() {
        int vertexID;
        int fragmentID;

        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        if (glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println("ERROR: vertex shader - " + filePath + "could not be be compiled");
            System.out.println(glGetShaderInfoLog(vertexID, glGetShaderi(vertexID, GL_INFO_LOG_LENGTH)));
            assert false : "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        if (glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.out.println("ERROR: fragment shader - " + filePath + "could not be be compiled");
            System.out.println(glGetShaderInfoLog(fragmentID, glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH)));
            assert false : "";
        }

        this.shaderProgrammeID = glCreateProgram();
        glAttachShader(this.shaderProgrammeID, vertexID);
        glAttachShader(this.shaderProgrammeID, fragmentID);
        glLinkProgram(this.shaderProgrammeID);

        if (glGetProgrami(this.shaderProgrammeID, GL_LINK_STATUS) == GL_FALSE) {
            System.out.println("ERROR: " + filePath + " shaders could not be linked");
            System.out.println(glGetProgramInfoLog(this.shaderProgrammeID, glGetProgrami(fragmentID, GL_INFO_LOG_LENGTH)));
            assert false : "";
        }
        System.out.println("Shader " + filePath + " compiled and linked successfully");
    }

    public void use() {
        glUseProgram(shaderProgrammeID);
    }

    public void detach() {
        glUseProgram(0);
    }
}
