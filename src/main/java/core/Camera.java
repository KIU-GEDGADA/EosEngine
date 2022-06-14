package core;

import math.Vector3f;

/**
 * The class that handles the camera of the game engine
 */
public class Camera {
    private static Camera instance;
    private Vector3f position;
    private Vector3f rotation;
    private float FOV;
    private float zFar;
    private float zNear;
    private float aspectRatio;

    /**
     * Getter, this function returns the instance of the current camera, if one does not exist it creates and then returns it
     * @return the instance of the current camera
     */
    public static Camera getInstance() {
        if (instance == null) {
            instance = new Camera();
        }
        return instance;
    }

    private Camera() {

    }

    /**
     * This function initializes the camera to the default settings:
     * position - 0 0 0
     * rotation - 0 0 0
     * zFar - 100
     * zNear - 1
     * Field of View - 60
     * @param aspectRatio the aspect ratio of the camera element
     */
    public void init(float aspectRatio) {
        position = Vector3f.zero();
        rotation = Vector3f.zero();
        this.aspectRatio = aspectRatio;
        zFar = 1000;
        zNear = 1;
        FOV = 60;
    }

    /**
     * Initializes the camera to the given custom settings
     * @param position the position of the camera in the 3D space
     * @param rotation the rotation of the camera
     * @param FOV the field of view
     * @param zFar - how close should an item be to not be seen
     * @param zNear - how far should an item be to not be seen
     * @param aspectRatio the aspect ratio
     */
    public void init(Vector3f position, Vector3f rotation, float FOV, float zFar, float zNear, float aspectRatio) {
        this.position = position;
        this.rotation = rotation;
        this.FOV = FOV;
        this.zFar = zFar;
        this.zNear = zNear;
        this.aspectRatio = aspectRatio;
    }

    /**
     * Getter, returns the field of view of the camera
     * @return the field of view of the camera
     */
    public float getFOV() {
        return FOV;
    }

    /**
     * Setter, sets the field of view of the camera, returns the instance of the camera
     * @param FOV the desired field of view
     * @return the current instance of the camera
     */
    public Camera setFOV(float FOV) {
        this.FOV = FOV;
        return this;
    }

    /**
     * Getter, returns zFar - maximum view distance
     * @return returns zFar
     */
    public float getzFar() {
        return zFar;
    }

    /**
     * Setter, sets the zFar parameter of the camera, returns the instance of the camera
     * @param zFar the desired maximum view distance of the camera
     * @return the current instance of the camera
     */
    public Camera setzFar(float zFar) {
        this.zFar = zFar;
        return this;
    }

    /**
     * Getter, returns zNear - minimum view distance
     * @return the zNear
     */
    public float getzNear() {
        return zNear;
    }

    /**
     * Setter, sets the zNear parameter of the camera, returns the instance of the camera
     * @param zNear the desired minimum view distance of the camera
     * @return the current instance of the camera
     */
    public Camera setzNear(float zNear) {
        this.zNear = zNear;
        return this;
    }

    /**
     * Getter, returns the aspect ratio of the camera
     * @return the aspect ratio of the camera
     */
    public float getAspectRatio() {
        return aspectRatio;
    }

    /**
     * Getter, returns the position vector of the camera
     * @return returns a 3 float element position vector of the camera, format: X, Y, Z
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Getter, returns the rotation vector of the camera
     * @return returns a 3 float element rotation vector of the camera
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * Setter, sets the position of the camera, returns the current instance of the camera
     * @param x the desired x coordinate of the camera
     * @param y the desired y coordinate of the camera
     * @param z the desired z coordinate of the camera
     * @return the current instance of the camera
     */
    public Camera setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
        return this;
    }

    /**
     * Setter, sets the rotation of the camera, returns the current instance of the camera
     * @param x the desired x coordinate of the camera
     * @param y the desired y coordinate of the camera
     * @param z the desired z coordinate of the camera
     * @return the current instance of the camera
     */
    public Camera setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
        return this;
    }

    /**
     * This function rotates the camera by the given margins, returns the current instance of the camera
     * @param x the amount to move the x coordinate by
     * @param y the amount to move the y coordinate by
     * @param z the amount to move the z coordinate by
     * @return the current instance of the camera
     */
    public Camera moveRotation(float x, float y, float z) {
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
        return this;
    }

    /**
     * This function moves the camera by the given margins in its direction, returns the current instance of the camera
     * @param x the amount to move the x coordinate by
     * @param y the amount to move the y coordinate by
     * @param z the amount to move the z coordinate by
     * @return the current instance of the camera
     */
    public Camera movePosition(float x, float y, float z) {
        if (z != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y)) * -1.0f * z;
            position.z += (float) Math.cos(Math.toRadians(rotation.y)) * z;
        }
        if (x != 0) {
            position.x += (float) Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * x;
            position.z += (float) Math.cos(Math.toRadians(rotation.y - 90)) * x;
        }
        position.y += y;
        return this;
    }

}
