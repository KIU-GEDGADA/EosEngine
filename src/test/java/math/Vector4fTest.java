package math;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector4fTest {
    Vector4f v1 = new Vector4f(2,2,2,2);
    Vector4f v2 = new Vector4f(3,3,3,3);
    @Test
    void testNormalize(){
        Vector4f testNormalizedVector = new Vector4f(0.5f,0.5f,0.5f,0.5f);
        assertEquals(testNormalizedVector,Vector4f.normalize(v1));
    }
}
