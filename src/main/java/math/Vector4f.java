package math;

/**
 * This class implements a 4 float Vector
 */
public class Vector4f {
    /**
     * This is the x coordinate for the Vector4f
     */
    public float x;
    /**
     * This is the y coordinate for the Vector4f
     */
    public float y;
    /**
     * This is the z coordinate for the Vector4f
     */
    public float z;
    /**
     * This is the w coordinate for the Vector4f
     */
    public float w;

    /**
     * Class constructor, this function initializes the elements of the vector to 0
     */
    public Vector4f() {
        x = y = z = w = 0.0f;
    }

    /**
     * Class constructor, this function creates a Vector4f with the given parameters
     *
     * @param x the first element of the vector
     * @param y the second element of the vector
     * @param z the third element of the vector
     * @param w the fourth element of the vector
     */
    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * This function sums two Vector4f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the sum of the two vectors
     */
    public static Vector4f add(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z, v1.w + v2.w);
    }

    /**
     * This function subtracts one Vector4f from another
     *
     * @param v1 the vector to be subtracted from
     * @param v2 the vector to subtract
     * @return the result of the subtraction
     */
    public static Vector4f sub(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z, v1.w - v2.w);
    }

    /**
     * This function multiplies a Vector4f by a scalar
     *
     * @param v      the vector to be multiplied
     * @param scalar the scalar to multiply by
     * @return the result of the scaling
     */
    public static Vector4f mul(Vector4f v, float scalar) {
        return new Vector4f(v.x * scalar, v.y * scalar, v.z * scalar, v.w * scalar);
    }

    /**
     * This function divides a Vector4f by a scalar
     *
     * @param v      the vector to be divided
     * @param scalar the scalar to divide by
     * @return the result of the division
     */
    public static Vector4f div(Vector4f v, float scalar) {
        return new Vector4f(v.x / scalar, v.y / scalar, v.z / scalar, v.w / scalar);
    }

    /**
     * This function returns the dot product of 2 Vector4f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the dot product
     */
    public static float dot(Vector4f v1, Vector4f v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z + v1.w * v2.w;
    }

    /**
     * This function scales one Vector4f by the seconds elements
     *
     * @param v     the vector to scale
     * @param scale the vector to scale by
     * @return the result of the scaling
     */
    public static Vector4f scale(Vector4f v, Vector4f scale) {
        return new Vector4f(v.x * scale.x, v.y * scale.y, v.z * scale.z, v.w * scale.w);
    }

    /**
     * This function calculates a linearly interpolated vector out of two Vector4f
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param t  Interpolation coefficient
     * @return a linearly interpolated Vector4f
     */
    public static Vector4f lerp(Vector4f v1, Vector4f v2, float t) {
        return new Vector4f(v1.x + (v2.x - v1.x) * t, v1.y + (v2.y - v1.y) * t, v1.z + (v2.z - v1.z) * t, v1.w + (v2.w - v1.w) * t);
    }

    /**
     * This function calculates the distance between two Vector4f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the distance between
     */
    public static float distance(Vector4f v1, Vector4f v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2) + Math.pow(v1.z - v2.z, 2) + Math.pow(v1.w - v2.w, 2));
    }

    /**
     * This function returns the projection of a vector to another vector
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @return The projection vector
     */
    public static Vector4f project(Vector4f v1, Vector4f v2) {
        return mul(v2, dot(v1, v2) / v2.sqrLength());
    }

    /**
     * This function returns a random Vector4f
     *
     * @return a random Vector4f
     */
    public static Vector4f random() {
        return new Vector4f((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    /**
     * This function returns a zero Vector4f
     *
     * @return a Vector4f with all elements being 0
     */
    public static Vector4f zero() {
        return new Vector4f(0, 0, 0, 0);
    }

    /**
     * This function returns a Vector4f with all elements being 1
     *
     * @return a Vector4f with all elements being 1
     */
    public static Vector4f one() {
        return new Vector4f(1, 1, 1, 1);
    }

    /**
     * This function returns the length of the Vector4f
     *
     * @return the length of the Vector4f
     */
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }

    /**
     * This function returns the squared length of the Vector4f
     *
     * @return the squared length of the Vector4f
     */
    public float sqrLength() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    /**
     * This function normalizes a Vector4f
     *
     * @param v the vector to normalize
     * @return the result of the normalization
     */
    public static Vector4f normalize(Vector4f v) {
        float length = v.length();
        return new Vector4f(v.x / length, v.y / length, v.z / length, v.w / length);
    }

    /**
     * This function normalizes the current Vector4f
     *
     * @return the normalized Vector4f
     */
    public Vector4f normalize() {
        float length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
        this.w /= length;

        return this;
    }

    /**
     * This function sums the current Vector4f with another
     *
     * @param v the Vector4f to add to the current
     * @return the result of the summation
     */
    public Vector4f add(Vector4f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        this.w += v.w;

        return this;
    }

    /**
     * This function subtracts another Vector4f from the current
     *
     * @param v the Vector4f to subtract
     * @return the result of the subtraction
     */
    public Vector4f sub(Vector4f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        this.w -= v.w;

        return this;
    }

    /**
     * This function multiplies the current Vector4f by a scalar
     *
     * @param scalar the scalar to multiply by
     * @return the result of the multiplication
     */
    public Vector4f mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;

        return this;
    }

    /**
     * This function divides the current Vector4f by a scalar
     *
     * @param scalar the scalar to divide by
     * @return the result of the division
     */
    public Vector4f div(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        this.w /= scalar;

        return this;
    }

    /**
     * This function translates the current Vector4f by another vector
     * adds the elements of the second Vector4f to the current
     *
     * @param delta the Vector4f to translate by
     * @return the translated matrix
     */
    public Vector4f translate(Vector4f delta) {
        this.x += delta.x;
        this.y += delta.y;
        this.z += delta.z;
        this.w += delta.w;

        return this;
    }

    /**
     * This function translates the current Vector by the given parameters
     * The Vector4f's x parameter is summed with x, the Vector4f's y parameter is summed with y, the Vector4f's z parameter is summed with z, the Vector4f's w parameter is summed with w
     *
     * @param x the value to add to the Vector4f's x parameter
     * @param y the value to add to the Vector4f's y parameter
     * @param z the value to add to the Vector4f's z parameter
     * @param w the value to add to the Vector4f's w parameter
     * @return the translated Vector4f
     */
    public Vector4f translate(float x, float y, float z, float w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;

        return this;
    }

    /**
     * This function negates the current Vector4f
     *
     * @return the negated Vector4f
     */
    public Vector4f negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;

        return this;
    }

    /**
     * This function returns a float array representation of the Vector4f, of the format: [x,y,z,w]
     *
     * @return a float array representation of the Vector4f
     */
    public float[] coordinateArray() {
        return new float[]{x, y, z, w};
    }

    /**
     * This function checks if the current Vector4f equals the given object,
     * if the object is an instance of Vector4f and the values are equal returns true, false otherwise
     *
     * @param v the object to compare to
     * @return true if the object is an instance of Vector4f and the values are equal, false otherwise
     */
    @Override
    public boolean equals(Object v) {
        float epsilon = 0.0001f;
        if (v instanceof Vector4f) {
            return Math.abs(this.x - ((Vector4f) v).x) < epsilon && Math.abs(this.y - ((Vector4f) v).y) < epsilon
                    && Math.abs(this.z - ((Vector4f) v).z) < epsilon && Math.abs(this.w - ((Vector4f) v).w) < epsilon;
        }
        return false;
    }

    /**
     * This function returns the string representation of the Vector4f of the format: (x,y,z,w)
     *
     * @return The string representation of a Vector4f
     */
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

}
