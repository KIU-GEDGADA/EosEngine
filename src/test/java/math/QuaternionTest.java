package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuaternionTest {

    Quaternion quat;


    @BeforeEach
    void init() {
        quat = new Quaternion(1, 2, 3, 4);
    }


    @Test
    void testConstructor1() {
        assertTrue(
                quat.getW() == 1 &&
                        quat.getX() == 2 &&
                        quat.getY() == 3 &&
                        quat.getZ() == 4
        );
    }

    @Test
    void testConstructor2() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Quaternion(new Vector3f(0, 0, 0), 1);
        });
    }

    @Test
    void testLength() {
        assertEquals((float) Math.sqrt(1 + 4 + 9 + 16), quat.length());
    }

    @Test
    void testNormalize() {
        float length = quat.length();
        Quaternion normalizedQuat = new Quaternion(1 / length, 2 / length, 3 / length, 4 / length);

        assertEquals(normalizedQuat, quat.normalize());
    }

    @Test
    void testConjugate() {
        Quaternion conjugateQuat = new Quaternion(1, -2, -3, -4);

        assertEquals(conjugateQuat, quat.conjugate());
    }

    @Test
    void testMulFloat() {
        Quaternion floatQuat = new Quaternion(1 * 2, 2 * 2, 3 * 2, 4 * 2);
        assertEquals(floatQuat, quat.mul(2));
    }

    @Test
    void testMulQuat() {
        Quaternion quatToMul = new Quaternion(4, 3, 2, 1);
        Quaternion quatToCompare = new Quaternion(-12, 6, 24, 12);
        assertEquals(quatToCompare, quat.mul(quatToMul));
    }

    @Test
    void testMulVec() {
        Vector3f vecToMul = new Vector3f(4, 3, 2);
        Vector3f vecToCompare = new Vector3f(-140, 3, 74);
        assertEquals(vecToCompare, quat.mul(vecToMul));
    }

    @Test
    void testAdd() {
        Quaternion quatToAdd = new Quaternion(4, 3, 2, 1);
        Quaternion quatToCompare = new Quaternion(5, 5, 5, 5);
        assertEquals(quatToCompare, quat.add(quatToAdd));
    }

    @Test
    void testSub() {
        Quaternion quatToSub = new Quaternion(4, 3, 2, 1);
        Quaternion quatToCompare = new Quaternion(-3, -1, 1, 3);
        assertEquals(quatToCompare, quat.sub(quatToSub));
    }

    @Test
    void testDot() {
        Quaternion quatToDot = new Quaternion(4, 3, 2, 1);
        assertEquals(4 + 6 + 6 + 4, quat.dot(quatToDot));
    }

    @Test
    void testInverse() {
        float sqr = 1 * 1 + -2 * -2 + -3 * -3 + -4 * -4;
        Quaternion quatToCompare = new Quaternion(1 / sqr, -2 / sqr, -3 / sqr, -4 / sqr);
        assertEquals(quatToCompare, quat.inverse());

    }
}
