package math;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Matrix4x4Test {
    Matrix4x4 m1 = new Matrix4x4(new float[][]{
            {1,0,0,1},
            {2,0,1,0},
            {3,0,5,0},
            {0,1,2,2}
    });
    Matrix4x4 m2 = new Matrix4x4(new float[][]{
            {3,1,1,0},
            {5,0,0,1},
            {4,0,1,5},
            {3,1,5,6}
    });
    @Test
    void multiply(){
        Matrix4x4 testMultMatrix = new Matrix4x4(new float[][]{
                {6,2,6,6},
                {10,2,3,5},
                {29,3,8,25},
                {19,2,12,23}
        });
        assertEquals(testMultMatrix,m1.multiply(m2));
    }
    @Test
    void transpose(){
        Matrix4x4 testTransMatrix = new Matrix4x4(new float[][]{
                {1,2,3,0},
                {0,0,0,1},
                {0,1,5,2},
                {1,0,0,2}
        });
        assertEquals(testTransMatrix,m1.transpose());
    }
    @Test
    void setIdentity(){
        Matrix4x4 testIdentityMatrix = new Matrix4x4(new float[][]{
                {1,0,0,0},
                {0,1,0,0},
                {0,0,1,0},
                {0,0,0,1}
        });
        m1.setIdentity();
        assertEquals(testIdentityMatrix,m1);
    }
}
