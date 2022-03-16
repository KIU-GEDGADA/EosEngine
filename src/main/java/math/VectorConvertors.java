package math;

public class VectorConvertors {
    
    public static Vector3f toVector3f(Vector2f v){
        return new Vector3f(v.x, v.y, 0);
    }
    
    public static Vector3f toVector3f(Vector2f v, int z){
        return new Vector3f(v.x, v.y, z);
    }

    public static Vector2f toVector2f(Vector3f v){
        return new Vector2f(v.x, v.y);
    }
}
