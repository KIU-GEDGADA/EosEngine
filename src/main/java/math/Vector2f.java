package math;

public class Vector2f {
    public float x;
    public float y;

    public Vector2f() {
        x = y = 0.0f;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2f add(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2f sub(Vector2f v1, Vector2f v2) {
        return new Vector2f(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector2f mul(Vector2f v, float scalar) {
        return new Vector2f(v.x * scalar, v.y * scalar);
    }

    public static Vector2f div(Vector2f v, float scalar) {
        return new Vector2f(v.x / scalar, v.y / scalar);
    }

    public static float dot(Vector2f v1, Vector2f v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static float angle(Vector2f v1, Vector2f v2) {
        return (float) Math.acos(dot(v1, v2) / (v1.length() * v2.length()));
    }

    public static Vector2f scale(Vector2f v, Vector2f scale) {
        return new Vector2f(v.x * scale.x, v.y * scale.y);
    }

    public static Vector2f normalize(Vector2f v) {
        float length = v.length();
        return new Vector2f(v.x / length, v.y / length);
    }

    public static float distance(Vector2f v1, Vector2f v2) {
        return sub(v1, v2).length();
    }

    public static Vector2f perpendicular(Vector2f v) {
        return new Vector2f(-v.y, v.x);
    }

    public static Vector2f random() {
        return new Vector2f((float) Math.random(), (float) Math.random());
    }

    public static Vector2f zero() {
        return new Vector2f(0, 0);
    }

    public static Vector2f one() {
        return new Vector2f(1, 1);
    }

    public static Vector2f up() {
        return new Vector2f(0, 1);
    }

    public static Vector2f down() {
        return new Vector2f(0, -1);
    }

    public static Vector2f left() {
        return new Vector2f(-1, 0);
    }

    public static Vector2f right() {
        return new Vector2f(1, 0);
    }

    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public float sqrLength() {
        return this.x * this.x + this.y * this.y;
    }

    public boolean equals(Vector2f v) {
        return this.x == v.x && this.y == v.y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void sub(Vector2f v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void mul(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void div(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    public void normalize() {
        float length = this.length();
        this.x /= length;
        this.y /= length;
    }

    public void translate(Vector2f delta) {
        this.x += delta.x;
        this.y += delta.y;
    }

    public void translate(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    public float[] coordinateArray() {
        return new float[] {x, y};
    }

}
