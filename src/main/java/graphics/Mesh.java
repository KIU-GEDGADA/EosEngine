package graphics;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

public class Mesh {
    private int vertexArrayObject;
    private int vertexBufferObject;
    private int elementArrayObject;
    private Shader shader;
    private int elementCount;

    public Mesh(Vertex[] vertices, int[] elements, Shader shader) {
        this.shader = shader;
        shader.compileAndLink();

        vertexArrayObject = glGenVertexArrays();
        vertexBufferObject = glGenBuffers();
        elementArrayObject = glGenBuffers();

        glBindVertexArray(vertexArrayObject);
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        for (Vertex vertex : vertices) {
            glBufferData(GL_ARRAY_BUFFER, flipBuffer(vertices), GL_STATIC_DRAW);
        }
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elements.length);
        elementBuffer.put(elements).flip();

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementArrayObject);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementArrayObject, GL_STATIC_DRAW);

        elementCount = elements.length;
        glBindVertexArray(0);
    }

    public void render() {
        glBindVertexArray(vertexArrayObject);

        int positionsSize = 3;
        int colorSize = 4;
        int floatSizeInBytes = 4;
        int vertexSizeInBytes = (positionsSize + colorSize) * floatSizeInBytes;

        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeInBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeInBytes, positionsSize * floatSizeInBytes);
        glEnableVertexAttribArray(1);

        shader.use();

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementCount, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);
    }

    public void clear() {
        glDeleteBuffers(vertexBufferObject);
        glDeleteVertexArrays(vertexArrayObject);

        shader.detach();
    }

    public void stop() {
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);
        shader.detach();
    }

    public void setShader(Shader shader) {
        try {
            stop();
        } catch (Exception e) {
            System.out.println("Shader could not be stopped or not running");
            e.printStackTrace();
        }
        this.shader = shader;
        this.shader.use();
    }

    public int getVertexArrayObject() {
        return vertexArrayObject;
    }

    public int getVertexBufferObject() {
        return vertexBufferObject;
    }

    public int getElementCount() {
        return elementCount;
    }

    public int getElementArrayObject() {
        return elementArrayObject;
    }

    public FloatBuffer flipBuffer(Vertex[] vertices) {
        FloatBuffer buffer = FloatBuffer.allocate(vertices.length * 3);
        for (Vertex vertex : vertices) {
            buffer.put(vertex.getPosition().coordinateArray());
        }
        buffer.flip();
        return buffer;
    }
}
