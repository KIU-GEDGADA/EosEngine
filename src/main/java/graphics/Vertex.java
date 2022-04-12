package graphics;

import math.*;

public class Vertex {
    public static final int SIZE = 3;

    public Vector3f position;

    public Vertex(Vector3f position) {
        this.position = position;
    }

    public Vertex(float x, float y, float z) {
        this.position = new Vector3f(x, y, z);
    }
}
