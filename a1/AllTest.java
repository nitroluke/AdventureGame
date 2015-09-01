package esof322.a1;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllTest {

    public void testEquals() {
        //    Obvious true
        Vector3D a = new Vector3D(1.0, 1.0, 1.0);
        Vector3D b = new Vector3D(1.0, 1.0, 1.0);
        assertTrue(a.equals(b));
        //    Obvious false (test each component)
        Vector3D c = new Vector3D(2.0, 1.0, 1.0);
        Vector3D d = new Vector3D(1.0, 2.0, 1.0);
        Vector3D e = new Vector3D(1.0, 1.0, 2.0);
        assertFalse(a.equals(c));
        assertFalse(a.equals(d));
        assertFalse(a.equals(e));
        //    False, because the tolerance states difference must be no greater than .001 
        Vector3D f = new Vector3D(1.001, 1.0, 1.0);
        Vector3D g = new Vector3D(0.999, 1.0, 1.0);
        assertFalse(f.equals(g));
        //    True, because the difference is less than 0.001
        Vector3D h = new Vector3D(1.0005, 1.0, 1.0);
        assertTrue(a.equals(h));
    }
    
    public void testScale(){
        //Identity
        Vector3D a = new Vector3D(1.0, 1.0, 1.0);
        Vector3D idAResult = a.scale(1.0);
        Vector3D idAExpected = new Vector3D(1.0, 1.0, 1.0);
        assertTrue(idAResult.equals(idAExpected));
        //Zero
        Vector3D b = new Vector3D(3.21,4.1,5.0);
        Vector3D zeroBResult = b.scale(0.0);
        Vector3D zeroBExpected = new Vector3D(0.0, 0.0, 0.0);
        assertTrue(zeroBResult.equals(zeroBExpected));
        //General Case
        double scalar = 2;
        Vector3D c = new Vector3D(5.2, 3.3, 4.21);
        Vector3D gcCResult = c.scale(scalar);
        Vector3D gcCExpected = new Vector3D(10.4, 6.6, 8.42);
        assertTrue(gcCResult.equals(gcCExpected));
    }
    
    @Test
    public void test() {
        testEquals();
        testScale();
    }

}
