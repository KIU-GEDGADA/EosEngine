package graphics.lighting;

import math.Vector3f;

public abstract class ILight {

    protected Vector3f color;
    protected float intensity;

    ILight(Vector3f color, float intensity) {
        this.color = color;
        this.intensity = intensity;
    }

    public Vector3f getColor() {
        return this.color;
    }

    public float getIntensity() {
        return this.intensity;
    }

    public ILight setColor(Vector3f color) {
        this.color = color;
        return this;
    }

    public ILight setIntensity(float intensity) {
        this.intensity = intensity;
        return this;
    }
}
