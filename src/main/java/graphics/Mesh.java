package graphics;

import math.Vector2f;
import math.Vector3f;
import utils.DataBufferUtils;
import utils.loaders.OBJLoader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import static org.lwjgl.opengl.GL33.*;


public class Mesh {
    public final boolean usingTexture;
    public String path;
    public Vertex[] vertices;
    public Vector2f[] textureCoordinates;
    public Vector3f[] normals;
    public int[] indices;
    private int VAO;
    private int VBO;
    private int CBO;
    private int IBO;
    private int UVBO;

    public Mesh(Vertex[] vertices, int[] indices, Vector2f[] textureCoordinates, Vector3f[] normals) {
        this.vertices = vertices;
        this.textureCoordinates = textureCoordinates;
        this.indices = indices;
        this.normals = normals;
        this.usingTexture = true;
    }

    public Mesh(String path, boolean usingTexture) {
        this.path = path;
        this.usingTexture = usingTexture;
    }

    public Mesh(Vertex[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
        this.usingTexture = false;
    }

    private void checkExternalLoading() {
        if (path != null) {
            Mesh tempMesh = OBJLoader.loadModel(path);
            this.vertices = tempMesh.vertices;
            this.indices = tempMesh.indices;
            this.textureCoordinates = tempMesh.textureCoordinates;
            this.path = null;
        }
    }

    public void init() {

        checkExternalLoading();

        VAO = glGenVertexArrays();
        glBindVertexArray(VAO);

        FloatBuffer[] buffers = DataBufferUtils.flipVertices(vertices);
        FloatBuffer vertexBuffer = buffers[0];
        FloatBuffer colorBuffer = buffers[1];
        FloatBuffer texBuffer = DataBufferUtils.flipCoordinates(textureCoordinates);
        IntBuffer indexBuffer = DataBufferUtils.flipIndices(indices);

        this.VBO = glGenBuffers();
        System.out.println("Mesh VBO: " + VBO + " created.");
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        this.IBO = glGenBuffers();
        System.out.println("Mesh IBO: " + IBO + " created.");
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

        if (usingTexture) {
            this.UVBO = glGenBuffers();
            System.out.println("Mesh UVBO: " + UVBO + " created.");
            glBindBuffer(GL_TEXTURE_BUFFER, UVBO);
            glBufferData(GL_ARRAY_BUFFER, texBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
        } else {
            this.CBO = glGenBuffers();
            System.out.println("Mesh CBO: " + CBO + " created.");
            glBindBuffer(GL_ARRAY_BUFFER, CBO);
            glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
        }

        glEnableVertexAttribArray(0);

        if (usingTexture) {
            glEnableVertexAttribArray(2);
        } else {
            glEnableVertexAttribArray(1);
        }

        glBindVertexArray(0);

        System.out.println("Mesh created with " + vertices.length + " vertices" + (usingTexture ? " and " + textureCoordinates.length + " texture coordinates." : ""));
    }

    public void bind() {
        glBindVertexArray(VAO);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public void render() {
        bind();
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
        unbind();
    }

    public void destroy() {
        glDisableVertexAttribArray(0);
        if (usingTexture) {
            glDisableVertexAttribArray(2);
        } else {
            glDisableVertexAttribArray(1);
        }
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        if (usingTexture) {
            glBindBuffer(GL_TEXTURE_BUFFER, 0);
        } else {
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        }
        glDeleteBuffers(VBO);
        glDeleteBuffers(IBO);
        if (usingTexture) {
            glDeleteBuffers(UVBO);
        } else {
            glDeleteBuffers(CBO);
        }
        unbind();
        glDeleteVertexArrays(VAO);
    }

}