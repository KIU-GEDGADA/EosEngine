package graphics.lighting;

import math.Vector3f;

public class DirectionalLight extends ILight {

    private Vector3f direction;

    public DirectionalLight(Vector3f color, Vector3f direction, float intensity) {
        super(color, intensity);
        this.direction = direction;
    }


    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }

}
