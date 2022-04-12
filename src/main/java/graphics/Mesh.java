package graphics;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;


public class Mesh {
    public Vertex[] vertices;

    private int VBO;
    private FloatBuffer vertexBuffer;
    private final int vertexCount;

    public Mesh(Vertex[] vertices){
        this.vertices = vertices;
        this.vertexCount = vertices.length;
    }

    public void init(){
        this.VBO = glGenBuffers();
        System.out.println("Mesh VBO: " + VBO + " created.");
    }

    public void bind(){
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        vertexBuffer = flippedBuffer(vertices);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
    }

    public void render(){
        glEnableVertexAttribArray(0);
        bind();
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        unbind();
        glDisableVertexAttribArray(0);
    }

    public void unbind(){
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    public void destroy(){
        vertexBuffer.clear();
        glDeleteBuffers(VBO);
    }

    public static FloatBuffer flippedBuffer(Vertex[] vertices) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.SIZE);
        for (Vertex vertex : vertices) {
            buffer.put(vertex.position.x);
            buffer.put(vertex.position.y);
            buffer.put(vertex.position.z);
        }
        buffer.flip();
        return buffer;
    }
}