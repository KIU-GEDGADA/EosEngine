package math;

/**
 * This class implements a 3 float Vector
 */
public class Vector3f {
    /**
     * This is the x coordinate for the Vector3f
     */
    public float x;
    /**
     * This is the y coordinate for the Vector3f
     */
    public float y;
    /**
     * This is the z coordinate for the Vector3f
     */
    public float z;

    /**
     * Class constructor, this function initializes the elements of the vector to 0
     */
    public Vector3f() {
        x = y = z = 0.0f;
    }

    /**
     * Class constructor, this function creates a Vector3f with the given parameters
     *
     * @param x the first element of the vector
     * @param y the second element of the vector
     * @param z the third element of the vector
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * This function sums two Vector3f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the sum of the two vectors
     */
    public static Vector3f add(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    /**
     * This function subtracts one Vector3f from another
     *
     * @param v1 the vector to be subtracted from
     * @param v2 the vector to subtract
     * @return the result of the subtraction
     */
    public static Vector3f sub(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    /**
     * This function multiplies a Vector3f by a scalar
     *
     * @param v      the vector to be multiplied
     * @param scalar the scalar to multiply by
     * @return the result of the scaling
     */
    public static Vector3f mul(Vector3f v, float scalar) {
        return new Vector3f(v.x * scalar, v.y * scalar, v.z * scalar);
    }

    /**
     * This function divides a Vector3f by a scalar
     *
     * @param v      the vector to be divided
     * @param scalar the scalar to divide by
     * @return the result of the division
     */
    public static Vector3f div(Vector3f v, float scalar) {
        return new Vector3f(v.x / scalar, v.y / scalar, v.z / scalar);
    }

    /**
     * This function returns the dot product of 2 Vector3f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the dot product
     */
    public static float dot(Vector3f v1, Vector3f v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    /**
     * This function calculates the angle between two Vector3f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the angle between
     */
    public static float angle(Vector3f v1, Vector3f v2) {
        return (float) Math.acos(dot(v1, v2) / (v1.length() * v2.length()));
    }

    /**
     * This function scales one Vector3f by the seconds elements
     *
     * @param v     the vector to scale
     * @param scale the vector to scale by
     * @return the result of the scaling
     */
    public static Vector3f scale(Vector3f v, Vector3f scale) {
        return new Vector3f(v.x * scale.x, v.y * scale.y, v.z * scale.z);
    }

    /**
     * This function normalizes a Vector3f
     *
     * @param v the vector to normalize
     * @return the result of the normalization
     */
    public static Vector3f normalize(Vector3f v) {
        float length = v.length();
        return new Vector3f(v.x / length, v.y / length, v.z / length);
    }

    /**
     * This function calculates the distance between two Vector3f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the distance between
     */
    public static float distance(Vector3f v1, Vector3f v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2) + Math.pow(v1.z - v2.z, 2));
    }

    /**
     * This function calculates the cross product of two Vector3f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return The vector of the cross product
     */
    public static Vector3f cross(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
    }

    /**
     * This function calculates a linearly interpolated vector out of two Vector3f
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param t  Interpolation coefficient
     * @return a linearly interpolated Vector3f
     */
    public static Vector3f lerp(Vector3f v1, Vector3f v2, float t) {
        return new Vector3f(v1.x + (v2.x - v1.x) * t, v1.y + (v2.y - v1.y) * t, v1.z + (v2.z - v1.z) * t);
    }

    /**
     * This function calculates the reflection off a plane defined by a normal
     *
     * @param v      The vector coming on the plane
     * @param normal The normal vector
     * @return The reflection vector
     */
    public static Vector3f reflect(Vector3f v, Vector3f normal) {
        return sub(v, mul(normal, 2 * dot(v, normal)));
    }

    /**
     * This function returns the projection of a vector to another vector
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @return The projection vector
     */
    public static Vector3f project(Vector3f v1, Vector3f v2) {
        return mul(v2, dot(v1, v2) / v2.sqrLength());
    }

    /**
     * This function makes vectors normalized and orthogonal to each other
     *
     * @param normal  The normal vector
     * @param tangent The tangent vector
     */
    public static void orthonormalize(Vector3f normal, Vector3f tangent) {
        normal.normalize();
        float u0 = dot(normal, tangent);
        tangent.sub(mul(normal, u0));
        tangent.normalize();
    }

    /**
     * This function makes vectors normalized and orthogonal to each other
     *
     * @param normal   The normal vector
     * @param tangent  The tangent vector
     * @param binomial The binomial vector
     */
    public static void orthonormalize(Vector3f normal, Vector3f tangent, Vector3f binomial) {
        normal.normalize();
        float u0 = dot(normal, tangent);
        tangent.sub(mul(normal, u0));
        tangent.normalize();
        float v0 = dot(tangent, binomial);
        u0 = dot(normal, binomial);
        binomial.sub(add(mul(tangent, v0), mul(normal, u0)));
        binomial.normalize();
    }

    /**
     * This function returns a random Vector3f
     *
     * @return a random Vector3f
     */
    public static Vector3f random() {
        return new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    /**
     * This function returns a zero Vector3f
     *
     * @return a Vector3f with all elements being 0
     */
    public static Vector3f zero() {
        return new Vector3f(0, 0, 0);
    }

    /**
     * This function returns a Vector3f with all elements being 1
     *
     * @return a Vector3f with all elements being 1
     */
    public static Vector3f one() {
        return new Vector3f(1, 1, 1);
    }

    /**
     * This function returns a Vector3f which is directed upward
     *
     * @return The up direction vector
     */
    public static Vector3f up() {
        return new Vector3f(0, 1, 0);
    }

    /**
     * This function returns a Vector3f which is directed downward
     *
     * @return The down direction vector
     */
    public static Vector3f down() {
        return new Vector3f(0, -1, 0);
    }

    /**
     * This function returns a Vector3f which is directed leftward
     *
     * @return The left direction vector
     */
    public static Vector3f left() {
        return new Vector3f(-1, 0, 0);
    }

    /**
     * This function returns a Vector3f which is directed rightward
     *
     * @return The right direction vector
     */
    public static Vector3f right() {
        return new Vector3f(1, 0, 0);
    }

    /**
     * This function returns a Vector3f which is directed forward
     *
     * @return The forward direction vector
     */
    public static Vector3f forward() {
        return new Vector3f(0, 0, -1);
    }

    /**
     * This function returns a Vector3f which is directed backward
     *
     * @return The backward direction vector
     */
    public static Vector3f backward() {
        return new Vector3f(0, 0, 1);
    }

    /**
     * This function returns the length of the Vector3f
     *
     * @return the length of the Vector3f
     */
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * This function returns the squared length of the Vector3f
     *
     * @return the squared length of the Vector3f
     */
    public float sqrLength() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    /**
     * This function sums the current Vector3f with another
     *
     * @param v the Vector3f to add to the current
     * @return the result of the summation
     */
    public Vector3f add(Vector3f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;

        return this;
    }

    /**
     * This function subtracts another Vector3f from the current
     *
     * @param v the Vector3f to subtract
     * @return the result of the subtraction
     */
    public Vector3f sub(Vector3f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;

        return this;
    }

    /**
     * This function multiplies the current Vector3f by a scalar
     *
     * @param scalar the scalar to multiply by
     * @return the result of the multiplication
     */
    public Vector3f mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;

        return this;
    }

    /**
     * This function divides the current Vector3f by a scalar
     *
     * @param scalar the scalar to divide by
     * @return the result of the division
     */
    public Vector3f div(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;

        return this;
    }

    /**
     * This function normalizes the current Vector3f
     *
     * @return the normalized Vector3f
     */
    public Vector3f normalize() {
        float length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;

        return this;
    }

    /**
     * This function translates the current Vector3f by another vector
     * adds the elements of the second Vector3f to the current
     *
     * @param delta the Vector3f to translate by
     * @return the translated matrix
     */
    public Vector3f translate(Vector3f delta) {
        this.x += delta.x;
        this.y += delta.y;
        this.z += delta.z;

        return this;
    }

    /**
     * This function translates the current Vector by the given parameters
     * The Vector3f's x parameter is summed with x, the Vector3f's y parameter is summed with y, the Vector3f's z parameter is summed with z
     *
     * @param x the value to add to the Vector3f's x parameter
     * @param y the value to add to the Vector3f's y parameter
     * @param z the value to add to the Vector3f's z parameter
     * @return the translated Vector3f
     */
    public Vector3f translate(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    /**
     * This function negates the current Vector3f
     *
     * @return the negated Vector3f
     */
    public Vector3f negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;

        return this;
    }

    /**
     * This function returns a float array representation of the Vector3f, of the format: [x,y,z,w]
     *
     * @return a float array representation of the Vector3f
     */
    public float[] coordinateArray() {
        return new float[]{x, y, z};
    }

    /**
     * This function checks if the current Vector3f equals the given object,
     * if the object is an instance of Vector3f and the values are equal returns true, false otherwise
     *
     * @param v the object to compare to
     * @return true if the object is an instance of Vector3f and the values are equal, false otherwise
     */
    @Override
    public boolean equals(Object v) {
        float epsilon = 0.00001f;
        if (v instanceof Vector3f) {
            return Math.abs(this.x - ((Vector3f) v).x) < epsilon && Math.abs(this.y - ((Vector3f) v).y) < epsilon && Math.abs(this.z - ((Vector3f) v).z) < epsilon;
        }
        return false;
    }

    /**
     * This function returns the string representation of the Vector3f of the format: (x,y,z,w)
     *
     * @return The string representation of a Vector3f
     */
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

}
