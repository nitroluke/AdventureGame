package esof322.a1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AllTest {

    public void testEquals() {
        // Obvious true
        Vector3D a = new Vector3D(1.0, 1.0, 1.0);
        Vector3D b = new Vector3D(1.0, 1.0, 1.0);
        assertTrue(a.equals(b));
        // Obvious false (test each component)
        Vector3D c = new Vector3D(2.0, 1.0, 1.0);
        Vector3D d = new Vector3D(1.0, 2.0, 1.0);
        Vector3D e = new Vector3D(1.0, 1.0, 2.0);
        assertFalse(a.equals(c));
        assertFalse(a.equals(d));
        assertFalse(a.equals(e));
        // False, because the tolerance states difference must be no greater than .001
        Vector3D f = new Vector3D(1.001, 1.0, 1.0);
        Vector3D g = new Vector3D(0.999, 1.0, 1.0);
        assertFalse(f.equals(g));
        // True, because the difference is less than 0.001
        Vector3D h = new Vector3D(1.0005, 1.0, 1.0);
        assertTrue(a.equals(h));
    }


    public void testScaleIdentity(){
        //Identity
        Vector3D a = new Vector3D(1.0, 1.0, 1.0);
        Vector3D idAResult = a.scale(1.0);
        Vector3D idAExpected = new Vector3D(1.0, 1.0, 1.0);
        assertTrue(idAResult.equals(idAExpected));
    }
    public void testScaleZero(){
        Vector3D b = new Vector3D(3.21,4.1,5.0);
        Vector3D zeroBResult = b.scale(0.0);
        Vector3D zeroBExpected = new Vector3D(0.0, 0.0, 0.0);
        assertTrue(zeroBResult.equals(zeroBExpected));
    }
    public void testScaleGeneralCase(){
        double scalar = 2;
        Vector3D c = new Vector3D(5.2, 3.3, 4.21);
        Vector3D gcCResult = c.scale(scalar);
        Vector3D gcCExpected = new Vector3D(10.4, 6.6, 8.42);
        assertTrue(gcCResult.equals(gcCExpected));
    }

    public void testNegate() {
        Vector3D b = new Vector3D(3.21, 4.1, 5.0);
        assertTrue(b.negate().equals(new Vector3D(-3.21, -4.1, -5.0)));
    }

    public void testMagnitude(){
        Vector3D a = new Vector3D(1.1,1.2,1.3);
        assertTrue(Math.abs(a.magnitude()-2.08327) < 0.001);
    }

    @Test
    public void test() {
        testEquals();
        testScaleIdentity();
        testScaleZero();
        testScaleGeneralCase();
        testNegate();
        testValidAddition();
        testInvalidAddition();
        testValidSubtraction();
        testInvalidSubtraction();
        testMagnitude();
    }

    public void testValidAddition() {
        Vector3D a = new Vector3D(1.0, 1.0, 1.0);
        Vector3D b = new Vector3D(2.0, 2.0, 2.0);
        Vector3D expected = new Vector3D(3.0, 3.0, 3.0);
        assertTrue((a.add(b)).equals(expected));
    }

    public void testInvalidAddition() {
        Vector3D a = new Vector3D(1.0, 1.0, 1.0);
        Vector3D b = new Vector3D(2.0, 2.0, 2.0);
        Vector3D expected = new Vector3D(4.0, 2.0, 1.0);
        assertFalse((a.add(b)).equals(expected));
    }

    public void testValidSubtraction() {
        Vector3D a = new Vector3D(1.0, 1.0, 1.0);
        Vector3D b = new Vector3D(2.0, 2.0, 2.0);
        Vector3D expected = new Vector3D(-1.0, -1.0, -1.0);
        assertTrue((a.subtract(b)).equals(expected));
    }

    public void testInvalidSubtraction() {
        Vector3D a = new Vector3D(1.0, 1.0, 1.0);
        Vector3D b = new Vector3D(2.0, 2.0, 2.0);
        Vector3D expected = new Vector3D(-2.0, -5.0, -6.0);
        assertFalse((a.subtract(b)).equals(expected));
    }
}
