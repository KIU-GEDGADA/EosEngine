package math;

public class VectorConvertors {
    
    public static Vector2f toVector2f(Vector3f v){
        return new Vector2f(v.x, v.y);
    }

    public static Vector2f toVector2f(Vector4f v){
        return new Vector2f(v.x, v.y);
    }

    public static Vector3f toVector3f(Vector2f v){
        return new Vector3f(v.x, v.y, 0);
    }
    
    public static Vector3f toVector3f(Vector2f v, int z){
        return new Vector3f(v.x, v.y, z);
    }

    public static Vector3f tVector3f(Vector4f v){
        return new Vector3f(v.x, v.y, v.z);
    }

    public static Vector4f toVector4f(Vector2f v){
        return new Vector4f(v.x, v.y, 0, 0);
    }

    public static Vector4f toVector4f(Vector2f v, int z, int w){
        return new Vector4f(v.x, v.y, z, w);
    }

    public static Vector4f toVector4f(Vector3f v){
        return new Vector4f(v.x, v.y, v.z, 0);
    }

    public static Vector4f toVector4f(Vector3f v, int w){
        return new Vector4f(v.x, v.y, v.z, w);
    }
}
