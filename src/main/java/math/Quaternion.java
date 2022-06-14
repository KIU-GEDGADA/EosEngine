package math;

/**
 * This function implements quaternions and operations on them
 */
public class Quaternion {
    private float w;
    private float x;
    private float y;
    private float z;

    /**
     * Class constructor, this function creates a quaternion from 4 float values
     * @param w the angle
     * @param x the first axis value
     * @param y the second axis value
     * @param z the third axis value
     */
    public Quaternion(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Class constructor, creates a quaternion from a Vector3f and an angle
     * Throws an IllegalArgumentException if the axis vector length is over 1
     * @param axis the axis Vector3f
     * @param angle the angle of the quaternion
     * @throws IllegalArgumentException if the axis vector length is over 1
     */
    public Quaternion(Vector3f axis, float angle) throws IllegalArgumentException {
        if (axis.length() == 1) {
            this.x = (float) (axis.x * Math.sin(angle / 2));
            this.y = (float) (axis.y * Math.sin(angle / 2));
            this.z = (float) (axis.z * Math.sin(angle / 2));
            this.w = (float) Math.cos(angle / 2);
        } else throw new IllegalArgumentException("Constructor not initialized correctly");
    }

    /**
     * This function calculates the length of the quaternion
     * @return the length of the quaternion
     */
    public float length() {
        return (float) Math.sqrt(Math.pow(w, 2) + Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    /**
     * This function normalizes the quaternion
     * @return the normalized quaternion
     */
    public Quaternion normalize() {
        return new Quaternion(w / length(), x / length(), y / length(), z / length());
    }

    /**
     * This function returns the conjugated quaternion
     * @return the conjugated quaternion
     */
    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    /**
     * THis function scales the quaternion by a given value
     * @param k the value to scale by
     * @return the scaled quaternion
     */
    public Quaternion mul(float k) {
        return new Quaternion(w * k, x * k, y * k, z * k);
    }

    /**
     * This function multiplies the quaternion with another quaternion
     * @param r the quaternion to multiply by
     * @return the result of the multiplication
     */
    public Quaternion mul(Quaternion r) {
        float w = this.w * r.w - this.x * r.x - this.y * r.y - this.z * r.z;
        float x = this.x * r.w + this.w * r.x + this.y * r.z - this.z * r.y;
        float y = this.y * r.w + this.w * r.y + this.z * r.x - this.x * r.z;
        float z = this.z * r.w + this.w * r.z + this.x * r.y - this.y * r.x;
        return new Quaternion(w, x, y, z);
    }

    /**
     * This function multiplies the axis of the quaternion by a Vector3f
     * @param vec the Vector3f to multiply by
     * @return the result of the multiplication
     */
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

    /**
     * This function adds the another quaternion to the current
     * @param r the quaternion to add
     * @return the result of the addition
     */
    public Quaternion add(Quaternion r) {
        return new Quaternion(w + r.getW(), x + r.getX(), y + r.getY(), z + r.getZ());
    }

    /**
     * This function subtracts another quaternion from the current
     * @param r the quaternion to subtract
     * @return the result of the subtraction
     */
    public Quaternion sub(Quaternion r) {
        return new Quaternion(w - r.getW(), x - r.getX(), y - r.getY(), z - r.getZ());
    }

    /**
     * This function results the dot product of the current quaternion and another quaternion
     * @param r the second quaternion
     * @return the dot product
     */
    public float dot(Quaternion r) {
        return w * r.getW() + x * r.getX() + y * r.getY() + z * r.getZ();
    }

    /**
     * This function returns the inverse of the quaternion
     * the inverse of a Quaternion is the conjugate divided by the sum of the squares of all elements.
     * @return the inverse of the current quaternion
     */
    public Quaternion inverse() {
        Quaternion conj = this.conjugate();
        float sqr = w * w + x * x + y * y + z * z;
        return new Quaternion(conj.w / sqr, conj.x / sqr, conj.y / sqr, conj.z / sqr);
    }

    /**
     * Getter, this function returns the angle of the quaternion
     * @return the angle
     */
    public float getW() {
        return w;
    }

    /**
     * Setter, this function sets the angle of the quaternion and returns the current instance of the quaternion
     * @param w the desired angle
     * @return the current instance of the quaternion
     */
    public Quaternion setW(float w) {
        this.w = w;
        return this;
    }

    /**
     * Getter, this function returns the first axis value of the quaternion
     * @return the first axis value of the quaternion
     */
    public float getX() {
        return x;
    }

    /**
     * Setter, this function sets the first axis value of the quaternion, returns the current instance of the quaternion
     * @param x the desired first axis value
     * @return the current instance of the quaternion
     */
    public Quaternion setX(float x) {
        this.x = x;
        return this;
    }

    /**
     * Getter, this function returns the second axis value of the quaternion
     * @return the second axis value of the quaternion
     */
    public float getY() {
        return y;
    }

    /**
     * Setter, this function sets the second axis value of the quaternion, returns the current instance of the quaternion
     * @param y the desired second axis value
     * @return the current instance of the quaternion
     */
    public Quaternion setY(float y) {
        this.y = y;
        return this;
    }

    /**
     * Getter, this function returns the third axis value of the quaternion
     * @return the third axis value of the quaternion
     */
    public float getZ() {
        return z;
    }

    /**
     * Setter, this function sets the third axis value of the quaternion, returns the current instance of the quaternion
     * @param z the desired third axis value
     * @return the current instance of the quaternion
     */
    public Quaternion setZ(float z) {
        this.z = z;
        return this;
    }

    /**
     * This function compares the current quaternion to another object, returns true if the object is an instance of a quaternion and values are equal
     * @param o the object to compare to
     * @return true if the object is an instance of a quaternion and values are equal
     */
    @Override
    public boolean equals(Object o) {
        float epsilon = 0.0001f;
        if (o instanceof Quaternion) {
            return Math.abs(this.x - ((Quaternion) o).x) < epsilon && Math.abs(this.y - ((Quaternion) o).y) < epsilon
                    && Math.abs(this.z - ((Quaternion) o).z) < epsilon && Math.abs(this.w - ((Quaternion) o).w) < epsilon;
        }
        return false;
    }

    /**
     * This function returns the string representation of the quaternion
     * @return the string representation of the quaternion
     */
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
