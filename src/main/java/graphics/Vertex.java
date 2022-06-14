package graphics;

import math.Vector3f;

/**
 * This class contains a vertex of a position vector and a color object
 */
public class Vertex {

    private Vector3f position;
    private Color color;

    /**
     * Class creator, creates a Vertex using a 3 float position vector and a color object
     * @param position a 3 float vector of positions
     * @param color a color object
     */
    public Vertex(Vector3f position, Color color) {
        this.position = position;
        this.color = color;
    }

    /**
     * Class creator, creates a Vertex using a 3 float position vector with a random color
     * @param position a 3 float vector of positions
     */
    public Vertex(Vector3f position) {
        this.position = position;
        this.color = Color.random();
    }

    /**
     * Getter, returns the Vertex's position vector
     * @return a 3 float vector of positions
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Setter, sets the Vertex's position vector to a desired vector
     * @param position the desired 3 float vector of positions
     */
    public void setPosition(Vector3f position) {
        this.position = position;
    }

    /**
     * Getter, returns the color object of the vertex
     * @return the color object of the vertex
     */
    public Color getColor() {
        return color;
    }

    /**
     * Setter, sets the color object of the vertex to a desired object
     * @param color the desired color object
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
