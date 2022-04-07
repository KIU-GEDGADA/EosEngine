package graphics;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL33.*;

public class Mesh {
    private int vertexArrayObject;
    private int vertexBufferObject;
    private int vertexCount;

    public Mesh(Vertex[] vertices) {
        vertexArrayObject = glGenVertexArrays();
        vertexBufferObject = glGenBuffers();

        glBindVertexArray(vertexArrayObject);
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        for (Vertex vertex : vertices) {
            glBufferData(GL_ARRAY_BUFFER, flipBuffer(vertices), GL_STATIC_DRAW);
        }
        vertexCount = vertices.length / 3;
        glBindVertexArray(0);
    }

    public void render() {
        glBindVertexArray(vertexArrayObject);
        glEnableVertexAttribArray(vertexArrayObject);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        glDisableVertexAttribArray(vertexArrayObject);
        glBindVertexArray(0);
    }

    public void clear() {
        glDeleteBuffers(vertexBufferObject);
        glDeleteVertexArrays(vertexArrayObject);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getVertexArrayObject() {
        return vertexArrayObject;
    }

    public int getVertexBufferObject() {
        return vertexBufferObject;
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
