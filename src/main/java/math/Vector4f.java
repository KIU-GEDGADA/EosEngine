package math;

public class Vector4f {
    public double w;
    public double x;
    public double y;
    public double z;

    Vector4f() {
        x = y = z = w = 0.0f;
    }

    Vector4f(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }

    public double sqrLength() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public boolean equals(Vector4f v) {
        return this.x == v.x && this.y == v.y && this.z == v.z && this.w == v.w;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    public Vector4f normalize(Vector4f v) {
        double length = v.length();
        return new Vector4f(v.x / length, v.y / length, v.z / length, v.w / length);
    }

    public void normalize() {
        double length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
        this.w /= length;
    }

    public static Vector4f add(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z, v1.w + v2.w);
    }

    public void add(Vector4f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        this.w += v.w;
    }

    public static Vector4f sub(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z, v1.w - v2.w);
    }

    public void sub(Vector4f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        this.w -= v.w;
    }

    public static Vector4f mul(Vector4f v, double scalar) {
        return new Vector4f(v.x * scalar, v.y * scalar, v.z * scalar, v.w * scalar);
    }

    public void mul(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
    }

    public static Vector4f div(Vector4f v, double scalar) {
        return new Vector4f(v.x / scalar, v.y / scalar, v.z / scalar, v.w / scalar);
    }

    public void div(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        this.w /= scalar;
    }

    public static double dot(Vector4f v1, Vector4f v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z + v1.w * v2.w;
    }

    public static Vector4f scale(Vector4f v, Vector4f scale) {
        return new Vector4f(v.x * scale.x, v.y * scale.y, v.z * scale.z, v.w * scale.w);
    }

    public static Vector4f lerp(Vector4f v1, Vector4f v2, double t) {
        return new Vector4f(v1.x + (v2.x - v1.x) * t, v1.y + (v2.y - v1.y) * t, v1.z + (v2.z - v1.z) * t, v1.w + (v2.w - v1.w) * t);
    }

    public static double distance(Vector4f v1, Vector4f v2) {
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2) + Math.pow(v1.z - v2.z, 2) + Math.pow(v1.w - v2.w, 2));
    }

    public static Vector4f project(Vector4f v1, Vector4f v2) {
        return mul(v2, dot(v1, v2) / v2.sqrLength());
    }

    public void translate(Vector4f delta) {
        this.x += delta.x;
        this.y += delta.y;
        this.z += delta.z;
        this.w += delta.w;
    }

    public void translate(double x, double y, double z, double w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
    }

    public static Vector4f random() {
        return new Vector4f(Math.random(), Math.random(), Math.random(), Math.random());
    }

    public static Vector4f zero() {
        return new Vector4f(0, 0, 0, 0);
    }

    public static Vector4f one() {
        return new Vector4f(1, 1, 1, 1);
    }

}
