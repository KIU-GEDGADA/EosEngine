package math;

/**
 * This class implements a 2 float Vector
 */
public class Vector2f {
    /**
     * This is the x coordinate of the Vector2f
     */
    public float x;
    /**
     * This is the y coordinate for the Vector2f
     */
    public float y;

    /**
     * Class constructor, this function initializes the elements of the vector to 0
     */
    public Vector2f() {
        x = y = 0.0f;
    }

    /**
     * Class constructor, this function creates a Vector2f with the given parameters
     *
     * @param x the first element of the vector
     * @param y the second element of the vector
     */
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This function sums two Vector2f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the sum of the two vectors
     */
    public static Vector2f add(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x + v2.x, v1.y + v2.y);
    }

    /**
     * This function subtracts one Vector2f from another
     *
     * @param v1 the vector to be subtracted from
     * @param v2 the vector to subtract
     * @return the result of the subtraction
     */
    public static Vector2f sub(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x - v2.x, v1.y - v2.y);
    }

    /**
     * This function multiplies a Vector2f by a scalar
     *
     * @param v      the vector to be multiplied
     * @param scalar the scalar to multiply by
     * @return the result of the scaling
     */
    public static Vector2f mul(Vector2f v, float scalar) {
        return new Vector2f(v.x * scalar, v.y * scalar);
    }

    /**
     * This function divides a Vector2f by a scalar
     *
     * @param v      the vector to be divided
     * @param scalar the scalar to divide by
     * @return the result of the division
     */
    public static Vector2f div(Vector2f v, float scalar) {
        return new Vector2f(v.x / scalar, v.y / scalar);
    }

    /**
     * This function returns the dot product of 2 Vector2f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the dot product
     */
    public static float dot(Vector2f v1, Vector2f v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    /**
     * This function calculates the angle between two Vector2f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the angle between
     */
    public static float angle(Vector2f v1, Vector2f v2) {
        return (float) Math.acos(dot(v1, v2) / (v1.length() * v2.length()));
    }

    /**
     * This function scales one Vector2f by the seconds elements
     *
     * @param v     the vector to scale
     * @param scale the vector to scale by
     * @return the result of the scaling
     */
    public static Vector2f scale(Vector2f v, Vector2f scale) {
        return new Vector2f(v.x * scale.x, v.y * scale.y);
    }

    /**
     * This function normalizes a Vector2f
     *
     * @param v the vector to normalize
     * @return the result of the normalization
     */
    public static Vector2f normalize(Vector2f v) {
        float length = v.length();
        return new Vector2f(v.x / length, v.y / length);
    }

    /**
     * This function calculates the distance between two Vector2f's
     *
     * @param v1 the first vector
     * @param v2 the second vector
     * @return the distance between
     */
    public static float distance(Vector2f v1, Vector2f v2) {
        return sub(v1, v2).length();
    }

    /**
     * This function returns a Vector2f's perpendicular Vector2f
     *
     * @param v the vector which's perpendicular to return
     * @return the perpendicular Vector2f
     */
    public static Vector2f perpendicular(Vector2f v) {
        return new Vector2f(-v.y, v.x);
    }

    /**
     * This function returns a random Vector2f
     *
     * @return a random Vector2f
     */
    public static Vector2f random() {
        return new Vector2f((float) Math.random(), (float) Math.random());
    }

    /**
     * This function returns a zero Vector2f
     *
     * @return a Vector2f with all elements being 0
     */
    public static Vector2f zero() {
        return new Vector2f(0, 0);
    }

    /**
     * This function returns a Vector2f with all elements being 1
     *
     * @return a Vector2f with all elements being 1
     */
    public static Vector2f one() {
        return new Vector2f(1, 1);
    }

    /**
     * This function returns a Vector2f with elements x = 0 and y = 1
     *
     * @return a Vector2f with elements x = 0 and y = 1
     */
    public static Vector2f up() {
        return new Vector2f(0, 1);
    }

    /**
     * This function returns a Vector2f with elements x = 0 and y = -1
     *
     * @return a Vector2f with elements x = 0 and y = -1
     */
    public static Vector2f down() {
        return new Vector2f(0, -1);
    }

    /**
     * This function returns a Vector2f with elements x = -1 and y = 0
     *
     * @return a Vector2f with elements x = -1 and y = 0
     */
    public static Vector2f left() {
        return new Vector2f(-1, 0);
    }

    /**
     * This function returns a Vector2f with elements x = 1 and y = 0
     *
     * @return a Vector2f with elements x = 1 and y = 0
     */
    public static Vector2f right() {
        return new Vector2f(1, 0);
    }

    /**
     * This function returns the length of the Vector2f
     *
     * @return the length of the Vector2f
     */
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * This function returns the squared length of the Vector2f
     *
     * @return the squared length of the Vector2f
     */
    public float sqrLength() {
        return this.x * this.x + this.y * this.y;
    }

    /**
     * This function compares the current Vector2f to another, returns true if they are equal, false otherwise
     *
     * @param v the Vector2f to compare to
     * @return returns true if they are equal, false otherwise
     */
    public boolean equals(Vector2f v) {
        return this.x == v.x && this.y == v.y;
    }

    /**
     * This function returns the string representation of the Vector2f of the format: (x,y)
     *
     * @return The string representation of a Vector2f
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * This function sums the current Vector2f with another
     *
     * @param v the Vector2f to add to the current
     * @return the result of the summation
     */
    public Vector2f add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;

        return this;
    }

    /**
     * This function subtracts another Vector2f from the current
     *
     * @param v the Vector2f to subtract
     * @return the result of the subtraction
     */
    public Vector2f sub(Vector2f v) {
        this.x -= v.x;
        this.y -= v.y;

        return this;
    }

    /**
     * This function multiplies the current Vector2f by a scalar
     *
     * @param scalar the scalar to multiply by
     * @return the result of the multiplication
     */
    public Vector2f mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;

        return this;
    }

    /**
     * This function divides the current Vector2f by a scalar
     *
     * @param scalar the scalar to divide by
     * @return the result of the division
     */
    public Vector2f div(float scalar) {
        this.x /= scalar;
        this.y /= scalar;

        return this;
    }

    /**
     * This function normalizes the current Vector2f
     *
     * @return the normalized Vector2f
     */
    public Vector2f normalize() {
        float length = this.length();
        this.x /= length;
        this.y /= length;

        return this;
    }

    /**
     * This function translates the current Vector2f by another vector
     * adds the elements of the second Vector2f to the current
     *
     * @param delta the Vector2f to translate by
     * @return the translated matrix
     */
    public Vector2f translate(Vector2f delta) {
        this.x += delta.x;
        this.y += delta.y;

        return this;
    }

    /**
     * This function translates the current Vector by the given parameters
     * The Vector2f's x parameter is summed with x, the Vector2f's y parameter is summed with y
     *
     * @param x the value to add to the Vector2f's x parameter
     * @param y the value to add to the Vector2f's y parameter
     * @return the translated Vector2f
     */
    public Vector2f translate(float x, float y) {
        this.x += x;
        this.y += y;

        return this;
    }

    /**
     * This function negates the current Vector2f
     *
     * @return the negated Vector2f
     */
    public Vector2f negate() {
        this.x = -this.x;
        this.y = -this.y;

        return this;
    }

    /**
     * This function returns a float array representation of the Vector2f, of the format: [x,y]
     *
     * @return a float array representation of the Vector2f
     */
    public float[] coordinateArray() {
        return new float[]{x, y};
    }

    /**
     * This function checks if the current Vector2f equals the given object,
     * if the object is an instance of Vector2f and the values are equal returns true, false otherwise
     *
     * @param v the object to compare to
     * @return true if the object is an instance of Vector2f and the values are equal, false otherwise
     */
    @Override
    public boolean equals(Object v) {
        float epsilon = 0.00001f;
        if (v instanceof Vector2f) {
            return Math.abs(this.x - ((Vector2f) v).x) < epsilon && Math.abs(this.y - ((Vector2f) v).y) < epsilon;
        }
        return false;
    }

}
