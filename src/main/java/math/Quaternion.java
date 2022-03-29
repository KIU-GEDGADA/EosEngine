package math;

public class Quaternion {
    private float w;
    private float x;
    private float y;
    private float z;

    public Quaternion(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion(Vector3f axis, float angle) {
        if (axis.length() == 1) {
            this.x = (float) (axis.x * Math.sin(angle / 2));
            this.y = (float) (axis.y * Math.sin(angle / 2));
            this.z = (float) (axis.z * Math.sin(angle / 2));
            this.w = (float) Math.cos(angle / 2);
        }
    }

    public float length() {
        return (float) Math.sqrt(Math.pow(w, 2) + Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Quaternion normalize() {
        return new Quaternion(w / length(), x / length(), y / length(), z / length());
    }

    public Quaternion mul(float k) {
        return new Quaternion(w * k, x * k, y * k, z * k);
    }

    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    public Quaternion mul(Quaternion r) {
        float w = this.w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        float x = this.w * r.getX() + this.x * getW() + y * getZ() - z * getY();
        float y = this.y * getW() + w * getY() + z * getX() - x * getZ();
        float z = this.z * getW() + w * getZ() + x * getY() - y * getX();
        return new Quaternion(w, x, y, z);
    }

    public Quaternion mul(Vector3f r) {
        float w = -x * r.x - y * r.y - z * r.z;
        float x = w * r.x + y * r.z - z * r.y;
        float y = w * r.y + z * r.x - x * r.z;
        float z = w * r.z + x * r.y - r.x;
        return new Quaternion(w, x, y, z);
    }

    public Quaternion add(Quaternion r) {
        return new Quaternion(w + r.getW(), x + r.getX(), y + r.getY(), z + r.getZ());
    }

    public Quaternion sub(Quaternion r) {
        return new Quaternion(w - r.getW(), x - r.getX(), y - r.getY(), z - r.getZ());
    }

    public float dot(Quaternion r) {
        return w * r.getW() + x * r.getX() + y * r.getY() + z * r.getZ();
    }

    //the inverse of a Quaternion is the conjugate divided by the sum of the squares of all elements.
    public Quaternion inverse() {
        Quaternion conj = this.conjugate();
        float sqr = w * w + x * x + y * y + z * z;
        return new Quaternion(conj.w / sqr, conj.x / sqr, conj.y / sqr, conj.z / sqr);
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
