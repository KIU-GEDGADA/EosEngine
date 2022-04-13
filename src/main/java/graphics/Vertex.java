package graphics;

import math.*;

public class Vertex {
    public static final int VERTEX_SIZE = 3;
    public static final int COLOR_SIZE = 4;

    public Vector3f position;
    public Color color;

    public Vertex(Vector3f position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Vertex(float x, float y, float z, Color color) {
        this.position = new Vector3f(x, y, z);
        this.color = color;
    }
}
