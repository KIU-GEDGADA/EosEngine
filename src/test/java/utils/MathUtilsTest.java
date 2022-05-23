package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    float value;
    float[][] cells;

    @BeforeEach
    void setUp() {
        value = 3.0f;
        cells = new float[][] {
                {1,2,3,4},
                {2,3,4,5},
                {3,4,5,6},
                {4,5,6,7}
        };
    }

    @Test
    void testClamp1() {
        assertEquals(1.0f, MathUtils.clamp(value));
    }

    @Test
    void testClamp2() {
        value = 0.3f;
        assertEquals(0.3f, MathUtils.clamp(value));
    }

    @Test
    void testFlatten2DArray() {
        assertArrayEquals(
                new float[]{1,2,3,4,
                        2,3,4,5,
                        3,4,5,6,
                        4,5,6,7
                }, MathUtils.flatten2DArray(cells));
    }
}