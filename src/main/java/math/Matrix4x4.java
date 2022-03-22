package math;

public class Matrix4x4 {
    private double[][] cells;

    public Matrix4x4() {
        cells = new double[4][4];
    }

    public Matrix4x4(double[][] cells) {
        this.cells = cells;
    }

    public static Matrix4x4 identity() {
        Matrix4x4 m = new Matrix4x4();
        m.setIdentity();
        return m;
    }

    public static Matrix4x4 zero() {
        return new Matrix4x4();
    }

    private static double determinant3x3(double m00, double m01, double m02,
                                         double m10, double m11, double m12,
                                         double m20, double m21, double m22) {
        return m00 * (m11 * m22 - m12 * m21) + m01 * (m12 * m20 - m10 * m22) + m02 * (m10 * m21 - m11 * m20);
    }

    public void setCell(int i, int j, double value) throws IllegalArgumentException {
        if (i < 0 || i > 3 || j < 0 || j > 3) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        cells[i][j] = value;
    }

    public double getCell(int i, int j) throws IllegalArgumentException {
        if (i < 0 || i > 3 || j < 0 || j > 3) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return cells[i][j];
    }

    public double[][] getMatrix() {
        return cells;
    }

    public void setMatrix(double[][] m) throws IllegalArgumentException {
        if (m.length != 4 || m[0].length != 4) {
            throw new IllegalArgumentException("Matrix must be 4x4");
        }
        cells = m;
    }

