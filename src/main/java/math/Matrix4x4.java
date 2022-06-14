package math;

/**
 * This function implements a 4 by 4 float matrix and operations on it
 */
public class Matrix4x4 {
    private float[][] cells;

    /**
     * Class creator
     */
    public Matrix4x4() {
        cells = new float[4][4];
    }

    /**
     * Class creator, initializes the matrices contents to the given values
     * @param cells the 2d float array of matrices values
     */
    public Matrix4x4(float[][] cells) {
        this.cells = cells;
    }

    /**
     * This function creates an empty Matrix4x4
     * @return an empty Matrix4x4
     */
    public static Matrix4x4 zero() {
        return new Matrix4x4();
    }

    /**
     * This function sets a cell of the Matrix4x4 to a given value, returns the current instance of the Matrix4x4
     * Throws an exception if an attempt is made to access a cell outside the Matrix4x4's range
     * @param i the row of the cell
     * @param j the column of the cell
     * @param value the desired value
     * @return the current instance of the Matrix4x4
     * @throws IllegalArgumentException if an attempt has been made to reach a cell outside the Matrix4x4's range
     */
    public Matrix4x4 setCell(int i, int j, float value) throws IllegalArgumentException {
        if (i < 0 || i > 3 || j < 0 || j > 3) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        cells[i][j] = value;

        return this;
    }

    /**
     * This function returns the value of a single cell of the Matrix4x4
     * Throws an exception if an attempt is made to access a cell outside the Matrix4x4's range
     * @param i the row of the cell
     * @param j the column of the cell
     * @return the value of the desired cell
     * @throws IllegalArgumentException if an attempt has been made to reach a cell outside the Matrix4x4's range
     */
    public float getCell(int i, int j) throws IllegalArgumentException {
        if (i < 0 || i > 3 || j < 0 || j > 3) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return cells[i][j];
    }

    /**
     * Getter, this function returns the 2d float array of the Matrix4x4's contents
     * @return the 2d float array of the Matrix4x4's contents
     */
    public float[][] getMatrix() {
        return cells;
    }

    /**
     * Setter, this function sets the 2d float array contents of the Matrix4x4 to the desired value, returns the current instance of the Matrix4x4
     * Throws an exception if the desired float array has more rows or columns than 4
     * @param m the desired 2d float array
     * @return the current instance of the Matrix4x4
     * @throws IllegalArgumentException if the desired float array has more rows or columns than 4
     */
    public Matrix4x4 setMatrix(float[][] m) throws IllegalArgumentException {
        if (m.length != 4 || m[0].length != 4) {
            throw new IllegalArgumentException("Matrix must be 4x4");
        }
        cells = m;

        return this;
    }

