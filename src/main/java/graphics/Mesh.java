package graphics;

import math.Vector2f;
import math.Vector3f;
import utils.DataBufferUtils;
import utils.loaders.OBJLoader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;


public class Mesh {
    private String path;
    private Vertex[] vertices;
    private Vector2f[] textureCoordinates;
    private Vector3f[] normals;
    private int[] indices;
    private int VAO;
    private int VBO;
    private int CBO;
    private int IBO;
    private int TBO;
    private int NBO;
    private boolean usingTexture;

    public Mesh(Vertex[] vertices, Vector2f[] textureCoordinates, Vector3f[] normals, int[] indices) {
        this.vertices = vertices;
        this.textureCoordinates = textureCoordinates;
        this.indices = indices;
        this.normals = normals;
    }

    public Mesh(String path) {
        this.path = path;
    }

    private void checkExternalLoading() {
        if (path != null) {
            Mesh tempMesh = OBJLoader.loadModel(path);
            this.vertices = tempMesh.vertices;
            this.indices = tempMesh.indices;
            this.textureCoordinates = tempMesh.textureCoordinates;
            this.normals = tempMesh.normals;
            this.path = null;
        }
    }

    public void init() {

        checkExternalLoading();

        VAO = glGenVertexArrays();
        glBindVertexArray(VAO);

        FloatBuffer[] vertexBuffers = DataBufferUtils.flipVertices(vertices);
        FloatBuffer textureBuffer = DataBufferUtils.flipCoordinates(textureCoordinates);
        IntBuffer indexBuffer = DataBufferUtils.flipIndices(indices);

        this.VBO = glGenBuffers();
        System.out.println("Mesh VBO: " + VBO + " created.");
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffers[0], GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        if (!usingTexture) {
            this.CBO = glGenBuffers();
            System.out.println("Mesh CBO: " + CBO + " created.");
            glBindBuffer(GL_ARRAY_BUFFER, CBO);
            glBufferData(GL_ARRAY_BUFFER, vertexBuffers[1], GL_STATIC_DRAW);
            glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
        } else {
            this.TBO = glGenBuffers();
            System.out.println("Mesh TBO: " + TBO + " created.");
            glBindBuffer(GL_TEXTURE_BUFFER, TBO);
            glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
            glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
        }

        this.NBO = glGenBuffers();
        System.out.println("Mesh NBO: " + NBO + " created.");
        glBindBuffer(GL_NORMAL_ARRAY, NBO);
        glBufferData(GL_NORMAL_ARRAY, DataBufferUtils.flipNormals(normals), GL_STATIC_DRAW);
        glVertexAttribPointer(3, 3, GL_FLOAT, false, 0, 0);

        this.IBO = glGenBuffers();
        System.out.println("Mesh IBO: " + IBO + " created.");
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        if (!usingTexture) {
            glEnableVertexAttribArray(1);
        } else {
            glEnableVertexAttribArray(2);
        }
        glEnableVertexAttribArray(3);

        glBindVertexArray(0);

        System.out.println("Mesh ID: " + VAO + " created with " + vertices.length + " vertices and " + textureCoordinates.length + " texture coordinates.");
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
        unbind();
        glDisableVertexAttribArray(0);
        if (!usingTexture) {
            glDisableVertexAttribArray(1);
        } else {
            glDisableVertexAttribArray(2);
        }
        glDisableVertexAttribArray(3);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        if (!usingTexture) {
            glBindBuffer(GL_ARRAY_BUFFER, 0);
        } else {
            glBindBuffer(GL_TEXTURE_BUFFER, 0);
        }
        glBindBuffer(GL_NORMAL_ARRAY, 0);
        glDeleteBuffers(VBO);
        glDeleteBuffers(IBO);
        glDeleteBuffers(TBO);
        glDeleteBuffers(CBO);
        glDeleteBuffers(NBO);
        glDeleteVertexArrays(VAO);
    }

    public void useTexture() {
        this.usingTexture = true;
    }

    public void useColor() {
        this.usingTexture = false;
    }

    public boolean isUsingTexture() {
        return usingTexture;
    }
}