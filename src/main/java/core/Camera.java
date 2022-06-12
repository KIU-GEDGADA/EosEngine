package core;

import math.Vector3f;

public class Camera {

    private Vector3f position;
    private Vector3f rotation;
    private float FOV;
    private float zFar;
    private float zNear;
    private float aspectRatio;

    public Camera(float aspectRatio) {
        position = Vector3f.zero();
        rotation = Vector3f.zero();
        this.aspectRatio = aspectRatio;
        zFar = 1000;
        zNear = 1;
        FOV = 60;
    }

    public Camera(Vector3f position, Vector3f rotation, float FOV, float zFar, float zNear, float aspectRatio) {
        this.position = position;
        this.rotation = rotation;
        this.FOV = FOV;
        this.zFar = zFar;
        this.zNear = zNear;
        this.aspectRatio = aspectRatio;
    }

    public float getFOV() {
        return FOV;
    }

    public void setFOV(float FOV) {
        this.FOV = FOV;
    }

    public float getzFar() {
        return zFar;
    }

    public void setzFar(float zFar) {
        this.zFar = zFar;
    }

    public float getzNear() {
        return zNear;
    }

    public void setzNear(float zNear) {
        this.zNear = zNear;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public void moveRotation(float x, float y, float z) {
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }

    public void movePosition(float x, float y, float z) {
        if (z != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * z;
            position.z += (float) Math.cos(Math.toRadians(rotation.y)) * z;
        }
        if (x != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * x;
            position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * x;
        }
        position.y += y;
    }

}
