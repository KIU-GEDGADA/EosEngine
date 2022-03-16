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

}
