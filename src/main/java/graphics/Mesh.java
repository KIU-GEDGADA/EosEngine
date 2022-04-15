package graphics;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;


public class Mesh {
    public Vertex[] vertices;
    public int[] indices;

    private int VAO;
    private int VBO;
    private int CBO;
    private int IBO;

    public Mesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public void init(){
        VAO = glGenVertexArrays();
        glBindVertexArray(VAO);

        FloatBuffer[] buffers = flipBuffer(vertices);
        FloatBuffer vertexBuffer = buffers[0];
        FloatBuffer colorBuffer = buffers[1];

        this.VBO = glGenBuffers();
        System.out.println("Mesh VBO: " + VBO + " created.");
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        this.CBO = glGenBuffers();
        System.out.println("Mesh CBO: " + CBO + " created.");
        glBindBuffer(GL_ARRAY_BUFFER, CBO);
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);

        this.IBO = glGenBuffers();
        System.out.println("Mesh IBO: " + IBO + " created.");
        IntBuffer indexBuffer = flipBuffer(indices);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindVertexArray(0);
    }

    public void bind(){
        glBindVertexArray(VAO);
    }

    public void unbind(){
        glBindVertexArray(0);
    }

    public void render(){
        bind();
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
        unbind();
    }

    public void destroy(){
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDeleteBuffers(VBO);
        glDeleteBuffers(CBO);
        glDeleteBuffers(IBO);
        unbind();
        glDeleteVertexArrays(VAO);
    }

    private FloatBuffer[] flipBuffer(Vertex[] vertices) {
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.VERTEX_SIZE);
        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(vertices.length * Vertex.COLOR_SIZE);
        for (Vertex vertex : vertices) {
            vertexBuffer.put(vertex.position.coordinateArray());
            colorBuffer.put(vertex.color.toArray());
        }
        vertexBuffer.flip();
        colorBuffer.flip();
        return new FloatBuffer[]{vertexBuffer, colorBuffer};
    }

    private IntBuffer flipBuffer(int[] indices) {
        IntBuffer indexBuffer = BufferUtils.createIntBuffer(indices.length);
        indexBuffer.put(indices);
        indexBuffer.flip();
        return indexBuffer;
    }
}