package math;

/**
 * This function handles functions manipulating matrices
 */
public class MatrixHelper {
    /**
     * This function returns an identity Matrix4x4
     * @return an identity Matrix4x4
     */
    public static Matrix4x4 identity() {
        Matrix4x4 m = new Matrix4x4();
        m.setIdentity();
        return m;
    }

    /**
     * This function returns the determinant of a 3x3 matrix
     * @param m00 the 0 row 0 column element of the 3x3 matrix
     * @param m01 the 0 row 1 column element of the 3x3 matrix
     * @param m02 the 0 row 2 column element of the 3x3 matrix
     * @param m10 the 1 row 0 column element of the 3x3 matrix
     * @param m11 the 1 row 1 column element of the 3x3 matrix
     * @param m12 the 1 row 2 column element of the 3x3 matrix
     * @param m20 the 2 row 0 column element of the 3x3 matrix
     * @param m21 the 2 row 1 column element of the 3x3 matrix
     * @param m22 the 2 row 2 column element of the 3x3 matrix
     * @return the determinant of the 3x3 matrix
     */
    public static float determinant3x3(float m00, float m01, float m02,
                                       float m10, float m11, float m12,
                                       float m20, float m21, float m22) {
        return m00 * (m11 * m22 - m12 * m21) + m01 * (m12 * m20 - m10 * m22) + m02 * (m10 * m21 - m11 * m20);
    }

    /**
     * This function calculates and returns a translation Matrix4x4 with the given parameters
     * @param x 0 row 3 column element of the matrix
     * @param y 1 row 3 column element of the matrix
     * @param z 2 row 3 column of the matrix
     * @return the translation Matrix4x4 with the given parameters
     */
    public static Matrix4x4 getTranslationMatrix(float x, float y, float z) {
        return new Matrix4x4(new float[][]{{1, 0, 0, x}, {0, 1, 0, y}, {0, 0, 1, z}, {0, 0, 0, 1}});
    }

    /**
     * This function calculates and returns a scale matrix with the given parameters
     * @param x 0 row 0 column element of the matrix
     * @param y 1 row 1 column element of the matrix
     * @param z 2 row 2 column element of the matrix
     * @return returns a Matrix4x4 with x,y,z,1 on the main diagonal
     */
    public static Matrix4x4 getScaleMatrix(float x, float y, float z) {
        return new Matrix4x4(new float[][]{{x, 0, 0, 0}, {0, y, 0, 0}, {0, 0, z, 0}, {0, 0, 0, 1}});
    }

    /**
     * This function calculates and returns a rotation matrix with the given parameters
     * Rotation matrix is calculated by multiplying 3 matrices:
     * @param x parameter to calculate 11,12,21,22 elements of the first matrix
     * @param y parameter to calculate 00,02,20,22 elements of the second matrix
     * @param z parameter to calculate 00,01,10,11 elements of the third matrix
     * @return the rotation Matrix4x4 calculated with the given parameters
     */
    public static Matrix4x4 getRotationMatrix(float x, float y, float z) {
        x = (float) Math.toRadians(x);
        y = (float) Math.toRadians(y);
        z = (float) Math.toRadians(z);
        Matrix4x4 rx = new Matrix4x4(new float[][]{
                {1, 0, 0, 0},
                {0, cos(x), -sin(x), 0},
                {0, sin(x), cos(x), 0},
                {0, 0, 0, 1}
        });
        Matrix4x4 ry = new Matrix4x4(new float[][]{
                {cos(y), 0, sin(y), 0},
                {0, 1, 0, 0},
                {-sin(y), 0, cos(y), 0},
                {0, 0, 0, 1}
        });
        Matrix4x4 rz = new Matrix4x4(new float[][]{
                {cos(z), -sin(z), 0, 0},
                {sin(z), cos(z), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
        return rx.multiply(ry).multiply(rz);
    }

    /**
     * This function calculates and returns the projection matrix for given parameters of a camera
     * @param FOV the field of view
     * @param aspectRatio the aspect ratio
     * @param zFar the maximum view distance
     * @param zNear the minimum view distance
     * @return the projection Matrix4x4 for given parameters of a camera
     */
    public static Matrix4x4 getProjectionMatrix(float FOV, float aspectRatio, float zFar, float zNear) {
        float top = tan((FOV / 2)) * zNear;
        float bottom = -top;
        float right = top * aspectRatio;
        float left = -right;

        return new Matrix4x4(new float[][]{
                {(2 * zNear) / (right - left), 0, (right + left) / (right - left), 0},
                {0, (2 * zNear) / (top - bottom), (top + bottom) / (top - bottom), 0},
                {0, 0, -((zFar + zNear) / (zFar - zNear)), -((2 * zFar * zNear) / (zFar - zNear))},
                {0, 0, -1, 0}
        });
    }

    /**
     * This function returns the cosines of a float
     * @param x the float for which to calculate the cosines
     * @return the cosines of the given parameter
     */
    public static float cos(float x) {
        return (float) Math.cos(x);
    }

    /**
     * This function returns the sinus of a float
     * @param x the float for which to calculate the sinus
     * @return the sinus of the given parameter
     */
    public static float sin(float x) {
        return (float) Math.sin(x);
    }

    /**
     * This function returns the tangents of a float
     * @param x the float for which to calculate the tangents
     * @return the tangents of the given parameter
     */
    public static float tan(float x) {
        return (float) Math.tan(x);
    }
}
