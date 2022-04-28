package math;

public class Vector4f {
    public float w;
    public float x;
    public float y;
    public float z;

    public Vector4f() {
        x = y = z = w = 0.0f;
    }

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static Vector4f add(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z, v1.w + v2.w);
    }

    public static Vector4f sub(Vector4f v1, Vector4f v2) {
        return new Vector4f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z, v1.w - v2.w);
    }

    public static Vector4f mul(Vector4f v, float scalar) {
        return new Vector4f(v.x * scalar, v.y * scalar, v.z * scalar, v.w * scalar);
    }

    public static Vector4f div(Vector4f v, float scalar) {
        return new Vector4f(v.x / scalar, v.y / scalar, v.z / scalar, v.w / scalar);
    }

    public static float dot(Vector4f v1, Vector4f v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z + v1.w * v2.w;
    }

    public static Vector4f scale(Vector4f v, Vector4f scale) {
        return new Vector4f(v.x * scale.x, v.y * scale.y, v.z * scale.z, v.w * scale.w);
    }

    public static Vector4f lerp(Vector4f v1, Vector4f v2, float t) {
        return new Vector4f(v1.x + (v2.x - v1.x) * t, v1.y + (v2.y - v1.y) * t, v1.z + (v2.z - v1.z) * t, v1.w + (v2.w - v1.w) * t);
    }

    public static float distance(Vector4f v1, Vector4f v2) {
        return (float) Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2) + Math.pow(v1.z - v2.z, 2) + Math.pow(v1.w - v2.w, 2));
    }

    public static Vector4f project(Vector4f v1, Vector4f v2) {
        return mul(v2, dot(v1, v2) / v2.sqrLength());
    }

    public static Vector4f random() {
        return new Vector4f((float) Math.random(), (float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    public static Vector4f zero() {
        return new Vector4f(0, 0, 0, 0);
    }

    public static Vector4f one() {
        return new Vector4f(1, 1, 1, 1);
    }

    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }

    public float sqrLength() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public boolean equals(Object v) {
        if(v instanceof Vector4f) {
            return this.x == ((Vector4f) v).x && this.y == ((Vector4f) v).y && this.z == ((Vector4f) v).z && this.w == ((Vector4f) v).w;
        }
        return false;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ", " + w + ")";
    }

    public Vector4f normalize(Vector4f v) {
        float length = v.length();
        return new Vector4f(v.x / length, v.y / length, v.z / length, v.w / length);
    }

    public void normalize() {
        float length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
        this.w /= length;
    }

    public void add(Vector4f v) {
        this.x += v.x;
        this.y += v.y;
        this.z += v.z;
        this.w += v.w;
    }

    public void sub(Vector4f v) {
        this.x -= v.x;
        this.y -= v.y;
        this.z -= v.z;
        this.w -= v.w;
    }

    public void mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
    }

    public void div(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        this.w /= scalar;
    }

    public void translate(Vector4f delta) {
        this.x += delta.x;
        this.y += delta.y;
        this.z += delta.z;
        this.w += delta.w;
    }

    public void translate(float x, float y, float z, float w) {
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

    public float[] coordinateArray() {
        return new float[] {x, y, z, w};
    }

}
