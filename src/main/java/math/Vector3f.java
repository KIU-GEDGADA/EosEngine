package math;

public class Vector3f {
    public float x;
    public float y;
    public float z;

    public Vector3f() {
        x = y = z = 0.0f;
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3f add(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vector3f sub(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vector3f mul(Vector3f v, float scalar) {
        return new Vector3f(v.x * scalar, v.y * scalar, v.z * scalar);
    }

    public static Vector3f div(Vector3f v, float scalar) {
        return new Vector3f(v.x / scalar, v.y / scalar, v.z / scalar);
    }

    public static float dot(Vector3f v1, Vector3f v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static float angle(Vector3f v1, Vector3f v2) {
        return (float) Math.acos(dot(v1, v2) / (v1.length() * v2.length()));
    }

    public static Vector3f scale(Vector3f v, Vector3f scale) {
        return new Vector3f(v.x * scale.x, v.y * scale.y, v.z * scale.z);
    }

    public static Vector3f normalize(Vector3f v) {
        float length = v.length();
        return new Vector3f(v.x / length, v.y / length, v.z / length);
    }

    public static float distance(Vector3f v1, Vector3f v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2) + Math.pow(v1.z - v2.z, 2));
    }

    public static Vector3f cross(Vector3f v1, Vector3f v2) {
        return new Vector3f(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
    }

    public static Vector3f lerp(Vector3f v1, Vector3f v2, float t) {
        return new Vector3f(v1.x + (v2.x - v1.x) * t, v1.y + (v2.y - v1.y) * t, v1.z + (v2.z - v1.z) * t);
    }

    public static Vector3f reflect(Vector3f v, Vector3f normal) {
        return sub(v, mul(normal, 2 * dot(v, normal)));
    }

    public static Vector3f project(Vector3f v1, Vector3f v2) {
        return mul(v2, dot(v1, v2) / v2.sqrLength());
    }

    public static void orthonormalize(Vector3f normal, Vector3f tangent) {
        normal.normalize();
        float u0 = dot(normal, tangent);
        tangent.sub(mul(normal, u0));
        tangent.normalize();
    }

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

    public static Vector3f random() {
        return new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    public static Vector3f zero() {
        return new Vector3f(0, 0, 0);
    }

    public static Vector3f one() {
        return new Vector3f(1, 1, 1);
    }

    public static Vector3f up() {
        return new Vector3f(0, 1, 0);
    }

    public static Vector3f down() {
        return new Vector3f(0, -1, 0);
    }

    public static Vector3f left() {
        return new Vector3f(-1, 0, 0);
    }

    public static Vector3f right() {
        return new Vector3f(1, 0, 0);
    }

    public static Vector3f forward() {
        return new Vector3f(0, 0, -1);
    }

    public static Vector3f backward() {
        return new Vector3f(0, 0, 1);
    }

    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public float sqrLength() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public boolean equals(Object v) {
        float epsilon = 0.00001f;
        if (v instanceof Vector3f) {
            return Math.abs(this.x - ((Vector3f) v).x) < epsilon && Math.abs(this.y - ((Vector3f) v).y) < epsilon && Math.abs(this.z - ((Vector3f) v).z) < epsilon;
        }
        return false;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public Vector3f add(Vector3f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;

        return this;
    }

    public Vector3f sub(Vector3f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;

        return this;
    }

    public Vector3f mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;

        return this;
    }

    public Vector3f div(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;

        return this;
    }

    public Vector3f normalize() {
        float length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;

        return this;
    }

    public Vector3f translate(Vector3f delta) {
        this.x += delta.x;
        this.y += delta.y;
        this.z += delta.z;

        return this;
    }

    public Vector3f translate(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;

        return this;
    }

    public Vector3f negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;

        return this;
    }

    public float[] coordinateArray() {
        return new float[]{x, y, z};
    }

}
