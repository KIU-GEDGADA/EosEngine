package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2fTest {

    Vector2f v1;
    Vector2f v2;
    Vector2f v3;

    @BeforeEach
    void setUp() {
        v1 = new Vector2f(2, 2);
        v2 = new Vector2f(7, 3);
        v3 = new Vector2f(2, 1);
    }

    @Test
    void testConstructor() {
        assertEquals(new Vector2f(0, 0), new Vector2f());
    }

    @Test
    void testNonStaticAdd() {

        //Adding 0 to 1
        Vector2f result = Vector2f.add(new Vector2f(0, 0), new Vector2f(0, 1));
        //  assertTrue(result.equals(new Vector2f(0, 1)));
        assertEquals(result.x, new Vector2f(0, 1).x);
        assertEquals(result.y, new Vector2f(0, 1).y);
    }

    @Test
    void testStaticAdd() {

        //Adding two big numbers together
        Vector2f result = Vector2f.add(new Vector2f(1000, 0), new Vector2f(1000, 1));
        assertTrue(result.equals(new Vector2f(2000, 1)));
    }

    @Test
    void testNonStaticSub() {

        //Going into negative
        Vector2f result = Vector2f.sub(new Vector2f(0, 0), new Vector2f(1, 1));
        assertTrue(result.equals(new Vector2f(-1, -1)));

    }

    @Test
    void testStaticSub() {
        //Going From negative to positive
        Vector2f result = Vector2f.add(new Vector2f(-5, -9), new Vector2f(136, 62));
        assertTrue(result.equals(new Vector2f(131, 53)));
    }

    @Test
    void testPolySubs() {
        assertEquals(Vector2f.sub(v1, v2), v1.sub(v2));
    }

    @Test
    void testNonStaticMul() {
        //Testing Zeros
        Vector2f result = Vector2f.mul(new Vector2f(0, 0), 10);
        assertTrue(result.equals(new Vector2f(0, 0)));

    }

    @Test
    void testStaticMul() {
        //Testing Negatives
        Vector2f result = Vector2f.mul(new Vector2f(3, 3), -1);
        assertTrue(result.equals(new Vector2f(-3, -3)));
    }

    @Test
    void testPolyMuls() {
        assertEquals(Vector2f.mul(v1, 3), v1.mul(3));
    }

    @Test
    void testNonStaticDiv() {
        Vector2f divTestVector = new Vector2f(1, 1);
        v1.div(2);
        assertEquals(divTestVector, v1);
    }

    @Test
    void testStaticDiv() {
        Vector2f divTestVector = new Vector2f(1, 1);
        assertEquals(divTestVector, Vector2f.div(v1, 2));
    }

    @Test
    void testAngle() {
        assertEquals(0.32175067, Vector2f.angle(v1, v3), 0.0001f);
    }

    @Test
    void testScale() {

        assertEquals(new Vector2f(4, 1), Vector2f.scale(v3, new Vector2f(2, 1)));
    }

    @Test
    void testNormalize() {
        assertEquals(new Vector2f(2f / (float) Math.sqrt(5), 1f / (float) Math.sqrt(5)), Vector2f.normalize(v3));
    }

    @Test
    void testPolyNormalize() {
        assertEquals(Vector2f.normalize(v3), v3.normalize());
    }

    @Test
    void testDistance() {
        assertEquals(1, Vector2f.distance(v1, v3));
    }

    @Test
    void testPerpendicular() {
        assertEquals(new Vector2f(-1, 2), Vector2f.perpendicular(v3));
    }

    @Test
    void testSqrLength() {
        assertEquals(2 * 2 + 1 * 1, v3.sqrLength());
    }


    @Test
    void testZero() {
        assertEquals(new Vector2f(0, 0), Vector2f.zero());
    }

    @Test
    void testOne() {
        assertEquals(new Vector2f(1, 1), Vector2f.one());
    }

    @Test
    void testUp() {
        assertEquals(new Vector2f(0, 1), Vector2f.up());
    }

    @Test
    void testDown() {
        assertEquals(new Vector2f(0, -1), Vector2f.down());
    }

    @Test
    void testLeft() {
        assertEquals(new Vector2f(-1, 0), Vector2f.left());
    }

    @Test
    void testRight() {
        assertEquals(new Vector2f(1, 0), Vector2f.right());
    }

    @Test
    void testTranslate() {
        Vector2f delta = new Vector2f(1, 2);
        assertEquals(new Vector2f(8, 5), v2.translate(delta));
    }

    @Test
    void testPolyTranslate() {
        Vector2f delta = new Vector2f(1, 2);
        assertEquals(v2.translate(delta), v2.translate(1, 2));
    }

    @Test
    void testNegate() {
        assertEquals(new Vector2f(-7, -3), v2.negate());
    }

}