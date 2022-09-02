package graphics.lighting;

import math.Vector3f;

public class PointLight extends ILight{

    private Vector3f position;
    private float constant, linear, exponent;


    public PointLight(Vector3f color, Vector3f position, float intensity, float constant, float linear, float exponent) {
        super(color, intensity);
        this.position = position;
        this.constant = constant;
        this.linear = linear;
        this.exponent = exponent;
    }

    public PointLight(Vector3f color, Vector3f position, float intensity) {
        this(color, position, intensity, 1, 0, 0);
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getConstant() {
        return constant;
    }

    public void setConstant(float constant) {
        this.constant = constant;
    }

    public float getLinear() {
        return linear;
    }

    public void setLinear(float linear) {
        this.linear = linear;
    }

    public float getExponent() {
        return exponent;
    }

    public void setExponent(float exponent) {
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        return "PointLight{" +
                "position=" + position +
                ", constant=" + constant +
                ", linear=" + linear +
                ", exponent=" + exponent +
                ", color=" + color +
                ", intensity=" + intensity +
                '}';
    }
}
