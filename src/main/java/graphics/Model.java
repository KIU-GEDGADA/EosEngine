package graphics;

public class Model {
    private Mesh mesh;
    private Texture texture;

    public Model(Mesh mesh, Texture texture) {
        this.mesh = mesh;
        this.texture = texture;
        mesh.useTexture();
    }

    public Model(Mesh mesh) {
        this.mesh = mesh;
        mesh.useColor();
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        if (this.texture == null) {
            mesh.useColor();
        } else {
            mesh.useTexture();
        }
    }

    public void init() {
        mesh.init();
        if (texture != null) {
            texture.init();
        }
    }

    public void destroy() {
        mesh.destroy();
        if (texture != null) {
            texture.destroy();
        }
    }
}
