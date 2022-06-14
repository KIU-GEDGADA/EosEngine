package math;

/**
 * This class handles conversions from and into different types of vectors
 */
public final class VectorConvertors {

    private VectorConvertors() {
    }

    /**
     * This function transforms a Vector3f to a Vector2f
     * Vector3f's z parameter gets lost
     * @param v the Vector3f to be converted
     * @return the Vector2f result of the conversion
     */
    public static Vector2f toVector2f(Vector3f v) {
        return new Vector2f(v.x, v.y);
    }

    /**
     * This function transforms a Vector4f to a Vector2f
     * Vector3f's w and z parameter gets lost
     * @param v the Vector4f to be converted
     * @return the Vector2f result of the conversion
     */
    public static Vector2f toVector2f(Vector4f v) {
        return new Vector2f(v.x, v.y);
    }

    /**
     * This function transforms a Vector2f to a Vector3f
     * Vector3f's z parameter is initialized to 0
     * @param v the Vector2f to be converted
     * @return the Vector3f result of the conversion
     */
    public static Vector3f toVector3f(Vector2f v) {
        return new Vector3f(v.x, v.y, 0);
    }

    /**
     * This function transforms a Vector2f to a Vector3f with the given z value
     * Vector3f's z parameter is initialized to z
     * @param v the Vector2f to be converted
     * @param z the desired z value of the Vector3f
     * @return the Vector3f result of the conversion
     */
    public static Vector3f toVector3f(Vector2f v, int z) {
        return new Vector3f(v.x, v.y, z);
    }

    /**
     * This function transforms a Vector4f to a Vector3f with the given z value
     * Vector4f's w parameter is lost
     * @param v the Vector4f to be converted
     * @return the Vector3f result of the conversion
     */
    public static Vector3f tVector3f(Vector4f v) {
        return new Vector3f(v.x, v.y, v.z);
    }

    /**
     * This function transforms a Vector2f to a Vector4f
     * Vector4f's z and w parameters are initialized to 0
     * @param v the Vector2f to be converted
     * @return the Vector4f result of the conversion
     */
    public static Vector4f toVector4f(Vector2f v) {
        return new Vector4f(v.x, v.y, 0, 0);
    }

    /**
     * This function transforms a Vector2f to a Vector4f with the given z and w values
     * Vector4f's z and w parameters are initialized to z and w
     * @param v the Vector2f to be converted
     * @param z the desired z value of the Vector4f
     * @param w  the desired w value of the Vector4f
     * @return the Vector4f result of the conversion
     */
    public static Vector4f toVector4f(Vector2f v, int z, int w) {
        return new Vector4f(v.x, v.y, z, w);
    }

    /**
     * This function transforms a Vector3f to a Vector4f
     * Vector4f's w parameter is initialized to 0
     * @param v the Vector3f to be converted
     * @return the Vector4f result of the conversion
     */
    public static Vector4f toVector4f(Vector3f v) {
        return new Vector4f(v.x, v.y, v.z, 0);
    }

    /**
     * This function transforms a Vector3f to a Vector4f with the given w value
     * Vector4f's w parameter is initialized to w
     * @param v the Vector3f to be converted
     * @param w the desired w value of the Vector4f
     * @return the Vector4f result of the conversion
     */
    public static Vector4f toVector4f(Vector3f v, int w) {
        return new Vector4f(v.x, v.y, v.z, w);
    }

}
