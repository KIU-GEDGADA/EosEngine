package graphics;

import math.Vector3f;
import math.Vector4f;

public class Vertex {

    private Vector3f position;
    private Vector4f color;

    public Vertex() {
        this.position = Vector3f.zero();
        this.color = new Vector4f(0, 0, 0, 0);
    }

    public Vertex(Vector3f position) {
        this.position = position;
        this.color = new Vector4f(0, 0, 0, 0);
    }

    public Vertex(Vertex vertex) {
        this.position = vertex.position;
        this.color = vertex.color;
    }

    public Vertex(Vector3f position, Vector4f colors) {
        this.position = position;
        this.color = colors;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector4f getColor() {
        return color;
    }

    public void setColor(Vector4f colors) {
        this.color = colors;
    }

    public String toString() {
        return "Position: " + position.toString() + ", Colors: " + color.toString();
    }

    public boolean equals(Vertex vertex) {
        return this.position.equals(vertex.position) && this.color.equals(vertex.color);
    }
}
