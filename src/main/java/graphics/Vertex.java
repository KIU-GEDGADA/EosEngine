package graphics;

import math.Vector3f;
import math.Vector4f;

public class Vertex {

    private Vector3f position;
    private Vector4f colors;

    public Vertex() {
        this.position = Vector3f.zero();
        this.colors = new Vector4f(0, 0, 0, 0);
    }

    public Vertex(Vector3f position) {
        this.position = position;
        this.colors = new Vector4f(0, 0, 0, 0);
    }

    public Vertex(Vertex vertex) {
        this.position = vertex.position;
        this.colors = vertex.colors;
    }

    public Vertex(Vector3f position, Vector4f colors) {
        this.position = position;
        this.colors = colors;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector4f getColors() {
        return colors;
    }

    public void setColors(Vector4f colors) {
        this.colors = colors;
    }

    public String toString() {
        return "Position: " + position.toString() + ", Colors: " + colors.toString();
    }

    public boolean equals(Vertex vertex) {
        return this.position.equals(vertex.position) && this.colors.equals(vertex.colors);
    }
}
