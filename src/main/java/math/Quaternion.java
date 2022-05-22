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

    public Quaternion(Vector3f axis, float angle) throws IllegalArgumentException {
        if (axis.length() == 1) {
            this.x = (float) (axis.x * Math.sin(angle / 2));
            this.y = (float) (axis.y * Math.sin(angle / 2));
            this.z = (float) (axis.z * Math.sin(angle / 2));
            this.w = (float) Math.cos(angle / 2);
        } else throw new IllegalArgumentException("Constructor not initialized correctly");
    }

    public float length() {
        return (float) Math.sqrt(Math.pow(w, 2) + Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Quaternion normalize() {
        return new Quaternion(w / length(), x / length(), y / length(), z / length());
    }

    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    public Quaternion mul(float k) {
        return new Quaternion(w * k, x * k, y * k, z * k);
    }

    public Quaternion mul(Quaternion r) {
        float w = this.w * r.w - this.x * r.x - this.y * r.y - this.z * r.z;
        float x = this.x * r.w + this.w * r.x + this.y * r.z - this.z * r.y;
        float y = this.y * r.w + this.w * r.y + this.z * r.x - this.x * r.z;
        float z = this.z * r.w + this.w * r.z + this.x * r.y - this.y * r.x;
        return new Quaternion(w, x, y, z);
    }

    public Vector3f mul(Vector3f vec) {
        float num = this.x * 2f;
        float num2 = this.y * 2f;
        float num3 = this.z * 2f;
        float num4 = this.x * num;
        float num5 = this.y * num2;
        float num6 = this.z * num3;
        float num7 = this.x * num2;
        float num8 = this.x * num3;
        float num9 = this.y * num3;
        float num10 = this.w * num;
        float num11 = this.w * num2;
        float num12 = this.w * num3;
        Vector3f result = Vector3f.zero();
        result.x = (1f - (num5 + num6)) * vec.x + (num7 - num12) * vec.y + (num8 + num11) * vec.z;
        result.y = (num7 + num12) * vec.x + (1f - (num4 + num6)) * vec.y + (num9 - num10) * vec.z;
        result.z = (num8 - num11) * vec.x + (num9 + num10) * vec.y + (1f - (num4 + num5)) * vec.z;
        return result;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quaternion that = (Quaternion) o;
        return Float.compare(that.w, w) == 0 && Float.compare(that.x, x) == 0 && Float.compare(that.y, y) == 0 && Float.compare(that.z, z) == 0;
    }

    @Override
    public String toString() {
        return "Quaternion{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
