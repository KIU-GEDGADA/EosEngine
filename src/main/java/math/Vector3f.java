package math;

public class Vector3f {
    public double x;
    public double y;
    public double z;

    Vector3f(){
        x = y = z = 0.0f;
    }

    Vector3f(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length(){
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double sqrLength(){
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public Boolean equals(Vector3f v1, Vector3f v2){
        return v1.x == v2.x && v1.y == v2.y && v1.z == v2.z;
    }

    public String toString(){
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public static Vector3f add(Vector3f v1, Vector3f v2){
        return new Vector3f(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vector3f sub(Vector3f v1, Vector3f v2){
        return new Vector3f(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vector3f mul(Vector3f v, double scalar){
        return new Vector3f(v.x * scalar, v.y * scalar, v.z * scalar);
    }

    public static Vector3f div(Vector3f v, double scalar){
        return new Vector3f(v.x / scalar, v.y / scalar, v.z / scalar);
    }

    public static double dot(Vector3f v1, Vector3f v2){
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static double angle(Vector3f v1, Vector3f v2){
        return Math.acos(dot(v1, v2) / (v1.length() * v2.length()));
    }

    public static Vector3f scale(Vector3f v, Vector3f scale){
        return new Vector3f(v.x * scale.x, v.y * scale.y, v.z * scale.z);
    }

    public static Vector3f normalize(Vector3f v){
        return new Vector3f(v.x / v.length(), v.y / v.length(), v.z / v.length());
    }

    public static double distance(Vector3f v1, Vector3f v2){
        return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2) + Math.pow(v1.z - v2.z, 2));
    }

    public static Vector3f cross(Vector3f v1, Vector3f v2){
        return new Vector3f(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
    }

    public static Vector3f lerp(Vector3f v1, Vector3f v2, double t){
        return new Vector3f(v1.x + (v2.x - v1.x) * t, v1.y + (v2.y - v1.y) * t, v1.z + (v2.z - v1.z) * t);
    }

    public static Vector3f reflect(Vector3f v, Vector3f normal){
        return sub(v, mul(normal, 2 * dot(v, normal)));
    }

    public static Vector3f project(Vector3f v, Vector3f normal){
        return mul(normal, dot(v, normal));
    }
    
    public static Vector3f move(Vector3f v, Vector3f direction, double distance){
        return add(v, mul(direction, distance));
    }
    
    public static void orthonormalize(Vector3f normal, Vector3f tangent){
        tangent.x = normal.y;
        tangent.y = -normal.x;
        tangent.z = 0;
        tangent = normalize(tangent);
    }

    public static Vector3f zero(){
        return new Vector3f(0, 0, 0);
    }
    
    public static Vector3f one(){
        return new Vector3f(1, 1, 1);
    }

    public static Vector3f up(){
        return new Vector3f(0, 1, 0);
    }

    public static Vector3f down(){
        return new Vector3f(0, -1, 0);
    }

    public static Vector3f left(){
        return new Vector3f(-1, 0, 0);
    }

    public static Vector3f right(){
        return new Vector3f(1, 0, 0);
    }

    public static Vector3f forward(){
        return new Vector3f(0, 0, -1);
    }

    public static Vector3f backward(){
        return new Vector3f(0, 0, 1);
    }
}
