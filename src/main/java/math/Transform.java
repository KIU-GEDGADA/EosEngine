package math;

public class Transform {
    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform(){
        translation = new Vector3f();
        rotation = new Vector3f();
        scale = new Vector3f(1.0f,1.0f,1.0f);
    }
    public Vector3f getTranslation(){
        return translation;
    }
    public void setTranslation(Vector3f v1){
        translation = v1;
    }
    public void setTranslation(float x, float y, float z){
        translation.x = x;
        translation.y = y;
        translation.z = z;
    }
    public Matrix4x4 getTransformation(){
        Matrix4x4 translationMatrix = MatrixHelper.getTranslationMatrix(translation.x,translation.y,translation.z);
        Matrix4x4 rotationMatrix = MatrixHelper.getRotationMatrix(rotation.x,rotation.y,rotation.z);
        Matrix4x4 scaleMatrix = MatrixHelper.getScaleMatrix(scale.x,scale.y,scale.z);
        return translationMatrix.multiply(rotationMatrix.multiply(scaleMatrix));
    }
}
