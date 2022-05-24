package graphics;

import math.Vector3f;

public class Vertex {

    private Vector3f position;
    private Color color;

    public Vertex(Vector3f position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Vertex(Vector3f position) {
        this.position = position;
        this.color = Color.random();
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
