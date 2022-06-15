package graphics;

/**
 * This class handles the texture and mesh of an item
 */
public class Model {
    private Mesh mesh;
    private Texture texture;

    /**
     * Class constructor, creates the model from the given mesh and texture
     * @param mesh the mesh object of the model
     * @param texture the texture object of the model
     */
    public Model(Mesh mesh, Texture texture) {
        this.mesh = mesh;
        this.texture = texture;
        mesh.useTexture();
    }

    /**
     * Class constructor, creates the model from the given mesh
     * @param mesh the mesh object of the model
     */
    public Model(Mesh mesh) {
        this.mesh = mesh;
        mesh.useColor();
    }

    /**
     * Getter, returns the mesh object of the model
     * @return the mesh object of the model
     */
    public Mesh getMesh() {
        return mesh;
    }

    /**
     * Getter, returns the texture object of the model
     * @return the texture object of the model
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Setter, sets the texture of the model
     * @param texture the desired texture model of the object
     */
    public void setTexture(Texture texture) {
        this.texture = texture;
        if (this.texture == null) {
            mesh.useColor();
        } else {
            mesh.useTexture();
        }
    }

    /**
     * This function initializes the mesh and texture of the model
     */
    public void init() {
        mesh.init();
        if (texture != null) {
            texture.init();
        }
    }

    /**
     * This function destroys the mesh and texture of the model
     */
    public void destroy() {
        mesh.destroy();
        if (texture != null) {
            texture.destroy();
        }
    }
}
