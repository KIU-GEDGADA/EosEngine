package math;

import core.Camera;

public class Transform {
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform() {
        position = Vector3f.zero();
        rotation = Vector3f.zero();
        scale = Vector3f.one();
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f v1) {
        position = v1;
    }

    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }
    public void setRotation(Vector3f v1) {
        rotation = v1;
    }
    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }

    public Matrix4x4 getTransformationMatrix() {
        Matrix4x4 translationMatrix = MatrixHelper.getTranslationMatrix(position.x, position.y, position.z);
        Matrix4x4 rotationMatrix = MatrixHelper.getRotationMatrix(rotation.x, rotation.y, rotation.z);
        Matrix4x4 scaleMatrix = MatrixHelper.getScaleMatrix(scale.x, scale.y, scale.z);
        return translationMatrix.multiply(rotationMatrix.multiply(scaleMatrix));
    }

    public Matrix4x4 getViewMatrix(Camera camera) {
        Vector3f position = camera.getPosition();
        Vector3f rotation = camera.getRotation();
        Matrix4x4 translationMatrix = MatrixHelper.getTranslationMatrix(-position.x, -position.y, -position.z);
        Matrix4x4 rotationMatrix = MatrixHelper.getRotationMatrix(rotation.x, rotation.y, rotation.z);

        return rotationMatrix.multiply(translationMatrix);
    }

    public Matrix4x4 getProjectionMatrix(Camera camera) {
        return MatrixHelper.getProjectionMatrix((float) Math.toRadians(camera.getFOV()), camera.getAspectRatio(), camera.getzFar(),  camera.getzNear());
    }
}
