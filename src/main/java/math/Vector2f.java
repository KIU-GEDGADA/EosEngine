package math;

public class Vector2f {
    public double x;
    public double y;

    Vector2f(){
        x = y = 0.0f;

    }

    Vector2f(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double length(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public double sqrLength(){
        return this.x * this.x + this.y * this.y;
    }

    public static Boolean equals(Vector2f v1, Vector2f v2){
        return v1.x == v2.x && v1.y == v2.y;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    public static Vector2f add(Vector2f v1, Vector2f v2){
        return new Vector2f(v1.x + v2.x, v1.y + v2.y);
    }

    public static Vector2f sub(Vector2f v1, Vector2f v2){
        return new Vector2f(v1.x - v2.x, v1.y - v2.y);
    }

    public static Vector2f mul(Vector2f v, double scalar){
        return new Vector2f(v.x * scalar, v.y * scalar);
    }

    public static Vector2f div(Vector2f v, double scalar){
        return new Vector2f(v.x / scalar, v.y / scalar);
    }

    public static double dot(Vector2f v1, Vector2f v2){
        return v1.x * v2.x + v1.y * v2.y;
    }

    public static double angle(Vector2f v1, Vector2f v2){
        return Math.acos(dot(v1, v2) / (v1.length() * v2.length()));
    }

    public static Vector2f scale(Vector2f v, Vector2f scale){
        return new Vector2f(v.x * scale.x, v.y * scale.y);
    }

    public static Vector2f normalize(Vector2f v){
        return new Vector2f(v.x / v.length(), v.y / v.length());
    }

    public static double distance(Vector2f v1, Vector2f v2){
        return Vector2f.sub(v1, v2).length();
    }

    public static Vector2f perpendicular(Vector2f v){
        return new Vector2f(-v.y, v.x);
    }

    public static Vector2f move(Vector2f v, Vector2f direction, double distance){
        return Vector2f.add(v, Vector2f.mul(direction, distance));
    }

    public static Vector2f zero(){
        return new Vector2f(0, 0);
    }

    public static Vector2f one(){
        return new Vector2f(1, 1);
    }

    public static Vector2f up(){
        return new Vector2f(0, 1);
    }

    public static Vector2f down(){
        return new Vector2f(0, -1);
    }

    public static Vector2f left(){
        return new Vector2f(-1, 0);
    }

    public static Vector2f right(){
        return new Vector2f(1, 0);
    }

    public static Vector2f random(){
        return new Vector2f(Math.random(), Math.random());
    }
}
