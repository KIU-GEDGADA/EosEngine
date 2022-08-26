package graphics;

public class IndexGroup {
    public int vertexIndex;
    public int textureIndex;
    public int normalIndex;

    public IndexGroup() {
        this.vertexIndex = -1;
        this.textureIndex = -1;
        this.normalIndex = -1;
    }

    public IndexGroup(int vertexIndex, int textureIndex, int normalIndex) {
        this.vertexIndex = vertexIndex;
        this.textureIndex = textureIndex;
        this.normalIndex = normalIndex;
    }
}