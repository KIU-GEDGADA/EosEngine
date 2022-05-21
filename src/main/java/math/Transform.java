package math;

import core.Item;

public class Transform {
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform() {
        position = new Vector3f();
        rotation = new Vector3f();
        scale = Vector3f.one();
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

    public Matrix4x4 getTransformation() {
        Matrix4x4 translationMatrix = MatrixHelper.getTranslationMatrix(position.x, position.y, position.z);
        Matrix4x4 rotationMatrix = MatrixHelper.getRotationMatrix(rotation.x, rotation.y, rotation.z);
        Matrix4x4 scaleMatrix = MatrixHelper.getScaleMatrix(scale.x, scale.y, scale.z);
        return translationMatrix.multiply(rotationMatrix.multiply(scaleMatrix));
    }
}
