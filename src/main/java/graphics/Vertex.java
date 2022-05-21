package graphics;

import math.Vector2f;
import math.Vector3f;

public class Vertex {

    public Vector3f position;
    public Color color;

    public Vertex(Vector3f position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Vertex(Vector3f position) {
        this.position = position;
        this.color = Color.RED;
    }

    public Vertex(float x, float y, float z, Color color) {
        this.position = new Vector3f(x, y, z);
        this.color = color;
    }
}