    /**
     * This function creates and returns the transposed Matrix4x4 of the current matrix
     * @return the transposed matrix
     */
    public Matrix4x4 transpose() {
        Matrix4x4 m = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                m.setCell(j, i, cells[i][j]);
            }
        }
        return m;
    }

    /**
     * This function multiplies the current Matrix4x4 with a second Matrix4x4
     * @param other the Matrix4x4 to multiply by
     * @return the result of the multiplication
     */
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

    /**
     * This function multiplies the current Matrix4x4 by a scalar
     * @param scalar the scalar to multiply by
     * @return the result of the scaling
     */
    public Matrix4x4 multiply(float scalar) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] *= scalar;
            }
        }
        return this;
    }

    /**
     * This function negates the current Matrix4x4
     * @return the negated Matrix4x4
     */
    public Matrix4x4 negate() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] != 0) {
                    cells[i][j] = -cells[i][j];
                }
            }
        }
        return this;
    }

    /**
     * This function returns the determinant of the current Matrix4x4
     * @return the determinant of the current Matrix4x4
     */
    public float determinant() {
        float det = 0;
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

    /**
     * This function returns the inverse of the current Matrix4x4
     * Throws an IllegalArguementException if the Matrix4x4 is non-invertible
     * @return the inverse of the current Matrix4x4
     * @throws IllegalArgumentException if the Matrix4x4 is non-invertible
     */
    public Matrix4x4 inverse() throws IllegalArgumentException {
        Matrix4x4 m = new Matrix4x4();
        float detInv = 1.0f / determinant();
        if (detInv == 0.0f) {
            throw new IllegalArgumentException("Matrix is not invertible");
        } else {
            m.setCell(0, 0, MatrixHelper.determinant3x3(
                    cells[1][1], cells[1][2], cells[1][3],
                    cells[2][1], cells[2][2], cells[2][3],
                    cells[3][1], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(0, 1, -MatrixHelper.determinant3x3(
                    cells[1][0], cells[1][2], cells[1][3],
                    cells[2][0], cells[2][2], cells[2][3],
                    cells[3][0], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(0, 2, MatrixHelper.determinant3x3(
                    cells[1][0], cells[1][1], cells[1][3],
                    cells[2][0], cells[2][1], cells[2][3],
                    cells[3][0], cells[3][1], cells[3][3])
                    * detInv);
            m.setCell(0, 3, -MatrixHelper.determinant3x3(
                    cells[1][0], cells[1][1], cells[1][2],
                    cells[2][0], cells[2][1], cells[2][2],
                    cells[3][0], cells[3][1], cells[3][2])
                    * detInv);
            m.setCell(1, 0, -MatrixHelper.determinant3x3(
                    cells[0][1], cells[0][2], cells[0][3],
                    cells[2][1], cells[2][2], cells[2][3],
                    cells[3][1], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(1, 1, MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][2], cells[0][3],
                    cells[2][0], cells[2][2], cells[2][3],
                    cells[3][0], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(1, 2, -MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][1], cells[0][3],
                    cells[2][0], cells[2][1], cells[2][3],
                    cells[3][0], cells[3][1], cells[3][3])
                    * detInv);
            m.setCell(1, 3, MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][1], cells[0][2],
                    cells[2][0], cells[2][1], cells[2][2],
                    cells[3][0], cells[3][1], cells[3][2])
                    * detInv);
            m.setCell(2, 0, MatrixHelper.determinant3x3(
                    cells[0][1], cells[0][2], cells[0][3],
                    cells[1][1], cells[1][2], cells[1][3],
                    cells[3][1], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(2, 1, -MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][2], cells[0][3],
                    cells[1][0], cells[1][2], cells[1][3],
                    cells[3][0], cells[3][2], cells[3][3])
                    * detInv);
            m.setCell(2, 2, MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][1], cells[0][3],
                    cells[1][0], cells[1][1], cells[1][3],
                    cells[3][0], cells[3][1], cells[3][3])
                    * detInv);
            m.setCell(2, 3, -MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][1], cells[0][2],
                    cells[1][0], cells[1][1], cells[1][2],
                    cells[3][0], cells[3][1], cells[3][2])
                    * detInv);
            m.setCell(3, 0, -MatrixHelper.determinant3x3(
                    cells[0][1], cells[0][2], cells[0][3],
                    cells[1][1], cells[1][2], cells[1][3],
                    cells[2][1], cells[2][2], cells[2][3])
                    * detInv);
            m.setCell(3, 1, MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][2], cells[0][3],
                    cells[1][0], cells[1][2], cells[1][3],
                    cells[2][0], cells[2][2], cells[2][3])
                    * detInv);
            m.setCell(3, 2, -MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][1], cells[0][3],
                    cells[1][0], cells[1][1], cells[1][3],
                    cells[2][0], cells[2][1], cells[2][3])
                    * detInv);
            m.setCell(3, 3, MatrixHelper.determinant3x3(
                    cells[0][0], cells[0][1], cells[0][2],
                    cells[1][0], cells[1][1], cells[1][2],
                    cells[2][0], cells[2][1], cells[2][2])
                    * detInv);
        }
        return m.transpose();
    }

    /**
     * This function compares the current Matrix4x4 with another object, returns true if they are equal, false otherwise
     * @param obj the object to compare to
     * @return true if the object is a Matrix4x4 and the value are equal, false otherwise
     */
    public boolean equals(Object obj) {
        float epsilon = 0.000001f;
        if (obj instanceof Matrix4x4) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (Math.abs(this.getCell(i, j) - ((Matrix4x4) obj).getCell(i, j)) > epsilon) {
                        if (!((((Matrix4x4) obj).getCell(i, j) == -0f && this.getCell(i, j) == 0f) || ((Matrix4x4) obj).getCell(i, j) == 0f && this.getCell(i, j) == -0f)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * This function returns a string representation of the Matrix4x4
     * @return a string representation of the Matrix4x4
     */
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

    /**
     * This function turns the current Matrix4x4 into an identity matrix
     * @return an identity matrix
     */
    public Matrix4x4 setIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = (i == j) ? 1 : 0;
            }
        }

        return this;
    }

    /**
     * This function sets all the cells of the Matrix4x4 to zero
     * @return a zero Matrix4x4
     */
    public Matrix4x4 setZero() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = 0;
            }
        }

        return this;
    }

}
