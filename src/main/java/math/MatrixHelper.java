package math;

public class MatrixHelper {
    public static Matrix4x4 identity() {
        Matrix4x4 m = new Matrix4x4();
        m.setIdentity();
        return m;
    }
    public static Matrix4x4 getTranslationMatrix(float x, float y, float z){
        return new Matrix4x4(new float[][]{{1,0,0,x},{0,1,0,y},{0,0,0,z},{0,0,0,1}});
    }
    public static Matrix4x4 getScaleMatrix(float x, float y, float z){
        return new Matrix4x4(new float[][]{{x,0,0,0},{0,y,0,0},{0,0,z,0},{0,0,0,1}});
    }
    public static Matrix4x4 getRotationMatrix(float x, float y, float z){
        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);
        Matrix4x4 rz = new Matrix4x4(new float[][]{
                {cos(z),-sin(z),0,0},
                {sin(z),cos(z),0,0},
                {0,0,1,0},
                {0,0,0,1}
        });
        Matrix4x4 rx = new Matrix4x4(new float[][]{
                {1,0,0,0},
                {0,cos(x),-sin(x),0},
                {0,sin(x),cos(x),0},
                {0,0,0,1}
        });
        Matrix4x4 ry = new Matrix4x4(new float[][]{
                {cos(y),-sin(y),0,0},
                {0,1,0,0},
                {sin(y),cos(y),1,0},
                {0,0,0,1}
        });
        return rz.multiply(ry.multiply(rx));
    }
    public static float cos(float x){
        return (float) Math.cos(x);
    }
    public static float sin(float x){
        return (float) Math.sin(x);
    }
}
