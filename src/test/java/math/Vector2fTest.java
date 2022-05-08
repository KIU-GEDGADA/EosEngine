package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2fTest {

    Vector2f vector1;
    Vector2f vector2;

    @BeforeEach
    void setUp() {
        vector1 = new Vector2f();
        vector2 = new Vector2f();
    }

    @Test
    void add() {

        //Adding 0 to 1
        var result = Vector2f.add(new Vector2f(0, 0), new Vector2f(0, 1));
        //  assertTrue(result.equals(new Vector2f(0, 1)));
        assertEquals(result.x, new Vector2f(0, 1).x);
        assertEquals(result.y, new Vector2f(0, 1).y);


        //Adding two big numbers together
        result = Vector2f.add(new Vector2f(1000, 0), new Vector2f(1000, 1));
        assertTrue(result.equals(new Vector2f(2000, 1)));
    }

    @Test
    void sub() {

        //Going into negative
        var result = Vector2f.sub(new Vector2f(0, 0), new Vector2f(1, 1));
        assertTrue(result.equals(new Vector2f(-1, -1)));

        //Going From negative to positive
        result = Vector2f.add(new Vector2f(-5, -9), new Vector2f(136, 62));
        assertTrue(result.equals(new Vector2f(131, 53)));

    }

    @Test
    void mul() {
        //Testing Zeros
        var result = Vector2f.mul(new Vector2f(0, 0), 10);
        assertTrue(result.equals(new Vector2f(0, 0)));

        //Testing Negatives
        result = Vector2f.mul(new Vector2f(3,3), -1);
        assertTrue(result.equals(new Vector2f(-3, -3)));

    }

    @Test
    void distance() {
        var result = Vector2f.distance(new Vector2f(0,5), new Vector2f(5,0));
        System.out.println(result);
        //assertEquals(result, -5,5);
    }
}