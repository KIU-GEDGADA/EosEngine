package math;

public class MatrixHelper {
    public static Matrix4x4 identity() {
        Matrix4x4 m = new Matrix4x4();
        m.setIdentity();
        return m;
    }
}
