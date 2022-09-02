package graphics;

import math.Vector2f;
import math.Vector3f;
import utils.DataBufferUtils;
import utils.loaders.OBJLoader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL45C.*;

/**
 * This class handles the mesh, texture and shader mapping of an item
 */
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

    /**
     * Class creator, creates a mesh from vertices, texture coordinates, normals and indices
     *
     * @param vertices           the coordinates and colors vertices of the mesh
     * @param textureCoordinates the coordinates to which a texture should be mapped
     * @param normals            the normals of the mesh
     * @param indices            the indices of the mesh
     */
    public Mesh(Vertex[] vertices, Vector2f[] textureCoordinates, Vector3f[] normals, int[] indices) {
        this.vertices = vertices;
        this.textureCoordinates = textureCoordinates;
        this.indices = indices;
        this.normals = normals;
    }

    /**
     * Class creator, creates a mesh from a filepath to a mesh object
     *
     * @param path the filepath of the desired mesh object
     */
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

    /**
     * This function initializes the mesh, it initializes the mesh to use textures or shaders depending on which one is to be used
     */
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

        this.CBO = glGenBuffers();
        System.out.println("Mesh CBO: " + CBO + " created.");
        glBindBuffer(GL_ARRAY_BUFFER, CBO);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffers[1], GL_STATIC_DRAW);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);

        this.TBO = glGenBuffers();
        System.out.println("Mesh TBO: " + TBO + " created.");
        glBindBuffer(GL_ARRAY_BUFFER, TBO);
        glBufferData(GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);

        this.NBO = glGenBuffers();
        System.out.println("Mesh NBO: " + NBO + " created.");
        glBindBuffer(GL_ARRAY_BUFFER, NBO);
        glBufferData(GL_ARRAY_BUFFER, DataBufferUtils.flipNormals(normals), GL_STATIC_DRAW);
        glVertexAttribPointer(3, 3, GL_FLOAT, false, 0, 0);

        this.IBO = glGenBuffers();
        System.out.println("Mesh IBO: " + IBO + " created.");
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, IBO);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);

        glBindVertexArray(0);

        System.out.println("Mesh ID: " + VAO + " created with " + vertices.length + " vertices and " + textureCoordinates.length + " texture coordinates.");
    }

    /**
     * This function binds the mesh in glfw
     */
    public void bind() {
        glBindVertexArray(VAO);
    }

    /**
     * This function unbinds the mesh in glfw
     */
    public void unbind() {
        glBindVertexArray(0);
    }

    /**
     * This function renders the mesh with triangles
     */
    public void render() {
        bind();
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
        unbind();
    }

    /**
     * This function unbinds the mesh and destroys it
     */
    public void destroy() {
        unbind();
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(3);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(VBO);
        glDeleteBuffers(IBO);
        glDeleteBuffers(TBO);
        glDeleteBuffers(CBO);
        glDeleteBuffers(NBO);
        glDeleteVertexArrays(VAO);
    }
}