    public Matrix4x4 transpose() {
        Matrix4x4 m = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m.setCell(j, i, cells[i][j]);
            }
        }
        return m;
    }

    public Matrix4x4 multiply(Matrix4x4 other) {
        Matrix4x4 result = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.setCell(i, j, cells[i][0] * other.getCell(0, j) + cells[i][1] * other.getCell(1, j)
                        + cells[i][2] * other.getCell(2, j) + cells[i][3] * other.getCell(3, j));
            }
        }
        return result;
    }

    public void multiply(double scalar) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] *= scalar;
            }
        }
    }

    public void negate() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = -cells[i][j];
            }
        }
    }

    public double determinant() {
        double det = 0;
        det += cells[0][0] * (cells[1][1] * cells[2][2] * cells[3][3] + cells[1][2] * cells[2][3] * cells[3][1]
                + cells[1][3] * cells[2][1] * cells[3][2] - cells[1][3] * cells[2][2] * cells[3][1]
                - cells[1][1] * cells[2][3] * cells[3][2] - cells[1][2] * cells[2][1] * cells[3][3]);
        det -= cells[0][1] * (cells[1][0] * cells[2][2] * cells[3][3] + cells[1][2] * cells[2][3] * cells[3][0]
                + cells[1][3] * cells[2][0] * cells[3][2] - cells[1][3] * cells[2][2] * cells[3][0]
                - cells[1][0] * cells[2][3] * cells[3][2] - cells[1][2] * cells[2][0] * cells[3][3]);
        det += cells[0][2] * (cells[1][0] * cells[2][1] * cells[3][3] + cells[1][1] * cells[2][3] * cells[3][0]
                + cells[1][3] * cells[2][0] * cells[3][1] - cells[1][3] * cells[2][1] * cells[3][0]
                - cells[1][0] * cells[2][3] * cells[3][1] - cells[1][1] * cells[2][0] * cells[3][3]);
        det -= cells[0][3] * (cells[1][0] * cells[2][1] * cells[3][2] + cells[1][1] * cells[2][2] * cells[3][0]
                + cells[1][2] * cells[2][0] * cells[3][1] - cells[1][2] * cells[2][1] * cells[3][0]
                - cells[1][0] * cells[2][2] * cells[3][1] - cells[1][1] * cells[2][0] * cells[3][2]);
        return det;
    }

    public Matrix4x4 inverse() throws IllegalArgumentException {
        Matrix4x4 m = new Matrix4x4();
        double detInv = 1.0f / determinant();
        if (detInv == 0.0f) {
            throw new IllegalArgumentException("Matrix is not invertible");
        } else {
            m.setCell(0, 0, determinant3x3(
                    cells[1][1], cells[1][2], cells[1][3],
                    cells[2][1], cells[2][2], cells[2][3],
                    cells[3][1], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(0, 1, -determinant3x3(
                    cells[0][1], cells[0][2], cells[0][3],
                    cells[2][1], cells[2][2], cells[2][3],
                    cells[3][1], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(0, 2, determinant3x3(
                    cells[1][0], cells[1][1], cells[1][3],
                    cells[2][0], cells[2][1], cells[2][3],
                    cells[3][0], cells[3][1], cells[3][3])
                    * detInv);
            m.setCell(0, 3, -determinant3x3(
                    cells[1][0], cells[1][1], cells[1][2],
                    cells[2][0], cells[2][1], cells[2][2],
                    cells[3][0], cells[3][1], cells[3][2])
                    * detInv);
            m.setCell(1, 0, -determinant3x3(
                    cells[0][1], cells[0][2], cells[0][3],
                    cells[2][1], cells[2][2], cells[2][3],
                    cells[3][1], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(1, 1, determinant3x3(
                    cells[0][0], cells[0][2], cells[0][3],
                    cells[2][0], cells[2][2], cells[2][3],
                    cells[3][0], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(1, 2, -determinant3x3(
                    cells[0][0], cells[0][1], cells[0][3],
                    cells[2][0], cells[2][1], cells[2][3],
                    cells[3][0], cells[3][1], cells[3][3])
                    * detInv);
            m.setCell(1, 3, determinant3x3(
                    cells[0][0], cells[0][1], cells[0][2],
                    cells[2][0], cells[2][1], cells[2][2],
                    cells[3][0], cells[3][1], cells[3][2])
                    * detInv);
            m.setCell(2, 0, determinant3x3(
                    cells[0][1], cells[0][2], cells[0][3],
                    cells[1][1], cells[1][2], cells[1][3],
                    cells[3][1], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(2, 1, -determinant3x3(
                    cells[0][0], cells[0][2], cells[0][3],
                    cells[1][0], cells[1][2], cells[1][3],
                    cells[3][0], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(2, 2, determinant3x3(
                    cells[0][0], cells[0][1], cells[0][3],
                    cells[1][0], cells[1][1], cells[1][3],
                    cells[3][0], cells[3][1], cells[3][3])
                    * detInv);
            m.setCell(2, 3, -determinant3x3(
                    cells[0][0], cells[0][1], cells[0][2],
                    cells[1][0], cells[1][1], cells[1][2],
                    cells[3][0], cells[3][1], cells[3][2])
                    * detInv);
            m.setCell(3, 0, -determinant3x3(
                    cells[0][1], cells[0][2], cells[0][3],
                    cells[1][1], cells[1][2], cells[1][3],
                    cells[2][1], cells[2][2], cells[2][3])
                    * detInv);
            m.setCell(3, 1, determinant3x3(
                    cells[0][0], cells[0][2], cells[0][3],
                    cells[1][0], cells[1][2], cells[1][3],
                    cells[2][0], cells[2][2], cells[2][3])
                    * detInv);
            m.setCell(3, 2, -determinant3x3(
                    cells[0][0], cells[0][1], cells[0][3],
                    cells[1][0], cells[1][1], cells[1][3],
                    cells[2][0], cells[2][1], cells[2][3])
                    * detInv);
            m.setCell(3, 3, determinant3x3(
                    cells[0][0], cells[0][1], cells[0][2],
                    cells[1][0], cells[1][1], cells[1][2],
                    cells[2][0], cells[2][1], cells[2][2])
                    * detInv);
        }
        return m;
    }

    public boolean equals() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] != cells[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                s += cells[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    public void setIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = (i == j) ? 1 : 0;
            }
        }
    }

    public void setZero() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = 0;
            }
        }
    }

}
