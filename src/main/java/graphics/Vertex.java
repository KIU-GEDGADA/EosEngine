package graphics;

import math.*;

public class Vertex {
    public static final int VERTEX_SIZE = 3;
    public static final int COLOR_SIZE = 4;
    public static final int UVCORDS_SIZE = 2;

    public Vector3f position;
    public Color color;
    public Vector2f UVCoordinates = null;

    public Vertex(Vector3f position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Vertex(Vector3f position, Color color,Vector2f uvCoordinates) {
        this.position = position;
        this.color = color;
        this.UVCoordinates = uvCoordinates;
    }

    public Vertex(Vector3f position,Vector2f uvCoordinates) {
        this.position = position;
        this.color = Color.GREY;
        this.UVCoordinates = uvCoordinates;
    }

    public Vertex(float x, float y, float z, Color color) {
        this.position = new Vector3f(x, y, z);
        this.color = color;
    }

    public Vertex(Vector3f position) {
        this.position = position;
        this.color = Color.GREY;
    }
}
