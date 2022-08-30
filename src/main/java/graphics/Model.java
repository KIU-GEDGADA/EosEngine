package graphics;

/**
 * This class handles the texture and mesh of an item
 */
public class Model {
    private Mesh mesh;
    private Material material;

    /**
     * Class constructor, creates the model from the given mesh and texture
     *
     * @param mesh    the mesh object of the model
     * @param texture the texture object of the model
     */
    public Model(Mesh mesh, Texture texture) {
        this.mesh = mesh;
        this.material = new Material(texture);
        mesh.useTexture();
    }

    /**
     * Class constructor, creates the model from the given mesh
     *
     * @param mesh the mesh object of the model
     */
    public Model(Mesh mesh) {
        this.mesh = mesh;
        this.material = new Material();
        mesh.useColor();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Getter, returns the mesh object of the model
     *
     * @return the mesh object of the model
     */
    public Mesh getMesh() {
        return mesh;
    }

    /**
     * Getter, returns the texture object of the model
     *
     * @return the texture object of the model
     */
    public Texture getTexture() {
        return material.getTexture();
    }

    /**
     * Setter, sets the texture of the model
     *
     * @param texture the desired texture model of the object
     */
    public Model setTexture(Texture texture) {
        material.setTexture(texture);
        if (!material.hasTexture()) {
            mesh.useColor();
        } else {
            mesh.useTexture();
        }
        return this;
    }

    public Model setTexture(Texture texture, float reflectance) {
        material.setTexture(texture);
        material.setReflectance(reflectance);
        if (!material.hasTexture()) {
            mesh.useColor();
        } else {
            mesh.useTexture();
        }
        return this;
    }

    /**
     * This function initializes the mesh and texture of the model
     */
    public void init() {
        mesh.init();
        if (material.hasTexture()) {
            material.getTexture().init();
        }
    }

    /**
     * This function destroys the mesh and texture of the model
     */
    public void destroy() {
        mesh.destroy();
        if (material.hasTexture()) {
            material.getTexture().destroy();
        }
    }
}
