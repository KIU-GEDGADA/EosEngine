package graphics;

import math.Vector3f;

import java.util.Arrays;

public class Vertex {

    private Vector3f position;
    private float[] colors;

    public Vertex() {
        position = Vector3f.zero();
        colors = new float[4];
    }

    public Vertex(Vector3f position) {
        this.position = position;
        this.colors = new float[4];
    }

    public Vertex(Vertex vertex) {
        this.position = vertex.position;
        this.colors = vertex.colors;
    }

    public Vertex(Vector3f position, float[] colors) {
        this.position = position;
        this.colors = colors;

    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float[] getColors() {
        return colors;
    }

    public void setColors(float[] colors) {
        this.colors = colors;
    }

    public String toString() {
        return "Position: " + position.toString() + ", Colors: " + Arrays.toString(colors);
    }

    public boolean equals(Vertex vertex) {
        return this.position.equals(vertex.position) && Arrays.equals(this.colors, vertex.colors);
    }
}
