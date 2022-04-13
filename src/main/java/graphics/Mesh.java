package graphics;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL33.*;


public class Mesh {
    public Vertex[] vertices;

    private int VAO;
    private int VBO;
    private int CBO;

    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;

    private final int vertexCount;

    public Mesh(Vertex[] vertices){
        this.vertices = vertices;
        this.vertexCount = vertices.length;
    }

    public void init(){
        VAO = glGenVertexArrays();
        glBindVertexArray(VAO);
        FloatBuffer[] buffers = flipBuffer(vertices);
        vertexBuffer = buffers[0];
        colorBuffer = buffers[1];

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

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glBindVertexArray(0);
    }

    public void bind(){
        glBindVertexArray(VAO);
    }

    public void render(){
        bind();
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        unbind();
    }

    public void unbind(){
        glBindVertexArray(0);
    }

    public void destroy(){
        unbind();
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(VBO);
        glDeleteBuffers(CBO);
        glDeleteVertexArrays(VAO);
    }

    public static FloatBuffer[] flipBuffer(Vertex[] vertices) {
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
}