package graphics;

import enums.Constants;
import math.Vector4f;

public class Material {

    private Vector4f ambientColor, diffuseColor, specularColor;
    private float reflectance;
    private Texture texture;

    public Material() {
        this.ambientColor = Constants.DEFAULT_COLOR;
        this.diffuseColor = Constants.DEFAULT_COLOR;
        this.specularColor = Constants.DEFAULT_COLOR;
        this.texture = null;
        this.reflectance = 0f;
    }


    public Material(Texture texture) {
        this(Constants.DEFAULT_COLOR, Constants.DEFAULT_COLOR, Constants.DEFAULT_COLOR, 0, texture);
    }

    public Material(Vector4f color, float reflectance) {
        this(color, color, color, reflectance, null);
    }

    public Material(Vector4f color, float reflectance, Texture texture) {
        this(color, color, color, reflectance, texture);
    }

    public Material(Vector4f ambientColor, Vector4f diffuseColor, Vector4f specularColor, float reflectance, Texture texture) {
        this.ambientColor = ambientColor;
        this.diffuseColor = diffuseColor;
        this.specularColor = specularColor;
        this.reflectance = reflectance;
        this.texture = texture;
    }

    public Vector4f getAmbientColor() {
        return ambientColor;
    }

    public Material setAmbientColor(Vector4f ambientColor) {
        this.ambientColor = ambientColor;
        return this;
    }

    public Vector4f getDiffuseColor() {
        return diffuseColor;
    }

    public Material setDiffuseColor(Vector4f diffuseColor) {
        this.diffuseColor = diffuseColor;
        return this;

    }

    public Vector4f getSpecularColor() {
        return specularColor;
    }

    public Material setSpecularColor(Vector4f specularColor) {
        this.specularColor = specularColor;
        return this;

    }

    public float getReflectance() {
        return reflectance;
    }

    public Material setReflectance(float reflectance) {
        this.reflectance = reflectance;
        return this;

    }

    public Texture getTexture() {
        return texture;
    }

    public Material setTexture(Texture texture) {
        this.texture = texture;
        return this;

    }

    public boolean hasTexture() {
        return this.texture != null;
    }

}
