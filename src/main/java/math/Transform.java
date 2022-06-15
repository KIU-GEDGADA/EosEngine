package math;

import core.Camera;

/**
 * This class handles the position, rotation and scale of an item
 */
public class Transform {
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    /**
     * Class constructor, initializes the position, rotation and scales to zeros
     */
    public Transform() {
        position = Vector3f.zero();
        rotation = Vector3f.zero();
        scale = Vector3f.one();
    }

    /**
     * Getter, returns the rotation vector of the transform
     * @return the 3 float rotation vector of the transform
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     * Getter, returns the scale vector of the transform
     * @return the 3 float scale vector of the transform
     */
    public Vector3f getScale() {
        return scale;
    }

    /**
     * Getter, returns the position vector of the transform
     * @return the 3 float position vector of the transform
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     * Setter, sets the position of the transform from a 3 float vector, returns the current instance of the transform
     * @param v1 the desired position vector
     * @return the current instance of the transform
     */
    public Transform setPosition(Vector3f v1) {
        position = v1;
        return this;
    }

    /**
     * Setter, sets the position of the transform from 3 floats, returns the current instance of the transform
     * @param x the desired x coordinate of the transform
     * @param y the desired y coordinate of the transform
     * @param z the desired y coordinate of the transform
     * @return the current instance of the transform
     */
    public Transform setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
        return this;

    }

    /**
     * Setter, sets the rotation of the transform from a 3 float vector, returns the current instance of the transform
     * @param v1 the desired rotation vector
     * @return the current instance of the transform
     */
    public Transform setRotation(Vector3f v1) {
        rotation = v1;
        return this;

    }

    /**
     * Setter, sets the rotation of the transform from 3 floats, returns the current instance of the transform
     * @param x the desired x rotation of the transform
     * @param y the desired y rotation of the transform
     * @param z the desired y rotation of the transform
     * @return the current instance of the transform
     */
    public Transform setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
        return this;

    }

    /**
     * This function returns the transformation matrix of the transform
     * the transformation matrix is the multiplication of the translation, rotation and scale matrix's
     * @return the transformation matrix of the transform
     */
    public Matrix4x4 getTransformationMatrix() {
        Matrix4x4 translationMatrix = MatrixHelper.getTranslationMatrix(position.x, position.y, position.z);
        Matrix4x4 rotationMatrix = MatrixHelper.getRotationMatrix(rotation.x, rotation.y, rotation.z);
        Matrix4x4 scaleMatrix = MatrixHelper.getScaleMatrix(scale.x, scale.y, scale.z);
        return translationMatrix.multiply(rotationMatrix.multiply(scaleMatrix));
    }

    /**
     * This function returns the view matrix of the transform
     * the view matrix is the multiplication of the rotation and translation matrix's
     * @return the view matrix of teh transform
     */
    public Matrix4x4 getViewMatrix() {
        Camera camera = Camera.getInstance();
        Vector3f position = camera.getPosition();
        Vector3f rotation = camera.getRotation();
        Matrix4x4 translationMatrix = MatrixHelper.getTranslationMatrix(-position.x, -position.y, -position.z);
        Matrix4x4 rotationMatrix = MatrixHelper.getRotationMatrix(rotation.x, rotation.y, rotation.z);

        return rotationMatrix.multiply(translationMatrix);
    }

    /**
     * This function returns the projection matrix of the transform for the current camera instance
     * @return the projection matrix of the transform for the current camera instance
     */
    public Matrix4x4 getProjectionMatrix() {
        Camera camera = Camera.getInstance();

        return MatrixHelper.getProjectionMatrix((float) Math.toRadians(camera.getFOV()), camera.getAspectRatio(), camera.getzFar(), camera.getzNear());
    }
}
