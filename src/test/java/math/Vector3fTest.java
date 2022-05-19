package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector3fTest {
    Vector3f v1;
    Vector3f v2;
    Vector3f v3;

    @BeforeEach
    void init() {
        v1 = new Vector3f(2, 2, 2);
        v2 = new Vector3f(7, 3, 9);
        v3 = new Vector3f(2, 2, 1);
    }

    @Test
    void testNonStaticAdd(){
        Vector3f additionTestVector = new Vector3f(9,5,11);
        v1.add(v2);
        assertEquals(additionTestVector,v1);
    }
    @Test
    void testStaticAdd() {
        Vector3f additionTestVector = new Vector3f(9, 5, 11);
        assertEquals(additionTestVector, Vector3f.add(v1, v2));
    }
    @Test
    void testNonStaticSub(){
        Vector3f subtractionTestVector = new Vector3f(5,1,7);
        v2.sub(v1);
        assertEquals(subtractionTestVector,v2);
    }
    @Test
    void testStaticSub() {
        Vector3f subtractionTestVector = new Vector3f(5, 1, 7);
        assertEquals(subtractionTestVector, Vector3f.sub(v2, v1));
    }

    @Test
    void testLength() {
        float correctLength = 3;
        assertEquals(correctLength, v3.length());
    }
    @Test
    void testNonStaticMul(){
        Vector3f mulTestVector = new Vector3f(4,4,4);
        v1.mul(2);
        assertEquals(mulTestVector,v1);
    }
    @Test
    void testStaticMul(){
        Vector3f mulTestVector = new Vector3f(4,4,4);
        assertEquals(mulTestVector,Vector3f.mul(v1,2));
    }
    @Test
    void testNonStaticDiv(){
        Vector3f divTestVector = new Vector3f(1,1,1);
        v1.div(2);
        assertEquals(divTestVector,v1);
    }
    @Test
    void testStaticDiv(){
        Vector3f divTestVector = new Vector3f(1,1,1);
        assertEquals(divTestVector,Vector3f.div(v1,2));
    }
    @Test
    void testAngle(){
        assertEquals(0.275641,Vector3f.angle(v1,v3),0.0001f);
    }
    @Test
    void testScale(){
        assertEquals(new Vector3f(4,2,0.5f),Vector3f.scale(v3,new Vector3f(2,1,0.5f)));
    }
    @Test
    void testNormalize(){
        assertEquals(new Vector3f(2f/3,2f/3,1f/3),Vector3f.normalize(v3));
    }
    @Test
    void testDistance(){
        assertEquals(1,Vector3f.distance(v1,v3));
    }
    @Test
    void testCross(){
        assertEquals(new Vector3f(-2,2,0),Vector3f.cross(v1,v3));
    }
    @Test
    void testLerp(){
        Vector3f lerpTestVector = new Vector3f(4.5f,2.5f,5.5f);
        assertEquals(lerpTestVector,Vector3f.lerp(v1,v2,0.5f));
    }
    @Test
    void testReflect(){
        Vector3f reflectTestVector = new Vector3f(-8,-8,-9);
        assertEquals(reflectTestVector,Vector3f.reflect(v3,new Vector3f(1,1,1)));
    }
    @Test
    void testProject(){
        Vector3f projectTestVector = new Vector3f(203f/139,87f/139,261f/139);
        assertEquals(projectTestVector,Vector3f.project(v3,v2));
    }
    @Test
    void testOrthonormalize(){
        Vector3f orthornomalizeTestVector1 = new Vector3f(2f/3,2f/3,1f/3);
        Vector3f orthonormalizeTestVector2 = new Vector3f((float) Math.sqrt(410)/246, (float)-Math.sqrt(410)*31/1230,(float) Math.sqrt(410)*26/615);
        Vector3f.orthonormalize(v3,v2);
        assertEquals(orthornomalizeTestVector1,v3);
        assertEquals(orthonormalizeTestVector2,v2);
    }
    @Test
    void testOrthonormalizeSecond(){
        Vector3f orthornomalizeTestVector1 = new Vector3f(2f/3,2f/3,1f/3);
        Vector3f orthonormalizeTestVector2 = new Vector3f((float) Math.sqrt(410)/246, (float)-Math.sqrt(410)*31/1230,(float) Math.sqrt(410)*26/615);
        Vector3f orthorormalizeTestVector3 = new Vector3f((float) -Math.sqrt(410)*3/82,(float)Math.sqrt(410)*11/410,(float)Math.sqrt(410)*4/205);
        Vector3f.orthonormalize(v3,v2,v1);
        assertEquals(orthornomalizeTestVector1,v3);
        assertEquals(orthonormalizeTestVector2,v2);
        assertEquals(orthorormalizeTestVector3,v1);
    }
    @Test
    void testTranslate(){
        v1.translate(new Vector3f(0.5f,0.5f,0.5f));
        assertEquals(new Vector3f(2.5f,2.5f,2.5f),v1);
    }
    @Test
    void testTranslateSecond(){
        v1.translate(0.5f,0.5f,0.5f);
        assertEquals(new Vector3f(2.5f,2.5f,2.5f),v1);
    }
    @Test
    void testNegate(){
        v1.negate();
        assertEquals(new Vector3f(-2,-2,-2),v1);
    }
}
