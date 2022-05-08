package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Matrix4x4Test {
    Matrix4x4 m1;
    Matrix4x4 m2;

    @BeforeEach
    void init() {
        m1 = new Matrix4x4(new float[][]{
                {1, 0, 0, 1},
                {2, 0, 1, 0},
                {3, 0, 5, 0},
                {0, 1, 2, 2}
        });
        m2 = new Matrix4x4(new float[][]{
                {3, 1, 1, 0},
                {5, 0, 0, 1},
                {4, 0, 1, 5},
                {3, 1, 5, 6}
        });
    }

    @Test
    void testMultiply() {
        Matrix4x4 testMultMatrix = new Matrix4x4(new float[][]{
                {6, 2, 6, 6},
                {10, 2, 3, 5},
                {29, 3, 8, 25},
                {19, 2, 12, 23}
        });
        assertEquals(testMultMatrix, m1.multiply(m2));
    }

    @Test
    void testTranspose() {
        Matrix4x4 testTransMatrix = new Matrix4x4(new float[][]{
                {1, 2, 3, 0},
                {0, 0, 0, 1},
                {0, 1, 5, 2},
                {1, 0, 0, 2}
        });
        assertEquals(testTransMatrix, m1.transpose());
    }

    @Test
    void testSetIdentity() {
        Matrix4x4 testIdentityMatrix = new Matrix4x4(new float[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
        m1.setIdentity();
        assertEquals(testIdentityMatrix, m1);
    }

    @Test
    void testSetZero() {
        m1.setZero();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                assertEquals(0, m1.getCell(i, j));
            }
        }
    }

    @Test
    void testSetCell() {
        m1.setCell(0, 0, 5);
        assertEquals(5, m1.getCell(0, 0));
    }

    @Test
    void testGetCell() {
        assertEquals(1, m1.getCell(0, 0));
    }

    @Test
    void testGetMatrix() {
        float[][] testCells = new float[][]{
                {1, 0, 0, 1},
                {2, 0, 1, 0},
                {3, 0, 5, 0},
                {0, 1, 2, 2}
        };
        assertArrayEquals(testCells, m1.getMatrix());
    }

    @Test
    void testDeterminant() {
        assertEquals(7, m1.determinant());
    }

    @Test
    void testNegate() {
        float[][] testCells = new float[][]{
                {-1, 0, 0, -1},
                {-2, 0, -1, 0},
                {-3, 0, -5, 0},
                {0, -1, -2, -2}
        };
        m1.negate();
        assertArrayEquals(testCells, m1.getMatrix());
    }

    @Test
    void test3x3Determinant() {
        assertEquals(14, MatrixHelper.determinant3x3(2, 1, 0, 3, 5, 0, 0, 2, 2));
    }

    @Test
    void testInverse() {
        Matrix4x4 inverseTestMatrix = new Matrix4x4(new float[][]{
                {0f,  5f / 7f, -1f / 7f, 0f},
                {-2f, 16f / 7f, -6f / 7f, 1f},
                {0f, -3f / 7f, 2f / 7f, 0f},
                {1f, -5f / 7f, 1f / 7f, 0f}
        });
        assertEquals(inverseTestMatrix,m1.inverse());
    }
}
