package graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

public class Mesh {
    int positionSize = 3;
    int colorSize = 4;
    int floatSizeInBytes = 4;
    int vertexSizeInBytes = (positionSize + colorSize) * floatSizeInBytes;

    private int vertexArrayObject;
    private int vertexBufferObject;
    private int elementArrayObject;
    private int vertexCount;
    private Shader shader;

    public Mesh(Vertex[] vertices, int[] elements, Shader shader) {
        this.shader = shader;
        this.shader.compileAndLink();
        this.shader.use();

        vertexCount = elements.length;

        vertexArrayObject = glGenVertexArrays();
        vertexBufferObject = glGenBuffers();
        elementArrayObject = glGenBuffers();

        glBindVertexArray(vertexArrayObject);
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        glBufferData(GL_ARRAY_BUFFER, flippedBuffer(vertices), GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementArrayObject);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, flippedBuffer(elements), GL_STATIC_DRAW);
        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeInBytes, 0);
        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeInBytes, positionSize * floatSizeInBytes);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindVertexArray(0);
    }

    public void render() {
        glBindVertexArray(vertexArrayObject);

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementArrayObject);

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);


        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glBindVertexArray(0);
        System.out.println("Rendered");
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

    public int getVertexCount() {
        return vertexCount;
    }

    public int getElementArrayObject() {
        return elementArrayObject;
    }

    public FloatBuffer flippedBuffer(Vertex[] vertices) {
        FloatBuffer buffer = FloatBuffer.allocate(vertices.length * 7);
        for (Vertex vertex : vertices) {
            buffer.put(vertex.getPosition().coordinateArray());
            buffer.put(vertex.getColor().coordinateArray());
        }
        buffer.flip();
        return buffer;
    }

    public IntBuffer flippedBuffer(int[] elements) {
        IntBuffer buffer = IntBuffer.allocate(elements.length);
        for (int element : elements) {
            buffer.put(element);
        }
        buffer.flip();
        return buffer;
    }
}