package math;

public class Quaternion {
    private double w;
    private double x;
    private double y;
    private double z;

    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion(Vector3f axis, double angle) {
        if (axis.length() == 1) {
            this.x = axis.x * Math.sin(angle / 2);
            this.y = axis.y * Math.sin(angle / 2);
            this.z = axis.z * Math.sin(angle / 2);
            this.w = Math.cos(angle / 2);
        }
    }

    public double length() {
        return Math.sqrt(Math.pow(w, 2) + Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    public Quaternion normalize() {
        return new Quaternion(w / length(), x / length(), y / length(), z / length());
    }

    public Quaternion mul(double k) {
        return new Quaternion(w * k, x * k, y * k, z * k);
    }

    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    public Quaternion mul(Quaternion r) {
        double w = this.w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        double x = this.w * r.getX() + this.x * getW() + y * getZ() - z * getY();
        double y = this.y * getW() + w * getY() + z * getX() - x * getZ();
        double z = this.z * getW() + w * getZ() + x * getY() - y * getX();
        return new Quaternion(w, x, y, z);
    }

    public Quaternion mul(Vector3f r) {
        double w = -x * r.x - y * r.y - z * r.z;
        double x = w * r.x + y * r.z - z * r.y;
        double y = w * r.y + z * r.x - x * r.z;
        double z = w * r.z + x * r.y - r.x;
        return new Quaternion(w, x, y, z);
    }

    public Quaternion add(Quaternion r) {
        return new Quaternion(w + r.getW(), x + r.getX(), y + r.getY(), z + r.getZ());
    }

    public Quaternion sub(Quaternion r) {
        return new Quaternion(w - r.getW(), x - r.getX(), y - r.getY(), z - r.getZ());
    }

    public double dot(Quaternion r) {
        return w * r.getW() + x * r.getX() + y * r.getY() + z * r.getZ();
    }

    //the inverse of a Quaternion is the conjugate divided by the sum of the squares of all elements.
    public Quaternion inverse() {
        Quaternion conj = this.conjugate();
        double sqr = w * w + x * x + y * y + z * z;
        return new Quaternion(conj.w / sqr, conj.x / sqr, conj.y / sqr, conj.z / sqr);
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
